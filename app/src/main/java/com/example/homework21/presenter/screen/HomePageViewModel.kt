package com.example.homework21.presenter.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework21.domain.usecase.FilterShmotkebiUseCase
import com.example.homework21.domain.usecase.GetShmotkebiUseCase
import com.example.homework21.domain.wrapper.ResultWrapper
import com.example.homework21.presenter.event.HomePageEvent
import com.example.homework21.presenter.mapper.toUI
import com.example.homework21.presenter.model.HomePageUI
import com.example.homework21.presenter.state.HomePageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getShmotkebiUseCase: GetShmotkebiUseCase,
    private val filterShmotkebiUseCase: FilterShmotkebiUseCase
) :ViewModel() {

    private val _uiStateFlow = MutableStateFlow(HomePageState())
    val uiStateFlow : StateFlow<HomePageState> = _uiStateFlow

    init {
        onEvent(HomePageEvent.LoadShmotkebi)
    }

    fun onEvent(event: HomePageEvent){
        when(event){
            HomePageEvent.LoadShmotkebi -> loadShmotkebi()
            HomePageEvent.ResetErrorMessageToNull -> resetErrorMessageToNull()
            is HomePageEvent.FilterShmotkebi -> filterShmotkebi(event.category,event.lastSelectedCategory)
        }
    }

    private fun loadShmotkebi(){
        viewModelScope.launch(Dispatchers.IO) {
            getShmotkebiUseCase().collect{result ->
                when(result){
                    is ResultWrapper.Error -> {
                        _uiStateFlow.update {
                            it.copy(errorMessage = result.errorMessage, isSuccess = null)
                        }
                    }
                    is ResultWrapper.Loading -> {
                        _uiStateFlow.update {
                            it.copy(isLoading = result.loading)
                        }
                    }
                    is ResultWrapper.Success -> {
                        _uiStateFlow.update {state->
                            state.copy(
                                isSuccess = result.data!!.toUI()
                            )
                        }
                    }
                }
            }
        }

    }

    private fun resetErrorMessageToNull(){
        _uiStateFlow.update {
            it.copy(errorMessage = null)
        }
    }

    private fun filterShmotkebi(category:String,lastSelectedCategory:Int){
        viewModelScope.launch(Dispatchers.IO) {
            val filteredShmotkebi = filterShmotkebiUseCase(category)
            val filteredMappedShmotkebi = withContext(Dispatchers.Default){
                filteredShmotkebi.map { it.toUI() }
            }
            val categoryList = _uiStateFlow.value.isSuccess?.categoryList ?: emptyList()
            val homePageUI = HomePageUI(categoryList,filteredMappedShmotkebi,lastSelectedCategory)
            _uiStateFlow.update {
                it.copy(isSuccess = homePageUI)
            }
        }
    }

}
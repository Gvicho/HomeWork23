package com.example.homework21.presenter.screen

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.homework21.R
import com.example.homework21.databinding.FragmentHomePageBinding
import com.example.homework21.presenter.base.BaseFragment
import com.example.homework21.presenter.event.HomePageEvent
import com.example.homework21.presenter.extension.showSnackBar
import com.example.homework21.presenter.screen.adapters.CallBack
import com.example.homework21.presenter.screen.adapters.CategorysRecyclerAdapter
import com.example.homework21.presenter.screen.adapters.ShmotkiRecyclerAdapter
import com.example.homework21.presenter.state.HomePageState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomePageFragment : BaseFragment<FragmentHomePageBinding>(FragmentHomePageBinding::inflate),CallBack {

    private val viewModel:HomePageViewModel by viewModels()
    private lateinit var myShmotkebisAdapter: ShmotkiRecyclerAdapter

    override fun bind() {
        //viewModel.onEvent(HomePageEvent.LoadShmotkebi)
        myShmotkebisAdapter = ShmotkiRecyclerAdapter()
        binding.apply {
            shmotkebisRecycler.adapter = myShmotkebisAdapter
        }
        myShmotkebisAdapter.submitList(viewModel.uiStateFlow.value.isSuccess?.shmotkebi?: emptyList())
    }

    override fun bindObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiStateFlow.collect{
                    handleResult(it)
                }
            }
        }

    }


    private fun handleResult(state:HomePageState){
        state.errorMessage?.let {
            showErrorMessage(it)
            viewModel.onEvent(HomePageEvent.ResetErrorMessageToNull)
        }

        showLoader(state.isLoading)

        state.isSuccess?.let {
            myShmotkebisAdapter.submitList(it.shmotkebi)
            setCategorys(it.categoryList,it.lastSelectedCategory)
            if(it.shmotkebi.isEmpty()){
                showErrorMessage(getString(R.string.not_found_items))
            }
        }
    }

    private fun setCategorys(categoryList:List<String>, lastSelectedCategory:Int){
        binding.shmotkebisCategoryRecycler.adapter = CategorysRecyclerAdapter(categoryList,this,lastSelectedCategory)
    }

    private fun showErrorMessage(errorMessage:String){
        binding.root.showSnackBar(errorMessage)
    }

    private fun showLoader(loading:Boolean){
        binding.progressBar.visibility = if(loading) View.VISIBLE else View.GONE
    }

    override fun filterShmotkebi(category: String,lastSelectedCategory: Int) {
        viewModel.onEvent(HomePageEvent.FilterShmotkebi(category,lastSelectedCategory))
    }

}
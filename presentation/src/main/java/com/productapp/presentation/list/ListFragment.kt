package com.productapp.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.product.sideapp.home.R
import com.product.sideapp.home.databinding.FragmentListsBinding
import com.productapp.presentation.common.ErrorPopupFragment
import com.productapp.presentation.common.extension.hide
import com.productapp.presentation.common.extension.show
import com.productapp.presentation.home.HomeFragment
import com.productapp.presentation.list.adapter.ProductListAdapter
import com.productapp.presentation.list.paging.PaginationScrollListener
import com.productapp.presentation.list.uievents.GetListEvents
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels()

    private var _binding: FragmentListsBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    private var listAdapter: ProductListAdapter? = null
    private val fragmentPopup = ErrorPopupFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListsBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getList()
        bindInitialUI()
        initVMObservers()

        findNavController().let {
            navController = it
        }
    }

    private fun initVMObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.listFlow.collect {
                        when (it) {
                            is GetListEvents.Success -> {
                                listAdapter?.updateProductList(it.listData.productList)
                            }

                            is GetListEvents.Failure -> {
                                fragmentPopup.show(
                                    requireActivity().supportFragmentManager,
                                    HomeFragment.ERROR_POP_UP_FRAGMENT_TAG
                                )
                            }

                            else -> Unit
                        }
                    }
                }
                launch {
                    viewModel.isPagingLoading.collect {
                        if (it) {
                            showLottie()
                        } else {
                            binding.animationLoadingLottieView.hide()
                        }
                    }
                }
            }
        }
    }


    private fun bindInitialUI() {
        bindRecyclerView()
    }

    private fun bindRecyclerView() {
        binding.listRecyclerView.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
            listAdapter = ProductListAdapter()
            adapter = listAdapter

            addOnScrollListener(object : PaginationScrollListener(
                binding.listRecyclerView.layoutManager as GridLayoutManager
            ) {
                override fun loadMoreItems() {
                    viewModel.setPagingLoading(true)
                    lifecycleScope.launch {
                        viewModel.getList()
                        delay(2000)
                        viewModel.setPagingLoading(false)
                    }
                }

                override fun isLastPage(): Boolean {
                    return TOTAL_COUNT % PRODUCT_LIMIT != 0
                }

                override fun isLoading(): Boolean {
                    return viewModel.isPagingLoading.value
                }
            })
        }
    }

    private fun showLottie() {
        binding.animationLoadingLottieView.apply {
            bringToFront()
            show()
            setAnimation(R.raw.animation_ll3rgqpm)
            playAnimation()
        }
    }

    /*
    private fun swipeToRefresh() {
        with(binding.swipeRefreshLayout) {
            setOnRefreshListener {
                viewModel.getList()
                CustomToast(context).showCustomToast(
                    icon = R.drawable.success_svg,
                    text = DEFAULT_SUCCESS_TOAST_TEXT,
                    toastDuration = TOAST_DURATION
                )
                isRefreshing = false
            }
        }
    }
     */

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val PRODUCT_LIMIT = 8
        const val TOTAL_COUNT = 16
    }
}
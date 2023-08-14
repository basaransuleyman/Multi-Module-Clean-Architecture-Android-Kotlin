package com.productapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.product.sideapp.home.R
import com.product.sideapp.home.databinding.FragmentHomeBinding
import com.productapp.presentation.common.ErrorPopupFragment
import com.productapp.presentation.common.extension.hide
import com.productapp.presentation.common.extension.show
import com.productapp.presentation.home.adapter.HomeMainAdapter
import com.productapp.presentation.home.uievents.GetHomeEvents
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeSharedViewModel by activityViewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    private var homeAdapter: HomeMainAdapter? = null

    private val fragmentPopup = ErrorPopupFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(
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
        initVMObservers()

        findNavController().let {
            navController = it
        }
    }

    private fun initVMObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.homeFlow.collect {
                        when (it) {
                            is GetHomeEvents.StartShimmer -> {
                                //TODO:Flow
                                binding.shimmerViewContainer.show()
                            }

                            is GetHomeEvents.Success -> {
                                //TODO:Flow
                                binding.shimmerViewContainer.hide()
                                bindUI()
                            }

                            is GetHomeEvents.Failure -> {
                                //TODO:Flow
                                fragmentPopup.show(
                                    requireActivity().supportFragmentManager,
                                    ERROR_POP_UP_FRAGMENT_TAG
                                )
                            }

                            else -> Unit
                        }
                    }
                }

                launch {
                    viewModel.sectionList.collect {
                        homeAdapter?.updateHomeList(it)
                    }
                }
            }

        }
    }

    private fun bindUI() {
        bindRecyclerView()
        routeActions()
    }

    private fun bindRecyclerView() {
        homeAdapter = HomeMainAdapter()
        binding.homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeAdapter
        }
    }

    private fun routeActions() {
        homeAdapter?.homeBannerClickListener { route ->
            when (route) {
                ROUTE_TO_DETAIL -> {
                    navController.navigate(R.id.detailFragment)
                }
                ROUTE_TO_LIST -> {
                    navController.navigate(R.id.listFragment)
                }
                ROUTE_TO_COMPOSE_MULTI_TYPE_LAZY_COLUMN -> {
                    navController.navigate(R.id.multiTypeLazyColumn)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ERROR_POP_UP_FRAGMENT_TAG = "error_popup_fragment"
        const val ROUTE_TO_DETAIL = "101"
        const val ROUTE_TO_LIST = "102"
        const val ROUTE_TO_COMPOSE_MULTI_TYPE_LAZY_COLUMN = "103"
    }

}
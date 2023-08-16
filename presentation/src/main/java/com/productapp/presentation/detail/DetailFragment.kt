package com.productapp.presentation.detail

import com.productapp.presentation.common.extension.shareLink
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
import com.productapp.domain.model.detail.DetailModel
import com.product.sideapp.home.databinding.FragmentDetailBinding
import com.productapp.presentation.common.ToolbarTransitionAnimation
import com.productapp.presentation.common.extension.setImageUrl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel: DetailSharedViewModel by activityViewModels()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(
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
        viewModel.getDetail()
        initVMObservers()

        findNavController().let {
            navController = it
        }
    }

    private fun initVMObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.detailFlow.collect { detailItem ->
                        detailItem?.let {
                            bindUI(it)
                        }
                    }
                }
            }
        }
    }

    private fun bindUI(detailItem: DetailModel) {
        binding.productImageImageView.setImageUrl(detailItem.productImage)
        binding.detailProductContentView.bind(detailItem)

        ToolbarTransitionAnimation().collapsingToolbarTransition(
            appBarLayout = binding.detailAppBarLayout,
            context = requireContext(),
            imageView = binding.productImageImageView,
            textView = binding.detailToolbarTextView,
            icon = binding.detailShareImageView,
            data = detailItem
        )

        binding.detailShareImageView.setOnClickListener {
           shareLink(detailItem.share, detailItem.share)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val INTENT_SHARE = "text/plain"
    }

}
package com.productapp.presentation.detail.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.product.common.model.detail.DetailModel
import com.product.sideapp.home.R
import com.product.sideapp.home.databinding.FragmentDetailBottomsheetBinding
import com.productapp.presentation.common.extension.setImageUrl
import com.productapp.presentation.detail.DetailSharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDetailBottomsheetBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailSharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBottomSheetStyle()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBottomsheetBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawableResource(R.color.dim_color)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDetail()
        initVMObservers()
    }

    private fun setupBottomSheetStyle() {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme)
    }

    private fun initVMObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.detailFlow.collect {
                    initialize(it)
                }
            }
        }
    }

    private fun initialize(productDetail: DetailModel?) {
        bindUI(productDetail)
    }

    private fun bindUI(product: DetailModel?) {
        with(binding) {
            product?.let {
                productNameTextView.text = product.productName
                productImageView.setImageUrl(product.productImage)
                detailProductContentView.bind(product)
            }
            productButton.setOnClickListener {
                findNavController().navigate(R.id.homeFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
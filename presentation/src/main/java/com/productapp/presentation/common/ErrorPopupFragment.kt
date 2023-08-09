package com.productapp.presentation.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.product.sideapp.home.R
import com.product.sideapp.home.databinding.FragmentPopupBinding

class ErrorPopupFragment : DialogFragment() {

    private var _binding: FragmentPopupBinding? = null
    private val binding get() = _binding!!

    private val popupIcon: Int = R.drawable.popup_error_icon

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopupBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
    }

    private fun bindUI() {
        with(binding) {
            popupIconImageView.setImageResource(popupIcon)
            popupTextView.text = binding.root.context.getString(R.string.error_popup)
            closeButton.setOnClickListener {
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

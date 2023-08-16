package com.productapp.presentation.home.viewholder

import android.os.CountDownTimer
import androidx.recyclerview.widget.RecyclerView
import com.productapp.domain.model.home.BannerItem
import com.product.sideapp.home.databinding.RowHomeBannerBinding
import com.productapp.presentation.home.adapter.HomeBannerAdapter

class HomeBannerViewHolder(
    private val binding: RowHomeBannerBinding,
    private val clickListener: ((String?) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var bannerTimer: CountDownTimer
    private var currentPosition = 0

    fun bind(bannerItem: List<BannerItem>) {
        val bannerAdapter = HomeBannerAdapter(bannerItem, clickListener)
        binding.viewPager.adapter = bannerAdapter
        startBannerTimer(bannerItem.size)
    }

    private fun moveToNextBannerItem(bannerSize: Int) {
        currentPosition++
        if (currentPosition >= bannerSize) {
            currentPosition = 0
        }
        binding.viewPager.setCurrentItem(currentPosition, true)
    }


    private fun startBannerTimer(bannerSize: Int) {
        bannerTimer = object : CountDownTimer(Long.MAX_VALUE, bannerInterval) {
            override fun onTick(millisUntilFinished: Long) {
                moveToNextBannerItem(bannerSize)
            }

            override fun onFinish() {
            }
        }
        bannerTimer.start()

    }

    companion object {
        const val bannerInterval: Long = 3000
    }

}
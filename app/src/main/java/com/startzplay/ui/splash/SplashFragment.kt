package com.startzplay.ui.splash

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.startzplay.R
import com.startzplay.base.BaseFragment
import com.startzplay.databinding.FragmentSplashBinding
import com.startzplay.utils.navigate
import com.startzplay.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment(R.layout.fragment_splash) {

    private val mBinding by viewBinding(FragmentSplashBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startAnimation()
        gotoHome()
    }

    //////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    private fun startAnimation() {
        val imageView=mBinding.splashLogo
        val rotationAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f).apply {
            duration = 2000
        }

        val scaleXAnimator = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 1.5f, 1f).apply {
            duration = 2000
        }

        val scaleYAnimator = ObjectAnimator.ofFloat(imageView, "scaleY", 1f, 1.5f, 1f).apply {
            duration = 2000
        }
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(rotationAnimator, scaleXAnimator, scaleYAnimator)
        animatorSet.start()
    }

    private fun gotoHome() {
        lifecycleScope.launch {
            delay(3000)
            navigate(R.id.action_splashFragment_to_homeFragment)
        }
    }
}
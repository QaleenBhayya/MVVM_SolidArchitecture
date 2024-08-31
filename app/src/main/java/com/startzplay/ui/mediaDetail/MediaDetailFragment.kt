package com.startzplay.ui.mediaDetail

import android.os.Bundle
import android.view.View
import coil.load
import com.startzplay.R
import com.startzplay.base.BaseFragment
import com.startzplay.databinding.FragmentMediaDetailBinding
import com.startzplay.utils.Constants
import com.startzplay.utils.navigate
import com.startzplay.utils.setImageFromUrl
import com.startzplay.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MediaDetailFragment : BaseFragment(R.layout.fragment_media_detail) {

    private val mBinding by viewBinding(FragmentMediaDetailBinding::bind)

    private var category: String? = null
    private var name: String? = null
    private var overView: String? = null
    private var posterPath: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObject()
        initSetupUi()
        initClicks()
    }

    //////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    private fun initObject() {
        category = arguments?.getString("media_type")
        name = arguments?.getString("title")
        overView = arguments?.getString("overView")
        posterPath = arguments?.getString("poster_path")
    }

    private fun initSetupUi() {
        mBinding.apply {
            category?.apply {
                detailTvCategory.apply {
                    visibility = View.VISIBLE
                    text =
                        if (category == "movie") "Movie" else if (category == "tv") " Tv Series" else category?.uppercase()
                }
                val visibility =
                    if (category == "movie" || category == "tv") View.VISIBLE else View.GONE
                detailBtnPlay.visibility = visibility
            }

            name?.apply {
                detailTvTitle.apply {
                    visibility = if (name!!.isNotEmpty()) View.VISIBLE else View.GONE
                    text = name
                }
            }

            overView?.apply {
                detailTvOverView.apply {
                    visibility = View.VISIBLE
                    text = overView
                }
            }
            posterPath?.apply {
                detailIvPoster.setImageFromUrl(Constants.IMAGE_BASE_URL + posterPath)
            }
        }
    }

    private fun initClicks() {
        mBinding.detailBtnPlay.setOnClickListener {
            gotoPlayer()
        }
    }

    //////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    private fun gotoPlayer() {
        navigate(R.id.action_mediaDetailFragment_to_playerFragment)
    }

}
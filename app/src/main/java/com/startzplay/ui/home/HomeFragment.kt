package com.startzplay.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import com.startzplay.R
import com.startzplay.base.BaseFragment
import com.startzplay.common.APIObserver
import com.startzplay.data.local.allCategory.AllCategory
import com.startzplay.data.remote.search.SearchItem
import com.startzplay.databinding.FragmentHomeBinding
import com.startzplay.utils.navigate
import com.startzplay.utils.showToast
import com.startzplay.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private var mHomeList = ArrayList<AllCategory>()
    private lateinit var mAdapter: HomeAdapter

    private val mBinding by viewBinding(FragmentHomeBinding::bind)
    private val mViewModel: HomeViewModel by viewModels()

    private var typingHandler: Handler? = null
    private var typingRunnable: Runnable? = null
    private var isTyping = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObj()
        setUpUi()
        initObservers()
    }

    //////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    private fun initObj() {
        mAdapter = HomeAdapter(requireContext(), ::onItemClick)
    }

    private fun setUpUi() {
        mBinding.homeRvCategory.adapter = mAdapter
        setupSearch()
    }

    private fun initObservers() {
        mViewModel.searchData.observe(viewLifecycleOwner, APIObserver(this, onSuccess = {
            it?.apply {
                mHomeList.clear()
                mHomeList.addAll(getAllCategory(it.results))
                mAdapter.addData(mHomeList)

            }
        }, onError = {
            requireActivity().showToast(it)
        }))
    }

    //////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    private fun setupSearch() {
        typingHandler = Handler(Looper.getMainLooper())
        mBinding.homeEdSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (!isTyping) {
                    isTyping = true
                }
                if (typingRunnable != null) {
                    typingHandler?.removeCallbacks(typingRunnable!!)
                }


                typingRunnable = Runnable {
                    isTyping = false
                    onUserStoppedTyping(s.toString())
                }
                typingHandler?.postDelayed(typingRunnable!!, 1000)


            }

            override fun afterTextChanged(s: Editable) {
            }
        })
    }

    private fun onItemClick(item: SearchItem) {
        val bundle = Bundle()
        bundle.putString("media_type", item.media_type)
        bundle.putString("title", item.title ?: item.name ?: "Name is missing")
        bundle.putString("overView", item.overview)
        bundle.putString("poster_path", item.poster_path)
        navigate(R.id.action_homeFragment_to_mediaDetailFragment, bundle)
    }


    private fun getAllCategory(results: List<SearchItem>): List<AllCategory> {
        val categoryList = ArrayList<AllCategory>()
        val mapper = results.groupBy { it.media_type }
        mapper.forEach { (category, medias) ->
            categoryList.add(AllCategory(category!!, medias))
        }
        val sortedCategoryList = categoryList.sortedBy { it.name }
        return sortedCategoryList
    }

    //////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    private fun onUserStoppedTyping(query: String) {
        if (query.isNotEmpty()) {
            mViewModel.multiSearch(query.trim(), false, "", 1)
        }
    }
}
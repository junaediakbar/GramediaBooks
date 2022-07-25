package com.juned.expertclass.gramediabooks.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.juned.expertclass.gramediabooks.R
import com.juned.expertclass.gramediabooks.core.data.Resource
import com.juned.expertclass.gramediabooks.core.ui.BookAdapter
import com.juned.expertclass.gramediabooks.core.ui.ViewModelFactory
import com.juned.expertclass.gramediabooks.databinding.FragmentHomeBinding
import com.juned.expertclass.gramediabooks.detail.DetailFragment


class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookAdapter = BookAdapter()
        bookAdapter.onItemClick = { selectedData ->
            val bundle = bundleOf(DetailFragment.EXTRA_BOOK to selectedData)
            view.findNavController().navigate(R.id.detailFragment, bundle)
        }

        val factory = ViewModelFactory.getInstance(requireContext())
        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        homeViewModel.book.observe(viewLifecycleOwner) { book ->
            if (book != null) {
                when (book) {
                    is Resource.Loading -> binding?.progressBar?.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        bookAdapter.setData(book.data)
                    }
                    is Resource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.viewError?.root?.visibility = View.VISIBLE
                        binding?.viewError?.tvError?.text =
                            book.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        }
        binding?.recyclerView?.apply{
            layoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = bookAdapter
        }
    }

}
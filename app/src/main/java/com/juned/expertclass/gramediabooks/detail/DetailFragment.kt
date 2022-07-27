package com.juned.expertclass.gramediabooks.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.juned.expertclass.gramediabooks.R
import com.juned.expertclass.gramediabooks.core.data.Resource
import com.juned.expertclass.gramediabooks.core.domain.model.Book
import com.juned.expertclass.gramediabooks.core.ui.ViewModelFactory
import com.juned.expertclass.gramediabooks.databinding.FragmentDetailBinding
import com.juned.expertclass.gramediabooks.home.HomeViewModel


class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        val factory = ViewModelFactory.getInstance(requireContext())
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val book = (arguments?.getParcelable<Book>(EXTRA_BOOK) as Book)
        detailViewModel.setBook(book.slug)
        setItemDetails(book)

        detailViewModel.book.observe(viewLifecycleOwner) { book ->
            if (book != null) {
                when (book) {
                    is Resource.Loading -> binding?.progressBar?.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.tvDataDesc?.text = book.data?.description
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

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: Toolbar = binding?.toolbar!!
        toolbar.setNavigationIcon(R.drawable.ic_baseline_back)

        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun setItemDetails(book: Book?){
        binding?.apply {
            tvDetailTitle.text = book?.title
            tvDataAuthor.text = book?.author
            tvDataDesc.text = book?.description
            tvDataPrice.text = book?.price
            Glide.with(imgDataImage.context)
                .load(book?.image)
                .apply(
                    RequestOptions.placeholderOf(R.color.greyStrokeColor)
                        .error(R.color.colorAccent)
                )
                .into(imgDataImage)
        }
    }

    companion object {
      const val EXTRA_BOOK = "extra_book"
    }
}
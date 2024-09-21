package com.santorence.secveniafilmapp.filmDetailsScreen.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.santorence.secveniafilmapp.R
import com.santorence.secveniafilmapp.databinding.FragmentFilmDetailBinding
import com.santorence.secveniafilmapp.fimsScreen.domain.model.FilmModel
import com.santorence.secveniafilmapp.fimsScreen.presentation.FilmsFragment.Companion.FILM_KEY

class FilmDetailFragment : Fragment() {

    private var _binding: FragmentFilmDetailBinding? = null
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilmDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arg = arguments?.getSerializable(FILM_KEY) as? FilmModel

        binding?.let {
            it.toolbar.toolbar.visibility = View.VISIBLE
            it.details.text = arg?.description
            it.genre.text = "${arg?.genres?.joinToString(", ")} ${arg?.year}"
            it.localizedName.text = arg?.localizedName
            it.toolbar.toolbarHeader.text = arg?.localizedName
            binding?.filmImage?.let { urlFilm ->
                Glide.with(requireContext()).load(arg?.imageUrl).placeholder(R.drawable.empty_image)
                    .into(
                        urlFilm
                    )
            }
            it.rating.text = String.format("%.1f", arg?.rating)
            it.toolbar.btnBack.visibility = View.VISIBLE
            it.toolbar.btnBack.setOnClickListener {
                findNavController().navigate(R.id.action_filmDetailFragment_to_filmsFragment)
            }
        }


        println(arg)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.santorence.secveniafilmapp.fimsScreen.presentation

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.santorence.secveniafilmapp.R
import com.santorence.secveniafilmapp.databinding.FragmentFilmsBinding
import com.santorence.secveniafilmapp.fimsScreen.domain.model.GenreModel
import com.santorence.secveniafilmapp.fimsScreen.presentation.viewAdapters.FilmsAdapter
import com.santorence.secveniafilmapp.fimsScreen.presentation.viewAdapters.GenreAdapter
import com.santorence.secveniafilmapp.utils.FilmsItemDecorator
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmsFragment : Fragment() {
    companion object {
        const val FILM_KEY = "film"
    }

    private val viewModel: FilmsViewModel by viewModel()
    private var _binding: FragmentFilmsBinding? = null
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilmsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.exceptionLiveData.observe(viewLifecycleOwner) {
            val snackbar =
                Snackbar.make(view, getString(it.errorMessage), Snackbar.LENGTH_INDEFINITE)
            snackbar.setAction(getString(R.string.tryAgain)) {
                viewModel.fetchFilms()
            }
            snackbar.setActionTextColor(resources.getColor(R.color.orange))
            snackbar.show()
        }

        viewModel.filmsLiveData.observe(viewLifecycleOwner) { filmModels ->
            val genreList = filmModels.map { it.genres }.flatMap { it }.toSet().toList().map { gen -> GenreModel(gen, false) }
                .toMutableList()

            binding?.genreRecycleView?.layoutManager = LinearLayoutManager(requireContext())
            val genreAdapter = GenreAdapter(genreList).apply {
                this.onGenreClick = { genre ->
                    viewModel.filterFilmsByGenre(genre)
                }
            }
            binding?.genreRecycleView?.adapter = genreAdapter

            binding?.let {
                it.filmsLinearLayout.gravity = Gravity.TOP
                it.toolbar.toolbarHeader.text = getString(R.string.films)
                it.filmScrollView.visibility = View.VISIBLE
                it.toolbar.toolbar.visibility = View.VISIBLE
                it.progress.visibility = View.GONE
            }
            val filmsItemDecorator = FilmsItemDecorator(10, 5)
            binding?.filmsRecyclerView?.addItemDecoration(filmsItemDecorator)

            val sortedByNameFilmsList = filmModels.sortedBy {
                it.localizedName?.first()
            }

            val filmsAdapter = FilmsAdapter(sortedByNameFilmsList.toMutableList()).apply {
                this.onFilmClick = { filmModel ->
                    findNavController().navigate(
                        R.id.action_filmsFragment_to_filmDetailFragment,
                        Bundle().apply {
                            putSerializable(
                                FILM_KEY, filmModel
                            )
                        })
                }
            }
            binding?.filmsRecyclerView?.adapter = filmsAdapter

            viewModel.genreLiveData.observe(viewLifecycleOwner) { genreItem ->
                val updateGenresList: List<GenreModel> = genreList.map {
                    if (it.genre == genreItem.genre) {
                        GenreModel(genreItem.genre, !it.isChecked)
                    } else {
                        GenreModel(it.genre, false)
                    }
                }

                genreAdapter.setGenreList(updateGenresList.toMutableList())
                val filteredFilmsList = filmModels.filter {
                    it.genres.contains(genreItem.genre)
                }.sortedBy {
                    it.localizedName?.first()
                }

                if (updateGenresList.map { it.isChecked }.contains(true)) {
                    filmsAdapter.setFilmsList(filteredFilmsList)
                } else {
                    filmsAdapter.setFilmsList(sortedByNameFilmsList)
                }
            }
        }





        viewModel.fetchFilms()
    }
}



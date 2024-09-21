package com.santorence.secveniafilmapp.fimsScreen.presentation

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.santorence.secveniafilmapp.R
import com.santorence.secveniafilmapp.databinding.FragmentFilmsBinding
import com.santorence.secveniafilmapp.fimsScreen.presentation.viewAdapters.FilmsAdapter
import com.santorence.secveniafilmapp.fimsScreen.presentation.viewAdapters.GenreAdapter
import com.santorence.secveniafilmapp.utils.FilmsItemDecorator
import com.santorence.secveniafilmapp.utils.GenreItem
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

        val genresList: MutableList<GenreItem> =
            mutableListOf(
                GenreItem("комедия", false),
                GenreItem("приключения", false),
                GenreItem("фантастика", false),
                GenreItem("боевик", false),
                GenreItem("ужасы", false),
                GenreItem("фэнтези", false),
                GenreItem("триллер", false),
                GenreItem("драма", false),
                GenreItem("детектив", false),
                GenreItem("криминал", false),
                GenreItem("биография", false),
                GenreItem("мелодрама", false)
            )

        binding?.genreRecycleView?.layoutManager = LinearLayoutManager(requireContext())
        val genreAdapter = GenreAdapter(genresList).apply {
            this.onGenreClick = { genre ->
                viewModel.filterFilmsByGenre(genre)
            }
        }
        binding?.genreRecycleView?.adapter = genreAdapter



        viewModel.filmsLiveData.observe(viewLifecycleOwner) { filmModels ->
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
                    findNavController().navigate(R.id.action_filmsFragment_to_filmDetailFragment, Bundle().apply { putSerializable(
                        FILM_KEY, filmModel) })
                    println(filmModel)
                }
            }
            binding?.filmsRecyclerView?.adapter = filmsAdapter
            println(filmModels)

            viewModel.genreLiveData.observe(viewLifecycleOwner) { genreItem ->
                val updateGenresList: List<GenreItem> = genresList.map {
                    if (it.genre == genreItem.genre) {
                        GenreItem(genreItem.genre, !it.isChecked)
                    } else {
                        GenreItem(it.genre, false)
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


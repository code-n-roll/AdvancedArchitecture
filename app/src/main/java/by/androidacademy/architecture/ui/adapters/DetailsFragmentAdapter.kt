package by.androidacademy.architecture.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import by.androidacademy.architecture.domain.model.Movie
import by.androidacademy.architecture.ui.DetailsFragment

class DetailsFragmentAdapter(
    fragmentManager: FragmentManager,
    private val movies: List<Movie>
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return DetailsFragment.newInstance(movies[position], position)
    }

    override fun getCount(): Int {
        return movies.size
    }
}
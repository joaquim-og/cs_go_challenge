package com.confradestech.csgochallenge.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.confradestech.csgochallenge.R
import com.confradestech.csgochallenge.presentation.VM.MainViewModel
import com.confradestech.csgochallenge.presentation.home.screen.HomeScreen
import com.confradestech.csgochallenge.utilities.extensions.navigateSafe
import com.confradestech.csgochallenge.utilities.extensions.obtainViewModel
import com.confradestech.csgochallenge.utilities.ui.theme.CsGoChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        obtainViewModel(
            requireActivity(),
            MainViewModel::class.java,
            defaultViewModelProviderFactory
        )
    }

    private var actualPageIndex = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireActivity()).apply {
            setContent {
                CsGoChallengeTheme {
                    HomeScreen(
                        matchesListState = viewModel.matchesListState,
                        onCardClicked = { match, game ->
                            viewModel.setSelectedGameValues(match, game)
                            navigateToDetailsFragment()
                        },
                        onSwipeAction = {
                            onSwipeRefresh()
                        },
                        onLoadMoreItems = {
                            actualPageIndex += 1
                            viewModel.fetchMatchesData(actualPageIndex)
                        }
                    )
                }
            }
        }
    }

    private fun onSwipeRefresh() {
        viewModel.fetchMatchesData(actualPageIndex)
    }

    private fun navigateToDetailsFragment() {
        navigateSafe(R.id.action_homeFragment_to_detailsFragment)
    }
}
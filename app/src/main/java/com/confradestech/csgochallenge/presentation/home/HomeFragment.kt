package com.confradestech.csgochallenge.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.confradestech.csgochallenge.presentation.VM.MainViewModel
import com.confradestech.csgochallenge.presentation.home.screen.HomeScreen
import com.confradestech.csgochallenge.ui.theme.CsGoChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()

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
                            //TODO
                            //update value on viewModel
                            // navigate to match details

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
}
package com.confradestech.csgochallenge.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.findNavController
import com.confradestech.csgochallenge.presentation.VM.MainViewModel
import com.confradestech.csgochallenge.presentation.details.screen.DetailsScreen
import com.confradestech.csgochallenge.utilities.extensions.obtainViewModel
import com.confradestech.csgochallenge.utilities.ui.theme.CsGoChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        obtainViewModel(
            requireActivity(),
            MainViewModel::class.java,
            defaultViewModelProviderFactory
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireActivity()).apply {
            setContent {
                CsGoChallengeTheme {
                    DetailsScreen(
                        game = viewModel.selectedGameState.game,
                        match = viewModel.selectedGameState.match,
                        onNavigateBack = ::onNavigateBack
                    )
                }
            }
        }
    }


    private fun onNavigateBack(){
        findNavController().navigateUp()
    }
}
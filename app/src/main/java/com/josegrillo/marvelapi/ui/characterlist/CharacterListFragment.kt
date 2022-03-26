package com.josegrillo.marvelapi.ui.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.josegrillo.marvelapi.base.BaseFragment
import com.josegrillo.marvelapi.databinding.FragmentCharacterListBinding
import com.josegrillo.marvelapi.ui.characterlist.adapter.CharacterAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.josegrillo.marvelapi.di.modules.GlideApp
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterListFragment : BaseFragment<FragmentCharacterListBinding>() {
    private val charactersListViewModel: CharacterListViewModel by viewModel()

    override fun configureViewElements() {

        binding.charactersList.adapter =
            CharacterAdapter(
                GlideApp.with(this@CharacterListFragment),
                charactersListViewModel::onCharacterSelected
            )

        binding.apply {
            (charactersList.adapter as? CharacterAdapter)?.let { adapter ->
                lifecycleScope.launch {
                    adapter.loadStateFlow.collectLatest { loadStates ->
                        progressview.root.isVisible =
                            loadStates.refresh is LoadState.Loading
                        errorView.root.isVisible =
                            loadStates.refresh is LoadState.Error
                        footerContainer.root.isVisible =
                            loadStates.append is LoadState.Error
                    }
                }
            }
        }

        binding.errorView.errorLayoutRetryButton.setOnClickListener {
            (binding.charactersList.adapter as? CharacterAdapter)?.retry()
        }

        binding.footerContainer.root.setOnClickListener {
            (binding.charactersList.adapter as? CharacterAdapter)?.retry()
        }
    }

    override fun setObservers() {
        binding.apply {
            charactersListViewModel.charactersData.observe(viewLifecycleOwner, {
                (charactersList.adapter as? CharacterAdapter)?.apply {
                    submitData(
                        lifecycle,
                        it
                    )
                }
            })

            charactersListViewModel.selectedCharacterId.observe(
                viewLifecycleOwner,
                { characterSelectedEvent ->
                    characterSelectedEvent?.getContentIfNotHandled()?.let { characterId ->
                        findNavController().navigate(
                            CharacterListFragmentDirections.actionCharacterListFragmentToDetailFragment(
                                characterId
                            )
                        )
                    }
                })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        charactersListViewModel.loadCharacters()
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCharacterListBinding.inflate(inflater, container, false)
}
package petros.efthymiou.groovy.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import petros.efthymiou.groovy.databinding.FragmentPlaylistDetailBinding
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistDetailsFragment : Fragment() {

    val args: PlaylistDetailsFragmentArgs by navArgs()

    lateinit var viewModel: PlaylistDetailsViewModel
    private lateinit var binding: FragmentPlaylistDetailBinding

    @Inject
    lateinit var viewModelFactory: PlaylistDetailsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlaylistDetailBinding.inflate(inflater, container, false)
        val id = args.playlistId

        setupViewModel()
        viewModel.getPlaylistDetails(id)

        observeLiveData()

        return binding.root
    }

    private fun observeLiveData() {
        viewModel.playlistsDetails.observe(this as LifecycleOwner) { playlistDetails ->
            if (playlistDetails.getOrNull() != null) {
                setupUI(playlistDetails)
            } else {
                TODO()
            }

        }
    }

    private fun setupViewModel() {
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(PlaylistDetailsViewModel::class.java)
    }

    private fun setupUI(playlistDetails: Result<PlaylistDetails>) {
        binding.playlistName.text = playlistDetails.getOrNull()!!.name
        binding.playlistDetails.text = playlistDetails.getOrNull()!!.details
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlaylistDetailsFragment()
    }
}
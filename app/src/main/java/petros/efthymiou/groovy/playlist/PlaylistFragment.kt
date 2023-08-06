package petros.efthymiou.groovy.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_playlist.loader
import kotlinx.android.synthetic.main.fragment_playlist.view.playlist_list
import petros.efthymiou.groovy.R
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistFragment : Fragment() {
    lateinit var viewModel: PlaylistViewModel

    @Inject
    lateinit var viewModelFactory: PlaylistViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)

        setupViewModel()

        observeLoader()

        observePlaylist(view)

        return view
    }

    private fun observeLoader() {
        viewModel.loader.observe(this as LifecycleOwner) { loading ->
            when (loading) {
                true -> loader.visibility = View.VISIBLE
                false -> loader.visibility = View.INVISIBLE
            }

        }
    }

    private fun observePlaylist(view: View) {
        viewModel.playlists.observe(this as LifecycleOwner) { playlists ->
            if (playlists.getOrNull() != null) {
                setupList(view.playlist_list, playlists.getOrNull()!!)
            } else {
                TODO()
            }

        }
    }

    private fun setupList(
        view: View?,
        playlists: List<Playlist>
    ) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyPlaylistRecyclerViewAdapter(playlists) { id ->
                val action =
                    PlaylistFragmentDirections.actionPlaylistFragmentToPlaylistDetailFragment(id)
                findNavController().navigate(action)
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlaylistViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlaylistFragment().apply {}
    }
}
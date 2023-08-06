package petros.efthymiou.groovy.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import petros.efthymiou.groovy.playlist.PlayListRepository
import petros.efthymiou.groovy.playlist.PlaylistViewModel
import javax.inject.Inject

class PlaylistDetailsViewModelFactory @Inject constructor(
    private val repository: PlayListRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlaylistViewModel(repository) as T
    }
}

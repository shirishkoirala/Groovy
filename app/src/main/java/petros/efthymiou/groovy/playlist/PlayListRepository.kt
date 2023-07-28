package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow

class PlayListRepository(
    private val service: PlaylistService
) {
    suspend fun getPlaylists(): Flow<Result<List<Playlist>>> =
        service.fetchPlaylist()

}

package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow

class PlayListRepository {
    suspend fun getPlaylists(): Flow<Result<List<Playlist>>> {
        TODO()
    }

}

package petros.efthymiou.groovy

import kotlinx.coroutines.flow.Flow

class PlayListRepository {
    suspend fun getPlaylists(): Flow<Result<List<Playlist>>> {
        TODO()
    }

}

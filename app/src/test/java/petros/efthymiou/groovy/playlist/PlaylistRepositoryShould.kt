package petros.efthymiou.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import petros.efthymiou.groovy.utils.BaseUnitTest

class PlaylistRepositoryShould : BaseUnitTest() {
    private val service: PlaylistService = mock()
    private val playlists = mock<List<Playlist>>()
    private val exception = RuntimeException("Something went wrong!")

    @Test
    fun getPlaylistFromService() = runBlockingTest {
        val repository = PlayListRepository(service)

        repository.getPlaylists()

        verify(service, times(1)).fetchPlaylist()
    }

    @Test
    fun emitPlaylistFromService() = runBlockingTest {
        val repository = mockSuccessfulCase()

        assertEquals(playlists, repository.getPlaylists().first().getOrNull())
    }

    @Test
    fun propagateErrors() = runBlockingTest {
        val repository = mockFailureTest()
        assertEquals(exception, repository.getPlaylists().first().exceptionOrNull())
    }

    private suspend fun mockFailureTest(): PlayListRepository {
        whenever(service.fetchPlaylist()).thenReturn(
            flow {
                emit(Result.failure<List<Playlist>>(exception))
            }
        )
        val repository = PlayListRepository(service)
        return repository
    }

    private suspend fun mockSuccessfulCase(): PlayListRepository {
        whenever(service.fetchPlaylist()).thenReturn(
            flow {
                emit(Result.success(playlists))
            }
        )
        val repository = PlayListRepository(service)
        return repository
    }
}
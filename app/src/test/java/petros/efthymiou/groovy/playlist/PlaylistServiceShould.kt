package petros.efthymiou.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import petros.efthymiou.groovy.utils.BaseUnitTest

class PlaylistServiceShould : BaseUnitTest() {
    private lateinit var service: PlaylistService
    private val api: PlaylistAPI = mock()
    private val playlists: List<Playlist> = mock()

    @Test
    fun fetchPlaylistFromAPI() = runBlockingTest {
        service = PlaylistService(api)
        service.fetchPlaylist().first()
        verify(api, times(1)).fetchAllPlaylists()
    }

    @Test
    fun convertValueToFlowResultAndEmitsThem() = runBlockingTest {
        mockSuccessfulCase()

        assertEquals(Result.success(playlists), service.fetchPlaylist().first())
    }

    @Test
    fun emitsErrorResultsWhenNetworkFails() = runBlockingTest {
        mockFailureCase()

        assertEquals(
            "Something went wrong",
            service.fetchPlaylist().first().exceptionOrNull()?.message
        )
    }

    private suspend fun mockFailureCase() {
        whenever(api.fetchAllPlaylists()).thenThrow(RuntimeException("Damn backend developer"))
        service = PlaylistService(api)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(api.fetchAllPlaylists()).thenReturn(playlists)

        service = PlaylistService(api)
    }
}
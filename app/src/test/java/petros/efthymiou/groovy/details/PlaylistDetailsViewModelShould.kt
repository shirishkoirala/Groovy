package petros.efthymiou.groovy.details

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import petros.efthymiou.groovy.utils.BaseUnitTest
import petros.efthymiou.groovy.utils.getValueForTest

class PlaylistDetailsViewModelShould : BaseUnitTest() {

    private lateinit var viewModel: PlaylistDetailsViewModel
    private val id = "1"
    private val service: PlaylistDetailsService = mock()
    private val playlistDetails: PlaylistDetails = mock()

    private val expected = Result.success(playlistDetails)
    private val exception = RuntimeException("Something went wrong")
    private val error = Result.failure<PlaylistDetails>(exception)

    @Test
    fun getPlaylistDetailsFromService() = runBlockingTest {
        mockSuccessfulCase()
        viewModel.playlistsDetails.getValueForTest()

        verify(service, times(1)).fetchPlaylistDetails(id)
    }

    @Test
    fun emitPlaylistDetailsFromService() = runBlockingTest {
        mockSuccessfulCase()
        assertEquals(expected, viewModel.playlistsDetails.getValueForTest())
    }

    @Test
    fun emitErrorWhenServiceFails() = runBlockingTest {
        mockFailureCase()

        assertEquals(error, viewModel.playlistsDetails.getValueForTest())
    }

    private suspend fun mockFailureCase() {
        whenever(service.fetchPlaylistDetails(id)).thenReturn(
            flow {
                emit(error)
            }
        )

        viewModel = PlaylistDetailsViewModel(service)
        viewModel.getPlaylistDetails(id)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(service.fetchPlaylistDetails(id)).thenReturn(
            flow {
                emit(expected)
            }
        )
        viewModel = PlaylistDetailsViewModel(service)
        viewModel.getPlaylistDetails(id)
    }
}
package petros.efthymiou.groovy.playlist

import org.junit.Assert.assertEquals
import org.junit.Test
import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.utils.BaseUnitTest

class PlaylistMapperShould : BaseUnitTest() {

    private val playlistRaw = PlaylistRaw("1", "Da name", "da category")
    private val playlistRawRock = PlaylistRaw("1", "Da name", "rock")

    private val mapper = PlaylistMapper()
    private val playlists = mapper(listOf(playlistRaw))

    private val playlist = playlists.first()
    private val playlistRock = mapper(listOf(playlistRawRock)).first()

    @Test
    fun keepSameId() {
        assertEquals(playlistRaw.id, playlist.id)
    }

    @Test
    fun keepSameName() {
        assertEquals(playlistRaw.name, playlist.name)
    }

    @Test
    fun keepSameCategory() {
        assertEquals(playlistRaw.category, playlist.category)
    }

    @Test
    fun mapDefaultImageWhenNotRock() {
        assertEquals(R.mipmap.playlist, playlist.image)
    }

    @Test
    fun mapRockImageWhenRockCategory() {
        assertEquals(R.mipmap.rock, playlistRock.image)
    }
}
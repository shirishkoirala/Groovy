package petros.efthymiou.groovy.details

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import retrofit2.Retrofit

@Module
@InstallIn(FragmentComponent::class)
class PlaylistDetailsModules {

    @Provides
    fun playlistsDetailsAPI(retrofit: Retrofit) = retrofit.create(PlaylistDetailsAPI::class.java)
}
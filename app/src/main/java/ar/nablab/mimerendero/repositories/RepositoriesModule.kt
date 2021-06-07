package ar.nablab.mimerendero.repositories

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
interface RepositoriesModule {

    @ViewModelScoped
    @Binds
    fun bindMapRepository(mapRepositoryImpl: MapRepositoryImpl): MapRepository
}

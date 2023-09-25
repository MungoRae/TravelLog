package uk.me.mungorae.travellog

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uk.me.mungorae.travellog.data.TravelsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HiltModule {

    @Binds
    @Singleton
    abstract fun bindsTravelRepository(repo: TravelsRepository.Impl): TravelsRepository
}


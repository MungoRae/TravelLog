package uk.me.mungorae.travellog.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uk.me.mungorae.travellog.data.TravelsRepository
import uk.me.mungorae.travellog.util.DateTimeProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HiltModule {

    @Binds
    @Singleton
    abstract fun bindsTravelRepository(repo: TravelsRepository.Impl): TravelsRepository

    @Binds
    @Singleton
    abstract fun bindsDateTimeProvider(dt: DateTimeProvider.Impl): DateTimeProvider
}

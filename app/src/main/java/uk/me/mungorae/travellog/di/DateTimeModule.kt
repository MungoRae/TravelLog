package uk.me.mungorae.travellog.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.Clock

@Module
@InstallIn(SingletonComponent::class)
class DateTimeModule {

    @Provides
    fun providesClock(): Clock {
        return Clock.systemDefaultZone()
    }
}
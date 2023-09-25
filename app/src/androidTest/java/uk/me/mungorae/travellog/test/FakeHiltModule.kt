package uk.me.mungorae.travellog.test

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.flow.StateFlow
import uk.me.mungorae.travellog.HiltModule
import uk.me.mungorae.travellog.data.Travel
import uk.me.mungorae.travellog.data.TravelsRepository
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [HiltModule::class]
)
abstract class FakeHiltModule {

    @Singleton
    @Binds
    abstract fun service(repo: TravelsRepository.Impl): TravelsRepository
}
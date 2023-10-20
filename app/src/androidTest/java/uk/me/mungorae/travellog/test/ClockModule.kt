package uk.me.mungorae.travellog.test

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import uk.me.mungorae.travellog.di.DateTimeModule
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DateTimeModule::class],
)
class ClockModule {

    @Provides
    fun providesTestClock(): Clock {
        return TEST_CLOCK
    }

    companion object {
        val TEST_CLOCK: Clock = Clock
            .fixed(Instant.parse("2001-01-01T10:15:30.00Z"), ZoneId.of("UTC"))
    }
}

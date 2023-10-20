package uk.me.mungorae.travellog.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

interface TravelsRepository {
    fun createTravel(travel: Travel)

    fun getTravels(): List<Travel>

    fun travelsStream(): StateFlow<List<Travel>>

    class Impl @Inject constructor() : TravelsRepository {
        private val travels: MutableStateFlow<List<Travel>> = MutableStateFlow(emptyList())

        override fun createTravel(travel: Travel) {
            travels.update {
                it.plus(travel)
            }
        }

        override fun getTravels(): List<Travel> = travels.value

        override fun travelsStream(): StateFlow<List<Travel>> = travels.asStateFlow()
    }
}

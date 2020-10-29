package com.next.reservations.web.mapper

import com.next.reservations.core.domain.ReservationTimeConfiguration
import com.next.reservations.core.service.ReservationManagingService
import com.next.reservations.core.service.ReservationTimeConfigurationService
import com.next.reservations.core.service.ReservationTimeManagingService
import com.next.reservations.core.utils.Utils
import com.next.reservations.web.request.ReservationTimeConfigurationRequest
import com.next.reservations.web.response.ReservationTimeConfigResponse
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ReservationTimeConfigMapper(private val reservationTimeConfigurationService: ReservationTimeConfigurationService,
                                  private val reservationManagingService: ReservationManagingService,
                                  private val reservationTimeManagingService: ReservationTimeManagingService) {


    fun createOrUpdateConfiguration(reservationTimeConfigurationRequest: ReservationTimeConfigurationRequest): Long {

        val date =
                if (reservationTimeConfigurationRequest.defaultStartDate == null)
                    null
                else {
                    Utils.parseDateFromString(reservationTimeConfigurationRequest.defaultStartDate)
                }

        return reservationTimeConfigurationService.createOrUpdate(reservationTimeConfigurationRequest.id,
                reservationTimeConfigurationRequest.name, date).id
    }


    fun getAllReservationTimeConfigs(): List<ReservationTimeConfigResponse> {
        return reservationTimeConfigurationService.findAll().map { convertReservationTimeConfigToResponse(it) }
    }

    fun changeDefaultConfig(newDefaultId: Long) {
        reservationManagingService.changeDefaultConfig(newDefaultId)
    }

    fun delete(id: Long) {
        reservationTimeManagingService.deleteReservationTimeConfig(id)
    }

    fun changeName(id: Long, name: String) =
            reservationTimeConfigurationService.changeName(id, name)

    private fun convertReservationTimeConfigToResponse(reservationTImeConfiguration: ReservationTimeConfiguration): ReservationTimeConfigResponse =
            ReservationTimeConfigResponse(
                    id = reservationTImeConfiguration.id,
                    name = reservationTImeConfiguration.name,
                    default = reservationTImeConfiguration.defaultConfig,
                    defaultStartDate = reservationTImeConfiguration.defaultStartDate?.toString()
            )

    fun addNew(name: String): Long {
        return reservationTimeConfigurationService.addNew(name).id
    }

    fun changeFutureDefault(id: Long, futureDefault: String) {
        val date = Utils.parseDateFromString(futureDefault)

        reservationManagingService.setNewFutureDefault(id, date)
    }
}

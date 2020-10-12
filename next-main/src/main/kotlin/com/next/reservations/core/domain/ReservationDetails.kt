package com.next.reservations.core.domain

import com.next.shared.domain.BaseEntity
import javax.persistence.*

@Entity
@Table(schema = "reservations", name = "reservation_details")
class ReservationDetails(
        @Column(name = "full_name")
        var fullName: String,

        @Column(name = "email")
        var email: String,

        @Column(name = "phone_number")
        var phoneNumber: String,

        @Column(name = "comment")
        var comment: String?,

        @Column(name = "number_of_players")
        var numberOfPlayers: Int
): BaseEntity()

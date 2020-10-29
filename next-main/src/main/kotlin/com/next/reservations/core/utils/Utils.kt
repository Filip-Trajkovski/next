package com.next.reservations.core.utils

import org.springframework.stereotype.Service
import java.time.LocalDate

object Utils {
    fun parseDateFromString(date: String): LocalDate {
        validateDate(date)

        val parts = date.split("-").map { it.toInt() }

        val parsedDate = LocalDate.of(parts[2], parts[1], parts[0])

        return parsedDate
    }

    fun validateDate(date: String){
        val parts = date.split("-").map { it.toInt() }
        if(parts.size != 3)
            throw RuntimeException("Invalid date")

        if(parts[0]<1 || parts[0]>31)
            throw RuntimeException("Invalid date")

        if(parts[1]<1 || parts[1]>12)
            throw RuntimeException("Invalid date")

        if(parts[2]<2020 || parts[2]>2025)
            throw RuntimeException("Invalid date")
    }
}

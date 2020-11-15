package com.next.reservations.core.service

import com.next.reservations.core.domain.Reservation
import com.next.reservations.core.event.ReservationEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart


@Service
class MailService {

    val username = "escaperoom-next@outlook.com"
    val password = "Mztskopje97#"

    private fun getHostContent(reservation: Reservation): String {
        return "Имате нова резервација на име ${reservation.reservationDetails.fullName}, телефонски број ${reservation.reservationDetails.phoneNumber}, имејл ${reservation.reservationDetails.email}, за терминот ${reservation.getCurrentReservationTimeDate()}!"
    }

    private fun getGuestContent(reservation: Reservation): String {
        return "Вашата резервација на име ${reservation.reservationDetails.fullName}, во терминот ${reservation.getCurrentReservationTimeDate()} е испратена."
    }

    private fun getProperties(): Properties {
        val prop: Properties = Properties()
        prop["mail.smtp.auth"] = true
        prop["mail.smtp.starttls.enable"] = "true"
        prop["mail.smtp.host"] = "smtp-mail.outlook.com"
        prop["mail.smtp.port"] = "587"
        prop["mail.smtp.ssl.trust"] = "smtp-mail.outlook.com"

        return prop
    }

    private fun getSession(): Session {
        val prop = getProperties()
        return Session.getInstance(prop, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(username, password)
            }
        })
    }

    @Async
    @EventListener
    fun sendReservationMails(reservationEvent: ReservationEvent) {
        sendToHost(reservationEvent.reservation)
        sendToGuest(reservationEvent.reservation)
    }

    private fun sendToHost(reservation: Reservation) {
        val mimeMessage = MimeMessage(getSession())
        mimeMessage.setFrom(InternetAddress(username))
        mimeMessage.setRecipients(Message.RecipientType.TO, username)
        mimeMessage.setSubject("Нова резервација!", "UTF-8")

        val mimeBodyPart = MimeBodyPart()
        mimeBodyPart.setContent(getHostContent(reservation), "text/plain; charset=utf-16")

        val multipart: Multipart = MimeMultipart()
        multipart.addBodyPart(mimeBodyPart)

        mimeMessage.setContent(multipart)

        Transport.send(mimeMessage)
    }

    private fun sendToGuest(reservation: Reservation) {
        val mimeMessage = MimeMessage(getSession())
        mimeMessage.setFrom(InternetAddress(username))
        mimeMessage.setRecipients(Message.RecipientType.TO, reservation.reservationDetails.email)
        mimeMessage.setSubject("Резервација во escape room Next!", "UTF-8")

        val mimeBodyPart = MimeBodyPart()
        mimeBodyPart.setContent(getGuestContent(reservation), "text/plain; charset=utf-16")

        val multipart: Multipart = MimeMultipart()
        multipart.addBodyPart(mimeBodyPart)

        mimeMessage.setContent(multipart)

        Transport.send(mimeMessage)
    }
}

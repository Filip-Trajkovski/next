package com.next.shared.domain

import java.io.Serializable
import java.util.*
import javax.persistence.*


@MappedSuperclass
open class BaseEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        val id: Long = 0
) {
    override fun equals(other: Any?): Boolean {
        if (other !is BaseEntity) {
            return false
        }
        return id == other.id
    }

    override fun hashCode(): Int {
        return Objects.hashCode(id)
    }

    override fun toString(): String {
        return String.format("{id=%s, type=%s}", id, javaClass.name)
    }
}

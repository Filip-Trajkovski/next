package com.next.main

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EntityScan(basePackages = ["com.next.*"])
@SpringBootApplication(scanBasePackages = ["com.next.*"])
@EnableJpaRepositories(basePackages = ["com.next.*"])
class NextMain
    fun main(args: Array<String>) {
        runApplication<NextMain>(*args)
    }


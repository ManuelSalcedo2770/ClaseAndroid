package com.example.superheroes.models

data class User(val email: String, val password: String) {
    object Users {
        val validUsers = listOf(
            User("user1@example.com", "password123"),
            User("user2@example.com", "securepass")
            // Agrega más usuarios aquí
        )
    }
}
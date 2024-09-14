package com.example.superheroes.models

data class User(val name:String,val email:String,val password: String){
    val computedName :String get() = "$name, welcome back"
    companion object{
        val users = listOf(
            User("Manu","manuelsalcedo2770@gmail.com","12345"),
            User("Ale","aled@gmail.com","12345"),
            User("Ronaldo","ronaldo@gmail.com","12345")
        )
    }
}
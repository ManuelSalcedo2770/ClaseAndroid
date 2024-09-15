package com.example.superheroes.models


data class Publisher(val id: Int,val name:String,val image:String){
    companion object{
        val publishers = mutableListOf<Publisher>(
            Publisher(1,"Marvel","https://1000marcas.net/wp-content/uploads/2021/07/Marvel-Comics-logo.png"),
            Publisher(2,"DC","https://seeklogo.com/images/D/dc-comics-logo-8721593E89-seeklogo.com.png")
        )
    }
}
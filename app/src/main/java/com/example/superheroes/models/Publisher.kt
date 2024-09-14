package com.example.superheroes.models


data class Publisher(val id: Int,val name:String,val image:String){
    companion object{
        val publishers = mutableListOf<Publisher>(
            Publisher(1,"Marvel","https://1000marcas.net/wp-content/uploads/2021/07/Marvel-Comics-logo.png"),
            Publisher(2,"DC","https://seeklogo.com/images/D/dc-comics-logo-8721593E89-seeklogo.com.png"),
            Publisher(3,"Dark Horse","https://1000logos.net/wp-content/uploads/2020/09/Dark-Horse-Comics-Logo-1991.png"),
            Publisher(4,"IDW","https://icons.iconarchive.com/icons/dominicanjoker/comic-publisher-folder/512/IDW-icon.png"),
            Publisher(5,"Top Cow","https://topcow.com/wp-content/uploads/2023/10/Logo-TCP.png"),
        )
    }
}
package com.masai.labourapp

class User {
    var name:String?= null
    var email: String?=null
    var uid:String?= null
    var location:String?=null
    var occupation:String?=null
    var charges:String?=null


    constructor(){}

    constructor(name:String?,email:String?,uid:String?,location:String?,occupation:String?,charges:String?){
        this.name = name
        this.email = email
        this.uid = uid
        this.location =location
        this.occupation=occupation
        this.charges=charges


    }
}
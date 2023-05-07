package com.karimov03.codialapp.Class

class Mentor {
    var id:Int?=null
    var name:String?=null
    var father:String?=null
    var kursi:String?=null

    constructor(id: Int?, name: String?, father: String?, kursi: String?) {
        this.id = id
        this.name = name
        this.father = father
        this.kursi = kursi
    }

    constructor(name: String?, father: String?, kursi: String?) {
        this.name = name
        this.father = father
        this.kursi = kursi
    }

}
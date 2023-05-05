package com.karimov03.codialapp.DataBase

import com.karimov03.codialapp.Class.Kurslar

interface DdInterface {

    fun AddKurs(kurslar: Kurslar)
    fun getAllKurs():List<Kurslar>


}
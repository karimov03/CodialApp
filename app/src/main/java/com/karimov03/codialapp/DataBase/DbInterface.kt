package com.karimov03.codialapp.DataBase

import com.karimov03.codialapp.Class.Kurslar
import com.karimov03.codialapp.Class.Mentor

interface DbInterface {

    fun AddKurs(kurslar: Kurslar)
    fun getAllKurs():List<Kurslar>



    fun addMentor(mentor: Mentor)
    fun editMentor(id: Int,mentor: Mentor)
    fun deleteMentor(id: Int)
    fun getAllMentor(string: String):List<Mentor>


}
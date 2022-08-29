package com.amo.prep1.utils

interface Shape {

    fun calcArea() : Double
    fun calcVolume() : Double

}

class Circle( val radius : Double) : Shape{

    override fun calcArea(): Double {
        return radius
    }

    override fun calcVolume(): Double {
        return radius * radius
    }

}

class Traingle(private val b : Double , private  val h : Double) : Shape{

    override fun calcArea(): Double {
        return ( b * h ) / 2
    }

    override fun calcVolume(): Double {
       return b * h
    }

}

fun mainFun(){
    val circle = Circle(2.2)
    val traingle = Traingle( 2.0, 22.2)
    println(circle.calcArea())
    println(traingle.calcArea())

    val arr = arrayOf(circle ,traingle)
    arr.forEach {
        it.calcArea()
    }

}
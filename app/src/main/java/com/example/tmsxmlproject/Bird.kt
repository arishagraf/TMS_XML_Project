package com.example.tmsxmlproject

abstract class Bird {
    abstract fun makeVoice()
    abstract fun makeEggs()
}

interface BirdAction {
    fun fly()
}

class Dove : Bird(), BirdAction {

    override fun fly() {
        println("i can fly")
    }

    override fun makeVoice() {
        println("can make a voice")
    }

    override fun makeEggs() {
        println("can make eggs")
    }
}

class Ostrich : Bird() {

    override fun makeVoice() {
        println("can make a voice")
    }

    override fun makeEggs() {
        println("can make eggs")
    }
}

//fun main() {
//    letsFly(Dove())
//}

private fun letsFly(bird: BirdAction) {
    bird.fly()
}


interface Employee {
    fun eat()
    fun takeCoffeeBreak()
    fun createReport()
    fun work()
    fun sleep()
    fun lie()
    fun writeCode()
}

class EmployeeImpl: Employee {

    override fun eat() {
        TODO("Not yet implemented")
    }

    override fun takeCoffeeBreak() {
        TODO("Not yet implemented")
    }

    override fun createReport() {
        // сделать отчет по работе сотрудника
    }

    override fun work() {
        // сколько времени он работает
    }

    override fun sleep() {
        TODO("Not yet implemented")
    }

    override fun lie() {
        TODO("Not yet implemented")
    }

    override fun writeCode() {
        // сколько строк кода он написал
    }

}

class Human: Employee {
    override fun eat() {
     ///
    }

    override fun takeCoffeeBreak() {
  ///
    }

    override fun createReport() {
        TODO("Not yet implemented")
    }

    override fun work() {
        TODO("Not yet implemented")
    }

    override fun sleep() {
      ///
    }

    override fun lie() {
  ///
    }

    override fun writeCode() {
        TODO("Not yet implemented")
    }
}

fun main() {
    handleEmployee(EmployeeImpl())
}

fun handleEmployee(employee: Employee){
    employee.takeCoffeeBreak()
}
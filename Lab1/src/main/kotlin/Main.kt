package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val a = FractionMutable(1,2,-1)
    a.add(FractionMutable(1, 3))
    println(a)
    a.mult(FractionMutable(5,2, -1))
    println(a)
    a.div(FractionMutable(2, 1))
    println(a)

}
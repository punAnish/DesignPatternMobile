package org.example

fun main() {
    val a = Fraction(1, 2, -1)
    println(a)  // -1/2
    println(a + Fraction(1, 3))  // -1/6
    println(a * Fraction(5, 2, -1))  // 5/4
    println(a / Fraction(2, 1))  // 1/4
    println(-Fraction(1, 6) + Fraction(1, 2))  // 1/3
    println(Fraction(2, 3) * Fraction(3, 2))  // 1/1
    println(Fraction(1, 2) > Fraction(2, 3))  // false
}
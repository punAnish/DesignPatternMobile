package org.example

import kotlin.math.sign

class Fraction(
    private val numerator: Int,
    private val denominator: Int,
    private val sign: Int = 1
) : Comparable<Fraction> {

    init {
        require(denominator != 0) { "Denominator cannot be zero." }
    }

    // GCD to reduce fractions
    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }

    // Return reduced numerator/denominator
    private fun reduce(): Fraction {
        val gcd = gcd(Math.abs(numerator), Math.abs(denominator))
        return Fraction(
            numerator / gcd,
            denominator / gcd,
            if (numerator * denominator >= 0) 1 else -1
        )
    }

    override fun toString(): String {
        val absNumerator = Math.abs(numerator)
        return if (sign == -1 && absNumerator != 0) "-$absNumerator/$denominator" else "$absNumerator/$denominator"
    }

    // Addition operator
    operator fun plus(other: Fraction): Fraction {
        val lcm = lcm(denominator, other.denominator)
        val num1 = numerator * (lcm / denominator) * sign
        val num2 = other.numerator * (lcm / other.denominator) * other.sign
        return Fraction(num1 + num2, lcm).reduce()
    }

    // Subtraction operator
    operator fun minus(other: Fraction): Fraction {
        return this + -other
    }

    // Multiplication operator
    operator fun times(other: Fraction): Fraction {
        return Fraction(numerator * other.numerator, denominator * other.denominator, sign * other.sign).reduce()
    }

    // Division operator
    operator fun div(other: Fraction): Fraction {
        return Fraction(numerator * other.denominator, denominator * other.numerator, sign * other.sign).reduce()
    }

    // Unary minus operator
    operator fun unaryMinus(): Fraction {
        return Fraction(numerator, denominator, -sign)
    }

    // Comparable implementation
    override fun compareTo(other: Fraction): Int {
        val diff = (this - other).numerator
        return diff.sign
    }

    // Helper: Least Common Multiple
    private fun lcm(a: Int, b: Int): Int {
        return (a * b) / gcd(a, b)
    }

    // Equals and hashCode to compare fractions
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Fraction) return false
        return this.compareTo(other) == 0
    }

    override fun hashCode(): Int {
        return numerator.hashCode() * 31 + denominator.hashCode()
    }
}

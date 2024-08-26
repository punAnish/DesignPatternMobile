package org.example

class FractionMutable(numerator: Int, denominator: Int, sign: Int = 1) {
    private var numerator = Math.abs(numerator)
    private var denominator = Math.abs(denominator)
    private var sign = if (sign >= 0) 1 else -1

    init {
        require(denominator != 0) { "Denominator cannot be zero." }
        reduce()
    }

    // Simplifies the fraction
    private fun reduce() {
        val gcd = gcd(numerator, denominator)
        numerator /= gcd
        denominator /= gcd
    }

    // Greatest common divisor
    private fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

    // Negates the fraction
    fun negate() {
        sign *= -1
    }

    // Adds another fraction to this fraction
    fun add(other: FractionMutable) {
        val commonDenominator = denominator * other.denominator
        val num1 = numerator * other.denominator * sign
        val num2 = other.numerator * denominator * other.sign
        numerator = Math.abs(num1 + num2)
        denominator = commonDenominator
        sign = if (num1 + num2 >= 0) 1 else -1
        reduce()
    }

    // Multiplies this fraction by another fraction
    fun mult(other: FractionMutable) {
        numerator *= other.numerator
        denominator *= other.denominator
        sign *= other.sign
        reduce()
    }

    // Divides this fraction by another fraction
    fun div(other: FractionMutable) {
        numerator *= other.denominator
        denominator *= other.numerator
        sign *= other.sign
        reduce()
    }

    // Returns the integer part of the fraction
    fun intPart(): Int {
        return (numerator / denominator) * sign
    }

    // Returns the string representation of the fraction
    override fun toString(): String {
        return "${if (sign == -1 && numerator != 0) "-" else ""}$numerator/$denominator"
    }
}

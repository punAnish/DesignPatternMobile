package org.example

class Fraction(
    numerator: Int,
    denominator: Int,
    sign: Int = 1
) : Comparable<Fraction> {

    private val num: Int
    private val den: Int

    init {
        require(denominator != 0) { "Denominator cannot be zero." }

        // Determine the overall sign of the fraction
        val overallSign = if (numerator * denominator * sign < 0) -1 else 1

        // Reduce the fraction
        val gcd = gcd(Math.abs(numerator), Math.abs(denominator))

        num = overallSign * Math.abs(numerator) / gcd
        den = Math.abs(denominator) / gcd
    }

    // GCD function to reduce the fraction
    private fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

    // LCM function to use in arithmetic operations
    private fun lcm(a: Int, b: Int): Int = (a * b) / gcd(a, b)

    override fun toString(): String {
        return if (num == 0) "0" else "$num/$den"
    }

    // Unary minus
    operator fun unaryMinus(): Fraction = Fraction(num, den, -1)

    // Addition
    operator fun plus(other: Fraction): Fraction {
        val lcm = lcm(den, other.den)
        val num1 = num * (lcm / den)
        val num2 = other.num * (lcm / other.den)
        return Fraction(num1 + num2, lcm)
    }

    // Subtraction
    operator fun minus(other: Fraction): Fraction = this + -other

    // Multiplication
    operator fun times(other: Fraction): Fraction {
        return Fraction(num * other.num, den * other.den)
    }

    // Division
    operator fun div(other: Fraction): Fraction {
        return Fraction(num * other.den, den * other.num)
    }

    // Negate the fraction
    fun negate(): Fraction = -this

    // **Add method for compatibility with the test**
    fun add(other: Fraction): Fraction = this + other

    // Compare two fractions
    override fun compareTo(other: Fraction): Int {
        val lcm = lcm(den, other.den)
        val num1 = num * (lcm / den)
        val num2 = other.num * (lcm / other.den)
        return num1.compareTo(num2)
    }

    // Equality check
    override fun equals(other: Any?): Boolean {
        return other is Fraction && this.compareTo(other) == 0
    }

    // Hash code implementation
    override fun hashCode(): Int {
        return num.hashCode() * 31 + den.hashCode()
    }
}







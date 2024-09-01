package org.example

class Lotto(private val lottoRange: IntRange = 1..40, private val n: Int = 7) {

    private fun pickNDistinct(range: IntRange, n: Int): List<Int>? {
        if (n > range.count()) return null
        return range.shuffled().take(n)
    }

    private fun numDistinct(list: List<Int>): Int {
        return list.toSet().size
    }

    private fun numCommon(list1: List<Int>, list2: List<Int>): Int {
        return list1.intersect(list2.toSet()).size
    }

    private fun isLegalLottoGuess(guess: List<Int>, range: IntRange = lottoRange, count: Int = n): Boolean {
        return guess.size == count && guess.all { it in range } && numDistinct(guess) == count
    }

    private fun checkGuess(guess: List<Int>, secret: List<Int>): Int {
        return if (isLegalLottoGuess(guess)) numCommon(guess, secret) else 0
    }

    private fun readNDistinct(low: Int, high: Int, n: Int): List<Int> {
        while (true) {
            println("Give $n numbers from $low to $high, separated by commas:")
            val input = readLine()
            val numbers = input?.split(",")?.mapNotNull { it.trim().toIntOrNull() } ?: listOf()

            if (isLegalLottoGuess(numbers, low..high, n)) {
                return numbers
            }
        }
    }

    fun playLotto() {
        val secretNumbers = pickNDistinct(lottoRange, n) ?: return

        while (true) {
            val guess = readNDistinct(lottoRange.first, lottoRange.last, n)
            val correctCount = checkGuess(guess, secretNumbers)
            println("lotto numbers: $secretNumbers, you got $correctCount correct")

            val (steps, computerGuess) = findLotto(secretNumbers)
            println("computer guess in $steps steps is $computerGuess")

            print("More? (Y/N): ")
            val continuePlaying = readLine()?.trim()?.uppercase() == "Y"
            if (!continuePlaying) break
        }
    }

    private fun findLotto(secretNumbers: List<Int>): Pair<Int, List<Int>> {
        var steps = 0
        val previousGuesses = mutableSetOf<List<Int>>()
        var guess: List<Int>
        var correctCount: Int

        do {
            steps++
            do {
                guess = pickNDistinct(lottoRange, n)!!
            } while (previousGuesses.contains(guess))
            previousGuesses.add(guess)
            correctCount = checkGuess(guess, secretNumbers)
        } while (correctCount != n)

        return Pair(steps, guess)
    }
}



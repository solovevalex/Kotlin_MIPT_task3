package org.example

class Matrix(val rows: List<List<Double>>) {
    operator fun times(other: Matrix): Matrix {
        if (this.rows[0].size != other.rows.size) {
            throw IllegalArgumentException("Число столбцов первой матрицы должно быть равно числу строк второй матрицы.")
        }

        val result = mutableListOf<List<Double>>()

        for (i in this.rows.indices) {
            val rowResult = mutableListOf<Double>()

            for (j in other.rows[0].indices) {
                var sum = 0.0

                for (k in this.rows[0].indices) {
                    sum += this.rows[i][k] * other.rows[k][j]
                }

                rowResult.add(sum)
            }

            result.add(rowResult)
        }

        return Matrix(result)
    }
}

fun main(args: Array<String>) {
    if (args.size != 2) {
        println("Ошибка: Необходимо передать две матрицы.")
        return
    }

    val matrix1 = parseMatrix(args[0])
    val matrix2 = parseMatrix(args[1])

    if (matrix1 == null || matrix2 == null) {
        println("Ошибка: Неверный формат матрицы. Используйте пробелы для разделения чисел в строке и ';' для разделения строк.")
        return
    }

    val result = matrix1 * matrix2
    printMatrix(result)
}

fun parseMatrix(input: String): Matrix? {
    val rows = input.split(";")
    val matrix = mutableListOf<List<Double>>()

    for (row in rows) {
        val numbers = row.split(" ")
        val numberList = mutableListOf<Double>()

        for (number in numbers) {
            val num = number.toDoubleOrNull()
            if (num == null) {
                return null
            } else {
                numberList.add(num)
            }
        }

        if (matrix.isNotEmpty() && matrix[0].size != numberList.size) {
            return null
        }

        matrix.add(numberList)
    }

    return Matrix(matrix)
}

fun printMatrix(matrix: Matrix) {
    for (row in matrix.rows) {
        println(row.joinToString(" "))
    }
}

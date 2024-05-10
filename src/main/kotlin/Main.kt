package org.example

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

    if (matrix1[0].size != matrix2.size) {
        println("Ошибка: Число столбцов первой матрицы должно быть равно числу строк второй матрицы.")
        return
    }

    val result = multiplyMatrices(matrix1, matrix2)
    printMatrix(result)
}

fun parseMatrix(input: String): List<List<Int>>? {
    val rows = input.split(";")
    val matrix = mutableListOf<List<Int>>()

    for (row in rows) {
        val numbers = row.split(" ")
        val numberList = mutableListOf<Int>()

        for (number in numbers) {
            val num = number.toIntOrNull()
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

    return matrix
}

fun multiplyMatrices(matrix1: List<List<Int>>, matrix2: List<List<Int>>): List<List<Int>> {
    val result = mutableListOf<List<Int>>()

    for (i in matrix1.indices) {
        val rowResult = mutableListOf<Int>()

        for (j in matrix2[0].indices) {
            var sum = 0

            for (k in matrix1[0].indices) {
                sum += matrix1[i][k] * matrix2[k][j]
            }

            rowResult.add(sum)
        }

        result.add(rowResult)
    }

    return result
}

fun printMatrix(matrix: List<List<Int>>) {
    for (row in matrix) {
        println(row.joinToString(" "))
    }
}

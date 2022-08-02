package ru.netology

const val RANGE_OF_MINUTES_AGO = 60
const val RANGE_OF_HOURS_AGO = 24
const val RANGE_OF_DAYS_AGO = 200_000
const val SECONDS_IN_MINUTE = 60
const val SECONDS_IN_HOUR = 3_600
const val SECONDS_IN_DAY = 86_400

fun main() {
    val timeAgo = timeAgoGenerator()
    for (time in timeAgo)
        println("Пользователь Снусмумрик был(а) в сети ${timeAgoToText(time)} [$time sec]")
}

fun timeAgoGenerator(): List<Int> {
    val randomTime = mutableListOf<Int>()
    for (i in 0 until 5) {
        randomTime.add((Math.random() * RANGE_OF_MINUTES_AGO).toInt() * SECONDS_IN_MINUTE)
        randomTime.add((Math.random() * RANGE_OF_HOURS_AGO).toInt() * SECONDS_IN_HOUR)
        randomTime.add((Math.random() * RANGE_OF_DAYS_AGO).toInt() + SECONDS_IN_DAY)
    }
    return randomTime
}

fun timeAgoToText(timeAgo: Int): String {
    val minutes = listOf("минуту", "минуты", "минут")
    val hours = listOf("час", "часа", "часов")
    return when (timeAgo) {
        in 0..60 -> "только что"
        in 61..3600 -> "${typeTimeAgo(timeAgo, minutes)} назад"
        in 3601..86400 -> "${typeTimeAgo(timeAgo, hours)} назад"
        in 86401..172800 -> "сегодня"
        in 172801..259200 -> "вчера"
        else -> "давно"
    }
}

fun typeTimeAgo(timeAgo: Int, timeType: List<String>): String {
    val showTime = (timeAgo / (
            if (timeType[0].substring(0, 1) == "м")
                SECONDS_IN_MINUTE
            else
                SECONDS_IN_HOUR
            )).toString()
    val single = showTime.substring(showTime.length - 1).toInt()
    val pair = if (showTime.length > 1)
                   showTime.substring(showTime.length - 2).toInt()
               else 0
    return when {
               (single == 1 &&
                pair != 11) -> "$showTime ${timeType[0]}"
               (single in 2..4 &&
                pair !in 12..14) -> "$showTime ${timeType[1]}"
                else -> "$showTime ${timeType[2]}"
    }
}
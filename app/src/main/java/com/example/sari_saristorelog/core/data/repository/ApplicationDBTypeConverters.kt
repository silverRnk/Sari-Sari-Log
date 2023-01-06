package com.example.sari_saristorelog.core.data.repository

import androidx.room.TypeConverter
import com.example.sari_saristorelog.core.util.TransactionColors
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

class ApplicationDBTypeConverters {
    @TypeConverter
    fun fromLongDateToLocalDateTime(date: Long?): LocalDateTime?{
        return if (date == null) null else LocalDateTime.ofInstant(Instant.ofEpochSecond(date), ZoneId.of("UTC"))
    }

    @TypeConverter
    fun fromLocalDateTimeToLongDate(date: LocalDateTime?): Long?{
        return date?.toEpochSecond(ZoneOffset.UTC)
    }

    @TypeConverter
    fun fromTransactionColorToInt(color: TransactionColors): Int{
        return when (color) {
            TransactionColors.Blue -> {
                0
            }
            TransactionColors.Green -> {
                1
            }
            TransactionColors.Yellow -> {
                2
            }
            TransactionColors.Orange -> {
                3
            }
        }
    }

    @TypeConverter
    fun fromIntToTransactionColor(color: Int): TransactionColors{
        return when(color){
            0 -> {
                TransactionColors.Blue
            }
            1 -> {
                TransactionColors.Green
            }
            2 -> {
                TransactionColors.Yellow
            }
            else -> {
                TransactionColors.Orange
            }
        }
    }
}
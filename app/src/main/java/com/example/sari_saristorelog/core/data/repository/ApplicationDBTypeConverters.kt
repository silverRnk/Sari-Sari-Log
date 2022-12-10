package com.example.sari_saristorelog.core.data.repository

import androidx.room.TypeConverter
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
}
package com.ifinancas.db.models.TransactionModel

import androidx.room.TypeConverter
import com.ifinancas.data.enums.Category
import com.ifinancas.data.enums.Tags
import java.util.Date

class Converters {
    // Para a Category enum
    @TypeConverter
    fun fromCategory(category: Category): String {
        return category.name
    }

    @TypeConverter
    fun toCategory(category: String): Category {
        return Category.valueOf(category)
    }

    // Para a Tags enum
    @TypeConverter
    fun fromTag(tag: Tags): String {
        return tag.name
    }

    @TypeConverter
    fun toTag(tag: String): Tags {
        return Tags.valueOf(tag)
    }

    // Para Date
    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDate(timestamp: Long): Date {
        return Date(timestamp)
    }
}

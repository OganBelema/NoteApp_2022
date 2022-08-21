package com.oganbelema.hellocomposev.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.*

@Entity
data class Note(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    @ColumnInfo(name = "entry_name")
    val entryDate: Date = Date.from(Instant.now())
)

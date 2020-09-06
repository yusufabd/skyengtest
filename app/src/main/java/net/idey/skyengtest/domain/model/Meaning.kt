package net.idey.skyengtest.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Meaning(
    val translation: String,
    val transcription: String,
    val imageUrl: String
) : Parcelable
package net.idey.skyengtest.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
    val text: String,
    val meanings: List<Meaning>
) : Parcelable
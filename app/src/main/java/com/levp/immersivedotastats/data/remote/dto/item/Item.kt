package com.levp.immersivedotastats.data.remote.dto.item

import com.google.gson.annotations.SerializedName

data class Item(
    val id: Int,
    @SerializedName("dname")
    val name: String,
    @SerializedName("img")
    val image: String,
    val cost: Int,
    @SerializedName("hint")
    val description: List<String>,
    @SerializedName("attrib")
    val attributes: List<ItemAttribute>
) {
    companion object {
        fun getEmpty() = Item(
            0,
            "",
            "",
            0,
            emptyList(),
            emptyList()
        )
    }
}

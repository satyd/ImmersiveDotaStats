package com.levp.immersivedotastats.data.remote.dto.item

data class ItemTmp(
    val attrib: List<Any>,
    val cd: Int,
    val charges: Boolean,
    val components: Any,
    val cost: Int,
    val created: Boolean,
    val dname: String,
    val hint: List<String>,
    val id: Int,
    val img: String,
    val lore: String,
    val mc: Boolean,
    val notes: String,
    val qual: String
)
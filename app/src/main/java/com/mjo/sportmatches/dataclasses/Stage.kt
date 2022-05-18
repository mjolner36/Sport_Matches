package com.mjo.sportmatches.dataclasses

data class Stage(
    val Ccd: String,
    val Ccdiso: String,
    val Chi: Int,
    val Cid: String,
    val Cnm: String,
    val Csnm: String,
    val Events: List<Event>,
    val Scd: String,
    val Scu: Int,
    val Sdn: String,
    val Sds: String,
    val Shi: Int,
    val Sid: String,
    val Snm: String
)
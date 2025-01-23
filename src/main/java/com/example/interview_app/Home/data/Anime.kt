package com.example.interview_app.Home.data

data class AnimeDetailsResponse(
    val data: AnimeData
)

data class AnimeData(
    val mal_id: Int,
    val url: String,
    val images: Images,
    val trailer: Trailer,
    val title: String,
    val title_english: String,
    val title_japanese: String,
    val type: String,
    val episodes: Int,
    val status: String,
    val synopsis: String,
    val score: Double,
    val rank: Int,
    val popularity: Int,
    val members: Int,
    val favorites: Int,
    val genres: List<Genre>,
    val themes: List<Theme>,
    val aired: Aired,
    val producers: List<Producer>,
    val licensors: List<Producer>,
    val studios: List<Producer>
)

data class Images(
    val jpg: ImageUrls,
    val webp: ImageUrls
)


data class Theme(
    val mal_id: Int,
    val name: String,
    val url: String
)

data class Aired(
    val from: String,
    val to: String,
    val string: String
)

data class Producer(
    val mal_id: Int,
    val name: String,
    val url: String
)

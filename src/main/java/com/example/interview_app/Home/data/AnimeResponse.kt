package com.example.interview_app.Home.data

data class AnimeResponse(
    val pagination: Pagination,
    val data: List<Anime>
)

data class Pagination(
    val last_visible_page: Int,
    val has_next_page: Boolean,
    val current_page: Int,
    val items: Items
)

data class Items(
    val count: Int,
    val total: Int,
    val per_page: Int
)

data class Anime(
    val mal_id: Int,
    val title: String,
    val episodes: Int?,
    val score: Double?,
    val synopsis: String?,
    val images: AnimeImages,
    val trailer: Trailer?,
    val genres: List<Genre>
)

data class AnimeImages(
    val jpg: ImageUrls,
    val webp: ImageUrls
)

data class ImageUrls(
    val image_url: String,
    val small_image_url: String,
    val large_image_url: String
)

data class Trailer(
    val youtube_id: String?,
    val url: String?,
    val embed_url: String?
)

data class Genre(
    val mal_id: Int,
    val name: String,
    val action:String,
    val url:String
)

data class AnimeDetailResponse(
    val mal_id: Int,
    val title: String,
    val synopsis: String?,
    val episodes: Int?,
    val score: Double?,
    val genres: List<Genre>,
    val trailer: Trailer?,
    val images: AnimeImages
)

package com.example.instagramfeedsui

data class Post(
    val userName: String,
    val profilePictureResId: Int,
    val postImageResId: Int,
    val likes: Int,
    val caption: String
)

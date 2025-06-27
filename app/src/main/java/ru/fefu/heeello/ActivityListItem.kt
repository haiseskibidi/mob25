package ru.fefu.heeello

sealed interface ActivityListItem {
    data class Activity(
        val id: String,
        val distance: String,
        val duration: String,
        val activityType: String,
        val timestamp: String,
        val userTag: String? = null // Для вкладки "Пользователи"
    ) : ActivityListItem

    data class DateSeparator(
        val date: String
    ) : ActivityListItem
} 
package com.vkp.utilitylibrary.datetime

import android.content.Context
import android.icu.util.TimeUnit
import com.vkp.utilitylibrary.datetime.TimeAgoUtils
import android.text.format.DateUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.Throws

object TimeAgoUtils {
    private const val SECOND_MILLIS = 1000L
    private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
    private const val DAY_MILLIS = 24 * HOUR_MILLIS
    private const val WEEKS_MILLIS = 7 * DAY_MILLIS
    private const val MONTHS_MILLIS = 4 * WEEKS_MILLIS
    private const val YEARS_MILLIS = 12 * MONTHS_MILLIS

    fun getTimeAgo(timeInLong: Long): String? {
        var time = timeInLong
        val now = System.currentTimeMillis()
        if (time < 1000000000000L) {
            time *= 1000
        }
        if (time > now || time <= 0) {
            return null
        }
        val diff = now - time
        return if (diff < MINUTE_MILLIS) {
            "just now"
        } else if (diff < 2 * MINUTE_MILLIS) {
            "a minute ago"
        } else if (diff < 50 * MINUTE_MILLIS) {
            "${diff / MINUTE_MILLIS} minutes ago"
        } else if (diff < 90 * MINUTE_MILLIS) {
            "an hour ago"
        } else if (diff < 24 * HOUR_MILLIS) {
            "${diff / HOUR_MILLIS} hours ago"
        } else if (diff < 7 * DAY_MILLIS) {
            if ((diff / DAY_MILLIS).toString() == "1") {
                "1 day ago"
            } else {
                "${diff / DAY_MILLIS} days ago"
            }
        } else if (diff < 4 * DateUtils.WEEK_IN_MILLIS) {
            if ((diff / DateUtils.WEEK_IN_MILLIS).toString() == "1") {
                "1 week ago"
            } else {
                "${diff / DateUtils.WEEK_IN_MILLIS} week ago"
            }
        } else {
            "More than a month ago"
        }
    }

    @Throws(ParseException::class)
    fun getTimeAgo(context: Context, timeString: String?, simpleDateFormat: String?): String? {
        val now = System.currentTimeMillis()
        var time = timestampToMilli(context, timeString, simpleDateFormat)
        if (time < 1000000000000L) {
            time *= 1000
        }

        if (time > now || time <= 0) {
            return null
        }
        val diff = now - time
        return if (diff < MINUTE_MILLIS) {
            "just now"
        } else if (diff < 2 * MINUTE_MILLIS) {
            "1 min ago"
        } else if (diff < 50 * MINUTE_MILLIS) {
            "${diff / MINUTE_MILLIS} min ago"
        } else if (diff < 90 * MINUTE_MILLIS) {
            "1 hr ago"
        } else if (diff < 24 * HOUR_MILLIS) {
            "${diff / HOUR_MILLIS} hrs ago"
        } else if (diff < 7 * DAY_MILLIS) {
            if ((diff / DAY_MILLIS).toString() == "1") {
                "1 day ago"
            } else {
                "${diff / DAY_MILLIS} days ago"
            }
        } else if (diff < 4 * DateUtils.WEEK_IN_MILLIS) {
            if ((diff / DateUtils.WEEK_IN_MILLIS).toString() == "1") {
                "1 week ago"
            } else {
                "${diff / DateUtils.WEEK_IN_MILLIS} weeks ago"
            }
        } else if (diff < 12 * MONTHS_MILLIS) {
            if ((diff / MONTHS_MILLIS).toString() == "1") {
                "1 month ago"
            } else {
                "${diff / MONTHS_MILLIS} months ago"
            }
        } else {
            "More than a year ago"
        }
    }

    @Throws(ParseException::class)
    fun timestampToMilli(context: Context, timestamp: String?, simpleDateFormat: String?): Long {
        val desiredFormat = SimpleDateFormat(
            simpleDateFormat,
            context.resources.configuration.locale
        )
        val date = timestamp?.let { desiredFormat.parse(it) }!!
        return date.time
    }
}
package com.vkp.utilitylibrary.datetime

import android.text.format.DateFormat
import android.text.format.DateUtils
import android.util.Log
import com.vkp.utilitylibrary.datetime.DateTimeFormat.DATETIME_FORMAT_1
import com.vkp.utilitylibrary.datetime.DateTimeFormat.TIME_FORMAT_0
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


object DateTimeUtils {

    private val LOG_TAG = "DateTimeUtils"
    private var pattern: String = DATETIME_FORMAT_1

    fun setDefaultPattern(format: String): String {
        return format

    }

    // Convert a Java Date object to String
    fun formatDate(date: Date, locale: Locale = Locale.getDefault()): String? {
        val dateFormat = SimpleDateFormat(setDefaultPattern(pattern), locale)
        return dateFormat.format(date)
    }

    // Convert a date string to Java Date Object
    fun formatDate(dateString: String, locale: Locale): Date {
        val dateFormat = SimpleDateFormat(setDefaultPattern(pattern), locale)
        return dateFormat.parse(dateString.trim { it <= ' ' }) as Date
    }

    // Convert a date string to Java Date Object

    fun formatDate(date: String): Date {
        return formatDate(date, Locale.getDefault())
    }

    // Convert a timeStamp into a date object

    @JvmOverloads
    fun formatDate(timeStamp: Long, units: DateTimeUnits = DateTimeUnits.MILLISECONDS): Date {
        return if (units == DateTimeUnits.SECONDS) Date(timeStamp * 1000L) else Date(timeStamp)
    }

    // Format date using a given pattern


    @JvmOverloads
    fun formatWithPattern(date: Date, pattern: String, locale: Locale = Locale.getDefault()): String? {
        var patternStr: String? = null
        try {
            val dateFormat = SimpleDateFormat(pattern, locale)
            patternStr = dateFormat.format(date)
        } catch (e: ParseException) {
            Log.e(LOG_TAG, "formatDate >> Fail to parse supplied date string >> $date")
            e.printStackTrace()
        }
        return patternStr
    }

    // Format date using a given pattern

    @JvmOverloads
    fun formatWithPattern(date: String, pattern: String, locale: Locale = Locale.getDefault()): String? {
        return formatWithPattern(formatDate(date), pattern, locale)
    }

    // Build a pattern for given style

    fun getPatternForStyle(style: DateTimeStyleType): String {
        val pattern: String = when (style) {
            DateTimeStyleType.LONG -> {
                "MMMM dd, yyyy"
            }
            DateTimeStyleType.MEDIUM -> {
                "MMM dd, yyyy"
            }
            DateTimeStyleType.SHORT -> {
                "MM/dd/yy"
            }
            else -> {
                "EEEE, MMMM dd, yyyy"
            }
        }
        return pattern
    }

    // Get localized date string

    fun formatWithStyle(date: Date, style: DateTimeStyleType, locale: Locale): String? {
        return formatWithPattern(date, getPatternForStyle(style), locale)
    }

    // Get localized date string (Using default locale)

    fun formatWithStyle(date: String, style: DateTimeStyleType, locale: Locale): String? {
        return formatWithStyle(formatDate(date), style, locale)
    }

    // Get localized date string (Using default locale)

    fun formatWithStyle(date: Date, style: DateTimeStyleType): String? {
        return formatWithStyle(date, style, Locale.getDefault())
    }

    // Get localized date string (Using default locale)

    fun formatWithStyle(date: String, style: DateTimeStyleType): String? {
        return formatWithStyle(date, style, Locale.getDefault())
    }

    // Extract time from date without seconds
    @JvmOverloads
    fun formatTime(date: Date, forceShowHours: Boolean = false): String {
        val dateFormat = SimpleDateFormat(TIME_FORMAT_0, Locale.getDefault())
        val time = dateFormat.format(date)
        val hhmmss = time.split(":".toRegex()).toTypedArray()
        val hours = hhmmss[0].toInt()
        val minutes = hhmmss[1].toInt()
        val seconds = hhmmss[2].toInt()
        return ((if (hours == 0 && !forceShowHours) "" else if (hours < 10) "0$hours:" else "$hours:") + (if (minutes == 0) "00" else if (minutes < 10) "0$minutes" else minutes.toString()) + ":" + if (seconds == 0) "00" else if (seconds < 10) "0$seconds" else seconds.toString())
    }

    // Extract time from date without seconds

    @JvmOverloads
    fun formatTime(date: String, forceShowHours: Boolean = false): String {
        return formatTime(formatDate(date), forceShowHours)
    }

    // Convert millis to human readable time

    fun millisToTime(millis: Long): String {
        val date = Date(millis)
        val formatter = SimpleDateFormat(TIME_FORMAT_0, Locale.getDefault())
        val formatted = formatter.format(date)
        return formatted.toString()
    }

    // Convert millis to human readable time

    fun timeToMillis(time: String): Long {
        val hhmmss = time.split(":".toRegex()).toTypedArray()
        var hours = 0
        val minutes: Int
        val seconds: Int
        if (hhmmss.size == 3) {
            hours = hhmmss[0].toInt()
            minutes = hhmmss[1].toInt()
            seconds = hhmmss[2].toInt()
        } else {
            minutes = hhmmss[0].toInt()
            seconds = hhmmss[1].toInt()
        }
        return ((hours * 60 + minutes * 60 + seconds) * 1000).toLong()
    }

    // Tell whether or not a given string represent a date time string or a simple date

    fun isDateTime(dateString: String): Boolean {
        return dateString.trim { it <= ' ' }.split(" ".toRegex()).toTypedArray().size > 1
    }

    // Tell whether or not a given date is yesterday

    fun isYesterday(date: Date): Boolean {
        // Check if yesterday
        val c1 = Calendar.getInstance() // today
        c1.add(Calendar.DAY_OF_YEAR, -1) // yesterday
        val c2 = Calendar.getInstance()
        c2.time = date //
        return (c1[Calendar.YEAR] == c2[Calendar.YEAR] && c1[Calendar.DAY_OF_YEAR] == c2[Calendar.DAY_OF_YEAR])
    }

    // Tell whether or not a given date is yesterday

    fun isYesterday(dateString: String): Boolean {
        return isYesterday(formatDate(dateString))
    }

    // Tell whether or not a given date is today date

    private fun isToday(date: Date): Boolean {
        return DateUtils.isToday(date.time)
    }

    // Tell whether or not a given date is today date

    fun isToday(dateString: String): Boolean {
        return isToday(formatDate(dateString))
    }

    // Get difference between two dates

    fun getDateDiff(nowDate: Date, oldDate: Date, dateDiff: DateTimeUnits): Int {
        val diffInMs = nowDate.time - oldDate.time
        val days = TimeUnit.MILLISECONDS.toDays(diffInMs).toInt()
        val hours = (TimeUnit.MILLISECONDS.toHours(diffInMs) - TimeUnit.DAYS.toHours(days.toLong())).toInt()
        val minutes = (TimeUnit.MILLISECONDS.toMinutes(diffInMs) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(diffInMs))).toInt()
        val seconds = TimeUnit.MILLISECONDS.toSeconds(diffInMs).toInt()
        return when (dateDiff) {
            DateTimeUnits.DAYS -> days
            DateTimeUnits.SECONDS -> seconds
            DateTimeUnits.MINUTES -> minutes
            DateTimeUnits.HOURS -> hours
            DateTimeUnits.MILLISECONDS -> diffInMs.toInt()
        }
    }

    // Get difference between two dates

    fun getDateDiff(nowDate: String, oldDate: Date, dateDiff: DateTimeUnits): Int {
        return getDateDiff(formatDate(nowDate), oldDate, dateDiff)
    }

    // Get difference between two dates

    fun getDateDiff(nowDate: Date, oldDate: String, dateDiff: DateTimeUnits): Int {
        return getDateDiff(nowDate, formatDate(oldDate), dateDiff)
    }

    // Get difference between two dates

    fun getDateDiff(nowDate: String, oldDate: String, dateDiff: DateTimeUnits): Int {
        return getDateDiff(nowDate, formatDate(oldDate), dateDiff)
    }


    // Get current date
    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat(setDefaultPattern(pattern), Locale.getDefault())
        val today = Calendar.getInstance().time
        return dateFormat.format(today)
    }

    // Get current time
    fun getCurrentTime(): String? {
        val dateFormat = SimpleDateFormat(TIME_FORMAT_0, Locale.getDefault())
        val today = Calendar.getInstance().time
        return dateFormat.format(today)
    }

    fun getDateTimeFromTimeStamp(time: Long, mDateFormat: String): String? {
        val dateFormat = SimpleDateFormat(mDateFormat, Locale.getDefault())
        val dateTime = Date(time)
        return dateFormat.format(dateTime)
    }

    // Get Timestamp from date and time

    @Throws(ParseException::class)
    fun getTimeStampFromDateTime(mDateTime: String, mDateFormat: String): Long {
        val dateFormat = SimpleDateFormat(mDateFormat, Locale.getDefault())
        val date = dateFormat.parse(mDateTime)
        return date.time
    }

    // Return  datetime String from date object

    fun formatDateTimeFromDate(mDateFormat: String, date: Date): String {
        return DateFormat.format(mDateFormat, date).toString()
    }

    /*fun formatDateFromDateString(inputDateFormat: String, outputDateFormat: String, inputDate: Date): Date? {
        val mInputDateFormat = SimpleDateFormat(inputDateFormat, Locale.getDefault())
        val mOutputDateFormat = SimpleDateFormat(outputDateFormat, Locale.getDefault())
        return mOutputDateFormat.format(mInputDateFormat.parse(inputDate))
    }*/


    // Convert one date format string  to another date format string in android
    @Throws(ParseException::class)
    fun formatDateFromDateString(inputDateFormat: String, outputDateFormat: String, inputDate: String): String? {
        val mInputDateFormat = SimpleDateFormat(inputDateFormat, Locale.getDefault())
        val mOutputDateFormat = SimpleDateFormat(outputDateFormat, Locale.getDefault())
        return mOutputDateFormat.format(mInputDateFormat.parse(inputDate) as Date)
    }

    // Get date string from seconds
    fun dateStringFromSeconds(seconds: Long, outputDateFormat: String): String? {
        val formatter = SimpleDateFormat(outputDateFormat, Locale.getDefault())
        return formatter.format(Date(seconds * 1000L))
    }

    @Throws(ParseException::class)
    fun getMaxDateFromTwoDatesString(createdDate: String, updatedDate: String, dateFormat: String): String? {
        val mDateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
        val mCreatedDate = mDateFormat.parse(createdDate) as Date
        val mUpdatedDate = mDateFormat.parse(updatedDate) as Date
        val mMaxDate = max(mCreatedDate, mUpdatedDate)
        return mDateFormat.format(mMaxDate)
    }

    /**
     * Returns the maximum of two dates. A null date is treated as being less
     * than any non-null date.
     */
    private fun max(d1: Date, d2: Date): Date {
        return if (d1.after(d2)) d1 else d2
    }

    /**
     * Returns the minimum of two dates. A null date is treated as being greater
     * than any non-null date.
     */
    fun min(d1: Date, d2: Date): Date {
        return if (d1.after(d2)) d1 else d2
    }

    @Throws(ParseException::class)
    fun formatTimeFromTimeString(inputTimeFormat: String, outputTimeFormat: String, timeString: String): String? {
        var timeFormatStr: String? = null
        try {
            val sdf = SimpleDateFormat(inputTimeFormat, Locale.getDefault())
            val dateObj = sdf.parse(timeString)
            timeFormatStr = SimpleDateFormat(outputTimeFormat, Locale.getDefault()).format(dateObj as Date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return timeFormatStr
    }

    @Throws(ParseException::class)
    fun formatTimeFromServerTimeString(outputTimeFormat: String, timeString: String): String? {
        var timeFormatStr: String? = null
        try {
            val sdf = SimpleDateFormat(outputTimeFormat, Locale.getDefault())
            val dateObj = sdf.parse(timeString)
            timeFormatStr = SimpleDateFormat(outputTimeFormat, Locale.getDefault()).format(dateObj as Date)

        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return timeFormatStr
    }


    @Throws(ParseException::class)
    fun addHrsInTimeString(inputTimeFormat: String, outputTimeFormat: String, timeString: String, addHrs: Int): String? {

        var timeFormatStr: String? = null
        try {
            val sdf = SimpleDateFormat(inputTimeFormat, Locale.getDefault())
            val dateObj = sdf.parse(timeString)
            val c = Calendar.getInstance()  // Convert Date to Calendar
            c.time = dateObj as Date
            c.add(Calendar.HOUR, addHrs)  // Perform addition/subtraction
            val addedHrsDate = c.time  // Convert calendar back to Date
            timeFormatStr = SimpleDateFormat(outputTimeFormat, Locale.getDefault()).format(addedHrsDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return timeFormatStr
    }

    // Get Previous month from a given date
    @JvmStatic
    fun getPreviousMonthDate(date: Date, prevMonth: Int = -1): Date {
        val c = Calendar.getInstance()
        c.time = date //
        c.add(Calendar.MONTH, prevMonth)
        return c.time
    }


    // Get Previous month from a given date as string
    @JvmStatic
    fun getPreviousMonthDate(date: String): Date {
        return getPreviousMonthDate(formatDate(date))
    }

    // Get Next month from a given date
    fun getNextMonthDate(date: Date, nextMonth: Int = 1): Date {
        val c = Calendar.getInstance()
        c.time = date //
        c.add(Calendar.MONTH, nextMonth)
        return c.time
    }

    // Get Previous month from a given date as string
    fun getNextMonthDate(date: String): Date {
        return getNextMonthDate(formatDate(date))
    }


    // Get Previous week date
    fun getPreviousWeekDate(date: Date, dayOfTheWeek: Int = getIntValueOfDays()): Date {
        val c = Calendar.getInstance()
        c.time = date
        c.firstDayOfWeek = dayOfTheWeek
        c[Calendar.DAY_OF_WEEK] = dayOfTheWeek
        c.add(Calendar.DATE, -7)
        return c.time
    }


    // Get Previous week date as string
    fun getPreviousWeekDate(date: String, dayOfTheWeek: Int): Date {
        return getPreviousWeekDate(formatDate(date), dayOfTheWeek)
    }


    // Get Next week date
    fun getNextWeekDate(date: Date, dayOfTheWeek: Int = getIntValueOfDays()): Date {
        val c = Calendar.getInstance()
        c.time = date
        c.firstDayOfWeek = dayOfTheWeek
        c[Calendar.DAY_OF_WEEK] = dayOfTheWeek
        c.add(Calendar.DATE, 7)
        return c.time
    }

    // Get Next week date
    fun getNextWeekDate(date: String, dayOfTheWeek: Int): Date {
        return getNextWeekDate(formatDate(date), dayOfTheWeek)
    }

    fun getIntValueOfDays(): Int {
        val cal = Calendar.getInstance()
        return cal.get(Calendar.DAY_OF_WEEK)
    }

}
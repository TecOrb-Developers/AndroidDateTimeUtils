package com.tecorb.androidutils

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tecorb.androidutils.databinding.ActivityMainBinding
import com.vkp.utilitylibrary.datetime.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvDate.text = "Date to date String:\n ${DateTimeUtils.formatDate(Date())}"
        binding.tvDate1.text = "date string to date String:\n ${DateTimeUtils.formatDate("07-11-2021 04:33:20")}"
        binding.tvDate2.text = "Format with pattern\n ${DateTimeUtils.formatWithPattern("07-11-2021 04:33:20", DateTimeFormat.DATETIME_FORMAT_11)}"
        binding.tvDate3.text = "Format with milliseconds\n ${DateTimeUtils.formatDate(1667903704704, DateTimeUnits.HOURS)}"
        binding.tvDate4.text = "Format with pattern\n ${DateTimeUtils.formatWithPattern("07-11-2021 04:33:20", DateTimeUtils.getPatternForStyle(DateTimeStyleType.FULL))}"
        binding.tvDate5.text = "Format time\n ${DateTimeUtils.formatTime("07-11-2021 04:33:20", false)}"
        binding.tvDate6.text = "Millis to time\n ${DateTimeUtils.millisToTime(1667906703995)}"
        binding.tvDate7.text = "Time to Millis\n ${DateTimeUtils.timeToMillis("04:55:03")}"
        binding.tvDate7.text = "Is Yesterday or Not:\n ${DateTimeUtils.isYesterday("07-11-2022 23:59:59")}"
        binding.tvDate8.text = "Is Today or Not:\n ${DateTimeUtils.isToday("08-11-2022 00:00:00")}"
        binding.tvDate9.text = "Difference bw dates:\n ${DateTimeUtils.getDateDiff("08-11-2022 10:20:25", "08-11-2022 10:20:05", DateTimeUnits.SECONDS)}"
        binding.tvDate10.text = "Date format using input & output format :\n ${
            DateTimeUtils.formatDateFromDateStringOrObject(
                DateTimeFormat.DATETIME_FORMAT_1, DateTimeFormat.DATETIME_FORMAT_4, "08-11-2022 10:20:05"
            )
        }"
        binding.tvDate11.text = "Compare & return Max Date:\n ${DateTimeUtils.getMaxDateFromTwoDateObjOrString("08-11-2022 10:25:05", "08-11-2022 16:20:05", DateTimeFormat.DATETIME_FORMAT_1)}"
        binding.tvDate12.text = "Get Previous Month date :\n ${DateTimeUtils.getPreviousMonthDate(Date())}"
        binding.tvDate13.text = "Get Next Month date :\n ${DateTimeUtils.getNextMonthDate(Date())}"
        binding.tvDate14.text = "Get Previous Week date :\n ${DateTimeUtils.getPreviousWeekDate(Date(), Calendar.MONDAY)}"
        binding.tvDate15.text = "Get Next Week date :\n ${DateTimeUtils.getNextWeekDate(Date())}"
        //binding.tvDate16.text = "Get time ago :\n ${TimeAgoUtils.getTimeAgo(1667906703995)}"
        //binding.tvDate16.text = "Get time ago :\n ${TimeAgoUtils.getTimeAgo(this,"07-11-2021 04:33:20","dd-MM-yyyy hh:mm:ss")}"
        binding.tvDate16.text = "Get time ago : ${DateTimeUtils.getDateTimeFromTimeStamp(1667863503000, "dd-MM-yy hh:mm:ss")}" +
                "\n${DateTimeUtils.getTimeStampFromDateTime("08-11-22 04:55:03", "dd-MM-yy hh:mm:ss")}"+
                "\n${DateTimeUtils.formatTimeFromTimeString("dd-MM-yy hh:mm:ss","hh:mm:ss a","08-11-22 04:55:03")}"


    }
}
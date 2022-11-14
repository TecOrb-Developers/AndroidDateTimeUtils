# AndroidDateTimeUtils
![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=23)


This is a simple AndroidDateTime-Utils library that helps you to change your date format as per your requirement into your apps.

## Features

- You can change your date format from date object to string and vice-versa.
- You can change your date format using formatWithPattern as you required.
- You can change your date format using specific style like short, medium, full and long date format.
- You can convert millis to time and vice-versa.
- You can get entered date is today or tommorrow.
- You can get difference between two date in terms of Days, Hours, Minutes,Second or Milliseconds.
- You can compare two date and return the max date from them.
- You can get the previous or nextmonth date and previous or next week date.
- You can get time ago string from milliseconds or any date and time string.

# How it works:

## Gradle Dependency

-  Add the JitPack repository to your project's build.gradle file

```groovy
    allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
- Add the dependency in your app's build.gradle file

```groovy
    dependencies {
    implementation 'com.github.TecOrb-Developers:AndroidDateTimeUtils:v1.0.2'
}
```

## How to use this library function

### 1. formatDate
`formatDate` is a method that allow you to convert date object to string or timeStamp to date and vice-versa.

#### Date string to Date object
```
val date = DateTimeUtils.formatDate("2022-11-14 11:14:49")

val date = DateTimeUtils.formatDate("2022/11/14 12:33:50")

val date = DateTimeUtils.formatDate("2022-11-14")

val date = DateTimeUtils.formatDate("2022/11/14")
```

#### Date object to date string
```
val dateString = DateTimeUtils.formatDate(Date())
```

#### timeStamp to Date object
By default it will considere given timeStamp in milliseconds but if you did retrieve the timeStamp from server then you have to parse the params `DateTimeUnits.SECONDS` in method also.
```
// Using milliseconds
val date = DateTimeUtils.formatDate(1497399731000)

// Using seconds (Server timeStamp)
val date = DateTimeUtils.formatDate(1497399731,DateTimeUnits.SECONDS)
```

### 2. formatWithStyle
`formatWithStyle` allow to parse date into localized format using common style like `DateTimeStyleType.SHORT`, `DateTimeStyleType.MEDIUM`, `DateTimeStyleType.LONG` AND `DateTimeStyleType.FULL` date.

#### Date object to localized date
```
DateTimeUtils.formatWithStyle(Date(), DateTimeStyleType.FULL) // Monday, November 14, 2022

DateTimeUtils.formatWithStyle(Date(), DateTimeStyleType.LONG) // November 14, 2022

DateTimeUtils.formatWithStyle(Date(), DateTimeStyleType.MEDIUM) // Nov 14, 2022

DateTimeUtils.formatWithStyle(Date(), DateTimeStyleType.SHORT) // 11/14/22
```

#### Date string to localized date
```
DateTimeUtils.formatWithStyle("2017-06-13", DateTimeStyleType.FULL) // Monday, November 14, 2022

DateTimeUtils.formatWithStyle("2017-06-13", DateTimeStyleType.LONG) // November 14, 2022

DateTimeUtils.formatWithStyle("2017-06-13", DateTimeStyleType.MEDIUM) // Nov 14, 2022

DateTimeUtils.formatWithStyle("2017-06-13", DateTimeStyleType.SHORT) // 11/14/22
```
### 3. formatWithPattern
`formatWithPattern` allow to define your own parse pattern following SimpleDateFormat scheme

#### Date string as source
```
DateTimeUtils.formatWithPattern("2022-11-14", "EEEE, MMMM dd, yyyy") // Monday, November 14, 2022
```

#### Date object as source
```
DateTimeUtils.formatWithPattern(Date(), "EEEE, MMMM dd, yyyy") // Monday, November 14, 2022
```
### 4. isToday
`isToday` method give true if a given date is today date otherwise false.

#### Date object as source
```
val isToday = DateTimeUtils.isToday(Date())
```

#### Date String as source
```
val isToday = DateTimeUtils.isToday("2022-11-14 14:14:50")
```
### 5. isYesterday
`isYesterday` method give true if a given date is yesterday date otherwise false.
#### Date object as source
```
val isYesterday = DateTimeUtils.isYesterday(Date())
```
#### Date String as source
```
val isYesterday = DateTimeUtils.isYestrday("2022-11-14 14:30:50")
```
### 6. Get previous or next Week date
`getPreviousWeekDate/getNextWeekDate` Return the next or a previous week date from a given date it also allow you to set the day of the week by using Calendar Constant

#### Date object as source
```
val date = DateTimeUtils.getPreviousWeekDate(Date(), Calendar.MONDAY)
```
#### Date String as source
```
val date = DateTimeUtils.getNextWeekDate("2022-11-14 14:50:20",Calendar.SUNDAY)
```
### 7. Get previous or next month date
`getPreviousMonthDate/getNextMonthDate` return the next or a previous month date from a given date

#### Date object as source
```
val date = DateTimeUtils.getNextMonthDate(Date())
```
#### Date String as source
```
val date = DateTimeUtils.getPreviousMonthDate("20122-11-11 15:15:50")
```
### 8. getDateDiff
`getDateDiff` give you the difference between two date in terms of days or hours or minutes or seconds or milliseconds.

####  Dates can be date object or date string
val date = Date()
val date2 = "2022-11-14 15:15:15"
```
// Get difference in milliseconds
val diff = DateTimeUtils.getDateDiff(date,date2, DateTimeUnits.MILLISECONDS)

// Get difference in seconds
val diff = DateTimeUtils.getDateDiff(date,date2, DateTimeUnits.SECONDS)

// Get difference in minutes
val diff = DateTimeUtils.getDateDiff(date,date2, DateTimeUnits.MINUTES)

// Get difference in hours
val diff = DateTimeUtils.getDateDiff(date,date2, DateTimeUnits.HOURS)

// Get difference in days
val diff = DateTimeUtils.getDateDiff(date,date2, DateTimeUnits.DAYS)
```
### 9. getMaxDateFromTwoDateObjOrString
`getMaxDateFromTwoDateObjOrString` returns the max date from two dates
```
val date = DateTimeUtils.getMaxDateFromTwoDateObjOrString(
            "08-11-2022 10:25:05", "08-11-2022 16:20:05", DateTimeFormat.DATETIME_FORMAT_1
            ) // 14-11-2022 16:20:05
```
### 10. format date using input and output date format
#### Date object as source
```
val date = DateTimeUtils.formatDateFromDateStringOrObject(
                DateTimeFormat.DATETIME_FORMAT_1, DateTimeFormat.DATETIME_FORMAT_19, Date()
            ) // Monday, November 14, 2022
```
#### Date string as source
```
val date = DateTimeUtils.formatDateFromDateStringOrObject(
                DateTimeFormat.DATETIME_FORMAT_1, DateTimeFormat.DATETIME_FORMAT_19, "14-11-2022 10:20:05"
            ) // Monday, November 14, 2022
```

### 11. getTimeAgo
`getTimeAgo` give us the time ago string of past time, like 3 hours ago

```
// timeAgo string from milliseconds
val timeAgo = TimeAgoUtils.getTimeAgo(1667906703995) // 5 days ago

// timeAgo string from dateTime string
val timeAgo = TimeAgoUtils.getTimeAgo(this,"07-11-2021 04:33:20","dd-MM-yyyy hh:mm:ss") // More then a year ago
```
### 12. Format Time

#### getCurrentTime
```
val currentTime = DateTimeUtils.getCurrentTime() // 04:55:30
```
#### getDateTimeFromTimeStamp
```
val dateTimeFromMillis = DateTimeUtils.getDateTimeFromTimeStamp(1667863503000, "dd-MM-yy hh:mm:ss") // 08-11-22 04:55:03
```
#### formatTimeFromTimeString
```
val date = DateTimeUtils.formatTimeFromTimeString("dd-MM-yy hh:mm:ss","hh:mm:ss a","08-11-22 04:55:03") // 04:55:03 am
```
#### millisToTime
`millisToTime` is usefull when your dealing with duration and want to display the player seekbar time.

```
val timeFromMillis = DateTimeUtils.millisToTime(1667906703995) // 04:55:03
```
#### timeToMillis
`timeToMillis` allow to convert time string to millseconds
```
val millisFromTime = DateTimeUtils.timeToMillis("04:55:03") // 3543000

val millisFromTime = DateTimeUtils.timeToMillis("14:20") // 860000
```

# Developers

MIT License

Copyright (c) 2019 TecOrb Technologies

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
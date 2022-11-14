# AndroidDateTimeUtils

![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=23)


This is a simple library that helps you convert your date format in your apps as per your requirement.

## Features

- You can change date format as per your need.
- You can change the date format like as Short Date, Medium Date, Long Date and Ful Date format.
- You can change date format form millisecond to time, and vice-versa.
- You can calculate the date differece between two dates in Hour or Minutes or Seconds as per your need.
- You can compate two date and retrun max date as string.
- You can get previous month, next month, previous week and next week. 

# How it works:

1. Gradle Dependency

- Add the JitPack repository to your project's build.gradle file

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
        implementation 'com.github.TecOrb-Developers:AndroidDateTimeUtil:1.0'
    }
```

# How to use the library functions

2. Convert a java Date object to string with default patter to “dd-MM-yyyy hh:mm:ss”
```
val date = DateTimeUtils.formatDate(DateTimeUtils.formatDate(Date())
```
3. Convert date format from default pattern to required patttern.
```
val date = DateTimeUtils.formatWithPattern("07-11-2021 04:33:20", DateTimeFormat.DATETIME_FORMAT_11)
```
4. Convert default pattern to specific pattern style like Short, Medium, Long, and Full  Date pattern.
```
val date = DateTimeUtils.formatWithPattern(
            "07-11-2021 04:33:20", DateTimeUtils.getPatternForStyle(DateTimeStyleType.FULL)
        )
```
5. Convert a java Date object to string with default patter to “dd-MM-yyyy hh:mm:ss”
```
val date = DateTimeUtils.formatDate(DateTimeUtils.formatDate(Date())
```
6. Convert milliseconds to time like hh:mm:ss
```
val time = DateTimeUtils.millisToTime(1667906703995)
```
7. Convert time like hh:mm:ss to milliseconds
```
val millis = DateTimeUtils.timeToMillis("04:55:03")
```
8. You can get true or false whether you have entered date is yesterday or not.
```
val isYesterday = DateTimeUtils.isYesterday("07-11-2022 23:59:59")
```
9. You can get true or false whether you have entered date is today or not.
```
val isToday = DateTimeUtils.isToday("08-11-2022 00:00:00")
```
10. You can get difference between two date in terms of Hours or Minutes or Seconds.
```
val timeDiffSeconds = DateTimeUtils.getDateDiff(
            "08-11-2022 10:20:25", "08-11-2022 10:20:05", DateTimeUnits.SECONDS
        )
```
11. You can change the date format using inputDateFormat, outputDateFormat and inputDateString.
```
val date = DateTimeUtils.formatDateFromDateString(
      DateTimeFormat.DATETIME_FORMAT_1, DateTimeFormat.DATETIME_FORMAT_4, "08-11-2022 10:20:05"
    )
```
12. Compate two date and return max date from them.
```
val date = DateTimeUtils.getMaxDateFromTwoDatesString(
            "08-11-2022 10:25:05", "08-11-2022 16:20:05", DateTimeFormat.DATETIME_FORMAT_1
        )
```
13. You can get previous or next month date and previous or next week date.
```
val datePreviosMonth = DateTimeUtils.getPreviousMonthDate(Date())

val dateNextMonth = DateTimeUtils.getNextMonthDate(Date())

val datePreviosWeek = DateTimeUtils.getPreviousWeekDate(Date())

val datePreviosWeek = DateTimeUtils.getNextWeekDate(Date())
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


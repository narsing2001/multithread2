/*
1.Not thread safe:old java.util.Date which is not a thread safe the new date-time API is immutable
and does'nt have setter methods
2.less operation:old API has few date operation but the new API provides us with many date operation
-------------------
3.Local: date time API with no complexity of timezones handling
---------------------
4.Zoned:specialized date-time API to deal with various timezones
 */

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class Date_Time_API {
    public static void main(String[] args) {
/*
1).LocalDate/LocalTime and LocalDateTime API:use it when time zones are NOT required
 */
    //the current date
        LocalDate date=LocalDate.now();
        System.out.println("current date is:"+date);

    //the current time
        LocalTime time=LocalTime.now();
        System.out.println("current time is:"+time);

    //find the current date and time
        LocalDateTime current=LocalDateTime.now();
        System.out.println("current date and time:"+current);

    //to print date time in particular format
        DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String format1=current.format(format);
        System.out.println("date time in formatted manner:"+format1);
     //printing months days and seconds
        Month month=current.getMonth();
        int day=current.getDayOfMonth();
        int second=current.getSecond();
        System.out.println("Month:"+month+"day:"+day+"seconds:"+second);

    //printing some specified date
        LocalDate date1=LocalDate.of(2002,7,21);
        System.out.println("my birth day:"+date1);

     //printing the date with the current time
        LocalDateTime specificDate=current.withDayOfMonth(21).withYear(2002);
        System.out.println("specific date with current time:"+specificDate);

        System.out.println("zoned time API-----------------------------------------------------------------------------------------");
    //to get the current zone
        ZonedDateTime currentZone=ZonedDateTime.now();
        System.out.println("Current zone :"+currentZone.getZone());

    //get time zone of the specific location
        ZoneId tokyo=ZoneId.of("Asia/Tokyo");
        ZonedDateTime tokyoZone=currentZone.withZoneSameInstant(tokyo);
        System.out.println("tokyo time zone is:"+tokyoZone);
    /*Period and Duration classes
    ------------------------------------
    1.it deals with date based amount of time
    2.it deals with time based amount of time
     */
        LocalDate d1=LocalDate.now();
        LocalDate d2=LocalDate.of(2002,Month.JULY,21);
        Period gap=Period.between(d1,d2);
        System.out.println("gap between dates"+"is a period of"+gap);

        LocalTime time1=LocalTime.now();
        Duration fiveHour=Duration.ofHours(5);
        //adding five years to current
        LocalTime time2=time1.plus(fiveHour);
        System.out.println("after adding five hours of duration:"+time2);
        Duration gap1=Duration.between(time2,time1);
        System.out.println("duration gap between time1 and time2:"+gap1);

        LocalDate date2=LocalDate.now();
        System.out.println("current date is:"+date2);
        System.out.println("adding 2 years to current date--------------------");
        LocalDate year=date2.plus(2, ChronoUnit.YEARS);
        System.out.println("next to next year:"+year);
        System.out.println("adding the 1 month to current date------------------------");
        LocalDate nextmonth=date2.plus(1,ChronoUnit.MONTHS);
        System.out.println("next month is:"+nextmonth);
        System.out.println("adding one week to current date:----------------------------");
        LocalDate nextweek=date2.plus(1,ChronoUnit.WEEKS);
        System.out.println("next week is:"+nextweek);
        System.out.println("adding the two decades to current date:");
        LocalDate decades=date2.plus(2,ChronoUnit.DECADES);
        System.out.println("20 years after today:"+decades);


        LocalDate date3=LocalDate.now();
        System.out.println("get first day of the next month-------------------");
        LocalDate dayofNextMonth=date3.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("first day of next month:"+dayofNextMonth);

        LocalDate nextsaturday=date3.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        System.out.println("next saturday from now is:"+nextsaturday);


        System.out.println("first day of current month--------------------------");
        LocalDate firstDay = date3.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("firstDayOfMonth:" +firstDay);

        System.out.println("last day of current month--------------------------");
        LocalDate lastDay = date3.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("lastDayOfMonth : " + lastDay);
        System.out.println("sucessfully complete programme");




    }
}

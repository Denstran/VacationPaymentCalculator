package com.example.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class DateUtils {
    // Содержит праздники в формате "Дата - Продолжительность".
    private final static HashMap<MonthDay, Integer> holidays = new HashMap<>();

    static {
        holidays.put(MonthDay.of(Month.MARCH, 8), 1);  // Международный женский день.
        holidays.put(MonthDay.of(Month.JANUARY, 1), 7); // Новогодние праздники.
        holidays.put(MonthDay.of(Month.JANUARY, 7), 1); // Рождество.
        holidays.put(MonthDay.of(Month.FEBRUARY, 23), 1); // День защитника Отчества.
        holidays.put(MonthDay.of(Month.MAY, 1), 1); // Праздник Весны и Труда.
        holidays.put(MonthDay.of(Month.MAY, 9), 1); // День Победы.
        holidays.put(MonthDay.of(Month.JUNE, 12), 1); // День России.
        holidays.put(MonthDay.of(Month.NOVEMBER, 4), 1); // День народного единства.
    }

    public static long countHolidaysInDateRange(LocalDate startDate, LocalDate endDate) {
        long count = 0;
        long amountOfDays = startDate.until(endDate, ChronoUnit.DAYS);
        while (amountOfDays != 0) {
            if (isHoliday(startDate)) {
                // Если отпуск сотрудника пересекается с праздником
                // учитываем только те дни праздника, которые попадают в отпуск.
                int holidayDuration = holidays.get(getMonthDay(startDate));
                count += Math.min(holidayDuration, amountOfDays);
            }

            startDate = startDate.plusDays(1);
            amountOfDays--;
        }

        return count;
    }

    public static long countWeekEndsInDateRange(LocalDate startDate, LocalDate endDate) {
        long count = 0;
        long amountOfDays = startDate.until(endDate, ChronoUnit.DAYS);
        while (amountOfDays > 0) {
            if (isWeekend(startDate)) {
                count++;
            }

            startDate = startDate.plusDays(1);
            amountOfDays--;
        }

        return count;
    }

    public static boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    public static MonthDay getMonthDay(LocalDate date) {
        return MonthDay.of(date.getMonth(), date.getDayOfMonth());
    }

    public static boolean isHoliday(LocalDate date) {
        return holidays.containsKey(getMonthDay(date));
    }
}

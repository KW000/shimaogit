package com.android.mylibrary.calendar;

/**
 * desc
 * Author:shimao
 * Date:2017.04.28 13:16
 */
public interface DatePickerController {
    public abstract int getMaxYear();

    public abstract void onDayOfMonthSelected(int year, int month, int day);

    public abstract void onDateRangeSelected(final SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays);

}


package com.company;
import java.time.*;
import java.time.format.DateTimeFormatter;

public abstract class CalendarEntry{
    protected LocalDateTime entryStartDate;
    protected String description;
    protected DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public CalendarEntry(LocalDateTime entryStartDate, String description){
        this.entryStartDate=entryStartDate;
        this.description=description;
    }

    public LocalDateTime GetEntryStartDate(){
        return entryStartDate;
    }

    public abstract void WriteCalendarEntry(CalendarEntry entry);
}

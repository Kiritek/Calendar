package com.company;

import java.time.LocalDateTime;

public class Event extends CalendarEntry{
    protected LocalDateTime entryEndDate;
    protected String eventPlace;

    Event(LocalDateTime entryStartDate,LocalDateTime entryEndDate, String description,String eventPlace){
        super(entryStartDate,description);
        this.entryEndDate = entryEndDate;
        this.eventPlace = eventPlace;
    }

    public LocalDateTime GetEntryEndDate(){
        return entryEndDate;
    }

    public String GetEventPlace(){
        return eventPlace;
    }


    @Override
    public void WriteCalendarEntry(CalendarEntry entry) {
        System.out.print(dateTimeFormat.format(entry.entryStartDate));
        System.out.println(" - "+ dateTimeFormat.format(((Event)entry).entryEndDate));
        System.out.println(entry);
        System.out.println(entry.description);
        System.out.println(((Event) entry).entryEndDate);
    }
}

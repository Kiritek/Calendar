package com.company;
import java.time.LocalDateTime;

public class Reminder extends CalendarEntry {
    protected EventPriority priority;

    Reminder(LocalDateTime entryStartDate, String description, EventPriority priority){
        super(entryStartDate,description);
        this.priority = priority;
    }

    @Override
    public void WriteCalendarEntry(CalendarEntry entry) {
        System.out.println(entry.dateTimeFormat.format(entry.GetEntryStartDate()));
        System.out.println("Przypomnienie");
        System.out.println(entry.description);
        System.out.println(((Reminder)entry).priority);
    }
}

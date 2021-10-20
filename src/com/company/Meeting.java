package com.company;

import java.time.LocalDateTime;

public class Meeting extends CalendarEntry {
    protected LocalDateTime EntryEndDate;
    protected String MeetingPerson;

    public Meeting(LocalDateTime EntryStartDate,LocalDateTime EntryEndDate, String Description,String MeetingPerson){
        super(EntryStartDate,Description);
        this.EntryEndDate = EntryEndDate;
        this.MeetingPerson = MeetingPerson;
    }
    private LocalDateTime GetEntryEndDate(){
        return EntryEndDate;
    }

    @Override
    public void WriteCalendarEntry(CalendarEntry entry) {
        System.out.print(entry.dateTimeFormat.format(entry.entryStartDate));
        System.out.println(" - "+entry.dateTimeFormat.format(((Meeting)entry).EntryEndDate));
        System.out.println("Meeting");
        System.out.println(entry);
        System.out.println(((Meeting) entry).MeetingPerson);

    }
}

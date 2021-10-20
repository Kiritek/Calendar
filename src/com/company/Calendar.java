package com.company;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

 class Calendar {
     private ArrayList<CalendarEntry> calendar = new ArrayList<>();

     private void SortCalendar() {
         calendar.sort(Comparator.comparing(entry->entry.toString()));
     }

     public void WriteAllEntries() throws EmptyCalendarException {
         if (calendar.isEmpty()) {
             throw new EmptyCalendarException();
         }
         else {
             calendar.forEach(entry -> entry.WriteCalendarEntry(entry));
         }
     }

     public void WriteTwoNearestEntries(){
         calendar.stream()
                 .filter(entry -> entry.GetEntryStartDate().isAfter(LocalDateTime.now()))
                 .limit(2)
                 .forEach(entry ->entry.WriteCalendarEntry(entry));

     }
     public void WriteEntriesByType(String entryType){
         calendar.stream()
                 .filter(entry -> entry.toString().equals(entryType))
                 .forEach(entry -> entry.WriteCalendarEntry(entry));
     }
     public void WriteEntriesBySearchDate(LocalDateTime entryStartDate, LocalDateTime entryEndDate)throws PastDateException {
         if (entryStartDate.isAfter(entryEndDate)) {
             throw new PastDateException();
         } else {
             calendar.stream()
                     .filter(entry -> entry.GetEntryStartDate().isAfter(entryStartDate))
                     .filter(entry -> entry.GetEntryStartDate().isBefore(entryEndDate))
                     .forEach(entry -> entry.WriteCalendarEntry(entry));
         }
     }
     public void AddEntry(CalendarEntry entry){
         calendar.add(entry);
         SortCalendar();
     }
}

package com.company;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private Calendar calendar = new Calendar();

    private LocalDateTime ReadDate(String description){
        return LocalDateTime.parse(ReadUserData(description +" in following format yyyy-MM-dd HH:mm"), formatter);
    }
    private String ReadUserData(String description) {
        System.out.println(description);
        return scanner.nextLine();
    }

    public void InitializeCalendar() {
        int actionSelection;
        do {
            wyswietlMenu();
            actionSelection = Integer.parseInt(scanner.nextLine());

            switch (actionSelection) {
                case 1 -> {
                    try {
                        CreateEntry();
                    } catch (PastDateException e) {
                        System.out.println("You tried to create entry in the past.");
                    }
                }
                case 2 -> {
                    try {
                        calendar.WriteAllEntries();
                    }
                    catch (EmptyCalendarException e) {
                        System.out.println("Calendar is empty.");
                    }
                }
                case 3 -> calendar.WriteTwoNearestEntries();
                case 4 -> {
                    try {
                        calendar.WriteEntriesBySearchDate(ReadDate("Enter start date of search."), ReadDate("Enter end date of search."));
                    }
                    catch (PastDateException e) {
                        System.out.println("You have entered start date starting after end date.");
                    }
                }
                case 5-> calendar.WriteEntriesByType(ReadUserData("Enter type of entry. Following options are available: Event, Meeting, Reminder"));
                case 6 -> { }
                default ->System.out.println("You have entered wrong number");
            }
        } while (actionSelection != 6);
    }

    private void wyswietlMenu(){
        System.out.println("Welcome to simple calculator type digit to select option.");
        System.out.println("1 - Add Entry");
        System.out.println("2 - Show all entries");
        System.out.println("3 - Show two nearby entries ");
        System.out.println("4 - Search entry by date");
        System.out.println("5 - Search entry by type");
        System.out.println("6 - Exit the calendar");
    }
    private void CreateEntry() throws PastDateException {
        LocalDateTime entryStartDate;
        String description;
        String entryType;
        entryType = ReadUserData("Enter type of entry. You can choose between: Event, Meeting, Reminder");
        description = ReadUserData("Enter description");
        entryStartDate = ReadDate("Enter date of entry");
        if (entryStartDate.isBefore(LocalDateTime.now())) {
            throw new PastDateException();
        } else {
            switch (entryType) {
                case "Reminder"-> calendar.AddEntry(new Reminder(entryStartDate, description,GetPriority()));
                case "Meeting"->calendar.AddEntry(new Meeting(entryStartDate, ReadDate("Podaj date i godzine zakończenia spotkania"), description, ReadUserData("Podaj osoba z którą odbędzie się spotkanie")));
                case "Event"-> calendar.AddEntry(new Event(entryStartDate, ReadDate("Podaj date i godzine zakończenia wydarzenia"), description, ReadUserData("Podaj miejsce w którym odbędzie się wydarzenie")));
                default-> System.out.println("You have entered wrong type of entry");
            }
        }
    }
    public EventPriority GetPriority(){
        EventPriority priorityEnum;
        String priorityString;
        do {
            priorityString = ReadUserData("Enter priority");
            switch (priorityString){
            case "Low"   -> priorityEnum = EventPriority.Low;
            case "Medium"-> priorityEnum = EventPriority.Medium;
            case "High"  -> priorityEnum = EventPriority.High;
            default      -> priorityEnum = EventPriority.Error;
            }
        }while (priorityEnum!=EventPriority.Error);
        return priorityEnum;
    }
}

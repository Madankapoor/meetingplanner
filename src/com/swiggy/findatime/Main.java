package com.swiggy.findatime;

import com.swiggy.findatime.models.Attende;
import com.swiggy.findatime.models.MeetingTime;
import com.swiggy.findatime.models.Resource;
import com.swiggy.findatime.repository.UserRepository;
import com.swiggy.findatime.scheduler.Scheduler;
import com.swiggy.findatime.scheduler.SchedulerImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scheduler scheduler = new SchedulerImpl();
        UserRepository userRepo = new UserRepository();
        Scanner scanner = new Scanner(System.in);

        int choice = 0;

        do {
            printMenu();
            choice = scanner.nextInt();

            switch (choice){
                case 1:
                    System.out.println("Enter user email: ");
                    String email = scanner.next();
                    userRepo.AddUser(email);
                    break;

                case 2:
                    System.out.println("Enter Meeting Description: ");
                    String description = scanner.next();
                    System.out.println("Enter scheduling Date: ");
                    String meetingDateStr = scanner.next();
                    Date meetingDate = new SimpleDateFormat("dd/MM/yyyy").parse(meetingDateStr);
                    System.out.println("Selected date:" + meetingDate.toString());
                    System.out.println("Enter no of Attendees: ");
                    int no = scanner.nextInt();
                    System.out.println("Enter Attendees email one by one in each line: ");
                    Set<Resource> resources = new HashSet<>();
                    for(int i=0;i<no;i++) {
                        String attendeeEmail = scanner.next();
                        System.out.println("Added Attendees " + attendeeEmail);
                        resources.add(new Attende(attendeeEmail));
                    }
                    System.out.println("Enter Start Hour in 24 hours timeformat: ");
                    int startHours = scanner.nextInt();
                    System.out.println("Enter Start minutes in 60 minutes: ");
                    int startMinute = scanner.nextInt();
                    System.out.println("Enter duration in  minutes: ");
                    int duration = scanner.nextInt();
                    MeetingTime meetingTime = new MeetingTime(meetingDate,startHours,startMinute,duration);

                    if(scheduler.scheduleMeeting(resources,meetingTime,description)){
                        System.out.println("Meeting Scheduled at " + meetingTime.toString());
                    }
                    else {
                        System.out.println("Meeting not Scheduled at " + meetingTime.toString());
                        List<MeetingTime> suggestedTimes = scheduler.scheduleSuggestions(resources,meetingTime,description);
                        for(MeetingTime sugestedTime: suggestedTimes)
                            System.out.println("Suggesting alternative time: " + sugestedTime.toString());
                    }
                    break;
            }

        }while (choice != 0 );

    }

    public  static void printMenu(){
        System.out.println("Select the option: ");
        System.out.println("1. Add User ");
        System.out.println("2. Schedule Meeting ");
        System.out.println("0. Exit");
    }
}

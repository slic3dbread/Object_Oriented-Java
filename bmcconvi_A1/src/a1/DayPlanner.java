/*
 * Name: Benjamin McConville
 * Date: 10-15-2014
 */
package a1;

import java.util.Scanner;

/**
 * This is a DayPlanner which has different activities that can be set
 *
 * @author Benjamin McConville
 */
public class DayPlanner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Keeps track of number of values in each array
        int countH = 0;
        int countS = 0;
        int countO = 0;

        //used in the command menus
        String userInput;
        String garbageValue;

        //Used to search
        int year, month, day, hour, minute;
        String searchString, timeChoice;
        Time startTime = new Time();
        Time endTime = new Time();

        //Three class arrays
        HomeActivity[] Home = new HomeActivity[10];
        SchoolActivity[] School = new SchoolActivity[10];
        OtherActivity[] Other = new OtherActivity[10];

        //Initializes the arrays
        for (int i = 0; i < 10; i++) {
            Home[i] = new HomeActivity();
            School[i] = new SchoolActivity();
            Other[i] = new OtherActivity();
        }

        //Gets users input
        Scanner in = new Scanner(System.in);

        do {

            //Prompt user for input through menu
            System.out.println("****************************");
            System.out.println("Welcome to your DayPlanner: ");
            System.out.println("****************************");
            System.out.println("(add) Add an event and time into your DayPlanner. ");
            System.out.println("(search) Search your DayPlanner for an Activity. ");
            System.out.println("(quit) Quit the program.");

            //Gets users input
            userInput = in.nextLine();

            //First choice
            if (userInput.equalsIgnoreCase("a") || userInput.equalsIgnoreCase("add")) {
                System.out.println("What activity do you want to add?");
                System.out.println("((H)ome activity, (S)chool activity or (O)ther activity:");
                //Chooses between the activities to add
                userInput = in.nextLine();
                userInput = userInput.toLowerCase();
                switch (userInput) {
                    case "h":
                    case "home":

                        Home[countH] = new HomeActivity();

                        //Prompts the user for input of Activity fields
                        System.out.println("Name of the activity:");
                        Home[countH].setTitle(in.nextLine());
                        System.out.println("Starting time:");
                        Home[countH].setStartTime();
                        System.out.println("End time:");
                        Home[countH].setEndTime();

                        System.out.println("Comment:");
                        Home[countH].setComment(in.nextLine());

                        countH++;
                        break;
                    case "s":
                    case "school":

                        School[countS] = new SchoolActivity();

                        System.out.println("Name of the activity:");
                        School[countS].setTitle(in.nextLine());
                        System.out.println("Starting time:");
                        School[countS].setStartTime();
                        System.out.println("End time:");
                        School[countS].setEndTime();

                        System.out.println("Comment:");
                        School[countS].setComment(in.nextLine());

                        countS++;
                        break;
                    case "o":
                    case "other":
                        Other[countO] = new OtherActivity();

                        System.out.println("Location of activity:");//Only for other activity
                        Other[countO].setLocation(in.nextLine());
                        System.out.println("Name of the activity:");
                        Other[countO].setTitle(in.nextLine());
                        System.out.println("Starting time:");
                        Other[countO].setStartTime();
                        System.out.println("End time:");
                        Other[countO].setEndTime();

                        System.out.println("Comment:");
                        Other[countO].setComment(in.nextLine());

                        countO++;
                        break;
                    default:
                        System.out.println("Choose from one of the 3 options.");//If none of the options are inputed it prompts the user

                }
                //The Second option
            } else if (userInput.equalsIgnoreCase("s") || userInput.equalsIgnoreCase("search")) {

                System.out.println("Use the 3 search filters to find your activity. If left blank the filter wont be used.");
                System.out.println("Enter any keywords for the title:");

                searchString = in.nextLine();//sets the string to be searched to this variable and mkaes it lowercase to check for values
                searchString = searchString.toLowerCase();
                System.out.println("Add a start time and end time for the activity(Both must be input)");
                System.out.println("Do you want to search for a Start time and End time:(y or yes)");
                timeChoice = in.nextLine();

                if (timeChoice.equalsIgnoreCase("y") || timeChoice.equalsIgnoreCase("yes")) {
                    System.out.println("Start Time:");
                    System.out.println("Enter the year:(four digits)");
                    year = in.nextInt();
                    System.out.println("Enter the month:(two digits)");
                    month = in.nextInt();
                    System.out.println("Enter the day of the month: (two digits)");
                    day = in.nextInt();
                    System.out.println("Enter the hour:(two digits)");
                    hour = in.nextInt();
                    System.out.println("Enter the minute: (two digits)");
                    minute = in.nextInt();

                    startTime = new Time(year, month, day, hour, minute);

                    System.out.println("End Time:");

                    System.out.println("Enter the year:(four digits)");
                    year = in.nextInt();
                    System.out.println("Enter the month:(two digits)");
                    month = in.nextInt();
                    System.out.println("Enter the day of the month: (two digits)");
                    day = in.nextInt();
                    System.out.println("Enter the hour:(two digits)");
                    hour = in.nextInt();
                    System.out.println("Enter the minute: (two digits)");
                    minute = in.nextInt();

                    endTime = new Time(year, month, day, hour, minute);

                    garbageValue = in.nextLine();
                } else {
                    //Used for when the user wants to search without using times
                    startTime.setToZero();
                    endTime.setToZero();
                }

                System.out.println("What kind of activity was it (H)ome, (S)chool or (O)ther.");

                userInput = in.nextLine();
                userInput = userInput.toLowerCase();

                if (userInput.isEmpty() && searchString.isEmpty() && startTime.equalsZero() && endTime.equalsZero()) {
                    System.out.println("You didn't put any search fields in.");
                } else {
                    switch (userInput) {
                        case "h":
                        case "home":
                            for (int i = 0; i < countH; i++) {//Checks for all values in the array. If one of the cases equals nothing then that part equals true in the compareTo and contains
                                if ((Home[i].equalsValue(searchString)) && Home[i].getStartTime().compareTo(startTime) && Home[i].getEndTime().compareTo(endTime)) {
                                    System.out.println("The activity is:");
                                    System.out.println(Home[i].toString());
                                }
                            }

                            break;
                        case "s":
                        case "school":
                            for (int i = 0; i < countS; i++) {
                                if (School[i].equalsValue(searchString) && School[i].getStartTime().compareTo(startTime) && School[i].getEndTime().compareTo(endTime)) {

                                    System.out.println("The activity is:");
                                    System.out.println(School[i].toString());

                                }
                            }
                            break;
                        case "o":
                        case "other":

                            for (int i = 0; i < countO; i++) {
                                if (Other[i].equalsValue(searchString) && Other[i].getStartTime().compareTo(startTime) && Other[i].getEndTime().compareTo(endTime)) {

                                    System.out.println("The activity is:");
                                    System.out.println(Other[i].toString());
                                }

                            }

                            break;
                        default: {//since none of the activity options were chosen we will check through all of them
                            for (int i = 0; i < countH; i++) {
                                if (Home[countH].equalsValue(searchString) && Home[countH].getStartTime().compareTo(startTime) && Home[countH].getEndTime().compareTo(endTime)) {

                                    System.out.println("The activity is:");
                                    System.out.println(Home[i].toString());
                                }
                            }
                            for (int i = 0; i < countS; i++) {
                                if (School[countS].equalsValue(searchString) && School[countS].getStartTime().compareTo(startTime) && School[countS].getEndTime().compareTo(endTime)) {

                                    System.out.println("The activity is:");
                                    System.out.println(School[countS].toString());

                                }
                            }
                            for (int i = 0; i < countO; i++) {
                                if (Other[countO].equalsValue(searchString) && Other[countO].getStartTime().compareTo(startTime) && Other[countO].getEndTime().compareTo(endTime)) {

                                    System.out.println("The activity is:");
                                    System.out.println(Other[countO].toString());

                                }
                            }
                        }

                    }

                }
            } else if (userInput.equalsIgnoreCase("q") || userInput.equalsIgnoreCase("quit")) {
                System.exit(0);//Exits the program
            } else {
                System.out.println("Please input one of the 3 options.");//prompts the user if tehy didn't input one of the three options
            }
            userInput = "w";
        } while (!userInput.equalsIgnoreCase("q"));//Continues looping untill the user wants to quit
    }

}

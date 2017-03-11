package dayplanner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 * DayPlannerDemo class
 *
 * @author Benjamin McConville
 *
 */
public class DayPlannerDemo {


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Scanner StreamObject;
        DayPlanner planner = new DayPlanner();
        String command = "";
        
        
        try{
            if(args.length==2){
                StreamObject = new Scanner(new FileInputStream(args[0]));
                planner.addActivityFile(StreamObject);
                StreamObject.close();
                
            }
        }
        catch(FileNotFoundException ex){//Check and see if it will throw exception if only 1 file
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        
       

        do {
            System.out.print("Enter add, search, or quit> ");
            command = input.nextLine();
            if (command.equalsIgnoreCase("add") || command.equalsIgnoreCase("a")) {
                planner.addActivity(input);
            } else if (command.equalsIgnoreCase("search") || command.equalsIgnoreCase("s")) {
                planner.searchActivities(input);
            }
        } while (!command.equalsIgnoreCase("quit") && !command.equalsIgnoreCase("q"));
        

        
        try{
            if(args.length==1){
                PrintWriter outputStream = new PrintWriter(new FileOutputStream(args[0]));

                planner.addActivityToFile(outputStream);
            }
            else if (args.length==2){
                PrintWriter outputStream = new PrintWriter(new FileOutputStream(args[1]));
 
                planner.addActivityToFile(outputStream);
            }
        }
        catch(FileNotFoundException ex){//Check and see if it will throw exception if no files
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
}

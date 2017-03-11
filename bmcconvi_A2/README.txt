###############################################################################
###############################################################################
###########  Name: Benjamin McConville                              ###########
###########  Date 11-07-2014    File:Assignment 2   Course:CIS2430  ###########
###############################################################################
###############################################################################
###############################################################################

How to run:
		Must be run with Java 8
		-Make sure you have set the environment and classpath
		-From the folder navigate through to src 
	Compilation:
		-javac dayplanner\*.java (the bracket will be / for unix)
	Run:
		(for windows) (for unix remove the -cp)
		-java -cp . dayplanner.DayPlannerDemo input.txt output.txt
		(when run without a text file it will not store or read data)
		(when run with one text file it will create that text file)
		(when run with two it will read from the first one and write to the 
		second one)	


##########################################################################################
Files/Folders included:
	src\dayplanner\		DayPlanner.java		DayPlannerDemo.java	Activity.java
	HomeActivity.java	SchoolActivity.java	OtherActivity.java	Time.java
	javadoc\		

##########################################################################################
Citation:	
	Parts of the DayPlanner, Activity, Time, OtherActivity, SchoolActivity, HomeActivity
and DayPlannerDemo are from the this file.
Author Fei Song:
http://moodle.socs.uoguelph.ca/mod/resource/view.php?id=1618

##########################################################################################
DayPlanner:
	This Program is a DayPlanner that uses other classes to ask the user to input Activities
	and search the Activities and display them.
	The input/output text file format is the same as the example in the Assignment

##########################################################################################
Bugs:
	No Hashmap to search

##########################################################################################
Test Plan

Test Cases:						Result:
Setting time values to be blank	when adding		The int values will still prompt till they get a values
Setting a search of title to blank		      	it will not search with the title
Check menu(input characters and long strings)   	Tests show no crashing and valid prompts for user to correct their mistakes
random values added after command line			creates file by that name with the output
3 different values on the command line			doesn't create output or read any file
set title and comment to nothing in input file		it reads them as blank and 
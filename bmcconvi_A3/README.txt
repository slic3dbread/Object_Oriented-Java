###############################################################################
###############################################################################
###########  Name: Benjamin McConville                              ###########
###########  Date 11-28-2014    File:Assignment 3   Course:CIS2430  ###########
###############################################################################
###############################################################################
###############################################################################
About program:
	The files included in the folder allows for a DayPlanner to be implemented
	based on the file you run. DayPlannerDemo is a command line output. Where
	DayPlannerGui is a graphical interface implementation.




##########################################################################################


How to run:
		Must be run with Java 8
		-Make sure you have set the environment and classpath
		-From the folder navigate through to src 
	Compilation:
		-javac dayplanner\*.java (the bracket will be / for unix)
	Run:
		(for windows) (for unix remove the -cp)
		
		For Gui:
		-java -cp . dayplanner.DayPlannerGui input.txt output.txt
		
		For command Line:
		-java -cp . dayplanner.DayPlannerDemo input.txt output.txt
		
		(when run without a text file it will not store or read data)
		(when run with one text file it will create that text file)
		(when run with two it will read from the first one and write to the 
		second one)	


##########################################################################################
Files/Folders included:
	src\dayplanner\		DayPlanner.java		DayPlannerGui.java	DayPlannerDemo.java
	Activity.java	HomeActivity.java	SchoolActivity.java	OtherActivity.java	Time.java
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
Extras:
	Implemented a confirm on exit window.

##########################################################################################
Test Plan

Test Cases:										Result:
Setting time values to be blank	when adding			The int values will still prompt till they get a values
Setting a search of title to blank		      		it will not search with the title
random values added after command line				creates file by that name with the output
3 different values on the command line				doesn't create output or read any file
set title and comment to nothing in input file		it reads them as blank and 
In GUI for panel add don't fill in times			Error message appears saying time was not valid
add activity and search it under the wrong type		it outputs no activities found
search an activity in the file added at cmd			it displays the activity with the right fields

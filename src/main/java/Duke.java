import jdk.jfr.Event;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;
/*Week 2 done*/
/*Level 1*/
/*Need Fixing */
public class Duke {
    /*testing branch*/
    private static final List<Task> ListofMessages  = new ArrayList<>();

    public static void Openingmessage() { /*Displays message when chatbot starts up*/
        System.out.println("Hello! I'm Duke What can I do for you?");
    }

    public static void removeItem (int ItemIndex){
        ListofMessages.remove(ItemIndex);
    }

    public static void DisplayListOfMessages() {
        int LengthOfArrayList;
        LengthOfArrayList = ListofMessages.size();
        for (int i = 0; i < LengthOfArrayList; i++) {
            int NumberToDisplay = i + 1;
            if (ListofMessages.get(i) instanceof ToDos) {
                String AdditemMessage = "[T]";
                String finalmessage = NumberToDisplay +   "."  +  AdditemMessage +   "[" + ((ListofMessages.get(i)).getStatusIcon()) + "]" + ((ListofMessages.get(i)).getTask());
                System.out.println(finalmessage);
            }else if (ListofMessages.get(i) instanceof Deadlines) {
                String ToManipulate = ListofMessages.get(i).getStatusIcon();
                String ItemType = ((Deadlines) ListofMessages.get(i)).getItem();
                String GetDateLine = ((Deadlines) ListofMessages.get(i)).getDeadLine();//Type casting
                String TaskName = ((Deadlines) ListofMessages.get(i)).getDeadLineTask();
                String finalmessage = NumberToDisplay + "." + ItemType + "[" + ListofMessages.get(i).getStatusIcon() + "] " + TaskName + "(by: " + GetDateLine + ")";
                System.out.println(finalmessage);
            }else if (ListofMessages.get(i) instanceof Events) {
                String ToManipulate = ListofMessages.get(i).getStatusIcon();
                String ItemType = ((Events) ListofMessages.get(i)).getItem();
                String GetDateLine = ((Events) ListofMessages.get(i)).getEvent();
                String TaskName = ((Events) ListofMessages.get(i)).getEventsDescription();
                String finalmessage = NumberToDisplay + "." + ItemType + "[" + ListofMessages.get(i).getStatusIcon() + "] " + TaskName + "(at: " + GetDateLine + ")";
                System.out.println(finalmessage);
            } else {
                String finalmessage = NumberToDisplay + ".[" + ((ListofMessages.get(i)).getStatusIcon()) + "]" + " " + ((ListofMessages.get(i)).getTask());
                System.out.println(finalmessage);
            }
        }
    }

    public static String NumberOfItemsInList() {
        int numberOfItems = ListofMessages.size();
        String NumberOfItems = "Now you have " + numberOfItems + " tasks in the list.";
        return NumberOfItems;
    }

    public static void main(String[] args) throws DukeException, IOException {

        Openingmessage(); /*Opening Message*/
        Scanner input = new Scanner(System.in);
        int tasktobedone;

        //Task for level 7 -> The file created will be in testui test
            Create file
            File myObj = new File("text.txt");
//        FileWriter fw = new FileWriter(myObj);
//        PrintWriter pw = new PrintWriter(fw);
//        pw.close();
//
        String currentdirectory = System.getProperty("user.dir");
        System.out.println(currentdirectory);


        File log = new File(currentdirectory + "text.txt");
        try {
            if (log.exists() == true) {
                System.out.println("We had to make a new file.");
                log.createNewFile();
            }
        }finally {
                System.out.println("fk");
            }







        while (true) { //Main start

                String message = input.next();

                //Make sure message is valid
                if(!(message.equals("list")) &&!(message.equals("todo"))&& !(message.equals("event"))&&
                        !(message.equals("deadline"))&&!(message.equals("delete"))&&!(message.equals("bye"))){
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-();");
                }

                String ToDelete = "delete";
                if (message.equals(ToDelete)) {
                    int nextvalue = input.nextInt() - 1;
                    Task tasktobehandled = ListofMessages.get(nextvalue);

                    if(tasktobehandled instanceof ToDos) {
                        String task = (((ToDos) tasktobehandled).getItem());
                        String item = ((tasktobehandled).getTask());
                        System.out.println("Noted. I've removed this task:");
                        String Tobedisplayed = "  " + task + "[ ]" + item;
                        System.out.println(Tobedisplayed);
                        removeItem(nextvalue);
                        System.out.println(NumberOfItemsInList());
                    }else if(tasktobehandled instanceof Deadlines){
                        String deadline = (((Deadlines) tasktobehandled).getDeadLine());
                        String deadlinetask = (((Deadlines) tasktobehandled).getDeadLineTask());
                        String item = (((Deadlines) tasktobehandled).getItem());
                        System.out.println("Noted. I've removed this task:");
                        String Tobedisplayed = "  " + item + "[ ]" + deadlinetask + " (by: "+ deadline + ")";
                        System.out.println(Tobedisplayed);
                        removeItem(nextvalue);
                        System.out.println(NumberOfItemsInList());
                    }else{ //instance of event
                        String eventdescription = (((Events) tasktobehandled).getEventsDescription());  //Task
                        String symbol = (((Events) tasktobehandled).getItem()); //Symbol
                        String item = (((Events) tasktobehandled).getEventsDescription()); //Due deate
                        System.out.println("Noted. I've removed this task:");
                        String Tobedisplayed = "  " + symbol + "[ ]" + eventdescription + " (at: "+ item + ")";
                        System.out.println(Tobedisplayed);
                        removeItem(nextvalue);
                        System.out.println(NumberOfItemsInList());

                    }
                    //Remove at the end

                }

                String ToDoItem = "todo";
                if (message.equals(ToDoItem)) {
                    String remainingmessage = input.nextLine();
                    ToDos t = new ToDos(remainingmessage);

                    //Fix this to try and catch blocks?
                    if (remainingmessage.isEmpty()) { //Message is only todo
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }

                    ListofMessages.add(t);
                    String GotIt = "Got it. I've added this task: ";
                    String DisplayItem = t.getItem();
                    String getStatus = t.getStatusIcon();
                    String DisplayItemWithTask = "  " + DisplayItem + "[" + getStatus + "]" + remainingmessage;
                    System.out.println(GotIt);
                    System.out.println(DisplayItemWithTask);
                    System.out.println(NumberOfItemsInList());

                }

                //Deadline item
                String DeadLineItem = "deadline";
                if (message.equals(DeadLineItem)) {
                    try {
                        String remainingmessage = input.nextLine();

                        int i = 0;
                        int lengthofremainingmessage = remainingmessage.length();
                        //Locate the / icon
                        while (true) {
                            if (remainingmessage.charAt(i) == '/') {
                                break;
                            }
                            i = i + 1;
                        }
                        String firsthalf = remainingmessage.substring(1, i);
                        String secondhalf = remainingmessage.substring(i + 4, lengthofremainingmessage);
                        Deadlines t = new Deadlines(firsthalf, secondhalf);
                        ListofMessages.add(t);
                        String GotIt = "Got it. I've added this task: ";
                        System.out.println(GotIt);
                        //Display item with task
                        String DisplayItem = "  " + t.getItem();
                        String getStatus = t.getStatusIcon();
                        getStatus = "[" + getStatus + "] ";
                        String DisplayItemWithTask = DisplayItem + getStatus + firsthalf + "(" + "by: " + secondhalf + ")";
                        System.out.println(DisplayItemWithTask);
                        System.out.println(NumberOfItemsInList());
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                    }

                }

                //For events
                String Eventitem = "event";
                if (message.equals(Eventitem)) {
                    String remainingmessage = input.nextLine();
                    int i = 0;
                    int lengthofremainingmessage = remainingmessage.length();
                    //Locate the / icon
                    while (true) {
                        if (remainingmessage.charAt(i) == '/') {
                            break;
                        }
                        i = i + 1;
                    }

                    String firsthalf = remainingmessage.substring(1, i);
                    String secondhalf = remainingmessage.substring(i + 4, lengthofremainingmessage);

                    Events t = new Events(firsthalf, secondhalf);
                    ListofMessages.add(t);
                    String GotIt = "Got it. I've added this task: ";
                    System.out.println(GotIt);
                    //Display item with task
                    String DisplayItem = "  " + t.getItem();
                    String getStatus = t.getStatusIcon();
                    getStatus = "[" + getStatus + "] ";
                    String DisplayItemWithTask = DisplayItem + getStatus + firsthalf + "(" + "at: " + secondhalf + ")";
                    System.out.println(DisplayItemWithTask);
                    System.out.println(NumberOfItemsInList());
                }
                //Command is list
                String ToShowMessagesInArrayList = "list";
                if (message.equals(ToShowMessagesInArrayList)) {
                    DisplayListOfMessages();
                }

                //Command is Exit
                String ToExit = "bye";
                if (message.equals(ToExit)) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }



                //Check if the message is done
                String CheckIfTaskIsDone = "done";
                if (message.equals(CheckIfTaskIsDone)) {
                    tasktobedone = (input.nextInt()) - 1;
                    Task TheTask = (ListofMessages.get(tasktobedone));


                    if (TheTask instanceof ToDos) {
                        TheTask.setStatus();
                        ListofMessages.set(tasktobedone, TheTask);
                        System.out.println("Nice! I've marked this task as done");
                        String ToBeprinted = TheTask.getStatusIcon();
                        ToBeprinted = "[" + ToBeprinted + "]" + TheTask.getTask();
                        System.out.println(ToBeprinted);
                    } else if (TheTask instanceof Deadlines) {
                        TheTask.setStatus();
                        ListofMessages.set(tasktobedone, TheTask);
                        System.out.println("Nice! I've marked this task as done");
                        String ToBeprinted = TheTask.getStatusIcon();
                        ToBeprinted = "[" + ToBeprinted + "]" + TheTask.getTask();
                        System.out.println(ToBeprinted);
                    } else {
                        TheTask.setStatus();
                        ListofMessages.set(tasktobedone, TheTask);
                        System.out.println("Nice! I've marked this task as done");
                        String ToBeprinted = TheTask.getStatusIcon();
                        String ToAdd = ((Events) ListofMessages.get(tasktobedone)).getEventsDescription();
                        ToBeprinted = "[" + ToBeprinted + "]" + TheTask.getTask();
                        System.out.println(ToBeprinted);
                    }

                }


            }//Main End

    }
}

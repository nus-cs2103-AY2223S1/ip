package duke;
import javafx.application.Platform;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {

    /**
     *Runs the main code
     * @param ListofMessages
     * @throws IOException
     * @throws DukeException
     */
//    public static void Parser (List<Task> ListofMessages) throws IOException, DukeException {
//
//        Scanner input = new Scanner(System.in);
//        File log = new File("log.txt");
//
//        if(log.exists()==false){
//            //System.out.println("We had to make a new file.");
//            log.createNewFile();
//        }
//
//        //Reading in data from the file
//        Scanner readfile = new Scanner(log);
//
//        Storage.readfilez(readfile,ListofMessages); //Reads all the input
//
//
//        Ui.OpeningMessage(); /*Opening Message*/
//
//        int tasktobedone;
//        while (true) { //Main start
//
//            String message = input.next(); //Task to be done by system
//
//            boolean List_and_ToDo = (!(message.equals("list")) && !(message.equals("todo")));
//            boolean Event_and_Deadline = (!(message.equals("event")) && !(message.equals("deadline")));
//            boolean Delete_and_Bye = (!(message.equals("delete")) && !(message.equals("bye")));
//            boolean Mark_and_Find =(!(message.equals("mark")) && !(message.equals("find")));
//
//            //Make sure message is valid
//            if(List_and_ToDo && Event_and_Deadline && Delete_and_Bye && Mark_and_Find){
//                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-();");
//            }
//
//            String ToDelete = "delete";
//            if (message.equals(ToDelete)) {
//                int nextvalue = input.nextInt() - 1;
//                Task tasktobehandled = ListofMessages.get(nextvalue);
//
//                if(tasktobehandled instanceof ToDos) {
//
//                    String task = (((ToDos) tasktobehandled).getItem());
//                    String item = ((tasktobehandled).getTask());
//                    TaskList.deletetodo(nextvalue,task,item,ListofMessages);
//                    Storage.UpdateFile(log,ListofMessages);
//
//                }else if(tasktobehandled instanceof Deadlines){
//                    String deadline = (((Deadlines) tasktobehandled).getDeadLine());
//                    String deadlinetask = (((Deadlines) tasktobehandled).getDeadLineTask());
//                    String item = (((Deadlines) tasktobehandled).getItem());
//
//                    TaskList.deletedeadline(nextvalue,item,deadlinetask,deadline,ListofMessages);
//
//                    Storage.UpdateFile(log,ListofMessages);
//
//                }else{ //instance of event
//                    String eventdescription = (((Events) tasktobehandled).getEventsDescription());  //Task
//                    String symbol = (((Events) tasktobehandled).getItem()); //Symbol
//                    String item = (((Events) tasktobehandled). getEventDeadlineString()); //Due date
//
//                    TaskList.deleteevents(nextvalue,symbol,eventdescription,item,ListofMessages);
//                    Storage.UpdateFile(log,ListofMessages);
//                }
//                //Remove at the end
//            }
//
//            String ToDoItem = "todo";
//            if (message.equals(ToDoItem)) {
//                String remainingmessage = input.nextLine();
//                ToDos t = new ToDos(remainingmessage);
//
//
//                TaskList.todo(ListofMessages,t,remainingmessage);
//                Storage.UpdateFile(log,ListofMessages);
//            }
//
//            //Deadline item
//            String DeadLineItem = "deadline";
//            if (message.equals(DeadLineItem)) {
//                try {
//                    String remainingmessage = input.nextLine();
//
//                    int i = 0;
//                    int lengthofremainingmessage = remainingmessage.length();
//                    //Locate the / icon
//                    while (true) {
//                        if (remainingmessage.charAt(i) == '/') {
//                            break;
//                        }
//                        i = i + 1;
//                    }
//                    String firsthalf = remainingmessage.substring(1, i);
//                    String secondhalf = remainingmessage.substring(i + 4, lengthofremainingmessage);
//
//                    Deadlines t = new Deadlines(firsthalf, secondhalf.trim());
//                    TaskList.deadline(ListofMessages,t,firsthalf,secondhalf);
//                    Storage.UpdateFile(log,ListofMessages);
//
//                } catch (StringIndexOutOfBoundsException e) {
//                    throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
//                }
//
//            }
//
//            //For events
//            String Eventitem = "event";
//            if (message.equals(Eventitem)) {
//                String remainingmessage = input.nextLine();
//                int i = 0;
//                int lengthofremainingmessage = remainingmessage.length();
//                //Locate the / icon
//                while (true) {
//                    if (remainingmessage.charAt(i) == '/') {
//                        break;
//                    }
//                    i = i + 1;
//                }
//
//                String firsthalf = remainingmessage.substring(1, i);
//                String secondhalf = remainingmessage.substring(i + 4, lengthofremainingmessage);
//
//                Events t = new Events(firsthalf, secondhalf);
//
//                TaskList.event(ListofMessages,t,firsthalf,secondhalf);
//
//                Storage.UpdateFile(log,ListofMessages);
//
//            }
//            //Command is list
//            String ToShowMessagesInArrayList = "list";
//            if (message.equals(ToShowMessagesInArrayList)) {
//                Storage.DisplayListOfMessages(ListofMessages);
//            }
//
//            //Check if the message is done
//            String CheckIfTaskIsDone = "mark";
//            if (message.equals(CheckIfTaskIsDone)) {
//
//                tasktobedone = (input.nextInt()) - 1;
//
//                Task TheTask = (ListofMessages.get(tasktobedone));
//
//                if (TheTask instanceof ToDos) {
//                    TheTask.setStatus();
//                    ListofMessages.set(tasktobedone, TheTask);
//                    System.out.println("Nice! I've marked this task as done");
//                    String ToBeprinted = TheTask.getStatusIcon();
//                    ToBeprinted = "[" + ToBeprinted + "]" + TheTask.getTask();
//                    System.out.println(ToBeprinted);
//                    Storage.UpdateFile(log,ListofMessages);
//
//                } else if (TheTask instanceof Deadlines) {
//                    TheTask.setStatus();
//                    ListofMessages.set(tasktobedone, TheTask);
//                    System.out.println("Nice! I've marked this task as done");
//                    String ToBeprinted = TheTask.getStatusIcon();
//                    //   String ToAdd = ((Deadlines) ListofMessages.get(tasktobedone)).getDeadLineTask();
//                    ToBeprinted = "[" + ToBeprinted + "]" + TheTask.getTask();
//                    System.out.println(ToBeprinted);
//                } else {
//                    TheTask.setStatus();
//                    ListofMessages.set(tasktobedone, TheTask);
//                    System.out.println("Nice! I've marked this task as done");
//                    String ToBeprinted = TheTask.getStatusIcon();
//                    String ToAdd = ((Events) ListofMessages.get(tasktobedone)).getEventsDescription();
//                    ToBeprinted = "[" + ToBeprinted + "]" + TheTask.getTask();
//                    System.out.println(ToBeprinted);
//                }
//
//            }
//
//            String ToFind = "find";
//            if (message.equals(ToFind)) {
//                String ToFindItem = input.nextLine().trim();
//                List<Task> ListofFoundTask  = new ArrayList<>();
//
//                for(int i =0; i < ListofMessages.size();i++) {
//                    Task task = ListofMessages.get(i);
//                    if (task.getTask().toLowerCase().matches(("(.*)" + ToFindItem + "(.*)").toLowerCase())) {
//                        ListofFoundTask.add(task);
//                    }
//                }
//                if(ListofFoundTask.size() <= 0){
//                    System.out.println("No Task Found");
//                }else{
//                    Storage.DisplayListOfMessages(ListofFoundTask);
//                }
//
//            }
//
//            //Command is Exit
//            String ToExit = "bye";
//            if (message.equals(ToExit)) {
//                System.out.println("Bye. Hope to see you again soon!");
//                break;
//            }
//
//        }//Main End
//    }

    public static String HandleUserInput(String response,List<Task> ListofMessages) throws DukeException, IOException {

        String tobereturned = "";
        Scanner input = new Scanner(System.in);
        File log = new File("log.txt");
        File help = new File("help.txt");
        Scanner ReadHelpPage = new Scanner(help);

        int tasktobedone;

        String[] strArr = response.split(" ", 2);
        String message = strArr[0]; //Task to be done by system
        int arraylength = strArr.length;

            boolean List_and_ToDo = (!(message.equals("list")) && !(message.equals("todo")));
            boolean Event_and_Deadline = (!(message.equals("event")) && !(message.equals("deadline")));
            boolean Delete_and_Bye = (!(message.equals("delete")) && !(message.equals("bye")));
            boolean Mark_and_Find =(!(message.equals("mark")) && !(message.equals("find")));
            boolean HelpList = (!(message.equals("help")));

            //Make sure message is valid
            if(List_and_ToDo && Event_and_Deadline && Delete_and_Bye && Mark_and_Find && HelpList){
//              throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-();");
                tobereturned += "☹ OOPS!!! I'm sorry, but I don't know what that means :-();";
            }

            String ToDelete = "delete";
            if (message.equals(ToDelete)) {
                int nextvalue = Integer.parseInt(strArr[1]) - 1;
                Task tasktobehandled = ListofMessages.get(nextvalue);

                if(tasktobehandled instanceof ToDos) {

                    String task = (((ToDos) tasktobehandled).getItem());
                    String item = ((tasktobehandled).getTask());
                    tobereturned += TaskList.deletetodo(nextvalue,task,item,ListofMessages);
                    Storage.UpdateFile(log,ListofMessages);

                }else if(tasktobehandled instanceof Deadlines){
                    String deadline = (((Deadlines) tasktobehandled).getDeadLine());
                    String deadlinetask = (((Deadlines) tasktobehandled).getDeadLineTask());
                    String item = (((Deadlines) tasktobehandled).getItem());

                    tobereturned += TaskList.deletedeadline(nextvalue,item,deadlinetask,deadline,ListofMessages);

                    Storage.UpdateFile(log,ListofMessages);

                }else{ //instance of event
                    String eventdescription = (((Events) tasktobehandled).getEventsDescription());  //Task
                    String symbol = (((Events) tasktobehandled).getItem()); //Symbol
                    String item = (((Events) tasktobehandled). getEventDeadlineString()); //Due date

                    tobereturned += TaskList.deleteevents(nextvalue,symbol,eventdescription,item,ListofMessages);
                    Storage.UpdateFile(log,ListofMessages);
                }
                //Remove at the end
            }

            String ToDoItem = "todo";
            if (message.equals(ToDoItem)) {
                if (arraylength == 1){
                    assert arraylength == 1;
                    tobereturned += "☹ OOPS!!! The description of a todo cannot be empty.";

                }else{
                    String remainingmessage = strArr[1].trim();
                    ToDos t = new ToDos(remainingmessage);

                    tobereturned += TaskList.todo(ListofMessages, t, remainingmessage);
                    Storage.UpdateFile(log, ListofMessages);
                }
            }

            //Deadline item
            String DeadLineItem = "deadline";
            if (message.equals(DeadLineItem)) {
                if (arraylength == 1){
                    assert arraylength == 1;
                    tobereturned += "☹ OOPS!!! The description of a deadline cannot be empty.";
                }else {
                    try {
                        String remainingmessage = strArr[1];

                        int i = 0;
                        int lengthofremainingmessage = remainingmessage.length();
                        //Locate the / icon
                        while (true) {
                            if (remainingmessage.charAt(i) == '/') {
                                break;
                            }
                            i = i + 1;
                        }
                        String firsthalf = remainingmessage.substring(0, i);
                        String secondhalf = remainingmessage.substring(i + 4, lengthofremainingmessage);

                        Deadlines t = new Deadlines(firsthalf, secondhalf.trim());
                        tobereturned += TaskList.deadline(ListofMessages, t, firsthalf, secondhalf);
                        Storage.UpdateFile(log, ListofMessages);

                    } catch (StringIndexOutOfBoundsException e) {
                        //throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                        tobereturned += " ☹ OOPS!!! The description of a deadline cannot be empty.";
                    }
                }
            }

            //For events
            String Eventitem = "event";
            if (message.equals(Eventitem)) {
                if (arraylength == 1){
                    assert arraylength == 1;
                    tobereturned += "☹ OOPS!!! The description of a event cannot be empty.";
                }else {

                    String remainingmessage = strArr[1];
                    int i = 0;
                    int lengthofremainingmessage = remainingmessage.length();
                    //Locate the / icon
                    while (true) {
                        if (remainingmessage.charAt(i) == '/') {
                            break;
                        }
                        i = i + 1;
                    }

                    String firsthalf = remainingmessage.substring(0, i);
                    String secondhalf = remainingmessage.substring(i + 4, lengthofremainingmessage);

                    Events t = new Events(firsthalf, secondhalf);

                    tobereturned += TaskList.event(ListofMessages, t, firsthalf, secondhalf);

                    Storage.UpdateFile(log, ListofMessages);
                }
            }
            //Command is list
            String ToShowMessagesInArrayList = "list";
            if (message.equals(ToShowMessagesInArrayList)) {
                assert message.equals(ToShowMessagesInArrayList);
                tobereturned += Storage.DisplayListOfMessages(ListofMessages);
            }

            //Check if the message is done
            String CheckIfTaskIsDone = "mark";
            if (message.equals(CheckIfTaskIsDone)) {

                tasktobedone = Integer.parseInt(strArr[1]) - 1;

                Task TheTask = (ListofMessages.get(tasktobedone));

                if (TheTask instanceof ToDos) {
                    assert TheTask instanceof ToDos;
                    TheTask.setStatus();
                    ListofMessages.set(tasktobedone, TheTask);
                    //System.out.println("Nice! I've marked this task as done");
                    String ToBeprinted = TheTask.getStatusIcon();
                    ToBeprinted = "[" + ToBeprinted + "]" + TheTask.getTask();
                    System.out.println(ToBeprinted);
                    Storage.UpdateFile(log,ListofMessages);
                    tobereturned +="Nice! I've marked this task as done";
                    tobereturned += "\n";
                    tobereturned +=  ToBeprinted;

                } else if (TheTask instanceof Deadlines) {
                    assert TheTask instanceof Deadlines;
                    TheTask.setStatus();
                    ListofMessages.set(tasktobedone, TheTask);
                    //System.out.println("Nice! I've marked this task as done");
                    String ToBeprinted = TheTask.getStatusIcon();
                    //   String ToAdd = ((Deadlines) ListofMessages.get(tasktobedone)).getDeadLineTask();
                    ToBeprinted = "[" + ToBeprinted + "]" + TheTask.getTask();
                   // System.out.println(ToBeprinted);
                    tobereturned +="Nice! I've marked this task as done";
                    tobereturned += "\n";
                    tobereturned +=  ToBeprinted;
                } else {
                    TheTask.setStatus();
                    ListofMessages.set(tasktobedone, TheTask);
                    //System.out.println("Nice! I've marked this task as done");
                    String ToBeprinted = TheTask.getStatusIcon();
                    String ToAdd = ((Events) ListofMessages.get(tasktobedone)).getEventsDescription();
                    ToBeprinted = "[" + ToBeprinted + "]" + TheTask.getTask();
                    //System.out.println(ToBeprinted);
                    tobereturned +="Nice! I've marked this task as done";
                    tobereturned += "\n";
                    tobereturned +=  ToBeprinted;
                }

            }

            String ToFind = "find";
            if (message.equals(ToFind)) {
                assert message.equals(ToFind);
                String ToFindItem =  strArr[1].trim();
                List<Task> ListofFoundTask  = new ArrayList<>();

                for(int i =0; i < ListofMessages.size();i++) {
                    Task task = ListofMessages.get(i);
                    if (task.getTask().toLowerCase().matches(("(.*)" + ToFindItem + "(.*)").toLowerCase())) {
                        ListofFoundTask.add(task);
                    }
                }
                if(ListofFoundTask.size() <= 0){
                    assert ListofFoundTask.size() <= 0;
                    tobereturned += "No Task Found";
                }else{
                    tobereturned += Storage.DisplayListOfMessages(ListofFoundTask);
                }

            }

            //Command is Exit
            String ToExit = "bye";
            if (message.equals(ToExit)) {
                assert message.equals(ToExit);
                tobereturned += "Bye. Hope to see you again soon!";
                Platform.exit();
                System.exit(0);
            }

            String Help = "help";
            if (message.equals(Help)) {
                while(ReadHelpPage.hasNextLine()){
                    tobereturned += ReadHelpPage.nextLine();
                    tobereturned += "\n";
                }

            }
        return tobereturned; //Returning message

    }
}

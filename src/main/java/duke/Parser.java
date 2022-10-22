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

    public static String handleUserInput(String response, List<Task> ListofMessages) throws DukeException, IOException {

        String endProduct = "";

        Scanner input = new Scanner(System.in);
        File log = new File("log.txt");
        File help = new File("help.txt");
        Scanner helpPage = new Scanner(help);

        int tasktobedone;

        String[] strArr = response.split(" ", 2);
        String message = strArr[0]; //Task to be done by system
        int arrayLength = strArr.length;

            boolean List_and_ToDo = (!(message.equals("list")) && !(message.equals("todo")));
            boolean Event_and_Deadline = (!(message.equals("event")) && !(message.equals("deadline")));
            boolean Delete_and_Bye = (!(message.equals("delete")) && !(message.equals("bye")));
            boolean Mark_and_Find =(!(message.equals("mark")) && !(message.equals("find")));
            boolean HelpList = (!(message.equals("help")));

            //Make sure message is valid
            if(List_and_ToDo && Event_and_Deadline && Delete_and_Bye && Mark_and_Find && HelpList){
                endProduct += "☹ OOPS!!! I'm sorry, but I don't know what that means :-();";
            }

            String ToDelete = "delete";
            if (message.equals(ToDelete)) {
                int nextvalue = Integer.parseInt(strArr[1]) - 1;
                Task tasktobehandled = ListofMessages.get(nextvalue);

                if(tasktobehandled instanceof ToDos) {

                    String task = (((ToDos) tasktobehandled).getItem());
                    String item = ((tasktobehandled).getTask());
                    endProduct += TaskList.deletetodo(nextvalue,task,item,ListofMessages);
                    Storage.updateFile(log,ListofMessages);

                }else if(tasktobehandled instanceof Deadlines){
                    String deadline = (((Deadlines) tasktobehandled).getDeadLine());
                    String deadlinetask = (((Deadlines) tasktobehandled).getDeadLineTask());
                    String item = (((Deadlines) tasktobehandled).getItem());
                    endProduct += TaskList.deletedeadline(nextvalue,item,deadlinetask,deadline,ListofMessages);
                    Storage.updateFile(log,ListofMessages);

                }else{ //instance of event
                    String eventdescription = (((Events) tasktobehandled).getEventsDescription());  //Task
                    String symbol = (((Events) tasktobehandled).getItem()); //Symbol
                    String item = (((Events) tasktobehandled). getEventDeadlineString()); //Due date

                    endProduct += TaskList.deleteevents(nextvalue,symbol,eventdescription,item,ListofMessages);
                    Storage.updateFile(log,ListofMessages);
                }
                //Remove at the end
            }

            String ToDoItem = "todo";
            if (message.equals(ToDoItem)) {
                if (arrayLength == 1){
                    assert arrayLength == 1;
                    endProduct += "☹ OOPS!!! The description of a todo cannot be empty.";

                }else{
                    String remainingMessage = strArr[1].trim();
                    ToDos t = new ToDos(remainingMessage);

                    endProduct += TaskList.todo(ListofMessages, t, remainingMessage);
                    Storage.updateFile(log, ListofMessages);
                }
            }

            //Deadline item
            String DeadLineItem = "deadline";
            if (message.equals(DeadLineItem)) {
                if (arrayLength == 1){
                    assert arrayLength == 1;
                    endProduct += "☹ OOPS!!! The description of a deadline cannot be empty.";
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
                        endProduct += TaskList.deadline(ListofMessages, t, firsthalf, secondhalf);
                        Storage.updateFile(log, ListofMessages);

                    } catch (StringIndexOutOfBoundsException e) {
                        endProduct += " ☹ OOPS!!! The description of a deadline cannot be empty.";
                    }
                }
            }

            //For events
            String Eventitem = "event";
            if (message.equals(Eventitem)) {
                if (arrayLength == 1){
                    assert arrayLength == 1;
                    endProduct += "☹ OOPS!!! The description of a event cannot be empty.";
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

                    endProduct += TaskList.event(ListofMessages, t, firsthalf, secondhalf);

                    Storage.updateFile(log, ListofMessages);
                }
            }
            //Command is list
            String ToShowMessagesInArrayList = "list";
            if (message.equals(ToShowMessagesInArrayList)) {
                assert message.equals(ToShowMessagesInArrayList);
                endProduct += Storage.displayListOfMessages(ListofMessages);
            }

            //Check if the message is done
            String ifTaskIsDone = "mark";
            if (message.equals(ifTaskIsDone)) {

                tasktobedone = Integer.parseInt(strArr[1]) - 1;

                Task currentTask = (ListofMessages.get(tasktobedone));

                if (currentTask instanceof ToDos) {
                    assert currentTask instanceof ToDos;
                    currentTask.setStatus();
                    ListofMessages.set(tasktobedone, currentTask);
                    String tobePrinted = currentTask.getStatusIcon();
                    tobePrinted = "[" + tobePrinted + "]" + currentTask.getTask();
                    System.out.println(tobePrinted);
                    Storage.updateFile(log,ListofMessages);
                    endProduct +="Nice! I've marked this task as done";
                    endProduct += "\n";
                    endProduct +=  tobePrinted;

                } else if (currentTask instanceof Deadlines) {
                    assert currentTask instanceof Deadlines;
                    currentTask.setStatus();
                    ListofMessages.set(tasktobedone, currentTask);
                    String toBePrinted = currentTask.getStatusIcon();
                    toBePrinted = "[" + toBePrinted + "]" + currentTask.getTask();
                    endProduct +="Nice! I've marked this task as done";
                    endProduct += "\n";
                    endProduct +=  toBePrinted;
                } else {
                    currentTask.setStatus();
                    ListofMessages.set(tasktobedone, currentTask);
                    String tobePrinted = currentTask.getStatusIcon();
                    tobePrinted = "[" + tobePrinted + "]" + currentTask.getTask();
                    endProduct +="Nice! I've marked this task as done";
                    endProduct += "\n";
                    endProduct +=  tobePrinted;
                }

            }

            String ToFind = "find";
            if (message.equals(ToFind)) {
                assert message.equals(ToFind);
                String ToFindItem =  strArr[1].trim();
                List<Task> listOfTaskFound  = new ArrayList<>();

                for(int i =0; i < ListofMessages.size();i++) {
                    Task task = ListofMessages.get(i);
                    if (task.getTask().toLowerCase().matches(("(.*)" + ToFindItem + "(.*)").toLowerCase())) {
                        listOfTaskFound.add(task);
                    }
                }
                if(listOfTaskFound.size() <= 0){
                    assert listOfTaskFound.size() <= 0;
                    endProduct += "No Task Found";
                }else{
                    endProduct += Storage.displayListOfMessages(listOfTaskFound);
                }
            }

            //Command is Exit
            String exit = "bye";
            if (message.equals(exit)) {
                assert message.equals(exit);
                endProduct += "Bye. Hope to see you again soon!";
                Platform.exit();
                System.exit(0);
            }

            String toHelp = "help";
            if (message.equals(toHelp)) {
                while(helpPage.hasNextLine()){
                    endProduct += helpPage.nextLine();
                    endProduct += "\n";
                }
            }
        return endProduct; //Returning message
    }
}

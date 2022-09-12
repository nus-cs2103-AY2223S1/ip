import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/*Week 2 done*/
/*Level 1*/
/*Need Fixing */
//Level 7 done
public class Duke {
    /*testing branch*/
    /*testsvdsv*/
    private static final List<Task> ListofMessages  = new ArrayList<>();

    public static void Openingmessage() { /*Displays message when chatbot starts up*/
        System.out.println("Hello! I'm Duke What can I do for you?");
    }

    public static void removeItem (int ItemIndex){
        ListofMessages.remove(ItemIndex);
    }

    public static void UpdateFile (File thefile) throws IOException {

        FileWriter fw = new FileWriter(thefile);
        PrintWriter pw = new PrintWriter(fw);

        int LengthOfArrayList;
        LengthOfArrayList = ListofMessages.size();
        for (int i = 0; i < LengthOfArrayList; i++) {
            if (ListofMessages.get(i) instanceof ToDos) {
                String task = (((ToDos) ListofMessages.get(i)).getToDoDescirption()).trim();
                int statusofITEM = (((ToDos) ListofMessages.get(i)).getStatusint());
                pw.println("T " + statusofITEM + " | " + task);
            }else if (ListofMessages.get(i) instanceof Deadlines) {
                String task = (((Deadlines) ListofMessages.get(i)).getDeadLineTask()).trim();
                String date = (((Deadlines) ListofMessages.get(i)).getDeadLine());
                int statusofITEM = (((Deadlines) ListofMessages.get(i)).getStatusint());
                pw.println("D " + statusofITEM + " | " + task + " | "+ date);
            }else{
                String task = (((Events) ListofMessages.get(i)).getEventsDescription()).trim();
                String date = (((Events) ListofMessages.get(i)).getEvent());
                int statusofITEM = (((Events) ListofMessages.get(i)).getStatusint());
                pw.println("E " + statusofITEM +" | " + task +" | " + date );
            }

        }

        pw.close();
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

    public static void main(String[] args) throws DukeException, IOException, ParseException {

        Scanner input = new Scanner(System.in);


        File log = new File("log.txt");


        if(log.exists()==false){
//                System.out.println("We had to make a new file.");
            log.createNewFile();
        }

        //Reading in data from the file
        Scanner readfile = new Scanner(log);
        while(readfile.hasNextLine()) {
            String firsttask = readfile.next();
            if (firsttask.equals("T")) {
                int status = readfile.nextInt();
                String Skipbar =readfile.next();
                String task = readfile.nextLine();

                ToDos t = new ToDos(task);
                if (status == 1){
                    t.setStatus();
                }
                ListofMessages.add(t);

            } else if (firsttask.equals("D")) {
                int status = readfile.nextInt();
                String Skipbar =readfile.next();
                String addtask = readfile.next().trim();
                String bar = "|";
                String tocheck = "";
                int i = 0;
                while (true) {
                    String toappend = readfile.next().trim();
                    tocheck = toappend;
                    addtask = addtask + " " + toappend;
                    i++;
                    if(!tocheck.equals(bar)){
                        break;
                    }
                }
                String skipbar2 = readfile.next();
                String deadlineday =  readfile.nextLine().trim();
                Deadlines d = new Deadlines(addtask, deadlineday);
                if (status == 1){
                    d.setStatus();
                }
                ListofMessages.add(d);
            } else {
                int status = readfile.nextInt();
                String Skipbar =readfile.next();
                String addtask = readfile.next().trim();
                String bar = "|";
                String tocheck = "";
                while (true) {
                    String toappend = readfile.next().trim();
                    tocheck = toappend;
                    addtask = addtask + " " + toappend;
                    if(!tocheck.equals(bar)){
                        break;
                    }
                }
                String skipbar2 = readfile.next();
                String deadlineday =  readfile.nextLine().trim();
                Events t = new Events(addtask, deadlineday);
                if (status == 1){
                    t.setStatus();
                }
                ListofMessages.add(t);
            }
        }


        Openingmessage(); /*Opening Message*/



        int tasktobedone;

        while (true) { //Main start

            String message = input.next(); //Task to be done by system

            //Make sure message is valid
            if(!(message.equals("list")) &&!(message.equals("todo"))&& !(message.equals("event"))&&
                    !(message.equals("deadline"))&&!(message.equals("delete"))&&!(message.equals("bye"))&&!(message.equals("mark"))){
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
                    UpdateFile(log);
                }else if(tasktobehandled instanceof Deadlines){
                    String deadline = (((Deadlines) tasktobehandled).getDeadLine());
                    String deadlinetask = (((Deadlines) tasktobehandled).getDeadLineTask());
                    String item = (((Deadlines) tasktobehandled).getItem());
                    System.out.println("Noted. I've removed this task:");
                    String Tobedisplayed = "  " + item + "[ ]" + deadlinetask + " (by: "+ deadline + ")";
                    System.out.println(Tobedisplayed);
                    removeItem(nextvalue);
                    System.out.println(NumberOfItemsInList());
                    UpdateFile(log);
                }else{ //instance of event
                    String eventdescription = (((Events) tasktobehandled).getEventsDescription());  //Task
                    String symbol = (((Events) tasktobehandled).getItem()); //Symbol
                    String item = (((Events) tasktobehandled).getEventsDescription()); //Due deate
                    System.out.println("Noted. I've removed this task:");
                    String Tobedisplayed = "  " + symbol + "[ ]" + eventdescription + " (at: "+ item + ")";
                    System.out.println(Tobedisplayed);
                    removeItem(nextvalue);
                    System.out.println(NumberOfItemsInList());
                    UpdateFile(log);
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
                UpdateFile(log);
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

                    Deadlines t = new Deadlines(firsthalf, secondhalf.trim());
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
                    UpdateFile(log);

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
                UpdateFile(log);

            }
            //Command is list
            String ToShowMessagesInArrayList = "list";
            if (message.equals(ToShowMessagesInArrayList)) {
                DisplayListOfMessages();
            }

            //Check if the message is done
            String CheckIfTaskIsDone = "mark";
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
                    UpdateFile(log);

                } else if (TheTask instanceof Deadlines) {
                    TheTask.setStatus();
                    ListofMessages.set(tasktobedone, TheTask);
                    System.out.println("Nice! I've marked this task as done");
                    String ToBeprinted = TheTask.getStatusIcon();
                    //   String ToAdd = ((Deadlines) ListofMessages.get(tasktobedone)).getDeadLineTask();
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
            //Command is Exit
            String ToExit = "bye";
            if (message.equals(ToExit)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

        }//Main End

    }
}

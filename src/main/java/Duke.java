
import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        System.out.println("----------------------\nHello! I'm HelperBot\nWhat can I do for you?\n----------------------\n");
        String userInput = sc.nextLine();
        Task[] listOfActions = new Task[100];
        int currentAction = 0;

        while (!userInput.equals("bye")) {
            //If user wants to check the list
            String output = "";
            for (int i = 0; i < currentAction; i++) {
                output = output + String.format("%d", i + 1) + "." + listOfActions[i] + "\n";
            }
            if (userInput.equals("list")) {
                System.out.println("----------------------\n" + output + "----------------------");
            }

            else if (userInput.length() > 4) {

                //Td
                if (userInput.substring(0, 4).equals("todo")) {
                    toDo(userInput, listOfActions, currentAction);
                    currentAction++;
                }

                //Marking done
                else if (userInput.length() == 6) {
                    if (userInput.substring(0, 4).equals("mark")) {
                        mark(userInput, listOfActions);
                    } else {
                        createNewTask(userInput, listOfActions, currentAction);
                        currentAction++;
                    }
                }

                //event

                else if (userInput.substring(0, 5).equals("event")) {
                    event(userInput, listOfActions, currentAction);
                    currentAction++;
                }

                //deadline

                else if (userInput.substring(0, 8).equals("deadline")) {
                    deadLine(userInput, listOfActions, currentAction);
                    currentAction++;

                }



                //Unmarking
                else if (userInput.length() == 8) {
                    if (userInput.substring(0, 6).equals("unmark")) {
                        unMark(userInput, listOfActions);
                    } else {
                        createNewTask(userInput, listOfActions, currentAction);
                        currentAction++;
                    }
                }

                //If user wants to add to list
                else {
                    createNewTask(userInput, listOfActions, currentAction);
                    currentAction++;
                }

            }

            else {
                createNewTask(userInput, listOfActions, currentAction);
                currentAction++;
            }
            userInput = sc.nextLine();
        }
        bye();
    }

    public static void bye() {
        System.out.println("Thank you and ATB :)");
    }

    public static void mark(String userInput, Task[] listOfActions) {
        int position = Character.getNumericValue(userInput.charAt(5)) - 1;
        listOfActions[position].mark();
        System.out.println("----------------------\n" + "One more mission ;)\n" +
                listOfActions[position] + "\n----------------------\n");
    }

    //Unmark method
    public static void unMark(String userInput, Task[] listOfActions) {
        int position = Character.getNumericValue(userInput.charAt(7)) - 1;
        listOfActions[position].unMark();
        System.out.println("----------------------\n" + "Congrats on completing :)\n" +
                listOfActions[position] + "\n----------------------\n");
    }

    //create new task method
    public static void createNewTask(String userInput, Task[] listOfActions, int currentAction) {
        Task newTask = new Task(userInput.strip());
        listOfActions[currentAction] = newTask;
        System.out.println("----------------------\nadded: " + userInput + "\n----------------------\n");
    }

    //td method
    public static void toDo(String userInput, Task[] listOfActions, int currentAction) {
        //action
        String action = userInput.substring(4);
        Todo td = new Todo(action.strip());
        listOfActions[currentAction] = td;
        System.out.println("----------------------\n" + "Ok Solid you got this work to do:\n" +
                listOfActions[currentAction] + String.format("\nYou have a total of %d work to do", currentAction + 1)
                + "\n----------------------\n");
    }

    //dedline method
    public static void deadLine(String userInput, Task[] listOfActions, int currentAction) {
        //get action
        int index = userInput.indexOf("/");
        String action = userInput.substring(9, index);
        //get deadline
        String dedline = userInput.substring(index + 3);
        Deadline ded = new Deadline(action.strip(), dedline.strip());
        listOfActions[currentAction] = ded;
        System.out.println("----------------------\n" + "Ok Solid you got this work to do:\n" +
                listOfActions[currentAction] + String.format("\nYou have a total of %d work to do", currentAction + 1)
                + "\n----------------------\n");
    }


    //event method
    public static void event(String userInput, Task[] listOfActions, int currentAction) {
        //get action
        int index = userInput.indexOf("/");
        String action = userInput.substring(6, index);
        //get date
        String dedline = userInput.substring(index + 3);
        Event e = new Event(action.strip(), dedline.strip());
        listOfActions[currentAction] = e;
        System.out.println("----------------------\n" + "Ok Solid you got this work to do:\n" +
                listOfActions[currentAction] + String.format("\nYou have a total of %d work to do", currentAction + 1)
                + "\n----------------------\n");
    }



}



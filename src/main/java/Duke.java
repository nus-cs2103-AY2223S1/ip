
import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);

        System.out.println("----------------------\nHello! I'm HelperBot\nWhat can I do for you?\n----------------------\n");
        String userInput = sc.nextLine();
        Task[] listOfActions = new Task[100];
        int currentAction = 0;
        int end = 0;

        while (end != 1) {
            //If user wants to check the list
            String output = "";
            for (int i = 0; i < currentAction; i++) {
                output = output + String.format("%d", i + 1) + "." + listOfActions[i] + "\n";
            }

            if (userInput.equals("bye")) {
                break;
            }

            else if (userInput.equals("list")) {
                System.out.println("----------------------\n" + output + "----------------------\n");
            }

            else if (userInput.length() >= 4) {

                //Td
                if (userInput.substring(0,4).equals("todo")) {
                    try {
                        toDo(userInput, listOfActions, currentAction);
                        currentAction++;
                    } catch (DukeException e) {
                        System.out.println("----------------------\nSorry you can't do nothing :(\n" +
                                "----------------------\n");
                    }
                }

                else if (userInput.length() == 4 && !userInput.equals("todo")) {
                    System.out.println("----------------------\nI am sorry pls input again\n" +
                            "----------------------\n");
                }

                else if (userInput.length() > 4) {

                    //event
                    if (userInput.substring(0, 5).equals("event")) {
                        try {
                            event(userInput, listOfActions, currentAction);
                            currentAction++;
                        } catch (DukeException e) {
                            System.out.println("----------------------\nNo event no good :(\n" +
                                    "----------------------\n");
                        }
                    }

                    else if (userInput.length() > 5) {

                        //Marking done
                        if (userInput.length() == 6) {
                            if (userInput.substring(0, 4).equals("mark")) {
                                try {
                                    mark(userInput, listOfActions);
                                } catch (DukeException e) {
                                    System.out.println("----------------------\nI am sorry pls input again\n" +
                                            "----------------------\n");
                                }
                            } else {
                                System.out.println("----------------------\nI am sorry pls input again\n" +
                                        "----------------------\n");
                            }
                        }

                        else if (userInput.length() > 7) {

                            //deadline
                            if (userInput.substring(0, 8).equals("deadline")) {
                                try {
                                    deadLine(userInput, listOfActions, currentAction);
                                    currentAction++;
                                } catch (DukeException e) {
                                    System.out.println("----------------------\nNo deadline? Impossible !!!\n" +
                                            "----------------------\n");
                                }

                            }

                            //Unmarking
                            else if (userInput.length() == 8) {
                                if (userInput.substring(0, 6).equals("unmark")) {
                                    try {
                                        unMark(userInput, listOfActions);
                                    } catch (DukeException e) {
                                        System.out.println("----------------------\nI am sorry pls input again\n" +
                                                "----------------------\n");
                                    }
                                } else {
                                    System.out.println("----------------------\nI am sorry pls input again\n" +
                                            "----------------------\n");
                                }
                            }
                        }
                        else {
                            System.out.println("----------------------\nI am sorry pls input again\n" +
                                    "----------------------\n");
                        }
                    }

                    else {
                        System.out.println("----------------------\nI am sorry pls input again\n" +
                                "----------------------\n");
                    }

                }



                //If random words longer than 4
                else {
                    System.out.println("----------------------\nI am sorry pls input again\n" +
                            "----------------------\n");
                }
            }

            //If random words <4
            else {
                System.out.println("----------------------\nI am sorry pls input again\n" +
                        "----------------------\n");
            }
            userInput = sc.nextLine();
        }
        bye();
    }

    public static void bye() {
        System.out.println("Thank you and ATB :)");
    }

    public static void mark(String userInput, Task[] listOfActions) throws DukeException{
        try {
            int position = Character.getNumericValue(userInput.charAt(5)) - 1;
            listOfActions[position].mark();
            System.out.println("----------------------\n" + "Congrats on completing :)\n" +
                    listOfActions[position] + "\n----------------------\n");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("");
        }
    }

    //Unmark method
    public static void unMark(String userInput, Task[] listOfActions) throws DukeException{
        try {
            int position = Character.getNumericValue(userInput.charAt(7)) - 1;
            listOfActions[position].unMark();
            System.out.println("----------------------\n" + "One more mission ;)\n" +
                    listOfActions[position] + "\n----------------------\n");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("");
        }
    }

    //td method
    public static void toDo(String userInput, Task[] listOfActions, int currentAction) throws DukeException{
        try {
            //action
            if (userInput.length() < 5) {
                throw new StringIndexOutOfBoundsException("");
            }
            String action = userInput.substring(4);
            Todo td = new Todo(action.strip());
            listOfActions[currentAction] = td;
            System.out.println("----------------------\n" + "Ok Solid you got this work to do:\n" +
                    listOfActions[currentAction] + String.format("\nYou have a total of %d work to do", currentAction + 1)
                    + "\n----------------------\n");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("");
        }
    }

    //dedline method
    public static void deadLine(String userInput, Task[] listOfActions, int currentAction) throws DukeException{
        try {
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
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("");
        }
    }


    //event method
    public static void event(String userInput, Task[] listOfActions, int currentAction) throws DukeException {
        try {
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
        } catch(StringIndexOutOfBoundsException e) {
            throw new DukeException("");
        }
    }



}



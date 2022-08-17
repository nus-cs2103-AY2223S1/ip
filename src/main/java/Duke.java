
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
                output =  output + String.format("%d", i + 1) + "." + listOfActions[i] + "\n";
            }
            if (userInput.equals("list")) {
                System.out.println("----------------------\n" + output + "----------------------");
            }

            //Marking done
            else if (userInput.length() == 6) {
                if (userInput.substring(0, 4).equals("mark")) {
                    int position = Character.getNumericValue(userInput.charAt(5)) - 1;
                    listOfActions[position].mark();
                    System.out.println("----------------------\n" + "One more mission ;)\n" +
                            listOfActions[position] + "\n----------------------\n");
                } else {
                    Task newTask = new Task(userInput);
                    listOfActions[currentAction] = newTask;
                    System.out.println(listOfActions[currentAction] + String.format("%d", currentAction));
                    System.out.println("----------------------\nadded: " + userInput + "\n----------------------\n");
                    currentAction++;
                }
            }

            else if (userInput.length() == 8) {
                //Unmarking
                if (userInput.substring(0, 6).equals("unmark")) {
                    int position = Character.getNumericValue(userInput.charAt(7)) - 1;
                    listOfActions[position].unMark();
                    System.out.println("----------------------\n" + "Congrats on completing :)\n" +
                            listOfActions[position] + "\n----------------------\n");
                } else {
                        Task newTask = new Task(userInput);
                        listOfActions[currentAction] = newTask;
                        System.out.println("----------------------\nadded: " + userInput + "\n----------------------\n");
                        currentAction++;
                }
            }

            //If user wants to add to list
            else {
                Task newTask = new Task(userInput);
                listOfActions[currentAction] = newTask;
                System.out.println("----------------------\nadded: " + userInput + "\n----------------------\n");
                currentAction++;
            }
            userInput = sc.nextLine();
        }
        System.out.println("Thank you and ATB :)");

    }




}




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
        String listOfActions[] = new String[100];
        int currentAction = 0;
        while (!userInput.equals("bye")) {
            //If user wants to check the list
            String output = "";
            for (int i = 0; i < currentAction; i++) {
                output = output + listOfActions[i] + "\n";
            }
            if (userInput.equals("list")) {
                System.out.println("----------------------\n" + output + "----------------------");
            }
            //If user wants to add to list
            else {
                listOfActions[currentAction] = (currentAction + ". " + userInput);
                System.out.println("----------------------\nadded: " + userInput + "\n----------------------\n");
                currentAction++;
            }
            userInput = sc.nextLine();
        }
        System.out.println("Thank you and ATB :)");

    }


}

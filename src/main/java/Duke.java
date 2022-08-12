import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        myPrinter(" Hello! I'm Duke\n What can I do for you?");
        ArrayList<Task> myList = new ArrayList<Task>();

        loop: while (true) {
            Scanner myObj = new Scanner(System.in);
            String userInput = myObj.nextLine();
            int itemNumber = 0;
            switch(userInput.split(" ")[0]) {
                case "bye":
                    myPrinter("Bye. Hope to see you again soon!");
                    break loop;
                case "list":
                    String toPrint = "";
                    for(int i = 0; i<myList.size(); i++) {
                        toPrint += (i+1) + ".["
                                + myList.get(i).getStatusIcon() + "] "
                                + myList.get(i).getDescription() + "\n";
                    }
                    myPrinter(toPrint.substring(0, toPrint.length()-1));
                    break;
                case "mark":
                    itemNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    myList.get(itemNumber).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("["
                            + myList.get(itemNumber).getStatusIcon() + "] "
                            + myList.get(itemNumber).getDescription());
                    break;
                case "unmark":
                    itemNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    myList.get(itemNumber).markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("["
                            + myList.get(itemNumber).getStatusIcon() + "] "
                            + myList.get(itemNumber).getDescription());
                    break;
                default:
                    Task myTask = new Task(userInput);
                    myList.add(myTask);
                    myPrinter("added: " + userInput);
            }
        }
    }

    private static void myPrinter(String myString) {
        System.out.println("____________________________________________________________");
        System.out.println(myString);
        System.out.println("____________________________________________________________");
    }
}

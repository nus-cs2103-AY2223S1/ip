import java.util.Scanner;
import java.util.ArrayList;
import java.util.ListIterator;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        myPrinter(" Hello! I'm Duke\n What can I do for you?");
        ArrayList<String> myList = new ArrayList<String>();

        while (true) {
            Scanner myObj = new Scanner(System.in);
            String userInput = myObj.nextLine();
            if (userInput.equals("bye")) {
                myPrinter("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                String toPrint = "";
                for(int i = 0; i<myList.size(); i++) {
                    toPrint += (i+1) + ". " + myList.get(i) + "\n";
                }
                myPrinter(toPrint.substring(0, toPrint.length()-1));
            } else {
                myList.add(userInput);
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

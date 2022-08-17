import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        String input = "";

        String welcomeMsg = "Hello I'm Duke!\n\t  What can I do for you?";
        Duke.printText(welcomeMsg);

        String[] textList = new String[100];
        int numOfItems = 0;
        while (input != "bye") {
            input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                Duke.printList(textList);
            } else {
                textList[numOfItems] = input;
                numOfItems += 1;
                Duke.printText("Added " + input);
            }
        }

        String exitMsgs = "Good bye!\n\t  Hope to see you soon!";
        Duke.printText(exitMsgs);
    }

    /**
     * Print the output in customised format.
     * @param list The list to print
     */ 
    public static void printList(String[] list) {
        System.out.println("\t\u2015\u2015\u2015");
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                break;
            }
            System.out.println(String.format("\t  %d.[ ] %s", i, list[i]));
        }
        System.out.println("\t\u2015\u2015\u2015");
    }

    /**
     * Print the output in customised format.
     * @param text The text to print
     */ 
    public static void printText(String text) {
        System.out.println("\t\u2015\u2015\u2015");
        System.out.println("\t  " + text);
        System.out.println("\t\u2015\u2015\u2015");
    }

}
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

        String[] welcomeMsgs = {"Hello I'm Duke!","What can I do for you?"};
        Duke.printFormatted(welcomeMsgs);

        String[] textList = new String[100];
        int numOfItems = 0;
        while (input != "bye") {
            input = sc.next();
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                Duke.printFormatted(textList);
            } else {
                textList[numOfItems] = String.format("%d. %s", numOfItems + 1, input);
                numOfItems += 1;
                Duke.printFormatted("Added " + input);
            }
        }

        String[] exitMsgs = {"Good bye!", "Hope to see you soon!"};
        Duke.printFormatted(exitMsgs);
    }

    /**
     * Print the output in customised format.
     * @param texts Array of sentences to print
     */ 
    public static void printFormatted(String[] texts) {
        System.out.println("\t\u2015\u2015\u2015");
        for (String line : texts) {
            if (line == null) {
                break;
            }
            System.out.println("\t  " + line);
        }
        System.out.println("\t\u2015\u2015\u2015");
    }

        /**
     * Print the output in customised format.
     * @param text The single sentence to print
     */ 
    public static void printFormatted(String text) {
        System.out.println("\t\u2015\u2015\u2015");
        System.out.println("\t  " + text);
        System.out.println("\t\u2015\u2015\u2015");
    }

}
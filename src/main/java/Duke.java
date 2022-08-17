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

        String[] replyMsgs = new String[100];
        while (input != "bye") {
            input = sc.next();
            replyMsgs[0] = input;
            if(input.equalsIgnoreCase("bye")) {
                break;
            }
            Duke.printFormatted(replyMsgs);
        }

        String[] exitMsgs = {"Good bye!", "Hope to see you soon!"};
        Duke.printFormatted(exitMsgs);
    }

    /**
     * Print the output in customised format.
     * @param text Array of sentences to print
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

}
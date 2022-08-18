//public class Duke {
//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }
//}

import java.util.Scanner;

public class Duke {

    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runBot();
    }

    public void runBot() {
        greetingMessage();

        boolean exitBot = false;
        while (!exitBot) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                exitBot = true;
            } else {
                echoInput(input);
            }
        }

        exitMessage();
    }

    public void greetingMessage() {
        String greeting = "Hello! I'm Duke\n\t" +
                "What can I do for you?";
        printMessage(greeting);
    }

    public void exitMessage() {
        String exit = "Bye. Hope to see you again soon!";
        printMessage(exit);
    }

    public void echoInput(String input) {
        printMessage(input);
    }

    public void printMessage(String input) {
        linePrint();
        System.out.println('\t' + input);
        linePrint();
    }

    public void linePrint() {
        System.out.println("\t____________________________________________________________");
    }
}

import java.util.Scanner;

public class Duke {
    private final static String lineBreak1
            = "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_"
             + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-";
    private final static String lineBreak2
            = "______________________________________________________"
            + "______________________________________________________";
    private final static String logo
            = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "Hello! I'm Duke\n"
            + "What can I do for you?";

    private static boolean inputCommand(String command) {
        if (!command.equals("bye")) {
            System.out.println(lineBreak2);
            System.out.println(command);
            return false;
        } else {
            System.out.println(lineBreak2);
            System.out.println("Goodbye! See you next time!\n" + lineBreak1);
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println(logo);
        Scanner commandInput = new Scanner(System.in);
        while (commandInput.hasNextLine()) {
            System.out.println(lineBreak1);
            String command = commandInput.nextLine();
            boolean isGoodBye = Duke.inputCommand(command);
            if (isGoodBye) {
                commandInput.close();
                break;
            }
        }
    }
}

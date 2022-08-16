import commands.List;
import input.Input;

import java.util.Scanner;

public class Duke {
    public static void output(String msg) {
        String line = "____________________________________________________________";
        String out = String.format("%s%nJARVIS:\n%s%n%s%n", line, msg, line);
        out.lines().forEach((eachLine) -> System.out.printf("\t%s%n",eachLine));
    }

    public static void parseInput(String input) throws InvalidArgumentException {
        if (input.contains("error")) {
            throw new InvalidArgumentException("Custom message for 'error'");
        }
    }

    public static void main(String[] args) {
        output("Hello,I'm JARVIS!\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.printf(">> ");
                String input = sc.nextLine().trim();

                Input ir = Input.newInput(input);

                if (input.equals("bye")) {
                    output("Bye. See you again soon!");
                    break;
                } else {
                    output(input);
                }

            } catch (Exception err) {
                output(err.getMessage());
            }
        }
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);


    }
}

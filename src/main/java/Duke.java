import commands.*;
import input.Input;
import models.task.TaskModel;

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
        String logo = "\n" +
                "     _   _    ___ __   __ ___  ___ \n" +
                "  _ | | /_\\  | _ \\\\ \\ / /|_ _|/ __|\n" +
                " | || |/ _ \\ |   / \\ V /  | | \\__ \\\n" +
                "  \\__//_/ \\_\\|_|_\\  \\_/  |___||___/\n";

        System.out.print(logo);
        output("Hello,I'm JARVIS!\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);

        TaskModel taskModel = new TaskModel();

        Command list = new ListCommand(taskModel);
        Command add = new AddCommand(taskModel);
        Command mark = new MarkCommand(taskModel);

        while (true) {
            try {
                System.out.printf(">> ");
                String input = sc.nextLine().trim();

                Input ir = Input.newInput(input);

                Command toRun = add;

                if (input.equals("bye")) {
                    output("Bye. See you again soon!");
                    break;
                } else if (list.isCommand(ir.getCommandName())) {
                    toRun = list;
                } else if (mark.isCommand(ir.getCommandName())) {
                    toRun = mark;
                }

                CommandResponse res = toRun.run(ir);
                output(res.getMessage());

            } catch (Exception err) {
                output(err.getMessage());
            }
        }
    }
}

import commands.*;
import input.Input;
import models.task.TaskModel;
import output.OutputLogger;

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
        OutputLogger.printIntroduction();
        Scanner sc = new Scanner(System.in);

        CommandRunner cmdRunner = new CommandRunner();

        while (true) {
            try {
                System.out.print(">> ");
                String input = sc.nextLine().trim();

                Input ir = Input.newInput(input);

                CommandResponse res = cmdRunner.run(ir);
                OutputLogger.output(res.getMessage());

                if (res.isExit()) {
                    break;
                }

            } catch (Exception err) {
                output(err.getMessage());
            }
        }
    }
}

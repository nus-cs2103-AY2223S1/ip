import commands.*;
import input.Input;
import output.OutputLogger;

import java.util.Scanner;

public class Duke {
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
                OutputLogger.output(err.getMessage());
            }
        }
    }
}

package duke;

import duke.exceptions.EndProgramException;

import java.util.Scanner;

class Ui {

    void run(TaskList tasks, Storage storage) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);
        InputParser parser = new InputParser();
        String input = "";
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            try {
                parser.parse(input, tasks, storage);
            } catch (Exception e) {
                System.out.println(e);
                if (e.equals(new EndProgramException()))
                    break;

            }
        }
    }
}

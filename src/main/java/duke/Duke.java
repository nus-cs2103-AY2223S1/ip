package duke;

import duke.command.Command;

import java.time.format.DateTimeParseException;

public class Duke {
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
    }

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETING = "Hello! I'm duke.Duke\n"
            + "What can I do for you?\n";

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        System.out.println(LOGO);
        System.out.println(GREETING);
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.read();
                Command command = Parser.parseInput(userInput);
                command.execute(this.tasks, this.ui);
                isExit = command.getExit();
            } catch (DukeException | IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Please format date in YYYY-MM-DD");
            }
        }
    }

    private static boolean isNumeric(String input) {
        for (char c : input.toCharArray()) {
            if (c < 48 || c > 57) {
                return false;
            }
        }
        return true;
    }
}

package duke;

import duke.command.Command;

public class Duke {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
        this.ui = new Ui();
    }

    public String add(Commands type) {
        String[] arguments;
        String msg = "";
        try {
            switch (type) {
                case TODO:
                    String input = sc.nextLine().trim();
                    if (input.isBlank()) {
                        throw new DukeException("Something went wrong! Could not read TODO.");
                    }
                    msg = this.taskList.addTask(new ToDo(input));
                    break;
                case DEADLINE:
                    arguments = sc.nextLine().trim().split("\\s/by\\s");
                    if (arguments.length < 2 || arguments[0].isBlank() || arguments[1].isBlank()) {
                        throw new DukeException("Something went wrong! Could not read DEADLINE.");
                    }
                    msg = this.taskList.addTask(new Deadline(arguments[0], arguments[1]));
                    break;
                case EVENT:
                    arguments = sc.nextLine().trim().split("\\s/at\\s");
                    if (arguments.length < 2 || arguments[0].isBlank() || arguments[1].isBlank()) {
                        throw new DukeException("Something went wrong! Could not read EVENT.");
                    }
                    msg = this.taskList.addTask(new Event(arguments[0], arguments[1]));
                    break;
            }
            return msg;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public void run() {
        Command cmd = new EmptyCommand();
        while (cmd.isExit()) {
            String input = this.ui.readCommand();
            cmd = Parser.parse(input);
            cmd.execute();
        }
    }

    public static void main(String[] args) {
        Duke dk = new Duke("data/duke.txt");
        dk.run();
    }
}

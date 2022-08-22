package duke;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private Parser parser;
    private TaskList taskList;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void updateTaskList(TaskList taskList) {
        this.taskList = taskList;
        this.parser = new Parser(this.taskList);
    }

    public void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n"
                + logo
                + "\nWhat can I do for you?");
    }
    public boolean handleInput() throws DukeException {
        String input = this.scanner.nextLine();

        Command command = this.parser.getCommand(input);
        return this.parser.executeCommand(command, input);
    }


}

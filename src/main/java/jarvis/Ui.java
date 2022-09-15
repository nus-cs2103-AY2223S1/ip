package jarvis;

import java.util.Scanner;

public class Ui {
    private TaskList taskList;
    private Parser parser;
    private Storage storage;
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Ui(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
        this.parser = new Parser(taskList, storage);
    }
    public String showLoadingError() {
        return "Oops, something went wrong when loading";
    }

    public void run() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Hello! I'm iTask\n" +
                "What can I do for you?");
        while (true) {
            String input = myObj.nextLine().trim();// Read user input
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            parser.Parse(input);
        }
    }
}

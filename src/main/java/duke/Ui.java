package duke;

import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    public String readCommand() {
        System.out.print("\n> ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    
    public void showWelcome() {
        String logo = " ____        _        \n" +
                "|  _ \\ _   _| | _____ \n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
    }

    public Ui showError(String message) {
        System.out.println(message);
        return this;
    }

    public Ui showMessage(String message) {
        System.out.println(message);
        return this;
    }
    
    public Ui showTaskListSize(TaskList tasks) {
        System.out.printf("You now have %d %s.\n",
                tasks.size(),
                tasks.size() == 1 ? "task" : "tasks"
        );
        return this;
    }
}
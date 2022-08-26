package duke;

import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private UserInterface userInterface;

    public Duke(String path) {
        try {
            this.taskList = new TaskList();
            storage = new Storage(path, taskList);
            userInterface = new UserInterface(taskList);
            storage.load();
        } catch (IOException e) {
            UserInterface.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/tasks.txt").run();
    }

    public void run() throws DukeException {
        ArrayList<Task> myList = new ArrayList<>();
        boolean bye = false;
        Scanner scanner = new Scanner(System.in);
        EventHandler handler = new EventHandler(this.taskList, this.userInterface);
        System.out.println("Hello my name is uncle raymond");

        while (!bye) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                bye = true;

                userInterface.sayBye();
            } else if (input.equals("list")) {
                userInterface.printList();
            } else if (input.startsWith("mark")) {
                handler.markTask(input);
            } else if (input.startsWith("unmark")) {
                handler.unmarkTask(input);
            } else if (input.startsWith("delete")) {
                int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                handler.deleteTask(taskIndex);
            } else if (input.startsWith("todo")) {
                handler.addTodo(input);
            } else if (input.startsWith("deadline")) {
                handler.addDeadline(input);
            } else if (input.startsWith("event")) {
                handler.addEvent(input);
            } else {
                throw new DukeUnknownCommandException();
            }
            storage.save();

        }

        scanner.close();
    }

}

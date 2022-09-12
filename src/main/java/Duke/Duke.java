package Duke;


import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;


public class Duke {

    private Database storage;
    private TaskList taskList;
    private Graphics graphics;

    public Duke(String path) {
        try {
            this.taskList = new TaskList();
            storage = new Database(path, taskList);
            graphics = new Graphics(taskList);
            storage.load();
        } catch (IOException e) {
            Graphics.loadingError();
            this.taskList = new TaskList();
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("Database/duke.txt").run();
    }

    public void run() throws DukeException {
        ArrayList<Task> myList = new ArrayList<>();
        boolean bye = false;
        Scanner scanner = new Scanner(System.in);
        ManageEvents manage = new ManageEvents(this.taskList, this.graphics);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("___________________________________");
        System.out.println("Hello! I'm Duke\n What can I do for you?");
        System.out.println("____________________________________");

        while (!bye) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                bye = true;
                graphics.sayGoodbye();
            } else if (input.equals("list")) {
                graphics.printList();
            } else if (input.startsWith("mark")) {
                manage.markTask(input);
            } else if (input.startsWith("unmark")) {
                manage.unmarkTask(input);
            } else if (input.startsWith("delete")) {
                int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                manage.deleteTask(taskIndex);
            } else if (input.startsWith("todo")) {
                manage.addTodo(input);
            } else if (input.startsWith("deadline")) {
                manage.addDeadline(input);
            } else if (input.startsWith("event")) {
                manage.addEvent(input);
            } else if (input.startsWith("find")) {
                manage.find(input);
            } else {
                throw new IllegalCommandException();
            }
            storage.save();

        }

        scanner.close();
    }

}
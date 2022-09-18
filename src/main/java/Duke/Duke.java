package Duke;

import java.io.IOException;


public class Duke {

    private Database database;
    private TaskList taskList;
    private Graphics graphics;


    public Duke(String path) {
        try {
            this.taskList = new TaskList();
            database = new Database(path, taskList);
            graphics = new Graphics(taskList);
            database.load();
        } catch (IOException e) {
            Graphics.loadingError();
            this.taskList = new TaskList();
        }
    }



    public static void main(String[] args) throws DukeException {
        new Duke("Database/duke.txt");
    }

    public String getResponse(String input) throws DukeException {

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
        if (input.equals("bye")) {
            database.save();
            return graphics.sayGoodbye();
        } else if (input.equals("list")) {
            database.save();
            return graphics.printList();
        } else if (input.startsWith("mark")) {
            database.save();
            return manage.markTask(input);
        } else if (input.startsWith("unmark")) {
            database.save();
            return manage.unmarkTask(input);
        } else if (input.startsWith("delete")) {
            int taskIndex = Integer.parseInt(input.substring(7)) - 1;
            database.save();
            return manage.deleteTask(taskIndex);
        } else if (input.startsWith("todo")) {
            database.save();
            return manage.addTodo(input);
        } else if (input.startsWith("deadline")) {
            database.save();
            return manage.addDeadline(input);
        } else if (input.startsWith("event")) {
            database.save();
            return manage.addEvent(input);
        } else if (input.startsWith("find")) {
            database.save();
            return manage.find(input);
        } else {
            throw new IllegalCommandException();
        }


    }

}
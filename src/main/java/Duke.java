import java.io.IOException;
import java.util.ArrayList;

class Duke {
    private TaskList taskList = new TaskList();
    private Parser parser = new Parser();

    public void start() {
        Storage storage = new Storage("data.txt");
        storage.fillData(taskList);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        UserInterface.print("Hello from\n" + logo);

        fillParser();

        while (true) {
            String input = UserInterface.read();
            if (input.equals("bye")) {
                try {
                    storage.SaveToStorage(taskList);
                } catch (IOException e) {
                    UserInterface.print(e.getMessage());
                }
                UserInterface.print("Bye. Hope to see you again soon!");
                break;
            }
            try {
                parser.executeCommand(input);
            } catch (DukeException e) {
                UserInterface.print(e.getMessage());
            }
        }
    }

    private void fillParser() {
        parser.addCommand("todo", argument -> {
            if (argument.equals("")){
                throw DukeException.invalidArgument;
            }
            Task item = Task.ToDo(argument);
            taskList.addTask(item);
            UserInterface.print("added: " + item);
            UserInterface.print("Now you have " + taskList.getSize() + " tasks in the list.");
        });

        parser.addCommand("deadline", argument -> {
            if (argument.equals("")){
                throw DukeException.invalidArgument;
            }
            Task item = Task.Deadline(argument);
            taskList.addTask(item);
            UserInterface.print("added: " + item);
            UserInterface.print("Now you have " + taskList.getSize() + " tasks in the list.");
        });

        parser.addCommand("event", argument -> {
            if (argument.equals("")){
                throw DukeException.invalidArgument;
            }
            Task item = Task.Event(argument);
            taskList.addTask(item);
            UserInterface.print("added: " + item);
            UserInterface.print("Now you have " + taskList.getSize() + " tasks in the list.");
        });

        parser.addCommand("mark", argument -> {
            try {
                int id = Integer.parseInt(argument);
                taskList.getTask(id - 1).changeMark(true);
                UserInterface.print("Nice! I've marked this task as done:\n" +
                        taskList.getTask(id - 1));
            } catch (NumberFormatException e){
                throw DukeException.invalidArgument;
            }
        });

        parser.addCommand("unmark", argument -> {
            try {
                int id = Integer.parseInt(argument);
                taskList.getTask(id - 1).changeMark(false);
                UserInterface.print("OK, I've marked this task as not done yet:\n" +
                        taskList.getTask(id - 1));
            } catch (NumberFormatException e){
                throw DukeException.invalidArgument;
            }
        });

        parser.addCommand("delete", argument -> {
            try {
                int id = Integer.parseInt(argument);
                Task deletedTask = taskList.getTask(id - 1);
                taskList.deleteTask(id - 1);
                UserInterface.print("Noted. I've removed this task:\n" +
                        deletedTask);
                UserInterface.print("Now you have " + taskList.getSize() + " tasks in the list.");
            } catch (NumberFormatException e){
                throw DukeException.invalidArgument;
            }
        });

        parser.addCommand("list", argument -> {
            if (!argument.equals("")){
                throw DukeException.invalidArgument;
            }
            UserInterface.print(taskList.toString());
        });
    }
}

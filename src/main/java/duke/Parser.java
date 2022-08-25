package duke;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private TaskList tasks;
    final static ArrayList<String> validCommands = new ArrayList<>(List.of("list", "mark", "unmark", "todo", "deadline", "event", "delete", "bye"));

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    public String parseCommand(String str) throws DukeException {
        String command = str.split(" ", 2)[0];
        if (validCommands.contains(command)) return command;
        throw new DukeException("OOPS!!! I'm sorry but I don't know what that means :-(");
    }

    public boolean isBye(String str) {
        return str.equals("bye");
    }

    public TaskList executeCommand(String command, String str) throws DukeException {
        Ui ui = new Ui();
        try {
            switch (command) {
            case "list":
                ui.displayList(this.tasks);
                break;
            case "mark":
                String s1 = str.substring(5);
                int i1 = Integer.parseInt(s1);
                this.tasks.markTask(i1 - 1);
                break;
            case "unmark":
                String s2 = str.substring(7);
                int i2 = Integer.parseInt(s2);
                this.tasks.unmarkTask(i2 - 1);
                break;
            case "todo":
                if (str.length() == 4)
                    throw new DukeException("OOPS!!! I'm sorry but description of a todo cannot be empty");
                String s3 = str.substring(5);
                if (s3.equals(" ") || s3.equals(""))
                    throw new DukeException("OOPS!!! I'm sorry but description of a todo cannot be empty");
                Todo t = new Todo(s3);
                this.tasks.addTask(t);
                break;
            case "deadline":
                if (str.length() == 8)
                    throw new DukeException("OOPS!!! I'm sorry but description of a deadline cannot be empty");
                String s4 = str.substring(9);
                String[] deadlineResult = s4.split(" /by ");
                Deadline d = new Deadline(deadlineResult[0], deadlineResult[1]);
                this.tasks.addTask(d);
                break;
            case "event":
                if (str.length() == 5)
                    throw new DukeException("OOPS!!! I'm sorry but description or time period of an event cannot be empty");
                String s5 = str.substring(6);
                String[] eventResult = s5.split(" /at ");
                Event e = new Event(eventResult[0], eventResult[1]);
                this.tasks.addTask(e);
                break;
            case "delete":
                String s6 = str.substring(7);
                int i = Integer.parseInt(s6);
                this.tasks.deleteTask(i - 1);
                break;
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return this.tasks;
    }

}

package dukebot.command;

import dukebot.Deadline;
import dukebot.DukeException;
import dukebot.Event;
import dukebot.Task;
import dukebot.TaskList;
import dukebot.ToDo;
import dukebot.Ui;

public class NewTaskCommand extends Command {

    private String command;

    private TaskList tasks;

    public NewTaskCommand(String command, TaskList tasks) {
        this.command = command;
        this.tasks = tasks;
    }

    @Override
    public String execute() throws DukeException {
        Task newTask;
        if (command.startsWith("todo")) {
            if (command.equals("todo")) {
                throw new DukeException("Description of todo cannot be empty");
            }
            String[] s = command.split(" ", 2);
            newTask = new ToDo(s[1]);
        } else if (command.startsWith("event")) {
            command = command.replace("event ", "");
            String[] s = command.split(" /at ", 2);
            if (s.length != 2) {
                throw new DukeException("Create event in this format: event <event> /at <time>");
            }
            newTask = new Event(s[0], s[1]);
        } else {
            command = command.replace("deadline ", "");
            String[] s = command.split(" /by ", 2);
            if (s.length != 2) {
                throw new DukeException("Create deadline in this format: deadline <deadline> /by <time>");
            }
            newTask = new Deadline(s[0], s[1]);
        }
        tasks.addTask(newTask);
        return Ui.showNewTask(newTask);
    }
}

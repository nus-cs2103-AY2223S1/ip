package jarvis.command;

import jarvis.JarvisException;
import jarvis.storage.Storage;
import jarvis.task.*;

public class AddCommand extends Command {
    public AddCommand(String command) {
        super(command);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws JarvisException {
        Task task;
        switch(super.getKeyCommand()) {
        case "todo":
            task = new ToDo(super.getDescription());
            tasks.add(task);
            storage.saveTasks(tasks);
            return String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                    task, tasks.size());
        case "deadline":
            task = new Deadline(super.getDescription(), super.getDate());
            tasks.add(task);
            storage.saveTasks(tasks);
            return String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                    task, tasks.size());
        case "event":
            task = new Event(super.getDescription(), super.getDate());
            tasks.add(task);
            storage.saveTasks(tasks);
            return String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                    task, tasks.size());
        default:
            throw new JarvisException("Unrecognised. Please enter a valid command.");
        }
    }
}

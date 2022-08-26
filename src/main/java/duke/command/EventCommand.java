package duke.command;

import duke.exception.IllegalDescriptionException;
import duke.logic.TaskList;
import duke.task.Event;

import java.time.LocalDate;

public class EventCommand extends Command {
    private TaskList taskList;
    private String description;
    private LocalDate time;

    public EventCommand(TaskList taskList, String description, LocalDate time) throws IllegalDescriptionException {
        this.taskList = taskList;
        //double check
        if (description.length() > 0) {
            this.description = description;
        } else {
            throw new IllegalDescriptionException("No description specified.");
        }
        this.time = time;
    }

    @Override
    public void run() {
        taskList.add(new Event(description, time));
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1).toString());
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
    }
}
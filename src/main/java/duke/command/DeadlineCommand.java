package duke.command;

import duke.task.Deadline;
import duke.exception.IllegalDescriptionException;
import duke.logic.TaskList;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private TaskList taskList;
    private String description;
    private LocalDate time;

    public DeadlineCommand(TaskList taskList, String description, LocalDate time) throws IllegalDescriptionException {
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
        taskList.add(new Deadline(description, time));
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1).toString());
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
    }
}

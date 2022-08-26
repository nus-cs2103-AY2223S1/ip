package duke.command;

import duke.exception.IllegalDescriptionException;
import duke.logic.TaskList;
import duke.task.Event;

import java.time.LocalDate;

/**
 * EventCommand is a command for Duke to remember an event.
 *
 * @author totsukatomofumi
 */
public class EventCommand extends Command {
    /** Task list the command has to add an event to. */
    private TaskList taskList;

    /** Description of the event. */
    private String description;

    /** Time the event is at. */
    private LocalDate time;

    /**
     * Constructor for the command.
     *
     * @param taskList the task list the command will modify.
     * @param description the description of the event.
     * @param time the time the event is at.
     * @throws IllegalDescriptionException If no description is specified, including just whitespaces.
     */
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

    /**
     * Adds new event to the list of tasks.
     */
    @Override
    public void run() {
        taskList.add(new Event(description, time));
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1).toString());
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
    }
}
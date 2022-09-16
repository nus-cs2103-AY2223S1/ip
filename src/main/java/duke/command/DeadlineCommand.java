package duke.command;

import duke.task.Deadline;
import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

    /**
     * A constructor that encapsulates the information of a new deadline task.
     *
     * @param parameters input of the command, where the taskName and deadline will be interpreted.
     */
    public DeadlineCommand(String parameters) {
        super((tasks, ui, storage) -> {
            try {

                String taskName = parameters.split(Deadline.KEYWORD_TO_SPLIT)[0];
                String by = parameters.split(Deadline.KEYWORD_TO_SPLIT)[1];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Task.TIME_INPUT_PATTERN);
                LocalDateTime byDateTime = LocalDateTime.parse(by, formatter);
                Task task = new Deadline(taskName, byDateTime);

                tasks.add(task);

                ui.showAddedTaskMessage(task);
                ui.countTasks(tasks);

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please input a valid date/time!");
            } catch (DateTimeParseException e) {
                System.out.println("Please input the date and time in the following format: " + Task.TIME_INPUT_PATTERN);
            }
        });
    }

}

package duke.command;

import duke.task.Task;
import duke.task.TaskList;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.function.Predicate;

public class FindDeadlinesCommand extends Command {

    /**
     * A constructor that encapsulates the information of finding tasks with a deadline.
     *
     * @param parameters input of the deadline for which we want to search.
     */
    public FindDeadlinesCommand(String parameters) {
        super((tasks, ui, storage) -> {
            try {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Task.DEADLINE_TIME_INPUT_PATTERN);
                LocalDate deadlineDate = LocalDate.parse(parameters, formatter);

                Predicate<? super Task> areDatesEqual = t ->
                        t.getDateTime().getYear() == deadlineDate.getYear() &&
                        t.getDateTime().getDayOfYear() == deadlineDate.getDayOfYear();
                TaskList filteredTasks = tasks.filterTasksWithThisPredicate(areDatesEqual);
                ui.listFilteredTasks(tasks, filteredTasks);


            } catch (DateTimeParseException e) {
                ui.showError("Please input the date and time in the following format: " + Task.DEADLINE_TIME_INPUT_PATTERN);
            }
        });
    }
}

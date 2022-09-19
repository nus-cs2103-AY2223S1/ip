package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.*;
import duke.utils.Interval;

/**
 * A handler class for Mark Commands.
 */
public class MarkHandler {
    /**
     * Handles the MARK Duke command.
     * Marks a Task as done from the provided list based on position index provided in input.
     *
     * @param list TaskList containing the Tasks to mark.
     * @param input Task number to mark as done, in String format.
     * @return Response of the executed MARK Command.
     **/
    public static String getResponse(TaskList list, String input) {
        try {
            int taskNum = Integer.parseInt(input) - 1;
            Task t = list.get(taskNum);
            if (t.getIsRecurring()) {
                String newTask = markRecurringTask(list, t);
                String response = "Nice! I've marked this task as done:\n"
                        + t
                        + "\n\n"
                        + "A new recurring task has been created:\n"
                        + newTask;
                return response;
            } else {
                markNonRecurringTask(t);
                return ("Nice! I've marked this task as done:\n" + t);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            int listSize = list.size();
            return (String.format("Please enter a valid number from 1 to %d.%n", listSize));
        } catch (DukeException exception) {
            return exception.getMessage();
        }
    }

    /**
     * Marks a non-recurring Task as complete.
     *
     * @param task Task to be marked complete.
     */
    public static void markNonRecurringTask(Task task) throws DukeException {
        task.markAsDone();
    }

    /**
     * Marks a recurring Task as complete, and adds subsequent recurring Task.
     *
     * @param list TaskList to add the next recurring task to.
     * @param task Recurring task to marked complete.
     */
    public static String markRecurringTask(TaskList list, Task task) throws DukeException {
        String description = task.getDescription();
        Interval interval = task.getInterval();
        task.markAsDone();
        if (task instanceof Event) {
            FormattedDate formattedDate = ((Event) task).getEventDate();
            System.out.println(interval);
            FormattedDate nextRecurringDate = FormattedDate.addIntervalToDate(formattedDate, interval);
            Event nextRecurringEvent = new Event(description, false, nextRecurringDate, interval);
            list.add(nextRecurringEvent);
            return nextRecurringEvent.toString();
        }
        if (task instanceof Deadline) {
            FormattedDate formattedDate = ((Deadline) task).getDeadlineDate();
            System.out.println(interval);
            FormattedDate nextRecurringDate = FormattedDate.addIntervalToDate(formattedDate, interval);
            Deadline nextRecurringEvent = new Deadline(description, false, nextRecurringDate, interval);
            list.add(nextRecurringEvent);
            return nextRecurringEvent.toString();
        }
        throw new DukeException("Unable to mark recurring task.");
    }
}

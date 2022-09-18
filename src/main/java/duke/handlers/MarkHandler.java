package duke.handlers;

import duke.models.Event;
import duke.models.FormattedDate;
import duke.models.Task;
import duke.models.TaskList;
import duke.utils.Interval;

public class MarkHandler {
    /**
     * Handles the MARK Duke command.
     * Marks a Task as done from the provided list based on position index provided in input.
     *
     * @return Response of the executed MARK Command.
     * @param list: TaskList containing the Tasks to mark.
     * @param input: Task number to mark as done, in String format.
     **/
    public static String getResponse(TaskList list, String input) {
        int taskNum = Integer.parseInt(input) - 1;
        try {
            Task t = list.get(taskNum);
            if (t.getIsRecurring()) {
                markRecurringTask(list, t);
            } else {
                markNonRecurringTask(t);
            }
            t.markAsDone();
            return ("Nice! I've marked this task as done:\n" + t);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            int listSize = list.size();
            return (String.format("Please enter a valid number from 1 to %d.%n", listSize));
        }
    }

    public static void markNonRecurringTask(Task task) {
        task.markAsDone();
    }

    public static void markRecurringTask(TaskList list, Task task) {
        String description = task.getDescription();
        Interval interval = task.getInterval();
        task.markAsDone();
        if (task instanceof Event) {
            FormattedDate formattedDate = ((Event) task).getFormattedDate();
            System.out.println(interval);
            FormattedDate nextRecurringDate = FormattedDate.addIntervalToDate(formattedDate, interval);
            Event nextRecurringEvent = new Event(description, false, nextRecurringDate, interval);
            list.add(nextRecurringEvent);
        }
    }
}

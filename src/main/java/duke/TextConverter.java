package duke;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class that deals with converting texts into tasks
 */
public class TextConverter {

    /**
     * Method that converts text into Tasks and adding it into an array list
     *
     * @param text The text
     * @param lst Arraylist of tasks
     * @return ArrayList<Task> The newly updated ArrayList with the task added
     */
    public static ArrayList<Task> textToTask(String text, ArrayList<Task> lst) {
        String[] stringDetails = text.split("\\|");
        String taskType = stringDetails[0];
        String markStatus = stringDetails[1];
        String description = stringDetails[2];
        switch (taskType) {
            case "T ":
                textToTodo(lst, markStatus, description);
                break;
            case "D ":
                textToDeadline(lst, markStatus, description, stringDetails[3]);
                break;
            case "E ":
                textToEvent(lst, markStatus, description, stringDetails[3]);
                break;
        }
        return lst;
    }

    /**
     * Method that converts text into Todo and adding it into an array list
     *
     * @param lst The list of all the tasks so far
     * @param markStatus Whether the Todo is marked
     * @param description The description of the Todo
     */
    public static void textToTodo(ArrayList<Task> lst, String markStatus, String description) {
        Todo todo = new Todo(description.substring(1));
        if (markStatus.equals(" 1 ")) {
            todo.mark();
        }
        lst.add(todo);
    }

    /**
     * Method that converts text into Deadline and adding it into an array list
     *
     * @param lst The list of all the tasks so far
     * @param markStatus Whether the Deadline is marked
     * @param description The description of the Deadline
     * @param date The date of the Deadline
     */
    public static void textToDeadline(ArrayList<Task> lst, String markStatus, String description, String date) {
        String by = date.substring(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate newDate = LocalDate.parse(by, formatter);
        Deadline deadline = new Deadline(description.substring(1), newDate);
        if (markStatus.equals(" 1 ")) {
            deadline.mark();
        }
        lst.add(deadline);
    }

    /**
     * Method that converts text into Event and adding it into an array list
     *
     * @param lst The list of all the tasks so far
     * @param markStatus Whether the Event is marked
     * @param description The description of the Event
     * @param time The time of the Event
     */
    public static void textToEvent(ArrayList<Task> lst, String markStatus, String description, String time) {
        String at = time.substring(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate newTime = LocalDate.parse(at, formatter);
        Event event = new Event(description.substring(1), newTime);
        if (markStatus.equals(" 1 ")) {
            event.mark();
        }
        lst.add(event);
    }
}

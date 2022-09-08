package duke;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class textConverter {
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

    public static void textToTodo(ArrayList<Task> lst, String markStatus, String description) {
        Todo todo = new Todo(description.substring(1));
        if (markStatus.equals(" 1 ")) {
            todo.mark();
        }
        lst.add(todo);
    }

    public static void textToDeadline(ArrayList<Task> lst, String markStatus, String description, String time) {
        String by = time.substring(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate date = LocalDate.parse(by, formatter);
        Deadline deadline = new Deadline(description.substring(1), date);
        if (markStatus.equals(" 1 ")) {
            deadline.mark();
        }
        lst.add(deadline);
    }

    public static void textToEvent(ArrayList<Task> lst, String markStatus, String description, String at) {
        Event event = new Event(description.substring(1), at.substring(1));
        if (markStatus.equals(" 1 ")) {
            event.mark();
        }
        lst.add(event);
    }
}

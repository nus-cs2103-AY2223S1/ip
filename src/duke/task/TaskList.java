package duke.task;

import java.util.ArrayList;
import java.util.List;
import duke.exception.*;

public class TaskList {

    private static List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public static Task markTask(Integer index) {
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    public static Task unmarkTask(Integer index) {
        Task task = tasks.get(index);
        task.unMark();
        return task;
    }

    public static Task deleteTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    public static Task addTodoTask(String description) throws EmptyDescriptionException {
        if (description == null) {
            throw new EmptyDescriptionException();
        }
        Task todoTask = new Todo(description);
        tasks.add(todoTask);
        return todoTask;
    }

    //TODO improve the code
    public static Task addDeadlineTask(String description) throws EmptyDescriptionException {
        if (description == null) {
            throw new EmptyDescriptionException();
        }
        String[] splittedStr = description.split("/");
        Task deadlineTask = new Deadline(splittedStr[0], splittedStr[1].substring(3));
        tasks.add(deadlineTask);
        return deadlineTask;
    }

    //TODO improve the code
    public static Task addEventTask(String description) throws EmptyDescriptionException {
        if (description == null) {
            throw new EmptyDescriptionException();
        }
        String[] splittedStr = description.split("/");
        Task eventTask = new Event(splittedStr[0], splittedStr[1].substring(3));
        tasks.add(eventTask);
        return eventTask;
    }

}

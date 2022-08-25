import TaskTypes.Deadline;
import TaskTypes.Event;
import TaskTypes.Task;
import TaskTypes.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public Parser() {
    }

    public Task parseSave(String save) throws Exception {
        String[] taskElements = save.split("\\|");
        Task newTask = null;
        boolean isDone = taskElements[1].equals("0");
        try {
            switch (taskElements[0]) {
                case "T": {
                    newTask = new ToDo(taskElements[2], isDone);
                    break;
                }
                case "D": {
                    String dateString = taskElements[3];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
                    newTask = new Deadline(taskElements[2], dateTime);
                    break;
                }
                case "E": {
                    String dateString = taskElements[3];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
                    newTask = new Event(taskElements[2], dateTime);
                    break;
                }
                default: {
                    throw new DukeException("Task type cannot be parsed");
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return newTask;
    }

}

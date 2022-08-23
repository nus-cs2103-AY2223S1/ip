package jduke.data;

import jduke.task.Deadline;
import jduke.task.Event;
import jduke.task.Task;
import jduke.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<String> storedTasks) {
        this.tasks = new ArrayList<>();
        for (String task : storedTasks) {
            String[] taskParams = task.split(" \\| ");
            switch (taskParams[0]) {
            case "T":
                tasks.add(new Todo(taskParams[2]));
                break;
            case "D":
                tasks.add(new Deadline(taskParams[2], taskParams[3]));
                break;
            case "E":
                tasks.add(new Event(taskParams[2], taskParams[3]));
                break;
            }
            if (taskParams[1].equals("1")) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        }
    }

    public String listTasks(String params) {
        StringBuilder sb = new StringBuilder();
        if (tasks.size() == 0) {
            return "|  no tasks found\n";
        } else if (params.equals("")) {
            for (int pos = 0; pos < tasks.size(); pos++) {
                sb.append(String.format("%d ==> %s%n", pos + 1, tasks.get(pos)));
            }
        } else {
            LocalDate date = LocalDate.parse(params,
                    DateTimeFormatter.ofPattern("d/M/yyyy"));
            boolean hasTask = false;
            for (int pos = 0; pos < tasks.size(); pos++) {
                if (tasks.get(pos).isEqualDate(date)) {
                    hasTask = true;
                    sb.append(String.format("%d ==> %s%n", pos + 1, tasks.get(pos)));
                }
            }
            if (!hasTask) {
                return "|  no tasks found\n";
            }
        }
        return sb.toString();
    }

    public String addTodo(String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        return String.format("|  added task:%n|    %s%n", todo);
    }

    public String addDeadline(String description, String timing) {
        Deadline deadline = new Deadline(description, timing);
        tasks.add(deadline);
        return String.format("|  added task:%n|    %s%n", deadline);
    }

    public String addEvent(String description, String timing) {
        Event event = new Event(description, timing);
        tasks.add(event);
        return String.format("|  added task:%n|    %s%n", event);
    }

    public String markTask(String params) {
        int pos = Integer.parseInt(params) - 1;
        if (pos < 0 || tasks.size() <= pos) {
            return String.format("|  invalid task number%n|  max id is %d%n", tasks.size());
        }
        tasks.get(pos).markAsDone();
        return String.format("|  marked task:%n|    %s%n", tasks.get(pos));
    }

    public String unmarkTask(String params) {
        int pos = Integer.parseInt(params) - 1;
        if (pos < 0 || tasks.size() <= pos) {
            return String.format("|  invalid task number%n|  max id is %d%n", tasks.size());
        }
        tasks.get(pos).markAsUndone();
        return String.format("|  unmarked task:%n|    %s%n", tasks.get(pos));
    }

    public String deleteTask(String params) {
        int pos = Integer.parseInt(params) - 1;
        if (pos < 0 || tasks.size() <= pos) {
            return String.format("|  invalid task number%n|  max id is %d%n", tasks.size());
        }
        String removedTask = tasks.get(pos).toString();
        tasks.remove(pos);
        return String.format("|  deleted task:%n|    %s%n", removedTask);
    }

    public ArrayList<String> getTasksToStore() {
        ArrayList<String> storage = new ArrayList<>();
        for (Task task : this.tasks) {
            storage.add(task.toStorageFormat());
        }
        return storage;
    }

}

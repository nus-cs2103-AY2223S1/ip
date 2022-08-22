package jduke.data;

import jduke.Jduke;
import jduke.data.exception.JdukeException;
import jduke.task.Deadline;
import jduke.task.Event;
import jduke.task.Task;
import jduke.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private static final String TODO_FORMAT = "todo <description>";
    private static final String EVENT_FORMAT = "event <description> /at <dd/mm/yyyy> <hhmm | optional>";
    private static final String DEADLINE_FORMAT = "deadline <description> /by <dd/mm/yyyy> <hhmm | optional>";
    private static final String LIST_FORMAT = "list <dd/mm/yyyy | optional>";
    private static final String MARK_FORMAT = "mark <integer>";
    private static final String UNMARK_FORMAT = "unmark <integer>";
    private static final String DELETE_FORMAT = "delete <integer>";
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
                tasks.add(new ToDo(taskParams[2]));
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

    public void listTasks(String params) throws JdukeException {
        if (!(params.matches("[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}") || params.matches(""))) {
            throw new JdukeException("invalid LIST format", LIST_FORMAT);
        }
        if (tasks.size() == 0) {
            System.out.println("|  no tasks found");
        } else if (params.equals("")) {
            for (int pos = 0; pos < tasks.size(); pos++) {
                System.out.printf("%d ==> %s%n", pos + 1, tasks.get(pos));
            }
        } else {
            LocalDate date = LocalDate.parse(params,
                    DateTimeFormatter.ofPattern("d/M/yyyy"));
            boolean hasTask = false;
            for (int pos = 0; pos < tasks.size(); pos++) {
                if (tasks.get(pos).isEqualDate(date)) {
                    hasTask = true;
                    System.out.printf("%d ==> %s%n", pos + 1, tasks.get(pos));
                }
            }
            if (!hasTask) {
                System.out.println("|  no tasks found");
            }
        }
    }

    public void printLastTask() {
        System.out.printf(
                "|  added task:%n|    %s%n|  %d%s in list%n",
                tasks.get(tasks.size() - 1),
                tasks.size(),
                (tasks.size() == 1 ? " task" : " tasks")
        );
    }

    public void addTodo(String params) throws JdukeException {
        if (!params.toLowerCase().matches("[^ ](.*)")) {
            throw new JdukeException("invalid TODO format", TODO_FORMAT);
        }
        tasks.add(new ToDo(params));
        printLastTask();
    }

    public void addDeadline(String params) throws JdukeException {
        if (!(params.toLowerCase().matches(
                "[^ ](.*) /by [0-9]{1,2}/[0-9]{1,2}/[0-9]{4} [0-9]{4}")
                || params.toLowerCase().matches("[^ ](.*) /by [0-9]{1,2}/[0-9]{1,2}/[0-9]{4}"))) {
            throw new JdukeException("invalid DEADLINE format", DEADLINE_FORMAT);
        }
        String[] deadlineParams = params.split(" /by ", 2);
        tasks.add(new Deadline(deadlineParams[0], deadlineParams[1]));
        printLastTask();
    }
    public void addEvent(String params) throws JdukeException {
        if (!(params.toLowerCase().matches(
                "[^ ](.*) /at [0-9]{1,2}/[0-9]{1,2}/[0-9]{4} [0-9]{4}")
                || params.toLowerCase().matches("[^ ](.*) /at [0-9]{1,2}/[0-9]{1,2}/[0-9]{4}"))) {
            throw new JdukeException("invalid EVENT format", EVENT_FORMAT);
        }
        String[] eventParams = params.split(" /at ", 2);
        tasks.add(new Event(eventParams[0], eventParams[1]));
        printLastTask();
    }

    public int handleTaskPos(String params, Jduke.Command command) throws JdukeException {
        if (!params.matches("([0-9]+)")) {
            switch (command) {
            case MARK:
                throw new JdukeException("invalid MARK format", MARK_FORMAT);
            case UNMARK:
                throw new JdukeException("invalid UNMARK format", UNMARK_FORMAT);
            case DELETE:
                throw new JdukeException("invalid DELETE format", DELETE_FORMAT);
            }
        }
        int pos = Integer.parseInt(params) - 1;
        if (pos < 0 || tasks.size() <= pos) {
            throw new JdukeException(
                    "invalid task number",
                    String.format("max index is %d", tasks.size())
            );
        }
        return pos;
    }
    public void unmarkTask(String params) throws JdukeException {
        int pos = handleTaskPos(params, Jduke.Command.UNMARK);
        tasks.get(pos).markAsUndone();
        System.out.printf("|  unmarked task:%n|    %s%n", tasks.get(pos));
    }
    public void markTask(String params) throws JdukeException {
        int pos = handleTaskPos(params, Jduke.Command.MARK);
        tasks.get(pos).markAsDone();
        System.out.printf("|  marked task:%n|    %s%n", tasks.get(pos));
    }
    public void deleteTask(String params) throws JdukeException {
        int pos = handleTaskPos(params, Jduke.Command.DELETE);
        System.out.printf(
                "|  deleted task:%n|    %s%n|  %d%s in list%n",
                tasks.get(pos),
                (tasks.size() - 1),
                (tasks.size() == 2 ? " task" : " tasks")
        );
        tasks.remove(pos);
    }

    public ArrayList<String> getTasksToStore() {
        ArrayList<String> storage = new ArrayList<>();
        for (Task task : this.tasks) {
            storage.add(task.toStorageFormat());
        }
        return storage;
    }

}

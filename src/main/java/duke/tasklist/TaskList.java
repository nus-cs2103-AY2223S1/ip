package duke.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.storage.Storage;

public class TaskList {
    private static TaskList tasklist;
    private final ArrayList<Task> storage;

    public TaskList() {
        this.storage = new ArrayList<>();
    }

    public void list() {
        if (this.storage.size() == 0) {
            System.out.println("List is currently empty");
        } else {
            System.out.println("Here are your tasks:");
            for (int i = 0; i < this.storage.size(); i++) {
                System.out.printf("%d.%s\n", i + 1,
                        this.storage.get(i).toString());
            }
        }
    }

    public void addTasksFromSave(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.
                ofPattern("yyyy-MM-dd HH:mm");
        String[] curr = input.split("\\s*\\|\\s*");
        String command = curr[0];

        switch (command) {
            case "T": {
                Task currTask = new Todo(curr[2]);
                if (curr[1].equals("1")) {
                    currTask.savedTaskMarkAsDone();
                }
                this.storage.add(currTask);
                break;
            }
            case "D": {
                Task currTask = new Deadline(curr[2],
                        LocalDateTime.parse(curr[3].trim(), formatter));
                if (curr[1].equals("1")) {
                    currTask.savedTaskMarkAsDone();
                }
                this.storage.add(currTask);
                break;
            }
            case "E": {
                Task currTask = new Event(curr[2],
                        LocalDateTime.parse(curr[3].trim(), formatter));
                if (curr[1].equals("1")) {
                    currTask.savedTaskMarkAsDone();
                }
                this.storage.add(currTask);
                break;
            }

        }
    }

    public void addTask(String[] input) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.
                    ofPattern("yyyy-MM-dd HHmm");
            Storage outputFile = Storage.getInstance();
            switch (input[0]) {
                case "todo":
                    if (input.length <= 1) {
                        throw new DukeException("Description of a todo cannot be empty!");
                    }
                    Todo todo = new Todo(input[1].trim());
                    storage.add(todo);
                    outputFile.writeToSavedFile();
                    System.out.printf("added %s\n", todo);
                    break;
                case "deadline":
                    if (input.length <= 1) {
                        throw new DukeException("Description of a deadline " +
                                "cannot be empty!");
                    }
                    if (!input[1].contains("/by") ||
                            input[1].indexOf("/by") == input[1].length() - 3) {
                        throw new DukeException("No date inserted for deadline");
                    }
                    String[] deadlineInfo = input[1].split("/by", 2);
                    Deadline deadline = new Deadline(deadlineInfo[0].trim(),
                            LocalDateTime.parse(deadlineInfo[1].trim(), formatter));
                    storage.add(deadline);
                    outputFile.writeToSavedFile();
                    System.out.printf("added %s\n", deadline);
                    break;
                case "event":
                    if (input.length <= 1) {
                        throw new DukeException("Description of an event " +
                                "cannot be empty!");
                    }
                    if (!input[1].contains("/at") ||
                            input[1].indexOf("/at") == input[1].length() - 3) {
                        throw new DukeException("No date inserted for event");

                    }
                    String[] eventInfo = input[1].split("/at", 2);
                    Event event = new Event(eventInfo[0].trim(),
                            LocalDateTime.parse(eventInfo[1].trim(), formatter));
                    storage.add(event);
                    outputFile.writeToSavedFile();
                    System.out.printf("added %s\n", event);
                    break;
                default:
                    throw new DukeException("Invalid task");
            }
            System.out.printf("Now you have %d tasks in the list\n",
                    storage.size());
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }

    }

    public void delete(String input) throws DukeException {
        if (storage.size() == 0) {
            throw new DukeException("No tasks to delete!");
        }
        if (input == null) {
            throw new DukeException("No input provided for deletion");
        }
        String s = input.replaceAll("\\D+", "");
        if (!s.equals("")) {
            int idx = Integer.parseInt(s);
            if (idx - 1 >= storage.size() || idx < 1) {
                throw new DukeException("Invalid selection for deletion");
            } else {
                Task t = storage.get(idx - 1);
                storage.remove(idx - 1);
                System.out.println("Removed the following task:\n" +
                        t);
            }
        } else {
            throw new DukeException("Invalid selection for deletion");
        }
    }

    public void unmark(String input) throws DukeException {
        if (this.storage.size() == 0) {
            throw new DukeException("No tasks to unmark!");
        } else {
            if (input == null) {
                throw new DukeException("No input provided for unmarking");
            }
            String s = input.replaceAll("\\D+", "");
            if (!s.equals("")) {
                int idx = Integer.parseInt(s);
                if (idx - 1 >= this.storage.size() || idx < 1) {
                    throw new DukeException("Invalid selection for unmarking");
                } else {
                    Task t = this.storage.get(idx - 1);
                    t.markAsNotDone();
                }
            } else {
                throw new DukeException("Invalid selection for unmarking");
            }
        }
    }

    public void mark(String input) throws DukeException {
        if (this.storage.size() == 0) {
            throw new DukeException("No tasks to mark!");
        } else {
            if (input == null) {
                throw new DukeException("No input provided for marking");
            }
            String s = input.replaceAll("\\D+", "");
            if (!s.equals("")) {
                int idx = Integer.parseInt(s);
                if (idx - 1 >= this.storage.size() || idx < 1) {
                    throw new DukeException("Invalid selection for marking");
                } else {
                    Task t = this.storage.get(idx - 1);
                    t.markAsDone();
                }
            } else {
                throw new DukeException("Invalid selection for marking");
            }
        }
    }

    public static TaskList getInstance() {
        if (tasklist == null) {
            tasklist = new TaskList();
        }
        return tasklist;
    }

    public void findWithFilter(String filter) {
        ArrayList<Task> filteredList = new ArrayList<>();

        for (Task x: this.storage) {
            if (x.toString().contains(filter)) {
                filteredList.add(x);
            }
        }

        if (filteredList.isEmpty()) {
            System.out.println("No matching tasks found");
        } else {
            System.out.println("Here are the tasks that match:");
            for (int i = 0; i < filteredList.size(); i++) {
                System.out.printf("%d.%s\n", i + 1,
                        filteredList.get(i).toString());
            }
        }
    }

    public ArrayList<Task> getTaskList() {
        ArrayList<Task> newList = new ArrayList<>();
        for (Task x: storage) {
            newList.add(x);
        }
        return newList;
    }
}

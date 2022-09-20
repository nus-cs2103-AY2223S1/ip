package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Encapsulates the list of tasks currently kept on memory.
 */
class TaskList {

    private final List<Task> tasks;

    /**
     * Create a new empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Create a new TaskList from a list of existing tasks.
     * @param lines The list of existing tasks.
     */
    public TaskList(Stream<String> lines) {
        tasks = populateList(lines);
    }

    /**
     * Populate the empty TaskList with list of existing tasks.
     * @param lines The list of existing tasks.
     * @return A new List<Task> containing the existing tasks.
     */
    List<Task> populateList(Stream<String> lines) {
        ArrayList<Task> arrayList = new ArrayList<>();
        lines.forEach(x -> {
            char type = x.charAt(1);
            boolean done = x.charAt(4) == 'X';
            String task;

            assert(type == 'T' || type == 'D' || type == 'E');

            if (type == 'D' || type == 'E') {
                task = x.substring(9, x.indexOf("(") - 1);
                String additional = x.substring(x.indexOf("(") + 6, x.indexOf(')'));
                if (type == 'D') {
                    arrayList.add(new Deadline(task, additional, done));
                } else {
                    arrayList.add(new Event(task, additional, done));
                }
            } else {
                task = x.substring(6);
                arrayList.add(new Todo(task, done));
            }
        });
        return arrayList;
    }

    /**
     * Looks for a task in TaskList matching query.
     * @param query The task to be matched.
     * @return Returns the matching task if found, or a nil response if not found.
     */
    String find(String query) {
        String[] response = new String[]{"Woof! This is what I found: \n"};
        tasks.stream().filter(x -> x.getName().contains(query))
                .forEach(x -> response[0] += x + "\n");
        return response[0];
    }

    List<Task> getTasks() {
        return tasks;
    }

    /**
     * Prints all tasks currently in memory.
     * @return Returns a string of all tasks currently in memory, to be printed.
     */
    String list() {
        String[] response = new String[]{"Woof! Here is everything you need: \n"};
        int counter[] = new int[]{1};
        tasks.forEach(x -> {
            response[0] += counter[0] + "." + x.toString() + "\n";
            counter[0]++;
        });
        return response[0];
    }

    /**
     * Marks a task as done or undone.
     * @param id The position of the task to mark.
     * @param done The status of the task.
     * @return Returns the description of the marked or unmarked task to be printed.
     */
    String mark(int id, boolean done) {
        String response = "";
        Task task = tasks.get(id - 1);
        task.setStatus(done);
        if (done) {
            response += "Good boy, I've marked this task as done. Time for a treat? \n";
        } else {
            response += "Woof, I've marked this task as not done yet: \n";
        }
        response += task;
        return response;
    }

    /**
     * Checks if the new task is a duplicate of an existing task.
     * @param name The name of the new task.
     * @param type The type of the new task.
     * @param additional Any additional information about the new task (deadline for Deadline tasks, eventTime for Events)
     * @return Returns true if the task already exists, else returns false.
     */
    boolean checkIfDuplicate(String name, String type, String additional) {
        if (type.equals("T")) {
            return tasks.stream().anyMatch(x -> x.getName().equals(name) && x.getClass() == Todo.class);
        } else {
            return tasks.stream().anyMatch(x -> {
                LocalDate date = LocalDate.parse(additional);
                if (x.getClass() == Deadline.class) {
                    return x.getName().equals(name) && ((Deadline) x).deadline.equals(date);
                } else if (x.getClass() == Event.class) {
                    return x.getName().equals(name) && ((Event) x).eventTime.equals(date);
                }
                return false;
            });
        }
    }

    /**
     * Adds a new task to the current list of tasks.
     * @param name Name of the task.
     * @param type Type of the task.
     * @param additional Deadline for Deadline tasks, eventTime for Event tasks. Not used for Todo tasks.
     * @return Returns a response, printing the added task.
     */
    String addTask(String name, String type, String additional) {
        if (checkIfDuplicate(name, type, additional)) {
            return "Woof! Duplicate task! Not added.";
        }
        String response = "";
        Task newTask;
        switch (type) {
        case "T":
            newTask = new Todo(name);
            break;
        case "D":
            newTask = new Deadline(name, additional);
            break;
        case "E":
            newTask = new Event(name, additional);
            break;
        default:
            return "Unknown task type";
        }
        tasks.add(newTask);
        response += "Woof!. I've added this task: \n";
        response += newTask + "\n";
        response += "Now you have " + tasks.size() + " tasks in the list.";
        return response;
    }

    /**
     * Removes a specified task from the current list of tasks.
     * @param id Position of the task.
     * @return Returns response containing the removed task, and number of tasks left.
     */
    String delete(int id) {
        String response = "";
        Task toRemove = tasks.remove(id - 1);
        response += "Woof! I've removed this task: \n";
        response += toRemove + "\n";
        response += "Now you have " + tasks.size() + " tasks in the list.";
        return response;
    }

}

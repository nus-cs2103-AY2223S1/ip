import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks = new ArrayList<>();

    public void list() {
        if (!tasks.isEmpty()) {
            System.out.println("Task list:");
            for (int i = 0; i < tasks.size(); i++) {
                int taskNum = i + 1;
                Task task = tasks.get(i);
                System.out.println(taskNum + "." + task);
            }
        } else {
            System.out.println("Your task list is empty!");
        }
    }

    private int getTaskNumber(String[] splitInputArray) throws DukeException {
        if (splitInputArray.length > 1) {
            try {
                return Integer.parseInt(splitInputArray[1]);
            } catch (NumberFormatException e) {
                throw new DukeException("Please specify a task number!");
            }
        } else {
            throw new DukeException("Please specify a task number!");
        }
    }

    public void changeTaskStatus(String[] splitInputArray) throws DukeException {
        int taskNum = getTaskNumber(splitInputArray);
        if (taskNum > 0 && taskNum <= tasks.size()) {
            Task task = tasks.get(taskNum - 1);
            if (splitInputArray[0].equals("mark")) {
                task.markAsDone();
            } else {
                task.markAsNotDone();
            }
        } else {
            throw new DukeException("No such task!");
        }
    }

    public void addTask(String[] splitInputArray) throws DukeException {
        String command = splitInputArray[0];
        boolean isToDo = command.equals("todo");
        if (splitInputArray.length < 2) {
            throw new DukeException("Please provide a task description" + (isToDo ? "!" : " and a date / time!"));
        }

        Task task;
        String details = splitInputArray[1];
        if (isToDo) {
            task = new ToDo(details);
        } else {
            boolean isDeadline = command.equals("deadline");
            int pos = details.indexOf(isDeadline ? " /by " : " /at ");
            if (pos > 0 && details.length() > pos + 5) {
                String description = details.substring(0, pos);
                String by = details.substring(pos + 5);
                task = isDeadline ? new Deadline(description, by) : new Event(description, by);
            } else {
                throw new DukeException("Please provide a task description and a date / time!");
            }
        }
        tasks.add(task);
        System.out.println("Task added:\n\t" + task);
    }

    public void deleteTask(String[] splitInputArray) throws DukeException {
        int taskNum = getTaskNumber(splitInputArray);
        if (taskNum > 0 && taskNum <= tasks.size()) {
            Task task = tasks.get(taskNum - 1);
            tasks.remove(taskNum - 1);
            System.out.println("Task removed:\n\t" + task);
        } else {
            throw new DukeException("No such task!");
        }
    }

    public void displayNumOfTasks() {
        System.out.println("You have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
    }
}

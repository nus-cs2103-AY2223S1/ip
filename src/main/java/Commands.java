import java.util.ArrayList;

public class Commands {
    protected static Ui ui = new Ui();

    public static void mark(String cmd, ArrayList<Task> taskList) throws DukeException{
        try {
            String number = cmd.split(" ")[1];
            int num = Integer.parseInt(number);
            Task task = taskList.get(num - 1);
            task.markAsDone();
            ui.marked(task);
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! The number you are talking about does not exist. " +
                    "\nPerhaps it is not a number at all? Please check again!");
        }
    }

    public static void unmark(String cmd, ArrayList<Task> taskList) throws DukeException{
        try {
            String number = cmd.split(" ")[1];
            int num = Integer.parseInt(number);
            Task task = taskList.get(num - 1);
            task.unmarkAsDone();
            ui.unmarked(task);
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! The number you are talking about does not exist." +
                    "\nPerhaps it is not a number at all? Please check again!");
        }
    }

    public static void deadline(String cmd, ArrayList<Task> taskList) throws DukeException {
        try {
            String taskName = cmd.substring(cmd.indexOf(" ") + 1, cmd.indexOf("/") - 1);
            String by = cmd.substring(cmd.indexOf("/") + 5);
            Task newTask = new Deadline(taskName, by);
            taskList.add(newTask);
            int amountOfTasks = taskList.size();
            ui.addTask(newTask, amountOfTasks);
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    public static void event(String cmd, ArrayList<Task> taskList) throws DukeException {
        try {
            String taskName = cmd.substring(cmd.indexOf(" ") + 1, cmd.indexOf("/") - 1);
            String at = cmd.substring(cmd.indexOf("/") + 5);
            Task newTask = new Event(taskName, at);
            taskList.add(newTask);
            int amountOfTasks = taskList.size();
            ui.addTask(newTask, amountOfTasks);
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
    }

    public static void toDo(String cmd, ArrayList<Task> taskList) throws DukeException {
        try {
            if (cmd.split(" ")[1] != null) {
                String taskName = cmd.substring(cmd.indexOf(" ") + 1);
                Task newTask = new ToDo(taskName);
                taskList.add(newTask);
                int amountOfTasks = taskList.size();
                ui.addTask(newTask, amountOfTasks);
            }
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! Please specify what you want to do!");
        }
    }

    public static void delete(String cmd, ArrayList<Task> taskList) throws DukeException {
        try {
            String number = cmd.split(" ")[1];
            int num = Integer.parseInt(number);
            Task task = taskList.get(num - 1);
            taskList.remove(num - 1);
            int amountOfTasksLeft = taskList.size();
            ui.delete(task, amountOfTasksLeft);
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! Looks like the task you're looking for does not exist :-(");
        }
    }

}

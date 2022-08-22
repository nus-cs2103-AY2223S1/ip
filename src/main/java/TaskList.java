import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    private int taskCount;
    private Ui ui;

    public TaskList(ArrayList<Task> arr) {
        tasks = arr;
        ui = new Ui();
        taskCount = tasks.size();
    }

    public TaskList() {
        tasks = new ArrayList<>();
        taskCount = 0;
    }

    public void addTask(String[] splitStr, Duke.TaskType type) throws DukeException {
        if (splitStr.length < 2) {
            throw new DukeException("The description of a task cannot be empty.");
        }
        switch (type) {
            case TODO:
                tasks.add(new Todo(splitStr[1]));
                taskCount++;
                ui.printMessage("Got it. I've added this task:\n       " + tasks.get(taskCount - 1)
                        + "\n     Now you have " + taskCount + " tasks in the list");
                break;
            case DEADLINE:
                String[] strDeadline = splitStr[1].split("/by", 2);
                if (strDeadline.length < 2 || strDeadline[1].equals("")) {
                    throw new DukeException("Please also specify the date and time.");
                }
                LocalDateTime deadlineDateTime = Parser.parseDateTime(strDeadline[1].trim());
                tasks.add(new Deadline(strDeadline[0].trim(), deadlineDateTime));
                taskCount++;
                ui.printMessage("Got it. I've added this task:\n       " + tasks.get(taskCount - 1)
                        + "\n     Now you have " + taskCount + " tasks in the list.");
                break;
            case EVENT:
                String[] strEvent = splitStr[1].split("/at", 2);
                if (strEvent.length < 2 || strEvent[1].equals("")) {
                    throw new DukeException("Please also specify the date and time.");
                }
                LocalDateTime eventDateTime = Parser.parseDateTime(strEvent[1].trim());
                tasks.add(new Event(strEvent[0].trim(), eventDateTime));
                taskCount++;
                ui.printMessage("Got it. I've added this task:\n       " + tasks.get(taskCount - 1)
                        + "\n     Now you have " + taskCount + " tasks in the list.");
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means!");
        }
    }

    public void deleteTask(String[] splitStr) throws DukeException{
        if (splitStr.length < 2) {
            throw new DukeException("Please specify task number to delete.");
        }
        int deleteNo;
        try {
            deleteNo = Integer.parseInt(splitStr[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify task number to delete.");
        }
        if (deleteNo <= 0) {
            throw new DukeException("Invalid task number.");
        }
        if (deleteNo > taskCount) {
            throw new DukeException("There are not that many tasks!");
        }
        taskCount--;
        ui.printMessage("Noted. I've removed this task:\n       " + tasks.get(deleteNo - 1)
                + "\n     Now you have " + taskCount + " tasks in the list.");
        tasks.remove(deleteNo - 1);
    }

    public void mark(String[] splitStr) throws DukeException {
        if (splitStr.length < 2) {
            throw new DukeException("Please specify task number to mark.");
        }
        int markNo;
        try {
            markNo = Integer.parseInt(splitStr[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify task number to mark.");
        }
        if (markNo <= 0) {
            throw new DukeException("Invalid task number.");
        }
        if (markNo > taskCount) {
            throw new DukeException("There are not that many tasks!");
        }
        tasks.get(markNo - 1).markAsDone();
        ui.printMessage("Nice! I've marked this task as done:\n       " + tasks.get(markNo - 1));
    }

    public void unmark(String[] splitStr) throws DukeException {
        if (splitStr.length < 2) {
            throw new DukeException("Please specify task number to unmark.");
        }
        int unmarkNo;
        try {
            unmarkNo = Integer.parseInt(splitStr[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify task number to unmark.");
        }
        if (unmarkNo <= 0) {
            throw new DukeException("Invalid task number.");
        }
        if (unmarkNo > taskCount) {
            throw new DukeException("There are not that many tasks!");
        }
        tasks.get(unmarkNo - 1).markAsUnDone();
        ui.printMessage("Nice! I've marked this task as not done yet:\n       " + tasks.get(unmarkNo - 1));
    }

    public void printTaskList() {
        int i = 1;
        String line = "    ____________________________________________________________";
        System.out.println(line);
        System.out.println("     Here are the tasks in your list");
        for (Task t : tasks) {
            System.out.println("     " + i + "." + t);
            i++;
        }
        System.out.println(line);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}

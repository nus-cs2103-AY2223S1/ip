package duke.tasklist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import duke.command.CommandType;
import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.task.Task;


/**
 * A class containing taskList and its operation.
 */
public class TaskList {
    private List<Task> taskList = new ArrayList<>();
    /**
     * Count the task in the taskList.
     * @return The number of task in the taskList.
     */
    public int countTask() {
        int res = 0;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) != null) {
                res += 1;
            }
        }
        return res;
    }
    /**
     *  Delete the indexed task and print out reply and update corresponding file.
     * @param i The index of the deleted task.
     * @param storage The storage containing the this taskList.
     */
    public String delete(int i, Storage storage) throws DukeException {
        try {
            Task deletedTask = this.taskList.remove(i - 1);
            String res = (" Noted. I've removed this task:\n" + deletedTask.printTask()
                    + "\n" + "Now you have " + this.countTask() + " tasks in the list.");
            storage.updateFile(this.taskList);
            return res;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry, the command is not in right format.");
        }
    }

    /**
     * Add a task to the taskList and print response.
     * @param task Task to be added.
     */
    public String addTask(Task task) {
        this.taskList.add(task);
        return ("Got it. I've added this task:\n" + task.printTask()
                 + "\nNow you have " + this.countTask() + " task in the list.\n");
    }
    /**
     * The method that list all the tasks in a formatted string.
     * @return A string of lines of tasks in the print format.
     */
    public String listAllTask() {
        String res = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            if (this.taskList.get(i) != null) {
                res += (i + 1) + ". " + this.taskList.get(i).printTask() + "\n";
            }
        }
        return ("Here are the tasks in your list:\n" + res);
    }
    /**
     * Mark certain task status as done.
     * @param i Index of the task.
     * @param storage The storage contains target file.
     * @return A response from duke.
     */
    public String markAsDone(int i, Storage storage) throws DukeException {
        try {
            this.taskList.get(i).taskDone();
            String res = ("Nice! I've marked this task as done:\n"
                    + this.taskList.get(i).printTask());
            storage.updateFile(this.taskList);
            return res;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Traveller, the index of target task is wrong.");
        } catch (Exception e) {
            throw new DukeException("Sorry, something went wrong when unmarking or marking the task.");
        }
    }
    /**
     * Mark certain task status as undone.
     * @param i Index of the task.
     * @param storage The storage contains target file.
     * @return A response from duke.
     */
    public String markUndone(int i, Storage storage) throws DukeException {
        try {
            this.taskList.get(i).taskUndone();
            String res = ("OK, I've marked this task as not done yet:\n"
                    + this.taskList.get(i).printTask());
            storage.updateFile(this.taskList);
            return res;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Traveller, the index of target task is wrong.");
        } catch (Exception e) {
            throw new DukeException("Sorry, something went wrong when unmarking or marking the task.");
        }
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }
    /**
     * Find tasks on a specific day.
     * @param d The target day.
     * @return A response from duke.
     */
    public String taskOnSpecificDay(LocalDate d) {
        String res = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            if (this.taskList.get(i).isOnSpecificDay(d)) {
                res += this.taskList.get(i).printTask() + "\n";
            }
        }
        return res;
    }
    /**
     * Find task on a certain day.
     * @param s The description of the date.
     * @return A response from duke.
     */
    public String getASpecificDay(String s) throws DukeException {
        try {
            String day = s.split(" ")[1];
            LocalDate d = LocalDate.parse(day);
            String res = taskOnSpecificDay(d);
            if (res.equals("")) {
                return "You don't have tasks on this day.";
            } else {
                return res;
            }
        } catch (Exception e) {
            throw new DukeException("The input format for find is not correct.");
        }
    }
    /**
     * Find task of a containing certain string.
     * @param target The target words of task.
     * @return Description of task.
     */
    public String findMatch(String target) {
        String res = "";
        int count = 1;
        for (int i = 0; i < countTask(); i++) {
            String[] temp = this.taskList.get(i).printTask().split(" ");
            for (int j = 0; j < temp.length; j++) {
                if (temp[j].contains(target)) {
                    res += count + "." + this.taskList.get(i).printTask() + "\n";
                    count++;
                    break;
                }
            }
        }
        if (count == 1) {
            res += "There is no match for your search.";
        }
        return res;
    }
    /**
     * Find task of a certain type.
     * @param fullCommand The description of task.
     * @return Description of task.
     */
    public String find(String fullCommand) {
        String res = "Are these tasks what you are looking for?\n";
        String target = fullCommand.split(" ", 2)[1];
        CommandType c = CommandType.COMMAND_MAP.get(target);
        if (c != null) {
            res += findCertainTypeTask(c);
        } else {
            res += findMatch(target);
        }
        return res;
    }
    /**
     * Find task of a certain type.
     * @param c The target type of task.
     * @return Description of task.
     */
    public String findCertainTypeTask(CommandType c) {
        String res = "";
        int count = 1;
        for (int i = 0; i < countTask(); i++) {
            Task t = this.taskList.get(i);
            if (t == null) {
                continue;
            }
            String typeDes = t.getDescription().split(" ")[0];
            if (CommandType.COMMAND_MAP.get(typeDes).equals(c)) {
                res += count + "." + this.taskList.get(i).printTask() + "\n";
            }
        }
        if (count == 1) {
            res += "There is no match for your search.";
        }
        return res;
    }
}

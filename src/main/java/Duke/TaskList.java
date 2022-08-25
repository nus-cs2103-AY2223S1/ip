package Duke;

import java.util.ArrayList;

public class TaskList {

    protected static final UI UI = new UI();
    protected ArrayList<Task> taskList;

    TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void add(Task task) {
        taskList.add(task);
        UI.printResponse("Got it. I've added this task:\n" + task.toString() + "\n" + "Now you have " + taskList.size() + " tasks in the list.\n");
    }

    public void printTaskList() {
        String response = UI.taskListOpening;

        if (taskList.size() <= 0) {
            response += UI.noListFound;
        } else {
            for (int i = 1; i <= taskList.size(); ++i) {
                response += (i + ". " + taskList.get(i - 1).toString()) + "\n";
            }
        }
        UI.printResponse(response);
    }

    public void markTaskAsDone(int index) throws DukeException {
        if (index < 0) {
            throw new DukeException("I cannot mark this task because your index is invalid");
        } else if (index >= taskList.size()) {
            throw new DukeException("I cannot mark this task because task does not exist");
        }

        taskList.get(index).markAsDone();
        UI.printResponse("Nice! I've marked this task as done:\n" + taskList.get(index).toString() +"\n");
    }

    public void markTaskAsNotDone(int index) throws DukeException{
        if (index < 0) {
            throw new DukeException("I cannot unmark this task because your index is invalid");
        } else if (index >= taskList.size()) {
            throw new DukeException("I cannot unmark this task because task does not exist");
        }

        taskList.get(index).markAsNotDone();
        UI.printResponse("OK, I've marked this task as not done yet: \n" + taskList.get(index).toString() + "\n");
    }

    public void deleteTask(int index) throws DukeException {
        if (index < 0) {
            throw new DukeException("I cannot delete this task because your index is invalid");
        } else if (index >= taskList.size()) {
            throw new DukeException("I cannot delete this task because task does not exist");
        }

        UI.printResponse("Noted. I've removed this task:\n"
                        + taskList.get(index).toString()
                        + "Now you have " + (taskList.size() - 1)+ " tasks in the list.\n");

        taskList.remove(index);
    }

    @Override
    public String toString() {
        return taskList.toString();
    }
}

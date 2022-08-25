package duke;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> userTasks;

    public TaskList(ArrayList<Task> userTasks) {
        this.userTasks = userTasks;
    }

    public TaskList() {
        this.userTasks = new ArrayList<>();
    }

    public void addTask(Task t) {
        this.userTasks.add(t);
    }

    public void deleteTask(int taskNumber) throws DukeException {
        try {
            Task userTask = this.userTasks.get(taskNumber);
            this.userTasks.remove(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("    " + "No such task exists.\n");
        }
    }

    public void markTask(int taskNumber) throws DukeException {
        try {
            Task userTask = this.userTasks.get(taskNumber);
            if (userTask.isCompleted()) {
                throw new DukeException("    " + "This task is already marked as done.\n");
            } else {
                userTask.setCompleted();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("    " + "No such task exists.\n");
        }
    }

    public void unmarkTask(int taskNumber) throws DukeException {
        try {
            Task userTask = this.userTasks.get(taskNumber);
            if (!userTask.isCompleted()) {
                throw new DukeException("    " + "This task is already marked as not done yet.\n");
            } else {
                userTask.setUncompleted();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("    " + "No such task exists.\n");
        }
    }

    public int length() {
        return this.userTasks.size();
    }

    public String getStorageRepresentation() {
        String storageRepresentation = "";
        for (Task userTask : this.userTasks) {
            storageRepresentation += userTask.getTextRepresentation();
        }
        return storageRepresentation;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < this.userTasks.size(); i++) {
            Task userTask = this.userTasks.get(i);
            output += "        " + (i + 1) + ". " + userTask + "\n";
        }
        return output;
    }

}

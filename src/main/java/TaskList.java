import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException("No cached tasks.");
        } else {
            this.taskList = taskList;
            Ui.sendMessage("Tasks loaded from cache.");
        }
    }

    public int getSize() {
        return this.taskList.size();
    }

    public void printTaskList() {
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println(this.taskList.get(i).toString() + " at index " + i);
        }
    }

    /**
     * Utility function with logic for adding tasks to the user's task list.
     * @param description The description of the task to be added.
     * @param type The type of task to be added.
     * @param remarks The remarks to be added for events or deadlines.
     */
    public void add(String description, Duke.TaskType type, String remarks) {
        String s = "Got it. I've added this task:\n\t";
        switch (type) {
        case TODO:
            Todo t = new Todo(description);
            this.taskList.add(t);
            s = s + "  " + t;
            break;
        case DEADLINE:
            Deadline d = new Deadline(description, remarks);
            this.taskList.add(d);
            s = s + "  " + d;
            break;
        case EVENT:
            Event e = new Event(description, remarks);
            this.taskList.add(e);
            s = s + "  " + e;
            break;
        default:
            break;
        }
        int size = taskList.size();
        s = s + "\n\tNow you have " + (size) + (size == 1 ? " task" : " tasks") + " in the list.";
        Ui.sendMessage(s);
    }

    /**
     * Utility function for deleting an item in the user's task list.
     * @param index The position of the task to be deleted in the ArrayList.
     */
    public void delete(int index) {
        System.out.println("inside delete... deleting item at index " + index);
        String s = "Got it. I've removed this task:\n\t";
        s = s + this.taskList.get(index);
        this.taskList.remove(index);
        int size = taskList.size();
        s = s + "\n\tNow you have " + (size) + (size == 1 ? " task" : " tasks") + " in the list.";
        Ui.sendMessage(s);
    }

    public void listTasks() {
        Ui.printTasks(this.taskList);
    }

    public void markTaskAsDone(int index) {
        this.taskList.get(index).markAsDone();
    }

    public void markTaskAsUndone(int index) {
        this.taskList.get(index).markAsUndone();
    }

    public String getTaskAsString(int index) {
        return this.taskList.get(index).toString();
    }


    public String formatAllTasksForFileStorage() {
        String r = "";
        for (Task t : this.taskList) {
            String s = "";
            String taskType = t.getClass().toString().split(" ")[1];
            String status = t.isDone ? "d" : "nd";
            String desc = t.description;
            String remarks = "";
            int index;
            if (taskType.equals("Event")) {
                index = t.toString().indexOf("at: ") + 4;
                remarks = t.toString().substring(index, t.toString().length() - 1);
            } else if (taskType.equals("Deadline")) {
                index = t.toString().indexOf("by: ") + 4;
                // remarks = t.toString().substring(index, t.toString().length() - 1);
                remarks = ((Deadline) t).getRemarks();
            }
            s = s + taskType.toLowerCase() + " | " + status + " | " + desc;
            if (!remarks.equals("")) {
                s = s + " | " + remarks;
            }
            r = r + s + "\n";
        }
        return r;
    }

    public Task getTask() {
        return this.taskList.get(0);
    }
}

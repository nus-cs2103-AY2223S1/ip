package duke.task;
import java.util.ArrayList;
import duke.helper.Ui;

public class TaskList {
    /**
     * Class to store the list of Tasks
     */
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void add(Task task) { taskList.add(task); }

    public void delete(int index) {
        taskList.remove(index);
    }

    public Task getTask(int index) { return taskList.get(index); }

    public int getSize() { return taskList.size(); }

    public void clear() {
        taskList.clear();
        Ui.clear();
    }

    public void printTasks() {
        int index = 0;
        Task item;
        while (index < taskList.size()) {
            item = taskList.get(index);
            System.out.println((index + 1) + "." + item.toString());
            index++;
        }
    }

    public String getTasks() {
        int index = 0;
        Task item;
        String result = "";
        while (index < taskList.size()) {
            item = taskList.get(index);
            result += (item.getInfo() + "\n");
            index++;
        }
        return result;
    }

    public void mark(int index) {
        taskList.get(index).markAsDone();
        Ui.mark();
    }

    public void unmark(int index) {
        taskList.get(index).markAsUndone();
        Ui.unmark();
    }

    /**
     * Method to find and print tasks based on keywords
     *
     * @param keywords the string of keywords to be searched
     */
    public void find(String keywords) {
        String[] keywordArr = keywords.split(" ");
        int arrLen = keywordArr.length;
        int pointer;
        TaskList result = new TaskList();

        for (Task task : taskList) {
            pointer = 0;

            while (pointer < arrLen) {
                if (!task.getDescription().contains(keywordArr[pointer])) {
                    break;
                }
                pointer++;
            }

            if (pointer == arrLen) {
                result.add(task);
            }
        }

        if (result.getSize() == 0) {
            Ui.noTaskFound();
        } else {
            Ui.taskFound();
            result.printTasks();
        }
    }
}

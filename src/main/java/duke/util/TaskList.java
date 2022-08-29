package duke.util;

//import util
import java.util.ArrayList;

//import task
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

//import exception
import duke.exception.TaskMarkException;
import duke.exception.TaskUnmarkException;
import duke.exception.TaskNotFoundException;


public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(String text) {
        this();
        if (text != "") {
            String[] texts = text.split("\n");
            String taskType;
            String description;
            for (int i = 0; i < texts.length; i++) {
                taskType = texts[i].substring(3,6);
                String[] descriptions;
                switch (taskType) {
                case "[T]":
                    description = texts[i].substring(10);
                    tasks.add(new Todo(description, texts[i].charAt(7) == 'X'));
                    break;
                case "[D]":
                    description = texts[i].substring(10);
                    descriptions = description.split("by");
                    tasks.add(new Deadline(descriptions[0].substring(0, description.indexOf("(") - 1),
                            descriptions[1].substring(2, descriptions[1].length() - 1), texts[i].charAt(7) == 'X'));
                    break;
                case "[E]":
                    description = texts[i].substring(10);
                    descriptions = description.split("at");
                    tasks.add(new Event(descriptions[0].substring(0, description.indexOf("(") - 1),
                            descriptions[1].substring(2, descriptions[1].length() - 1), texts[i].charAt(7) == 'X'));
                    break;
                }

            }
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    private boolean isIndexOutOfBound(int index)  {
        return (index < 0 || index > tasks.size() - 1);
    }

    public Task markTask(int index) throws TaskMarkException, TaskNotFoundException {
        if (isIndexOutOfBound(index)) {
            throw new TaskNotFoundException(index + 1);
        }
        Task task = tasks.get(index);
        task.mark();
        return task;
    }

    public Task unmarkTask(int index) throws TaskUnmarkException, TaskNotFoundException {
        if (isIndexOutOfBound(index)) {
            throw new TaskNotFoundException(index + 1);
        }
        Task task = tasks.get(index);
        task.unmark();
        return task;
    }

    public Task deleteTask(int index) throws TaskNotFoundException {
        if (isIndexOutOfBound(index)) {
            throw new TaskNotFoundException(index + 1);
        }
        return tasks.remove(index);
    }

    public boolean isEmpty() {
        return tasks.size() == 0;
    }

    public String getStatus() {
        String totalTaskMessage = "Total Task: " + tasks.size();
        String unmarkTaskMessage = "Unmarked Task: " + getNoOfUnmarkTask();
        return totalTaskMessage + "\n" + unmarkTaskMessage;
    }

    private int getNoOfUnmarkTask() {
        Task task;
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            task = tasks.get(i);
            if (!task.isDone()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        int size = tasks.size();
        String text = String.format("%d. %s", 1, tasks.get(0));
        for (int i = 1; i < size; i++) {
            text += String.format("\n%d. %s", i+1, tasks.get(i));
        }
        return text;
    }
}

package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<String> tasks) {
        this.list = new ArrayList<>();
        Task newTask = null;
        for (String task : tasks) {
            List<String> stringList = Stream
                    .of(task.split("\\p{Punct}"))
                    .map(e -> new String(e))
                    .collect(Collectors.toList());
            String description = stringList.get(2);
            boolean isDone = stringList.get(1) == "X" ? true : false;
            if (stringList.size() == 3) {
                newTask = new ToDo(description, isDone);
            } else {
                String code = stringList.get(0);
                String stringDateTime = stringList.get(3);
                LocalDateTime dateTime = Parser.processDateTime(stringDateTime);
                if (code == "E") {
                    newTask = new Event(dateTime, description, isDone);
                } else {
                    newTask = new Deadline(dateTime, description, isDone);
                }
            }
            this.list.add(newTask);
        }
    }

    public ArrayList<String> extractToStringArray() {
        ArrayList<String> textArray = new ArrayList<>();
        for (Task task : this.list) {
            String entry = task.printText();
            textArray.add(entry);
        }
        return textArray;
    }

    /**
     * Adds task to the current task list
     * @param task Task object
     * @return boolean response
     */
    public boolean addTask(Task task) {
        boolean response = this.list.add(task);
        return response;
    }

    /**
     * Deletes a task in the list and returns the deleted task
     * @param taskIndex index of the current task
     * @return deleted Task object
     */
    public Task deleteTask(int taskIndex) {
        Task removedTask = this.list.remove(taskIndex);
        return removedTask;
    }

    /**
     * Marks a task as done
     * @param taskIndex index of the current task
     * @return marked Task object
     */
    public Task markTask(int taskIndex) {
        this.list.get(taskIndex).markAsDone();
        return this.list.get(taskIndex);        
    }

    /**
     * Unmarks a task as undone
     * @param taskIndex index of the current task
     * @return unmarked Task object
     */
    public Task unmarkTask(int taskIndex) {
        this.list.get(taskIndex).unmarkAsDone();
        return this.list.get(taskIndex);
    }
    
    public TaskList findTasks(String keyword) {
        TaskList foundTasks = new TaskList();
        for (Task task : this.list) {
            if (task.description.contains(keyword)) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }

    /**
     * Returns the number of tasks in the task list
     * @return number of tasks
     */
    public int getNumberOfTasks() {
        return this.list.size();
    }

    @Override
    public String toString() {
        if (this.list.isEmpty()) {
            return "[No tasks available]";
        }
        String result = "";
        for (int i = 1; i <= this.list.size(); ++i) {
            String index = String.valueOf(i);
            String details = ". " + this.list.get(i - 1);
            result += index + details + "\n";
        }
        return result;
    }
}
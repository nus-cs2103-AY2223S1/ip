package carbon;

import carbon.task.Deadline;
import carbon.task.Event;
import carbon.task.Task;
import carbon.task.Todo;
import carbon.error.CarbonException;
import carbon.error.InvalidParamException;
import carbon.error.OutOfBoundsException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void loadTask(String data) throws CarbonException {
        try {
            Task task = Task.decodeTask(data);
            this.tasks.add(task);
        } catch (CarbonException error) {
            throw error;
        }
    }

    public String encodeTasks() {
        String encodedTasks = "";
        int len = this.tasks.size();
        for (int i = 0; i < len; i++) {
            encodedTasks += this.tasks.get(i).encode();
        }
        return encodedTasks;
    }

    public String validateAndMark(String input, boolean doneness) throws CarbonException {
        int taskNumber;
        int len = input.length();
        if (doneness) {
            int requiredLen = "mark ".length();
            if (len <= requiredLen) {
                CarbonException invalidParam = new InvalidParamException(input);
                throw invalidParam;
            } else {
                taskNumber = Integer.valueOf(input.substring(requiredLen));
            }
        } else {
            int requiredLen = "unmark ".length();
            if (len <= requiredLen) {
                CarbonException invalidParam = new InvalidParamException(input);
                throw invalidParam;
            } else {
                taskNumber = Integer.valueOf(input.substring(requiredLen));
            }
        }

        if (taskNumber < 1 || taskNumber > this.tasks.size()) {
            CarbonException outOfBounds = new OutOfBoundsException(taskNumber, this.tasks.size());
            throw outOfBounds;
        } else {
            String log = this.setTaskDoneness(taskNumber, doneness);
            return log;
        }
    }

    public String setTaskDoneness(int taskNumber, boolean doneness) {
        Task task = this.tasks.get(taskNumber - 1);
        task.changeDoneness(doneness);
        String log = String.format("Got it! \n\n    %s", task);
        return log;
    }

    public String addTask(String input, Task.Type type) throws CarbonException {
        Task newTask;
        switch (type) {
            case TODO: {
                newTask = Todo.createTask(input);
                break;
            }
            case DEADLINE: {
                newTask = Deadline.createTask(input);
                break;
            }
            case EVENT: {
                newTask = Event.createTask(input);
                break;
            }
            default:
                // should never reach here
                newTask = null;
        }
        this.tasks.add(newTask);
        String log = String.format(
                "I have added: \n    %s\n\n    We've got %s so far.", 
                newTask, 
                this.countTasks()
                );
        return log;
    }

    public String deleteTask(String input) throws CarbonException {
        int len = input.length();
        int requiredLen = "delete ".length();
        if (len <= requiredLen) {
            CarbonException invalidParam = new InvalidParamException(input);
            throw invalidParam;
        } else {
            int taskNumber = Integer.valueOf(input.substring(requiredLen));
            if (taskNumber < 1 || taskNumber > this.tasks.size()) {
                CarbonException outOfBounds = new OutOfBoundsException(taskNumber, this.tasks.size());
                throw outOfBounds;
            } else {
                Task taskDeleted = this.tasks.remove(taskNumber - 1);
                String log = String.format(
                        "I have removed: \n    %s\n\n    We've got %s left.",
                        taskDeleted,
                        this.countTasks()
                        );
                return log;
            }
        }
    }

    public String listItems() {
        int size = this.tasks.size();
        if (size == 0) {
            String log = "There are no tasks so far.";
            return log;
        }

        String log = "Here are the tasks so far. \n";
        for (int i = 0; i < size; i++) {
            String taskLog = String.format(
                    "\n    %d: %s", 
                    i + 1, 
                    this.tasks.get(i)
                    );
            log += taskLog;
        }
        return log;
    }

    private String countTasks() {
        int count = this.tasks.size();
        if (count == 1) {
            return String.format("%d task", count);
        } else {
            return String.format("%d tasks", count);
        }
    }
}

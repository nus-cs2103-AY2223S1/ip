package duke.tasklist;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.util.ArrayList;
import java.time.LocalDate;


public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList(ArrayList<String> tasks) {
        taskList = new ArrayList<>();
        for (String s : tasks) {
            String[] splitCommand = s.split("\\| ", 3);
            String taskType = splitCommand[0];
            boolean isMarked = splitCommand[1].equals("1 ");
            String description = splitCommand[2];
            switch (taskType) {
            case "T ": {
                taskList.add(new Todo(description));
                int numOfTasks = taskList.size();
                if (isMarked) {
                    taskList.get(numOfTasks - 1).markAsCompleted();
                } else {
                    taskList.get(numOfTasks - 1).markAsNotCompleted();
                }
                break;
            }
            case "D ": {
                String[] splitBy = description.split("\\| ", 2);
                LocalDate byDate = LocalDate.parse(splitBy[1]);
                taskList.add(new Deadline(splitBy[0], byDate));
                int numOfTasks = taskList.size();
                if (isMarked) {
                    taskList.get(numOfTasks - 1).markAsCompleted();
                } else {
                    taskList.get(numOfTasks - 1).markAsNotCompleted();
                }
                break;
            }
            case "E ": {
                String[] splitAt = description.split("\\| ", 2);
                LocalDate atDate = LocalDate.parse(splitAt[1]);
                taskList.add(new Event(splitAt[0], atDate));
                int numOfTasks = taskList.size();
                if (isMarked) {
                    taskList.get(numOfTasks - 1).markAsCompleted();
                } else {
                    taskList.get(numOfTasks - 1).markAsNotCompleted();
                }
                break;
            }
            default:
                break;
            }
        }
    }

    public void addTodo(String s) {
        taskList.add(new Todo(s));
    }

    public void addDeadline(String s) {
        String[] splitWord = s.split("/by ", 2);
        String description = splitWord[0];
        String by = splitWord[1];
        LocalDate byDate = LocalDate.parse(by);
        taskList.add(new Deadline(description, byDate));
    }
    public void addEvent(String s) {
        String[] splitWord = s.split("/at ", 2);
        String description = splitWord[0];
        String at = splitWord[1];
        LocalDate atDate = LocalDate.parse(at);
        taskList.add(new Event(description, atDate));
    }

    public String readTask(int index) {
        return taskList.get(index).toString();
    }

    public String readStatus(int index) {
        return taskList.get(index).getStatus();
    }

    public void setCompleted(int index) {
        taskList.get(index).markAsCompleted();
    }

    public void setNotCompleted(int index) {
        taskList.get(index).markAsNotCompleted();
    }

    public int getNumOfTasks() {
        return taskList.size();
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public void changeDateFormat(int index) {
        if (taskList.get(index) instanceof Deadline) {
            ((Deadline) taskList.get(index)).changeDateFormat();
        } else if (taskList.get(index) instanceof Event) {
            ((Event) taskList.get(index)).changeDateFormat();
        }
    }

    public String getDescription(int index) {
        return taskList.get(index).getDescription();
    }

    public String getDate(int index) {
        Task curr = taskList.get(index);
        if (curr instanceof Deadline) {
            return ((Deadline) curr).getBy();
        } else if (curr instanceof Event) {
            return ((Event) curr).getAt();
        } else {
            return "There is no date for this task.";
        }
    }
}

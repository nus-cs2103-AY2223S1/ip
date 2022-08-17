package Sakura;
import java.util.List;
import java.util.ArrayList;

public class TaskManager {
    protected List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addDescription(Task newTask) {
        tasks.add(newTask);
        System.out.println("\tYes Sir! I've added this task: \n\t  "
                + newTask
                + " \n\tNow you have "
                + tasks.size()
                + " tasks in the list.");
    }

    public void deleteDescription(Task deletedTask) {
        System.out.println("\tRight away Sir! I've SHREDDED this task: \n\t  "
                + deletedTask
                + " \n\tNow you have "
                + tasks.size()
                + " tasks in the list.");
    }

    public void addTask(String input) {
        if (input.toLowerCase().startsWith("todo")) {
            Todo todo = new Todo(input.substring("todo ".length()));
            this.addDescription(todo);
        } else if (input.toLowerCase().startsWith("deadline")) {
            try {
                String[] strArr = input.split("/by", 2);
                Deadline deadline = new Deadline(strArr[0], strArr[1].substring(3));
                this.addDescription(deadline);
            } catch (ArrayIndexOutOfBoundsException e) {
                SakuraException.invalidDeadline();
            }
        } else if (input.toLowerCase().startsWith("event")) {
            try {
                String[] strArr = input.split("/at", 2);
                Event event  = new Event(strArr[0], strArr[1].substring(3));
                this.addDescription(event);
            } catch (ArrayIndexOutOfBoundsException e) {
                SakuraException.invalidEvent();
            }
        }
    }

    public void showAllTask() {
        String list = "\tSir, these are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            list += "\n\t" + index + ". " + tasks.get(i);
        }
        System.out.println(list);
    }

    public void markTask(String input) {
        try {
            if (input.split(" ").length != 2) {
                SakuraException.invalidMark();
            } else {
                int taskIndex = Integer.parseInt(input.substring("mark ".length()));
                Task task = tasks.get(taskIndex - 1);
                task.markDone();
                System.out.println("\tAlright solid work! You've completed this task: \n\t  " + task);
            }
        } catch (IndexOutOfBoundsException e) {
            SakuraException.noSuchTask();
        }
    }

    public void unmarkTask(String input) {
        try {
            if (input.split(" ").length != 2) {
                SakuraException.invalidMark();
            } else {
                int taskIndex = Integer.parseInt(input.substring("unmark ".length()));
                Task task = tasks.get(taskIndex - 1);
                task.markUndone();
                System.out.println("\tHey this is not done yet? Remember to finish it soon: \n\t  " + task);
            }
        } catch (IndexOutOfBoundsException e) {
            SakuraException.noSuchTask();
        }
    }

    public void deleteTask(String input) {
        try {
            if (input.split(" ").length != 2) {
                SakuraException.invalidCommand();
            } else {
                int taskIndex = Integer.parseInt(input.substring("delete ".length()));
                Task task = tasks.get(taskIndex - 1);
                tasks.remove(task);
                deleteDescription(task);
            }
        } catch (IndexOutOfBoundsException e) {
            SakuraException.noSuchTask();
        }
    }

}

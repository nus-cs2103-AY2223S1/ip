package util;

import tasks.Task;

import java.util.List;

public class Ui {
    public String basic(String reply) {
        return addSeparator(reply);
    }

    public String addTask(Task task, int listLength) {
        String taskss = listLength > 1 ? " tasks" : " task";
        return addSeparator("Alright, I have added the following task to the list!\n  "
                + task + "\nYou currently have " + listLength + taskss + ".");
    }

    public String list(List<Task> taskList) {
        StringBuilder reply = new StringBuilder();
        reply.append("Here are the tasks you have added:\n");
        int count = 1;
        for (Task task: taskList) {
            reply.append("  ").append(count++).append(". ").append(task.toString()).append("\n");
        }
        reply.setLength(reply.length() - 1);
        return addSeparator(reply.toString());
    }

    public String markDone(Task task) {
        return addSeparator( "Yay! I have marked this task as done!\n  " + task);
    }

    public String markUndone(Task task) {
        return addSeparator("Got it, I have marked this task as undone.\n  " + task);
    }

    public String delete(Task task, int listLength) {
        String taskss = listLength > 1 ? " tasks" : " task";
        return addSeparator("Alright, I have removed the following task from the list!\n  "
                + task + "\nYou currently have " + listLength + taskss + ".");
    }

    public String invalid() {
        return addSeparator("I'm afraid i can't help you with that.\nType \"help\" for the list of things I can do for you.");
    }

    public String addSeparator(String reply) {
        String separator = "_______________________________________";
        return separator + "\n" + reply + "\n" + separator;
    }
}

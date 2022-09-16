package duke;

import java.util.ArrayList;

public class Ui {
    private final String HORIZONTAL_LINE_BREAK = "-------------------------";

    public void hello() {
        System.out.println("To all Subjects of Ymir. My name is Eren Yeager.\n" + "How can I help you?" + "\n" + HORIZONTAL_LINE_BREAK);
    }

    public String goodBye() {
        return String.format("\tKeep moving forward until you finish all your tasks. Goodbye.\n");
    }

    public void showLoadingError() {
        System.out.println("I cannot load your file!");
    }

    //Solution adapted from https://github.com/24Donovan24/ip/blob/master/src/main/java/duke/Ui.java

    public String printList(TaskList tasks) {
        StringBuilder builder = new StringBuilder();
        builder.append("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
            Task task = tasks.getTask(i);
            builder.append("\n\t");
            builder.append((i + 1) + ". " + task.toString());
        }
        return builder.toString();
    }

    public String printToDo(Task todo, int size) {
        return String.format("\tGot it. I've added this task:\n\t%s\n\tNow you have %d tasks in your list.", todo, size);
    }

    public String printDeadLine(Task deadline, int size) {
        return String.format("\tGot it. I've added this task:\n\t%s\n\tNow you have %d tasks in your list.", deadline, size);
    }

    public String printEvent(Task event, int size) {
        return String.format("\tGot it. I've added this task:\n\t%s\n\tNow you have %d tasks in your list.", event, size);
    }

    public String printMark(Task task) {
        return String.format("\tNice! I've marked this task as done:\n\t%s", task);

    }

    public String printUnMark(Task task) {
        return String.format("\tNice! I've marked this task as not done:\n\t%s", task);
    }

    public String printDelete(Task task, int size) {
        return String.format("\tNoted. I've removed this task:\n\t%s\n\tNow you have %d tasks in your list.", task, size);
    }

    public String printFind(ArrayList<Task> list) {
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the matching tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            builder.append((i + 1) + ". " + task.toString());
        }
        return builder.toString();
    }

}

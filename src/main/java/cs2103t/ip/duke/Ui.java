package cs2103t.ip.duke;

import javafx.application.Platform;

public class Ui {

    private final String LINE = "_______________________________\n";

    public String showLine() {
        return LINE;
    }

    public String showWelcome() {
        return LINE +
                "Hello I'm Duke\n" +
                "What can I do for you?\n" +
                LINE;
    }

    public String showBye() {
        return LINE +
                "Bye. Hope to see you again soon! (Closing window in 3 seconds) \n" +
                LINE;
    }

    public String showMark(Tasklist list, int taskNum) {
        return LINE +
                "Nice! I've marked this task as done: \n" +
                list.getTasks().get(taskNum - 1).getStatusIcon() +
                list.getTasks().get(taskNum - 1).description + "\n" + LINE;
    }

    public String showUnmark(Tasklist list, int taskNum) {
        return LINE +
                "OK, I've marked this task as not done yet: \n" +
                list.getTasks().get(taskNum - 1).getStatusIcon() +
                list.getTasks().get(taskNum - 1).description + "\n" + LINE;
    }

    public String showList(Tasklist tasks, int index) {
        String list = LINE + "\n" + "Here are the tasks in your list: \n";
        for (int i = 0; i < index; i++) {
            list += i + 1;
            list += ". ";
            list += tasks.getTasks().get(i).toString();
            list += "\n";
        }
        return list + LINE;
    }

    public String showFilteredList(Tasklist tasks, int index) {
        String list = LINE + "\n" + "Here are the matching tasks in your list: \n";
        for (int i = 0; i < index; i++) {
            list += i + 1;
            list += ". ";
            list += tasks.getTasks().get(i).toString();
            list += "\n";
        }
        return list + LINE;
    }

    public String showTodo(Todo todo, int index) {
        return todo.addString(index);
    }

    public String showDeadline(Deadlines deadlines, int index) {
        return deadlines.addString(index);
    }

    public String showEvent(Event event, int index) {
        return event.addString(index);
    }

    public String showDelete(Task toDelete, int index) {
        return LINE + "Noted. I've removed this task: \n" +
                toDelete.toString() + "\n" +
                "Now you have " + index + " tasks in the list. \n" + LINE;
    }

    public void showLoadingError() {
        System.out.println("Error Loading from Database :( ");
    }
}

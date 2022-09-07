package duke;
import java.util.ArrayList;

public class Ui {
    public String greetUi() {
        return "Hello I'm Duke\nWhat can I do for you?";
    }

    public String bidFarewellUi() {
        return "Bye. Hope to see you again soon!";
    }

    public static String appendCurrentTaskInList(String currList, int count, ArrayList<Task> list) {
        return currList + "\n" + count + "." + list.get(count - 1).toString();
    }

    public String displayListUi(ArrayList<Task> list) {
        String currList= "";
        for (int i = 1; i <= list.size(); i++) {
           currList = appendCurrentTaskInList(currList, i, list);
        }
        return "Here are the tasks in your list:" + currList;
    }

    public String deleteUi(Task task, ArrayList<Task> list) {
        return "Noted. I've removed this task:\n" + task.toString() + "\n"
                + "Now you have " + list.size() + " tasks in the list.";
    }

    public String addToListUi(Task task, ArrayList<Task> list) {
        String taskWord = "task";
        if(list.size() != 1) {
            taskWord = "tasks";
        }
        return "Got it. I've added this task:\n" + task.toString() + "\n" +
                "Now you have " + list.size() + " " + taskWord + " in the list.";
    }

    public String getEmptyEventExceptionUi() {
        return "OOPS!!! The description of an event cannot be empty.";
    }

    public String getEmptyDeadlineExceptionUi() {
        return "OOPS!!! The description of a deadline cannot be empty.";
    }

    public String getEmptyTodoExceptionUi() {
        return "OOPS!!! The description of a todo cannot be empty.";
    }

    public String getUnknownWordExceptionUi() {return "OOPS!!! I'm sorry, but I don't know what that means :-(";};

    public String markHelperUi(Task task) {
       return "Nice! I've marked this task as done: " + task.toString();
    }
    public String unmarkHelperUi(Task task) {
        return "OK, I've marked this task as not done yet: " + task.toString();
    }

}


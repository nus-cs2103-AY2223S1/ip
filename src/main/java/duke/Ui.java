package duke;


import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String getInput() {
        return sc.nextLine();
    }

    public String greeting() {
        return "Hello I'm Duke, what can I do for you?";
    }

    public String showList() {
        if (TaskList.taskList.size() == 0) {
            return "Currently, you do not have any tasks.";
        }
        String list = "";
        for (int i = 0; i < TaskList.taskList.size(); i++) {
            int j = i + 1;
            list = list + j + "." + TaskList.taskList.get(i) + "\n";
        }
        return "Here are your tasks:" + "\n" + list;
    }

    public String bye() {
        return "Bye, see you again!";
    }

    public String printMark(int index) {
        return "Nice! I've marked this task as done:" + "\n" + "  " + TaskList.taskList.get(index).toString();
    }

    public String printUnmark(int index) {
        return "OK, I've marked this task as not done yet:" + "\n" + "  " + TaskList.taskList.get(index).toString();
    }

    public String printTask(Task task) {
        return "Added this task:" + "\n"
                + "  " + task.toString()
                + ", to your list" + "\n"
                + "Now you have "
                + TaskList.taskList.size()
                + " tasks in the list.";
    }

    public String printEdit(Task task) {
        return "Edited task to:" + "\n"
                + " " + task.toString();
    }

    public String printDelete(Task task, int index) {
        return "Noted. I've removed this task:" + "\n"
                + "  "
                + task + "\n"
                + "Now you have " + TaskList.taskList.size() + " tasks in the list." + "\n";

    }
    public String printFind(String description) {
        String output = "";
        TaskList.searchKeyword(description);
        if (TaskList.getTempList().size() == 0) {
            output = "Sorry could not find a match";
        } else {
            output = "Here are your results:" + "\n";
            for (int i = 0; i < TaskList.getTempList().size(); i++) {
                int number = i + 1;
                output += number + "." + TaskList.getTempList().get(i) + "\n";
            }
        }
        return output;
    }

    public void printError(String message) {
        System.out.println(message);
    }

}


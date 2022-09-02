package duke;

public class Ui {

    public void start() {
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printOnAdd(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task.toString());
        System.out.println("Now you have " + taskList.size() + " task" + (taskList.size() == 1 ? " " : "s ") + "in the list");
    }

    public void printOnDelete(Task task, TaskList taskList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + task.toString());
        System.out.println("Now you have " + taskList.size() + " task" + (taskList.size() == 1 ? " " : "s ") + "in the list");
    }

    public void printOnMark(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task.toString());
    }

    public void printOnUnmark(Task task) {
        System.out.println("Ok. I've marked this task as not done yet: ");
        System.out.println(task.toString());
    }

    public void printTaskList(TaskList taskList) {
        if (taskList.size() == 0) {
            System.out.println("There are currently no tasks in the list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            taskList.print();
        }
    }

}

package duke;
public class Ui {

    Ui() {

    }

    void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    void add(String taskFullDescription, int totalNoOfTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println(taskFullDescription);
        System.out.println("Now you have " + totalNoOfTask + " tasks in the list.");
    }

    void delete(String taskFullDescription, int totalNoOfTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskFullDescription);
        System.out.println("Now you have " + totalNoOfTask + " tasks in the list.");
    }

    void mark(String taskFullDescription) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskFullDescription);
    }

    void unmark(String taskFullDescription) {
        System.out.println("OK! I've marked this task as not done yet:");
        System.out.println(taskFullDescription);
    }

    /*void createFiles() {
        System.out.println("There is no existing task list. Duke is creating a new one now.");
    }*/


}

package Duke;

import java.util.ArrayList;

public class Graphics {

    private TaskList taskList;

    public Graphics(TaskList taskList) {
        this.taskList = taskList;
    }

    void printList() {
        System.out.println("____________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i).toString());
        }
        System.out.println("____________________________________");
    }

    void addMessage(Task task) {
        int taskCount = taskList.size();
        System.out.println("____________________________________");
        System.out.println("Got it, i've added this task:");
        System.out.println(task.toString());
        System.out.println("You now have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________");
    }

    void markMessage(int taskIndex) {
        System.out.println("____________________________________");
        System.out.println("I've marked this task as done");
        System.out.println(taskList.get(taskIndex).toString());
        System.out.println("____________________________________");
    }

    public  void unmarkMessage(int taskIndex) {
        System.out.println("____________________________________");
        System.out.println("Ok, i've unmarked this task");
        System.out.println(taskList.get(taskIndex).toString());
        System.out.println("____________________________________");
    }

    public static void deleteMessage(TaskList list, int taskIndex) {
        System.out.println("____________________________________");
        System.out.println("Got it, i've deleted this task");
        System.out.println(list.get(taskIndex).toString());
        System.out.println("____________________________________");
    }

    void sayBye() {
        System.out.println("Why you dowan me :(");
    }

    public static void loadingError() {
        System.out.println("Error loading file from specified path, creating new list");
    }
}
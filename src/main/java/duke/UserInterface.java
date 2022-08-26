package duke;

public class UserInterface {

    private TaskList taskList;

    public UserInterface(TaskList taskList) {
        this.taskList = taskList;
    }

    void printList() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i).toUser());
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    void addTaskMessage(Task task) {
        int taskCount = taskList.size();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Got it, i've added this task:");
        System.out.println(task.toUser());
        System.out.println("You now have " + taskCount + " tasks in the list.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

     void markTaskMessage(int taskIndex) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("I've marked this task as done");
        System.out.println(taskList.get(taskIndex).toUser());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public  void unmarkTaskMessage(int taskIndex) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Ok, i've unmarked this task");
        System.out.println(taskList.get(taskIndex).toUser());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void taskDeletedMessage(TaskList list, int taskIndex) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Got it, i've deleted this task");
        System.out.println(list.get(taskIndex).toUser());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    void sayBye() {
        System.out.println("bai bai");
    }

    public static void showLoadingError() {
        System.out.println("Error loading file from specified path, creating new list");
    }
}

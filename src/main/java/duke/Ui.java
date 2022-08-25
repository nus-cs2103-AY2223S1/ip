package duke;

public class Ui {

    public void showWelcome() {
        String dukeGreeting = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(dukeGreeting);
    }

    public void showGoodbye() {
        String dukeGoodbye = "    " + "Bye. Hope to see you again soon!";
        System.out.println(dukeGoodbye);
    }

    public void showError(DukeException e) {
        System.out.println(e.getMessage());
    }

    public void showLoadingError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void showTasks(TaskList taskList) {
        if (taskList.length() != 0) {
            System.out.println("    " + "Here are the tasks in your list:\n");
            System.out.println(taskList.toString());
        } else {
            System.out.println("    " + "There are no tasks in your list.\n");
        }

    }

    public void showMarkSuccess(int taskNumber) {
        System.out.println( "    " + "Nice! I've marked task " + (taskNumber + 1) + " as done.\n");
    }

    public void showUnmarkSuccess(int taskNumber) {
        System.out.println("    " + "OK, I've marked task " + (taskNumber + 1) + " as not done yet.\n");
    }

    public void showRemoveTaskSuccess(int taskNumber, TaskList taskList) {
        System.out.println("    " + "Noted. I've removed task " + (taskNumber + 1) + ".\n" +
                "    " + "Now you have " + taskList.length() +  " tasks in the list.\n");
    }

    public void showAddTaskSuccess(TaskList taskList) {
        System.out.println("    " + "Task added. You now have " + taskList.length() + " tasks in the list.\n");
    }

    public void showMatchingTasks(String searchInput, TaskList tasks) {
        System.out.println("    " + "Here are the matching tasks in your list:\n");
        System.out.println(tasks.getMatchingTasksRepresentation(searchInput));
    }
}

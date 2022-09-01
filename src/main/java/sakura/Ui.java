package sakura;

import java.util.List;

public class Ui {
    static String DIV = "_________________________________________________________________";
    static String DIV2 = "========================================";

    public String printDiv() {
        return "\t" + DIV;
    }

    /**
     * Displays greeting messages.
     * @return string message to user.
     */
    public static String greet() {
        String logo = " <SAKURA.>";

        return "Hello! This is \n" + logo + "\nat your service!!\n"
            + DIV
            + "\nHow may I serve you today, Senpai?"
            + "\t" + DIV2 + "\n";
    }

    /**
     * Displays exit messages.
     * @return string message to user.
     */
    public String showExit() {
        return "Bye Senpai! It was a pleasure serving you, see you again soon!" + "\t" + DIV2 + "\n";
    }

    /**
     * Adds a particular task input by the user into the list of tasks.
     * Displays add message to user.
     * @param tasks current list of tasks.
     * @param newTask new task to be added.
     * @return string message to user.
     */
    public static String addDescription(List<Task> tasks, Task newTask) {
        tasks.add(newTask);
        return "Yes Senpai! I've added this task: \n\t  "
                + newTask
                + " \n\tNow you have "
                + tasks.size()
                + " tasks in the list.";
    }

    /**
     * Displays delete message to user.
     * @param tasks current list of tasks.
     * @param deletedTask task to be deleted.
     * @return string message to user.
     */
    public static String deleteDescription(List<Task> tasks, Task deletedTask) {
        return "Right away Senpai! I've SHREDDED this task: \n\t  "
                + deletedTask
                + " \n\tNow you have "
                + tasks.size()
                + " tasks in the list.";
    }

    /**
     * Displays all the tasks in the list.
     *
     * @param tasks current list of tasks.
     * @return string message to user.
     */
    public static String showAllTask(List<Task> tasks) {
        StringBuilder list = new StringBuilder("Senpai, these are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            list.append("\n\t").append(index).append(". ").append(tasks.get(i));
        }
        return list.toString();
    }

    public static String searchTaskDescription(List<Task> searchTask) {
        if (searchTask.size() > 0) {
            StringBuilder searcher = new StringBuilder("Senpai, these are the tasks in your list:");
            for (int i = 0; i < searchTask.size(); i++) {
                int index = i + 1;
                Task task = searchTask.get(i);
                searcher.append("\t").append(index).append(".").append(task);
            }
            return searcher.toString();
        } else {
            return "Senpai, there are no such tasks in your list :(";
        }
    }
}


//               "\u001B[31m███████  █████  ██   ██ ██    ██ ██████   █████    \u001B[0m\n"
//                       + "\u001B[31m██      ██   ██ ██  ██  ██    ██ ██   ██ ██   ██   \u001B[0m\n"
//                       + "\u001B[31m███████ ███████ █████   ██    ██ ██████  ███████   \u001B[0m\n"
//                       + "\u001B[31m     ██ ██   ██ ██  ██  ██    ██ ██   ██ ██   ██   \u001B[0m\n"
//                       + "\u001B[31m███████ ██   ██ ██   ██  ██████  ██   ██ ██   ██  ██\u001B[0m\n";

//            "\u001B[36m            ..''''       .'.       |    ..'' |         | |`````````,       .'.       \u001B[0m\n"
//                    + "\u001B[36m         .''           .''```.     |..''     |         | |'''|'''''      .''```.     \u001B[0m\n"
//                    + "\u001B[34m      ..'            .'       `.   |``..     |         | |    `.       .'       `.   \u001B[0m\n"
//                    + "\u001B[35m....''             .'           `. |    ``.. `._______.' |      `.   .'           `. \u001B[0m\n";
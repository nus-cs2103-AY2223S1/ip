package sakura;

import java.util.List;

import javafx.application.Platform;

public class Ui {
    static String DIV = "_________________________________________________________________";
    static String DIV2 = "========================================";

    public String printDiv() {
        return "\t" + DIV;
    }

    /**
     * The logo
     * @return the logo
     */
    public static String logo() {
        String logo =
         "  ######   #                   ##########           #       \n"
        +"           #   ###    ######            # ######### #   ### \n"
        +"########## ####            #           #  #       # ####    \n"
        +"     #     #               #   ########   #       # #       \n"
        +"     #     #               #       ##     #       # #       \n"
        +"    #      #        ##########   ##       ######### #       \n"
        +"  ##        #######            ##                    #######\n";

        return logo;
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
     * To wait for 1.5 seconds before closing the program
     */
    public static void exitProgram() {
        new Thread(() -> {
            try{
                Thread.sleep(1500);
            } catch (Exception e){
            }finally{
                Platform.exit();
            }
        }).start();
    }


    /**
     * Adds a particular task input by the user into the list of tasks.
     * Displays add message to user.
     * @param tasks current list of tasks.
     * @param newTask new task to be added.
     * @return string message to user.
     */
    public static String addDescription(List<Task> tasks, Task newTask) {
        int initialSize = tasks.size();
        tasks.add(newTask);
        assert tasks.size() == initialSize + 1 : "Size of tasks list is incorrect after adding new task.";
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
            StringBuilder searcher = new StringBuilder("Senpai, these are the tasks in your list: \n");
            for (int i = 0; i < searchTask.size(); i++) {
                int index = i + 1;
                Task task = searchTask.get(i);
                searcher.append("\t").append(index).append(".").append(task).append("\n");
            }
            return searcher.toString();
        } else {
            return "Senpai, there are no such tasks in your list :(";
        }
    }

    public static String showSortedTasks(List<Task> tasks) {
        StringBuilder list = new StringBuilder("Senpai, here are the tasks sorted by date:");
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            list.append("\n\t").append(index).append(". ").append(tasks.get(i));
        }
        return list.toString();
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
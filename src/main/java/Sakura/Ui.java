package Sakura;

import java.util.List;

public class Ui {
    static String DIV = "________________________________________________________________";
    static String DIV2 = "==============================================================";

    public void printDiv() {
        System.out.println("\t" + DIV);
    }

    /**
     * Displays greeting messages.
     */
    void greet() {
        String logo =     "   ▄████████    ▄████████    ▄█   ▄█▄ ███    █▄     ▄████████    ▄████████             ▄■▄          \n"
                        + "  ███    ███   ███    ███   ███ ▄███▀ ███    ███   ███    ███   ███    ███       ▄■██■█   █■██■▄    \n"
                        + "  ███    █▀    ███    ███   ███▐██▀   ███    ███   ███    ███   ███    ███      ██  ▄  ▀▄▀  ▄  ██   \n"
                        + "  ███          ███    ███  ▄█████▀    ███    ███  ▄███▄▄▄▄██▀   ███    ███      ▀█▄  ▀▄ █ ▄▀  ▄█▀   \n"
                        + "▀███████████ ▀███████████ ▀▀█████▄    ███    ███ ▀▀███▀▀▀▀▀   ▀███████████         █■■((■))■■█      \n"
                        + "         ███   ███    ███   ███▐██▄   ███    ███ ▀███████████   ███    ███      ▄█▀  ▄▀ █ ▀▄  ▀█▄   \n"
                        + "   ▄█    ███   ███    ███   ███ ▀███▄ ███    ███   ███    ███   ███    ███      ██  ▀  ▄▀▄  ▀  ██   \n"
                        + " ▄████████▀    ███    █▀    ███   ▀█▀ ████████▀    ███    ███   ███    █▀        ▀■██■█   █■██■▀    \n"
                        + "                            ▀                      ███    ███                          ▀■▀          \n";


        System.out.println("Hello! This is \n" + logo + "\nat your service!!");
        System.out.println(DIV);
        System.out.println("How may I serve you today, Senpai?");
        System.out.println("\t" + DIV2 + "\n");
    }

    /**
     * Displays exit messages.
     */
    public void showExit() {
        System.out.println("\tBye Senpai! It was a pleasure serving you, see you again soon!");
        System.out.println("\t" + DIV2 + "\n");
    }

    /**
     * Adds a particular task input by the user into the list of tasks.
     * Displays add message to user.
     *
     * @param tasks current list of tasks.
     * @param newTask new task to be added.
     */
    public static void addDescription(List<Task> tasks, Task newTask) {
        tasks.add(newTask);
        System.out.println("\tYes Senpai! I've added this task: \n\t  "
                + newTask
                + " \n\tNow you have "
                + tasks.size()
                + " tasks in the list.");
    }

    /**
     * Displays delete message to user.
     *
     * @param tasks current list of tasks.
     * @param deletedTask task to be deleted.
     */
    public static void deleteDescription(List<Task> tasks, Task deletedTask) {
        System.out.println("\tRight away Senpai! I've SHREDDED this task: \n\t  "
                + deletedTask
                + " \n\tNow you have "
                + tasks.size()
                + " tasks in the list.");
    }

    /**
     * Displays all the tasks in the list.
     *
     * @param tasks current list of tasks.
     */
    public static void showAllTask(List<Task> tasks) {
        StringBuilder list = new StringBuilder("\tSenpai, these are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            list.append("\n\t").append(index).append(". ").append(tasks.get(i));
        }
        System.out.println(list);
    }

    public static void searchTaskDescription(List<Task> searchTask) {
        if (searchTask.size() > 0) {
            System.out.println("\tSenpai, these are the tasks in your list:");
            for (int i = 0; i < searchTask.size(); i++) {
                int index = i + 1;
                Task task = searchTask.get(i);
                System.out.println("\t" + index + "." + task);
            }
        } else {
            System.out.println("\tSenpai, there are no such tasks in your list :(");
        }
    }
}

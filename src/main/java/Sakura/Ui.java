package Sakura;

import java.io.IOException;
import java.util.List;

public class Ui {
    static String DIV = "________________________________________________________________";
    static String DIV2 = "============================================================";

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

    void exit() {
        try {
            saveData();
            System.out.println("\tBye Senpai! It was a pleasure serving you, see you again soon!");
            System.out.println("\t" + DIV2 + "\n");
        } catch (IOException e) {
            SakuraException.saveError();
        }
    }

    public static void addDescription(List<Task> tasks, Task newTask) {
        tasks.add(newTask);
        System.out.println("\tYes Sir! I've added this task: \n\t  "
                + newTask
                + " \n\tNow you have "
                + tasks.size()
                + " tasks in the list.");
    }

    public static void deleteDescription(List<Task> tasks, Task deletedTask) {
        System.out.println("\tRight away Sir! I've SHREDDED this task: \n\t  "
                + deletedTask
                + " \n\tNow you have "
                + tasks.size()
                + " tasks in the list.");
    }

    public static void showAllTask(List<Task> tasks) {
        String list = "\tSir, these are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            list += "\n\t" + index + ". " + tasks.get(i);
        }
        System.out.println(list);
    }
}

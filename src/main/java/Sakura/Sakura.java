package Sakura;

import java.util.Scanner;

public class Sakura {
    static String DIV = "________________________________________________________________";
    static String DIV2 = "============================================================";
    static boolean inProgress = true;

    private static void Start(String input, TaskManager taskManager) {
        if (input.equals("bye")) {
            inProgress = false;
            System.out.println("\tBye Senpai! It was a pleasure serving you, see you again soon!");
        } else if (input.equals("list")) {
            taskManager.showAllTask();
        } else if (input.toLowerCase().startsWith("mark")) {
            taskManager.markTask(input);
        } else if (input.toLowerCase().startsWith("unmark")) {
            taskManager.unmarkTask(input);
        } else if (input.toLowerCase().startsWith("todo") || input.toLowerCase().startsWith("deadline") || input.toLowerCase().startsWith("event")) {
            taskManager.addTask(input);
        } else if (input.toLowerCase().startsWith("delete")) {
            taskManager.deleteTask(input);
        } else {
            SakuraException.genericTask();
        }
    }

    private static void run() {
        Scanner sc = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        while (inProgress) {
            String command = sc.nextLine();
            System.out.println("\t" + DIV);
            Start(command, taskManager);
            System.out.println("\t" + DIV + "\n");
        }
    }

    private static void greet() {
        System.out.println(DIV);
        System.out.println("How may I serve you today, Senpai?");
        System.out.println("\t" + DIV2 + "\n");
    }

    public static void main(String[] args) throws IndexOutOfBoundsException {
        String logo =
                  "   ▄████████    ▄████████    ▄█   ▄█▄ ███    █▄     ▄████████    ▄████████             ▄■▄          \n"
                + "  ███    ███   ███    ███   ███ ▄███▀ ███    ███   ███    ███   ███    ███       ▄■██■█   █■██■▄    \n"
                + "  ███    █▀    ███    ███   ███▐██▀   ███    ███   ███    ███   ███    ███      ██  ▄  ▀▄▀  ▄  ██   \n"
                + "  ███          ███    ███  ▄█████▀    ███    ███  ▄███▄▄▄▄██▀   ███    ███      ▀█▄  ▀▄ █ ▄▀  ▄█▀   \n"
                + "▀███████████ ▀███████████ ▀▀█████▄    ███    ███ ▀▀███▀▀▀▀▀   ▀███████████         █■■((■))■■█      \n"
                + "         ███   ███    ███   ███▐██▄   ███    ███ ▀███████████   ███    ███      ▄█▀  ▄▀ █ ▀▄  ▀█▄   \n"
                + "   ▄█    ███   ███    ███   ███ ▀███▄ ███    ███   ███    ███   ███    ███      ██  ▀  ▄▀▄  ▀  ██   \n"
                + " ▄████████▀    ███    █▀    ███   ▀█▀ ████████▀    ███    ███   ███    █▀        ▀■██■█   █■██■▀    \n"
                + "                            ▀                      ███    ███                          ▀■▀          \n";


        System.out.println("Hello! This is \n" + logo + "\nat your service!!");
        Sakura.greet();
        Sakura.run();
    }
}

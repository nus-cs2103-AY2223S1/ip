package Sakura;

import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class Sakura {
    static String DIV = "________________________________________________________________";
    static String DIV2 = "============================================================";
    static boolean inProgress = true;
    private static String DIR = "data";
    private static String FILENAME = "Sakura.txt";
    private static String DATAPATH = String.valueOf(Paths.get(Sakura.DIR, Sakura.FILENAME));

    private TaskList taskList;
    private Ui ui;
    static Storage storage;

    public Sakura(String filePath) {
        storage = new Storage(filePath);
        this.ui = new Ui();
        this.taskList = new TaskList(storage.loadData());
    }

    private void Start(String input, TaskList taskList) {
        if (input.equals("bye")) {
            inProgress = false;
            this.exit();
        } else if (input.equals("list")) {
            Ui.showAllTask(taskList.tasks);
        } else if (input.toLowerCase().startsWith("mark")) {
            taskList.markTask(input);
        } else if (input.toLowerCase().startsWith("unmark")) {
            taskList.unmarkTask(input);
        } else if (input.toLowerCase().startsWith("todo") || input.toLowerCase().startsWith("deadline") || input.toLowerCase().startsWith("event")) {
            taskList.addTask(input);
        } else if (input.toLowerCase().startsWith("delete")) {
            taskList.deleteTask(input);
        } else {
            SakuraException.genericTask();
        }
    }

    private void exit() {
        try {
            storage.saveData(this.taskList);
            this.ui.showExit();
        } catch (IOException e) {
            SakuraException.saveError();
        }
    }

    private void run() {
        ui.greet();
        Scanner sc = new Scanner(System.in);
        while (inProgress) {
            String command = sc.nextLine();
            System.out.println("\t" + DIV);
            Start(command, taskList);
            System.out.println("\t" + DIV + "\n");
        }
    }

    public static void main(String[] args) throws IndexOutOfBoundsException {
        new Sakura("./data/Sakura.txt").run();
    }
}

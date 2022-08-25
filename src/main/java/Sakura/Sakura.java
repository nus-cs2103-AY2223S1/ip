package Sakura;

import java.util.Scanner;

public class Sakura {
    static boolean inProgress = true;

    public static TaskList taskList;
    public static Ui ui;
    public static Storage storage;

    public Sakura(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        taskList = new TaskList(storage.loadData());
    }

    private void run() {
        ui.greet();
        Scanner sc = new Scanner(System.in);
        while (inProgress) {
            String command = sc.nextLine();
            Parser.parseCommand(command, taskList);
        }
    }

    public static void main(String[] args) throws IndexOutOfBoundsException {
        new Sakura("./data/Sakura.txt").run();
    }
}

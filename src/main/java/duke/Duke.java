package main.java.duke;

import java.util.Scanner;

public class Duke {

    private TaskList tasks;

    public Duke(String filePath) {
        tasks = new TaskList(Storage.load(filePath));
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Ui.setup();
        while (true) {
            try {
                String line = scanner.nextLine();
                System.out.println(Ui.divider());
                Parser.parse(line, tasks);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}

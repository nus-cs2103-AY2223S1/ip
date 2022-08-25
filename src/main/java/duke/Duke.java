package main.java.duke;

import java.util.Scanner;

public class Duke {

    private TaskList tasks;
    private Parser parser;

    public Duke(String filePath) {
        tasks = new TaskList(Storage.load(filePath));
        parser = new Parser();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Ui.setup();
        while (true) {
            try {
                String line = scanner.nextLine();
                System.out.println(Ui.divider());
                int result = this.parser.parse(line, tasks);
                if (result == 1) {
                    break;
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}

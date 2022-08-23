package duke;

import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    private TaskList tasks;
    private final Scanner SCANNER = new Scanner(System.in);
    private Ui ui;
    private Storage storage;
    private Parser parser;

    Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
    }

    private void run() {
        ui.Introduction();
        tasks = storage.ReadData();

        String input = SCANNER.nextLine();
        while (!input.equals("bye")) {
            parser.Process(input, tasks);
            input = SCANNER.nextLine();
        }
        storage.SaveData(tasks);
    }
    public static void main(String[] args) {
        new Duke().Run();
    }




}

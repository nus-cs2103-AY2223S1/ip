package duke;

import java.io.*;
import java.time.format.DateTimeParseException;

import java.util.Scanner;

public class Duke {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    public Duke() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
        taskList = new TaskList();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.start();
        try {
            storage.read(taskList);
            while (sc.hasNext()) {
                String input = sc.nextLine();
                parser.parseInput(input, ui, storage, taskList);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }



}

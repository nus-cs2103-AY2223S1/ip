package DukeBot;
import DukeBot.command.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static Storage storage;
    private static TaskList tasks = new TaskList();
    //private Ui ui;

    public static void main(String[] args) {
        storage = new Storage("src/main/tasks.txt");
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            System.out.println(e);
            System.out.println("Creating new file.");
            tasks = new TaskList();
        }
        System.out.println("-----------------------------------------------");
        System.out.println("| Hi this is Thesh. What can I do for you? |");
        System.out.println("-----------------------------------------------");
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        Parser p = new Parser(tasks);
        while (!isExit) {
            try {
                Command c = p.parse(sc.nextLine());
                c.execute();
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
        storage.write(tasks);
    }
}

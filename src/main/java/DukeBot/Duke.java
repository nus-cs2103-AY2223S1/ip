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

    private static File fileToRead;

    private static Path path;

    /*
    public Duke(String filePath) {

    }
     */

    public static void list() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.println(String.format("      %d. %s", i + 1, tasks.get(i)));
        }
    }

    public static void mark(int taskNumber) throws DukeException {
        tasks.get(taskNumber).markComplete();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println(String.format("      %s", tasks.get(taskNumber)));
    }

    public static void unmark(int taskNumber) throws DukeException {
        tasks.get(taskNumber).markIncomplete();
        System.out.println("    OK, I've marked this task as not done yet");
        System.out.println(String.format("      %s", tasks.get(taskNumber)));
    }

    public static void delete(int taskToDelete) {
        tasks.deleteTask(taskToDelete);
    }

    /*
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {

        }

    }
     */

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

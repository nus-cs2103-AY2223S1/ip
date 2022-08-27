import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
//    public static void printLine() {
//        System.out.println("-".repeat(100));
//    }
//
//    public static void greetings() {
//        printLine();
//        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
//        printLine();
//    }
//
//    public static void printBye() {
//        printLine();
//        System.out.println("Bye. Hope to see you again soon!");
//        printLine();
//    }
//
//    public static String listAllItems() {
//        //printLine();
//        String result = "";
//        result += "-".repeat(100);
//        for (int i = 0; i < tasks.size(); i++) {
//            int number = i + 1;
//            result += "\n" + number + ". " + tasks.get(i).toString();
//            ;
//            //System.out.println(number + ". " + tasks.get(i).toString());
//        }
//        result += "\n" + "-".repeat(100);
//        return result;
//    }


    public Duke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(filePath);
    }

    public void run() {
        ui.greetings();
        while (true) {
            String input = ui.readCommand();
            String command = input.split(" ")[0];
            switch (command) {
                case "bye":
                    ui.printBye();
                    return;
                case "list":
                    System.out.println(ui.listAllItems(tasks.getTasks()));
                    break;
                case "mark":
                    int num1 = Integer.parseInt(input.split(" ")[1]);
                    tasks.mark(num1, ui);
                    storage.update(ui.listAllItems(tasks.getTasks()));
                    break;
                case "unmark":
                    int num2 = Integer.parseInt(input.split(" ")[1]);
                    tasks.unmark(num2, ui);
                    storage.update(ui.listAllItems(tasks.getTasks()));
                    break;
                case "delete":
                    int num3 = Integer.parseInt(input.split(" ")[1]);
                    tasks.delete(num3, ui);
                    storage.update(ui.listAllItems(tasks.getTasks()));
                    break;
                default:
                    try {
                        tasks.addATask(input);
                        storage.update(ui.listAllItems(tasks.getTasks()));
                    } catch (DukeException e) {
                        ui.printMessage(e.getMessage());
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new Duke("src/main/java/duke.txt").run();
    }

}

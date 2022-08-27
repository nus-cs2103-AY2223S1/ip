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

    public void addATask(String item) throws DukeException {
        String[] arr = item.split(" ", 2);
        String type = arr[0];
        switch (type) {
            case "todo":
                if (arr.length == 1) {
                    throw new DukeException(" OOPS!!! The description of a todo cannot be empty.");
                }
                String todoName = arr[1].trim();
                Task newTodo = new Todo(todoName);
                tasks.add(newTodo);
                break;
            case "deadline":
                String[] deadlineArr = arr[1].split("/");
                String deadlineName = deadlineArr[0];
                String dl = deadlineArr[1];
                Task newDeadline = new Deadline(deadlineName, dl);
                tasks.add(newDeadline);
                break;
            case "event":
                String[] eventArr = arr[1].split("/");
                String eventName = eventArr[0];
                String eventTime = eventArr[1];
                Task newEvent = new Event(eventName, eventTime);
                tasks.add(newEvent);
                break;
            default:
                DukeException e = new DukeException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
                throw e;
        }

        ui.printLine();
        int numOfTasks = tasks.size();
        System.out.println("Got it. I've added this task:" + "\n" + tasks.get(numOfTasks - 1).toString() +
                "\n" + "Now you have " + numOfTasks + " tasks in the list.");
        ui.printLine();
    }

    public void mark(int num) {
        ui.printLine();
        tasks.get(num - 1).mark();
        System.out.println("OK, I've marked this task as done:");
        System.out.println(tasks.get(num - 1).toString());
        ui.printLine();
    }

    public void unmark(int num) {
        ui.printLine();
        tasks.get(num - 1).unMark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(num - 1).toString());
        ui.printLine();
    }

    public void delete(int num) {
        ui.printLine();
        Task removedTask = tasks.get(num - 1);
        tasks.remove(num - 1);
        System.out.println("Noted. I've removed this task:" + "\n" + removedTask.toString()
                + "Now you have " + tasks.size() + " tasks in the list.");
        ui.printLine();
    }

    public Duke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(filePath);
    }

    public void run() {
        ui.greetings();
        //create the duke file that stores the text
        String filePath = new File("").getAbsolutePath();
        filePath = filePath.concat("/src/main/java/duke.txt");
        Path path = Paths.get(filePath);
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
                    mark(num1);
                    storage.update(ui.listAllItems(tasks.getTasks()));
                    break;
                case "unmark":
                    int num2 = Integer.parseInt(input.split(" ")[1]);
                    unmark(num2);
                    storage.update(ui.listAllItems(tasks.getTasks()));
                    break;
                case "delete":
                    int num3 = Integer.parseInt(input.split(" ")[1]);
                    delete(num3);
                    storage.update(ui.listAllItems(tasks.getTasks()));
                    break;
                default:
                    try {
                        addATask(input);
                        storage.update(ui.listAllItems(tasks.getTasks()));
                    } catch (DukeException e) {
                        ui.printMessage(e.getMessage());
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new Duke("/src/main/java/duke.txt").run();
    }

}

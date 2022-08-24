package DukeBot;
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
        while (sc.hasNext()) {
            String command = sc.nextLine();
            System.out.println("    ____________________________________________________________");
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                Duke.list();
            } else if (command.startsWith("mark")) {
                try {
                    String taskNumberString = command.split(" ")[1];
                    int taskNumber = Integer.parseInt(taskNumberString);
                    Duke.mark(taskNumber - 1);
                } catch (DukeException e) {
                    System.out.println(e);
                } catch (NumberFormatException e) {
                    System.out.println("    please only input a number after 'mark'.");
                } catch (NullPointerException e) {
                    System.out.println(String.format("There is only %d tasks in the list.", Task.getTaskCount()));
                }
            } else if (command.startsWith("unmark")) {
                try {
                    String taskNumberString = command.split(" ")[1];
                    int taskNumber = Integer.parseInt(taskNumberString);
                    Duke.unmark(taskNumber - 1);
                } catch (DukeException e) {
                    System.out.println(e);
                } catch (NumberFormatException e) {
                    System.out.println("    please only input a number after 'unmark'.");
                } catch (NullPointerException e) {
                    System.out.println(String.format("There is only %d tasks in the list.", Task.getTaskCount()));
                }
            } else if (command.startsWith("delete")) {
                command = command.replace("delete ", "");
                int taskToDelete = Integer.parseInt(command);
                Duke.delete(taskToDelete - 1);
            } else {
                Task newTask = null;
                if (command.startsWith("todo")) {
                    if (command.equals("todo")) {
                        System.out.println("    OOPS!!! The description of a todo cannot be empty.");
                        continue;
                    }
                    command = command.replace("todo ", "");
                    newTask = new ToDo(command);

                } else if (command.startsWith("deadline")) {
                    command = command.replace("deadline ", "");
                    String[] commands = command.split(" /by ");
                    if (commands.length != 2) {
                        System.out.println("    Sorry, please give a deadline in this format:\n    " +
                                "  'deadline <action> /by <deadline>'");
                        continue;
                    }
                    try {
                        newTask = new Deadline(commands[0], commands[1]);
                    } catch (DukeException e) {
                        System.out.println(e);
                        continue;
                    }
                } else if (command.startsWith("event")) {
                    command = command.replace("event ", "");
                    String[] commands = command.split(" /at ");
                    if (commands.length != 2) {
                        System.out.println("    Sorry, please give an event in this format:\n    " +
                                "  'event <event> /at <timing>'");
                        continue;
                    }
                    try {
                        newTask = new Event(commands[0], commands[1]);
                    } catch (DukeException e) {
                        System.out.println(e);
                        continue;
                    }
                } else {
                    System.out.println("    OOPS!!! I'm sorry, but I don't know what that means :-(");
                    continue;
                }
                tasks.addTask(newTask, false);
            }
            System.out.println("    ____________________________________________________________");
        }
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
        storage.write(tasks);
    }
}

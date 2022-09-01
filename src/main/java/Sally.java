import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Sally {
    protected static ArrayList<Task> list = new ArrayList<>();
    private static Scanner sc;

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Sally(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (SallyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        printBorder();
        System.out.println("Hello! I'm Sally");
        System.out.println("What can I do for you?");
        printBorder();

        try {
            readsFile();
        } catch (FileNotFoundException e) {
        }
        sc = new Scanner(System.in);
        run();
    }

    public void run() {
        String message = sc.nextLine();

        if (message.equals("bye")) {
            printBorder();
            System.out.println("Until next time!");
            printBorder();
            return;
        }

        try {
            if (message.equals("list")) {
                printBorder();
                if (list.size() == 0) {
                    System.out.println("You don't have any list right now");
                } else {
                    System.out.println("Here's your current list:");
                    System.out.println(printList());
                }
                printBorder();
            }

            // Delete
            else if (message.startsWith("delete")) {
                if (message.length() > 7 && message.substring(7).trim().chars().allMatch(Character::isDigit)) {
                    int taskNum = Integer.parseInt(message.substring(7).trim()) - 1;
                    if (0 <= taskNum && taskNum < list.size()) {
                        String removed = list.get(taskNum).toString();
                        list.remove(taskNum);
                        printBorder();
                        System.out.println("This task has been removed from your to-do list:\n" + removed);
                        printBorder();
                    } else {
                        throw new SallyException.SallyTaskNotFoundException();
                    }
                } else {
                    throw new SallyException.SallyTaskNotFoundException();
                }
                try {
                    Sally.savesFile();
                } catch (IOException e) {
                    System.out.println("File Not Found");
                }
            }

            // Unmark
            else if (message.contains("unmark")) {
                int taskNum = Integer.parseInt(message.substring(7)) - 1; // -1 so that index is constant
                if (taskNum >= 0 && taskNum < list.size()) {
                    Task task = list.get(taskNum);
                    String description = task.toString();
                    printBorder();
                    if (task.isDone) {
                        task.markAsUndone();
                        String unmarkTask = task.toString();
                        System.out.println("Got it, I've unmarked this task for you!\n" + unmarkTask);
                        try {
                            Sally.savesFile();
                        } catch (IOException e) {
                            System.out.println("File Not Found");
                        }
                    } else {
                        System.out.println("You have not marked: \n  " + description + "\n");
                    }
                    printBorder();
                } else {
                    throw new SallyException.SallyTaskNotFoundException();
                }
            } else if (!message.contains("unmark") && message.contains("mark")) {
                int taskNum = Integer.parseInt(message.substring(5)) - 1; // -1 so that index is constant
                if (taskNum >= 0 && taskNum < list.size()) {
                    Task task = list.get(taskNum);
                    String description = task.toString();
                    printBorder();
                    if (!task.isDone) {
                        task.markAsDone();
                        String markTask = task.toString();
                        System.out.println("Got it, I've marked this task for you!\n" + markTask);
                        try {
                            savesFile();
                        } catch (IOException e) {
                            System.out.println("File Not Found");
                        }
                    } else {
                        System.out.println("You have previously done: \n    " + description + "\n");
                    }
                    printBorder();
                } else {
                    throw new SallyException.SallyTaskNotFoundException();
                }
            } else {

                //ToDos
                if (message.startsWith("todo")) {
                    if (message.length() > 4) {
                        String description = message.substring(5);
                        Task.makeTask(description, "", Task.Type.TODO, true);
                        System.out.println("setelah make task");
                    } else {
                        throw new SallyException.SallyNoDescriptionException();
                    }
                }

                //Deadlines
                else if (message.startsWith("deadline")) {
                    String description, by;
                    if (message.length() <= 8) {
                        throw new SallyException.SallyInvalidInputException();
                    } else if (message.contains("/by ")) {
                        description = message.substring(9, message.indexOf("/by") - 1);
                        by = message.substring(message.indexOf("/by") + 4);
                        LocalDate localDate;
                        try {
                            localDate = LocalDate.parse(by);
                            by = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyy"));
                        } catch (DateTimeParseException e) {
                            System.out.println("Oops! Date has to be in the format of yyyy-mm-dd");
                        }
                        Task.makeTask(description, by, Task.Type.DEADLINE, true);
                    } else {
                        throw new SallyException.SallyNoDeadlineException();
                    }
                }

                //Events
                else if (message.startsWith("event")) {
                    String description, at;
                    if (message.length() <= 5) {
                        throw new SallyException.SallyInvalidInputException();
                    } else if (message.contains("/at ")) {
                        description = message.substring(6, message.indexOf("/at") - 1);
                        at = message.substring(message.indexOf("/at") + 4);
                        Task.makeTask(description, at, Task.Type.EVENT, true);
                    } else {
                        throw new SallyException.SallyNoPlaceException();
                    }
                }

                //Any other messages
                else {
                    throw new SallyException.SallyInvalidInputException();
                }
            }
        } catch (SallyException e) {
            System.out.println(e);
        } finally {
            run();
        }
    }

    protected static String printList() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            int number = i + 1;
            output = output + number + ". " + list.get(i).toString() + "\n";
        }
        return output;
    }

    public static void printBorder() {
        System.out.println(border());
    }

    public static String border() {
        return "-------------------------------------------------------------------------------------";
    }
}
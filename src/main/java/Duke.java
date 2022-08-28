import exceptions.DukeException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> tasks = new ArrayList<>();

    private static void printSeparator() {
        System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    
    private static void printMsg(String msg) {
        printSeparator();
        System.out.println("     " + msg);
        printSeparator();
    }
    
    private static void printMultiMsg(String[] msgs) {
        printSeparator();
        for (String msg : msgs) {
            System.out.println("     " + msg);
        }
        printSeparator();
    }

    private static void saveTasksToFile() {
        try (FileOutputStream fos = new FileOutputStream("./duke.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadTasksFromFile() {
        try (FileInputStream fis = new FileInputStream("./duke.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Object o = ois.readObject();
            if (o instanceof List) {
                tasks = (List<Task>) o;
            }
        } catch (FileNotFoundException ignored) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        loadTasksFromFile();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printMultiMsg(new String[]{"Hello my name is Duke", "What can I do for you?"});

        Scanner sc = new Scanner (System.in);
        String command, description;

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.length() < 1) continue;

            try {
                String[] words = input.split(" ", 2);
                command = words[0];
                if (words.length > 1) description = words[1];
                else description = "";

                if (command.equals("bye")) {
                    printMsg("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("list")) {
                    String[] taskStrings = new String[tasks.size()];
                    for (int i = 0; i < tasks.size(); i++) {
                        taskStrings[i] = (i + 1) + ". " + tasks.get(i).toString();
                    }
                    printMultiMsg(taskStrings);
                } else if (command.equals("mark")) {
                    Task task = tasks.get(Integer.parseInt(description) - 1);
                    task.markDone();
                    printMultiMsg(new String[]{"Nice! I've marked this task as done:", task.toString()});
                } else if (command.equals("delete")) {
                    Task task = tasks.get(Integer.parseInt(description) - 1);
                    tasks.remove(task);
                    printMultiMsg(new String[]{
                            "Noted. I've removed this task:",
                            "  " + task,
                            "Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list."
                    });
                } else if (command.equals("todo")) {
                    if (description.length() == 0) throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    Task task = new ToDo(description, false);
                    tasks.add(task);
                    printMultiMsg(new String[]{
                            "Got it. I've added this task:",
                            "  " + task,
                            "Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list."
                    });
                } else if (command.equals("deadline")) {
                    String[] splitArgs = description.split(" /by ", 2);
                    String title = splitArgs[0];
                    String stringBy = splitArgs[1];
                    LocalDate dateBy = LocalDate.parse(stringBy);
                    Task task = new Deadline(title, false, dateBy);
                    tasks.add(task);
                    printMultiMsg(new String[]{
                            "Got it. I've added this task:",
                            "  " + task,
                            "Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list."
                    });
                } else if (command.equals("event")) {
                    String[] splitArgs = description.split(" /at ", 2);
                    String title = splitArgs[0];
                    String stringAt = splitArgs[1];
                    LocalDate dateAt = LocalDate.parse(stringAt);
                    Task task = new Event(title, false, dateAt);
                    tasks.add(task);
                    printMultiMsg(new String[]{
                            "Got it. I've added this task:",
                            "  " + task,
                            "Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list."
                    });
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                saveTasksToFile();
            } catch (DukeException e) {
                printMsg(e.getMessage());
            }
        }
    }
}

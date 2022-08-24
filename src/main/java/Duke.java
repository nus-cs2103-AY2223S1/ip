import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

enum Commands {
    TODO,
    EVENT,
    DEADLINE,
    DELETE
}
public class Duke {

    public static void saveFile(ArrayList<Task> ls) {
        try {
            File createFile = new File("Duke.txt");
            if (createFile.createNewFile()) {
                System.out.println("Creating Duke.txt...");
            } else {
                System.out.println("Updating Duke.txt...");
            }
            FileWriter writer = new FileWriter("Duke.txt");
            for (int i = 0; i < ls.size(); i++) {
                writer.write(ls.get(i) + "\n");
            }
            writer.close();
            System.out.println("Duke.txt is fully updated.");
        } catch (IOException e) {
            System.out.println("An error occurred when trying to save your task list. Please try again.");
        }
    }

    public static ArrayList<Task> retrieveFile() {
        System.out.println("Retrieving files...");
        try {
            File savedFile = new File("Duke.txt");
            SaveFile retrieved = new SaveFile(savedFile);
            return retrieved.retrieveInfo();
        } catch (FileNotFoundException e) {
            System.out.println("No saved files. Starting a new task sheet!");
        } catch (DukeException e) {
            System.out.println("Error occured: " + e.getMessage() + "\nData retrieval has stopped.");
        }
        return new ArrayList<Task>();
    }

    public static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }
    public static void main(String[] args) throws DukeException {
        greet();
        Scanner scanner = new Scanner(System.in);
        String input = "";
        ArrayList<Task> list = retrieveFile();
        while (true) {
            input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + "." + list.get(i));
                    }
                } else if (input.startsWith("mark")) {
                    int target = Integer.valueOf(input.split(" ")[1]) - 1;
                    System.out.println(list.get(target).mark());
                } else if (input.startsWith("unmark")) {
                    int target = Integer.valueOf(input.split(" ")[1]) - 1;
                    System.out.println(list.get(target).unmark());
                } else if (input.startsWith("todo")) {
                    String[] info = input.split("todo ");
                    if (info.length <= 1) {
                        throw new DukeException("OOPS!! The description of a todo cannot be empty.");
                    }
                    list.add(new ToDo(info[1]));
                    System.out.println("Got it. I've added this task:\n" + list.get(list.size() -1));
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                } else if (input.startsWith("deadline")) {
                    String[] info = input.split("deadline ");
                    if (info.length <= 1) {
                        throw new DukeException("OOPS!! The description of a deadline cannot be empty.");
                    }
                    String[] item = info[1].split(" /by ");
                    if (item.length <= 1) {
                        throw new DukeException("OOPS!! A deadline has to be set!");
                    }
                    list.add(new Deadline(item[0], item[1]));
                    System.out.println("Got it. I've added this task:\n" + list.get(list.size() -1));
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                } else if (input.startsWith("event")) {
                    String[] info = input.split("event ");
                    if (info.length <= 1) {
                        throw new DukeException("OOPS!! The description of a event cannot be empty.");
                    }
                    String[] item = info[1].split(" /at ");
                    if (item.length <= 1) {
                        throw new DukeException("OOPS!! The timing of the event has to be set!");
                    }
                    list.add(new Event(item[0], item[1]));
                    System.out.println("Got it. I've added this task:\n" + list.get(list.size() -1));
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                } else if (input.startsWith("delete")) {
                    String[] info = input.split("delete ");
                    if (info.length <= 1) {
                        throw new DukeException("OOPS!! The item to be deleted has to be specified.");
                    }
                    int target = Integer.valueOf(info[1]) - 1;
                    Task toRemove = list.get(target);
                    list.remove(target);
                    System.out.println("Noted. I've removed this task:\n" + toRemove);
                } else {
                    throw new DukeException("OOPS!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException error) {
                System.out.println(error.getMessage());
            }

        }
        saveFile(list);
        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}

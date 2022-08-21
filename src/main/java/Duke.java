import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        String input = "";
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        String[] validInputs = {"delete", "mark", "unmark", "todo", "deadline", "event"};
        String logFilePath = "data/duke.txt";
        File logDir = new File("data");
        File taskLog = new File(logFilePath);

        if (!logDir.exists()) {
            logDir.mkdirs();
            System.out.println("data folder created");
        }

        try {
            Scanner readFile = new Scanner(taskLog);
            while (readFile.hasNext()) {
                String taskString = readFile.nextLine();
                String[] split = taskString.split(" \\| ");
                System.out.println(split[0] + split[1] + split[2]);
                switch (split[0]) {
                    case "T": { // Checks for Todo
                        list.add(new Todo(split[2]));
                        break;
                    }
                    case "D": { // Checks for Deadline
                        list.add(new Deadline(split[2], split[3]));
                        break;
                    }
                    case "E": { // Checks for Event
                        list.add(new Event(split[2], split[3]));
                        break;
                    }
                }
                if (split[1] == "X") {
                    list.get(list.size() - 1).markAsDone();
                }
            }
        } catch (FileNotFoundException e) {

        }

        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");

        while (!input.equals("bye")) {
            try {
                input = scan.nextLine(); // Reads user input
                String[] split = input.split(" ", 2);
                if (input.equals("list")) { // Prints out items in list
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i+1 + "." + list.get(i).toString());
                    }
                } else if (!input.equals("bye")) { // Only adds input to list if it is not "bye"
                    if (split.length > 0 && Arrays.asList(validInputs).contains(split[0])) {
                        switch (split[0]) {
                            case "delete": { // Checks for delete
                                int index = Integer.parseInt(split[1]) - 1;
                                System.out.println("Noted. I've removed this task:" + "\n" +
                                        "\t" + list.get(index).toString());
                                list.remove(index);
                                System.out.println("Now you have " + list.size() + " tasks in the list.");
                                break;
                            }
                            case "mark": { // Checks for mark
                                int index = Integer.parseInt(split[1]) - 1;
                                list.get(index).markAsDone();
                                System.out.println("Nice! I've marked this task as done:" + "\n" +
                                        "\t" + list.get(index).toString());
                                break;
                            }
                            case "unmark": { // Checks for unmark
                                int index = Integer.parseInt(split[1]) - 1;
                                list.get(index).markAsUndone();
                                System.out.println("Ok, I've marked this task as not done yet:" + "\n" +
                                        "\t" + list.get(index).toString());
                                break;
                            }
                            case "todo":  // Checks for Todo
                                if (split.length < 2) {
                                    throw new DukeException("todo");
                                } else {
                                    list.add(new Todo(split[1]));
                                    System.out.println("Got it. I've added this task:" + "\n\t" +
                                            list.get(list.size() - 1).toString());
                                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                                }
                                break;
                            case "deadline":  // Checks for Deadline
                                if (split.length < 2) {
                                    throw new DukeException("deadline");
                                } else {
                                    String[] temp = split[1].split(" /by ", 2);
                                    list.add(new Deadline(temp[0], temp[1]));
                                    System.out.println("Got it. I've added this task:" + "\n\t" +
                                            list.get(list.size() - 1).toString());
                                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                                }
                                break;
                            case "event":  // Checks for Event
                                if (split.length < 2) {
                                    throw new DukeException("event");
                                } else {
                                    String[] temp = split[1].split(" /at ", 2);
                                    list.add(new Event(temp[0], temp[1]));
                                    System.out.println("Got it. I've added this task:" + "\n\t" +
                                            list.get(list.size() - 1).toString());
                                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                                }
                                break;
                        }
                    }
                    else {
                        throw new DukeException(); // Invalid input
                    }
                }
                writeToFile(logFilePath, getLogString(list));
            } catch (DukeException e) {
                System.out.println(e.getDescription());
            } catch (IOException e) {
                System.out.println("Something went wrong!" + e.getMessage());
            }
        }

        System.out.println("Bye. Hope to see you again soon!"); // Exits when user types "bye"
    }

    private static void writeToFile(String filepath, String textToAdd) throws IOException {
        FileWriter copyTasks = new FileWriter(filepath);
        copyTasks.write(textToAdd);
        copyTasks.close();
    }

    private static String getLogString(ArrayList<Task> tasks) {
        String retString = "";

        for (Task value : tasks) {
            String logTask = "";
            if (value instanceof Todo) {
                Todo task = (Todo) value;
                logTask = String.format("T | %s | %s", task.getStatusIcon(), task.getDescription());
            } else if (value instanceof Deadline) {
                Deadline task = (Deadline) value;
                logTask = String.format("D | %s | %s | %s", task.getStatusIcon(), task.getDescription(), task.by);
            } else {
                Event task = (Event) value;
                logTask = String.format("E | %s | %s | %s", task.getStatusIcon(), task.getDescription(), task.when);
            }
        retString = retString + logTask + System.lineSeparator();
        }

        return retString;
    }
}

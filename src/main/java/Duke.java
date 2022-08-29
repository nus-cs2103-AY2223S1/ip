import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileWriter;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "I'm Duke! \n What can I do for you?");

        // Read user input
        Scanner scanner = new Scanner(System.in);

        // list of items
        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            try {
                String input = scanner.nextLine();
                Commands command = Commands.parseCommand(input.split(" ")[0])
                        .orElseThrow(() -> new DukeException("Sorry, I don't understand you. Please try again."));
                if (command == Commands.bye) {
                    printWithLineBreak("Goodbye! Hope to see you again!");
                    break;
                } else if (command == Commands.list) {
                    printWithLineBreak(printArray(list));
                } else if (command == Commands.mark) {
                    try {
                        int index = Integer.parseInt(input.split(" ")[1]) - 1;
                        list.get(index).setDone();
                        printWithLineBreak("Nice! I've marked this task as done:\n" + "[X] " + list.get(index));
                    } catch (Exception e) {
                        throw new DukeException("Invalid Index given!");
                    }
                } else if (command == Commands.unmark) {
                    try {
                        int index = Integer.parseInt(input.split(" ")[1]) - 1;

                        list.get(index).setUndone();
                        printWithLineBreak("OK, I've marked this task as not done yet:\n" + "[ ] " + list.get(index));
                    } catch (Exception e) {
                        throw new DukeException("Invalid Index given!");
                    }
                } else if (command == Commands.todo) {
                    try {
                        String description = input.split(" ", 2)[1];
                        Todo todo = new Todo(description);
                        list.add(todo);

                        printWithLineBreak("Got it. I've added this task:\n" + todo + "\nNow you have " + list.size()
                                + " tasks in your list.");
                    } catch (Exception e) {
                        if (input.split(" ", 2).length == 1) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                    }
                } else if (command == Commands.deadline) {
                    try {
                        String description = input.split(" ", 2)[1].split("/")[0];
                        String date = input.split(" ", 2)[1].split("/")[1].split(" ")[1];
                        Deadline deadline = new Deadline(description, date);
                        list.add(deadline);

                        printWithLineBreak(
                                "Got it. I've added this task:\n" + deadline + "\nNow you have " + list.size()
                                        + " tasks in your list.");
                    } catch (Exception e) {
                        if (input.split(" ", 2).length == 1) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        }
                    }
                } else if (command == Commands.event) {
                    try {
                        String description = input.split(" ", 2)[1].split("/")[0];
                        String date = input.split(" ", 2)[1].split("/")[1].split(" ", 2)[1];
                        Event event = new Event(description, date);
                        list.add(event);

                        printWithLineBreak("Got it. I've added this task:\n" + event + "\nNow you have " + list.size()
                                + " tasks in your list.");
                    } catch (Exception e) {
                        if (input.split(" ", 2).length == 1) {
                            throw new DukeException("The description of a event cannot be empty.");
                        }
                    }
                } else if (command == Commands.delete) {
                    try {
                        int index = Integer.parseInt(input.split(" ")[1]) - 1;
                        Task deleted = list.get(index);
                        list.remove(index);
                        printWithLineBreak(
                                "Noted, I've removed this task:\n" + deleted + "\nNow you have " + list.size()
                                        + " tasks in your list.");
                    } catch (Exception e) {
                        throw new DukeException("Invalid Index given!");
                    }
                } else {
                    throw new DukeException("Sorry, I don't understand you. Please try again.");
                }

                // Saves list to file ./data/duke.txt
                saveList(list);
            } catch (DukeException e) {
                printWithLineBreak(e.getMessage());
            }
        }

        // Close scanner
        scanner.close();

    }

    public static void printWithLineBreak(String text) {
        System.out.println("\n" + text + "\n");
    }

    public static String printArray(ArrayList<Task> list) {
        if (list.size() == 0) {
            return "You have no tasks in your list.";
        }
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            result += "\t" + (i + 1) + ". " + list.get(i) + "\n";
        }
        return result;
    }

    public static void saveList(ArrayList<Task> list) {
        try {
            // Checks if directory ./data/ exists and creates it if not
            Path path = Path.of("./data/");
            if (Files.notExists(path)) {
                Files.createDirectory(path);
            }
            // Saves to file duke.txt
            FileWriter writer = new FileWriter("./data/duke.txt", false);
            for (int i = 0; i < list.size(); i++) {
                writer.write(list.get(i).toString() + "\n");
                writer.close();
            }
        } catch (Exception e) {
            System.out.println("Error while saving list to file.");
        }
    }
}

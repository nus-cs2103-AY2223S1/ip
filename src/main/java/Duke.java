import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Duke {

    private static void writeTasksToDisk(List<Task> tasks) {
        try {

            // Note: This deletes the existing contents of data/duke.txt.
            PrintWriter printWriter = new PrintWriter("data/duke.txt");

            for (Task task : tasks) {
                printWriter.println(task.toFileRepresentation());
            }

            printWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred when saving the tasks.");
            e.getStackTrace();
        }
    }

    private static void loadTasksFromDisk(List<Task> tasks) {
        try {
            // Create the tasks directory if it does not already exist.
            File tasksDirectory = new File("data/");
            if (!tasksDirectory.exists()) {
                tasksDirectory.mkdir();
            }
            File file = new File("data/duke.txt");
            if (!file.createNewFile()) { // Create the duke.txt file if it does not already exist.
                // Parse the file.
                try {
                    Scanner scanner = new Scanner(file);
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        // TODO: Forbid the user from entering the | character.
                        String[] components = line.split("\\|");
                        boolean isTaskInitiallyComplete = (components[1] == "1");
                        switch (components[0]) {
                        case "T":
                            tasks.add(new ToDo(components[2], isTaskInitiallyComplete));
                            break;
                        case "D":
                            tasks.add(new Deadline(components[2], isTaskInitiallyComplete, components[3]));
                            break;
                        case "E":
                            tasks.add(new Event(components[2], isTaskInitiallyComplete, components[3]));
                            break;
                        }
                    }
                } catch (FileNotFoundException e) {
                    // This really should not happen.
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // Create a scanner that reads user input.
        Scanner sc = new Scanner(System.in).useDelimiter("\n");

        // Create a list to store tasks.
        List<Task> tasks = new ArrayList<>();

        loadTasksFromDisk(tasks);

        // Greet the user.
        System.out.println("Duke: Hello! I am Duke.");

        while (true) {
            String userInput = sc.next();

            if (userInput.equals("bye")) {
                writeTasksToDisk(tasks);
                System.out.println("Duke: Bye!");
                break;
            }

            if (userInput.equals("list")) {
                System.out.println("Duke: Here are your tasks.");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%3d: %s\n", i + 1, tasks.get(i));
                }
            }

            else if (userInput.startsWith("mark ")) {

                int index;

                try {
                    index = Integer.parseInt(userInput.substring(5)) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("Duke: That doesn't look like a number to me. Try harder.");
                    continue; // Let the user try again.
                }

                if (index == -1) { // The user entered 0.
                    System.out.println("Duke: Oh, so you think you're a programmer?");
                    continue; // Let the user try again.
                }
                if (index >= tasks.size()) {
                    System.out.println("Duke: You don't even have that many tasks... yet!");
                    continue; // Let the user try again.
                }

                tasks.get(index).setComplete(true);
                System.out.println("Duke: Task marked as complete.");
                System.out.println(tasks.get(index));
            }

            else if (userInput.startsWith("unmark ")) {
                int index;

                try {
                    index = Integer.parseInt(userInput.substring(7)) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("Duke: That doesn't look like a number to me. Try harder.");
                    continue; // Let the user try again.
                }

                if (index == -1) { // The user entered 0.
                    System.out.println("Duke: Oh, so you think you're a programmer?");
                    continue; // Let the user try again.
                }
                if (index >= tasks.size()) {
                    System.out.println("Duke: You don't even have that many tasks... yet!");
                    continue; // Let the user try again.
                }
                tasks.get(index).setComplete(false);
                System.out.println("Duke: Task marked as incomplete.");
                System.out.println(tasks.get(index));
            }

            else if (userInput.startsWith("todo ")) {
                String toDoName = userInput.substring(5).strip();
                tasks.add(new ToDo(toDoName, false));
                System.out.printf("Duke: I have added the to-do %s.\n", toDoName);
            }

            // e.g. userInput is "event supernova @ friday night"
            else if (userInput.startsWith("event ")) {
                // TODO: Ensure that the string contains a "@" and a time range is specified.
                String eventName = userInput.substring(6).split("@ ")[0].strip();
                String eventTimeRange = userInput.split("@ ")[1].strip();
                tasks.add(new Event(eventName, false, eventTimeRange));
                System.out.printf("Duke: I have added the event %s.\n", eventName);
            }

            else if (userInput.startsWith("deadline ")) {
                // TODO: Ensure that the string contains a "@" and a due date is specified.
                String deadlineName = userInput.substring(9).split("@ ")[0].strip();
                String deadlineDueDate = userInput.split("@ ")[1].strip();
                tasks.add(new Deadline(deadlineName, false, deadlineDueDate));

                System.out.printf("Duke: I have added the deadline %s.\n", deadlineName);
            }

            else if (userInput.startsWith("delete ")) {
                // TODO: Ensure that the input is reasonable.
                int index = Integer.parseInt(userInput.substring(7)) - 1;
                System.out.printf("Duke: I have removed the task %s.\n", tasks.get(index));
                tasks.remove(index);
            }

            else {
                System.out.println("Duke: I don't understand!");
            }
        }
    }
}
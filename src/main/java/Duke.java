import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo = "     _   _    ______     _____ ____\n"
                + "    | | / \\  |  _ \\ \\   / /_ _/ ___|\n"
                + " _  | |/ _ \\ | |_) \\ \\ / / | |\\___ \\\n"
                + "| |_| / ___ \\|  _ < \\ V /  | | ___) |\n"
                + " \\___/_/   \\_\\_| \\_\\ \\_/  |___|____/\n";
        System.out.println(logo);

        // greeting messages
        say("Hello. I'm Jarvis", true, false);
        say("What can I do for you?", false, true);

        readFile();

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            try {
                String userInput = scanner.nextLine();

                if (userInput.equals("bye")) {
                    // when user enters bye
                    // exit programme
                    exit();
                    break;
                } else if (userInput.equals("list")) {
                    // when user enters list
                    // display the current list
                    list(tasks);
                } else if (userInput.startsWith("mark")) {
                    // when user enters mark and a number
                    // mark the corresponding task as done
                    if (userInput.split(" ", 2).length == 1) {
                        throw new DukeException("Please specify which task to mark.");
                    }
                    int index = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                    if (index >= tasks.size()) {
                        throw new DukeException("It seems that there is no corresponding task.");
                    }
                    mark(tasks, index);
                } else if (userInput.startsWith("unmark")) {
                    // when user enters unmark and a number
                    // mark the corresponding task as not done
                    if (userInput.split(" ", 2).length == 1) {
                        throw new DukeException("Please specify which task to unmark.");
                    }
                    int index = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                    if (index >= tasks.size()) {
                        throw new DukeException("It seems that there is no corresponding task.");
                    }
                    unmark(tasks, index);
                } else if (userInput.startsWith("delete")) {
                    // when user enters delete and a number
                    // delete the corresponding task from the list
                    if (userInput.split(" ", 2).length == 1) {
                        throw new DukeException("Please specify which task to delete.");
                    }
                    int index = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                    if (index >= tasks.size()) {
                        throw new DukeException("It seems that there is no corresponding task.");
                    }
                    delete(tasks, index);
                } else {
                    // add user input to the list
                    // check what type of task it is

                    if (userInput.startsWith("todo")) {
                        // the task is a todo
                        if (userInput.split(" ", 2).length == 1) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        String description = userInput.split(" ", 2)[1].split("/")[0].trim();

                        ToDo todo = new ToDo(description);
                        tasks.add(todo);
                        writeFile(tasks);

                        say("Got it. I've added this task:", true, false);
                        say("  " + todo, false, false);
                        say("Now you have " + tasks.size() + " tasks in the list.", false, true);
                    } else if (userInput.startsWith("deadline")) {
                        // the task is a deadline
                        if (userInput.split(" ", 2).length == 1) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        }
                        String description = userInput.split(" ", 2)[1].split("/")[0].trim();
                        String by = userInput.split(" ", 2)[1].split("/")[1].split(" ", 2)[1];

                        Deadline deadline = new Deadline(description, by);
                        tasks.add(deadline);
                        writeFile(tasks);

                        say("Got it. I've added this task:", true, false);
                        say("  " + deadline, false, false);
                        say("Now you have " + tasks.size() + " tasks in the list.", false, true);
                    } else if (userInput.startsWith("event")) {
                        // the task is an event
                        if (userInput.split(" ", 2).length == 1) {
                            throw new DukeException("The description of an event cannot be empty.");
                        }
                        String description = userInput.split(" ", 2)[1].split("/")[0].trim();
                        String at = userInput.split(" ", 2)[1].split("/")[1].split(" ", 2)[1];

                        Event event = new Event(description, at);
                        tasks.add(event);
                        writeFile(tasks);

                        say("Got it. I've added this task:", true, false);
                        say("  " + event, false, false);
                        say("Now you have " + tasks.size() + " tasks in the list.", false, true);
                    } else {
                        throw new DukeException("I'm sorry, but I don't quite understand what that means.");
                    }
                }
            } catch (DukeException exception) {
                say(exception.getMessage(), true, true);
            }
        }

    }

    public static void say(String message, boolean isFirstLine, boolean isLastLine) {
        String line = "____________________________________________________________";
        if (isFirstLine) {
            System.out.println(line);
        }
        System.out.println(" " + message);
        if (isLastLine) {
            System.out.println(line);
        }
    }

    public static void exit() {
        say("Bye. Hope to see you again soon.", true, true);
    }

    public static void list(ArrayList<Task> tasks) {
        say("Here are the tasks in your list:", true, false);
        for (int i = 0; i < tasks.size(); i++) {
            boolean isFirstLine = false;
            boolean isLastLine = i == tasks.size() - 1;
            say(i + 1 + ". " + tasks.get(i).toString(), isFirstLine, isLastLine);
        }
    }

    public static void mark(ArrayList<Task> tasks, int index) {
        tasks.get(index).setStatus(true);
        writeFile(tasks);
        say("Nice! I've marked this task as done:", true, false);
        say(tasks.get(index).toString(), false, true);
    }

    public static void unmark(ArrayList<Task> tasks, int index) {
        tasks.get(index).setStatus(false);
        writeFile(tasks);
        say("OK, I've marked this task as not done yet:", true, false);
        say(tasks.get(index).toString(), false, true);
    }

    public static void delete(ArrayList<Task> tasks, int index) {
        say("Noted. I've removed this task:", true, false);
        say(tasks.get(index).toString(), false, false);
        tasks.remove(index);
        writeFile(tasks);
        say("Now you have " + tasks.size() + " tasks in the list.", false, true);
    }

    public static void initialiseFile() {
        final String PATH = "../ip/";
        final String directoryName = PATH.concat("data");
        final String fileName = "duke.txt";

        File directory = new File(directoryName);
        if (!directory.exists()) {
            System.out.println("directory created");
            directory.mkdir();
        }

        File file = new File(directoryName + "/" + fileName);
        try {
            if (!file.exists()) {
                System.out.println("file created");
                file.createNewFile();
            }
        } catch (IOException exception) {
            say(exception.getMessage(), true, true);
        }
    }

    public static String readFile() {
//        System.out.println("read file");
        initialiseFile();
        File file = new File("data/duke.txt");
        String output = "";

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                output += scanner.nextLine() + System.lineSeparator();
            }
        } catch (IOException exception) {
            say(exception.getMessage(), true, true);
        }

        return output;
    }

    public  static void writeFile(ArrayList<Task> tasks) {
        initialiseFile();
        File file = new File("data/duke.txt");

        try {
            FileWriter writer = new FileWriter(file);
            for (Task task: tasks) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException exception) {
            say(exception.getMessage(), true, true);
        }
    }
}

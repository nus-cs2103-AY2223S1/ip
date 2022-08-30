import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.*;
import java.io.*;
import java.lang.StringBuilder;

public class Duke {
    private final ArrayList<Task> tasks;
    private int numOfTasks;
    private static final String line = "---------------------------------------------------";

    public Duke() {
        this.tasks = readTasksFromHardDrive();
        this.numOfTasks = this.tasks.size();
    }

    private String taskTense() {
        return numOfTasks == 1 ? " task" : " tasks";
    }

    private void greet() {
        System.out.println(line);
        System.out.println("Hi there! I'm Duke\n" +
                "     What's up?");
        System.out.println(line);
    }

    private void echo(String input) throws DukeException {
        throw new DukeException("What's " + input + " ??" + "\nPlease say something I can understand!");
    }

    private void listTasks() {
        if (numOfTasks == 0) {
            System.out.println("Nothing to do right now...");
        } else {
            System.out.println("Tasks: ");
            System.out.println(convertTasksToListString());
        }
        System.out.println("You have " + numOfTasks + taskTense() + "!");
    }

    private void changeTaskStatus(int index, boolean bool) throws DukeException {
        try {
            if (bool) {
                tasks.get(index).markAsDone();
                System.out.println("Good job! I've marked this task as done:");
                System.out.println(tasks.get(index));
            } else {
                tasks.get(index).markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks.get(index));
            }
            saveToHardDisk();
        } catch (Exception e) {
            throw new DukeException("No such task!");
        }
    }

    private void addTask(String input) throws DukeException {
        String description = input.substring(input.indexOf(" ") + 1);
        if (input.startsWith("todo")) {
            String[] command = input.split("todo", 2);
            if (command.length < 2 || command[1].trim().length() == 0) {
                throw new DukeException("Hold up! Description cannot be empty!");
            }
            ToDo newTodo = new ToDo(description);
            tasks.add(numOfTasks, newTodo);
            numOfTasks++;
        } else if (input.startsWith("deadline")) {
            String[] command = input.split("deadline", 2);
            String[] date = command[1].split("/by", 2);
            if (command[1].trim().length() == 0 || date[0].trim().length() == 0) {
                throw new DukeException("Hold up! Description cannot be empty!");
            }
            if (date[1].trim().length() == 0) {
                throw new DukeException("Wait! When do you want to do this by??");
            }
            Deadline newDeadline = new Deadline(description.substring(0, description.indexOf("/by") - 1),
                    description.substring(description.indexOf("/") + 4));
            tasks.add(numOfTasks, newDeadline);
            numOfTasks++;
        } else if (input.startsWith("event")) {
            String[] command = input.split("event", 2);
            String[] date = command[1].split("/at", 2);
            if (command[1].trim().length() == 0 || date[0].trim().length() == 0) {
                throw new DukeException("Hold up! Description cannot be empty!");
            }
            if (date[1].trim().length() == 0) {
                throw new DukeException("Wait! When is this event??");
            }
            Event newEvent = new Event(description.substring(0, description.indexOf("/at") - 1),
                    description.substring(description.indexOf("/") + 4));
            tasks.add(numOfTasks, newEvent);
            numOfTasks++;
        } else {
            echo(input);
        }
        saveToHardDisk();
        System.out.println("Got it. I've added this task:\n" + "  " + tasks.get(numOfTasks - 1).toString());
        System.out.println("Now you have " + numOfTasks + taskTense() + " in the list.");
    }

    private void deleteTask(int index) throws DukeException {
        try {
            Task removedTask = tasks.remove(index);
            System.out.println("Done! " + removedTask.toString() + " has been deleted :(");
            numOfTasks--;
            saveToHardDisk();
        } catch (Exception e) {
            throw new DukeException("I can't find such a task to delete!");
        }
    }

    private void saveToHardDisk() throws DukeException {
        byte[] data = convertTasksToListString().getBytes();
        Path p = Paths.get("./data/duke.txt");
        Path dataPath = Paths.get("./data");

        boolean isDataFileExisting = Files.exists(dataPath);

        if (!isDataFileExisting) {
            File f1 = new File(String.valueOf(dataPath));
            isDataFileExisting = f1.mkdir();
        }
        if (isDataFileExisting) {
            try (OutputStream out = new BufferedOutputStream(
                    Files.newOutputStream(p))) {
                out.write(data, 0, data.length);
            } catch (IOException x) {
                throw new DukeException("There was an error saving your changes!");
            }
        }
    }

    private String convertTasksToListString() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < numOfTasks; i++) {
            String newLine = (i + 1) + ". " + tasks.get(i).toString() + "\n";
            list.append(newLine);
        }
        return list.toString();
    }

    private ArrayList<Task> readTasksFromHardDrive() {
        Path p = Paths.get("./data/duke.txt");
        boolean isFileExisting = Files.exists(p);
        ArrayList<Task> tasks = new ArrayList<>();

        if (!isFileExisting) {
            return tasks;
        }
        try (InputStream in = Files.newInputStream(p);
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task newTask;
                // process data
                switch (line.charAt(4)) {
                    case 'T':
                        newTask = new ToDo(line.substring(10));
                        break;
                    case 'E':
                        newTask = new Event(line.substring(10, line.indexOf("(at:") - 1),
                                line.substring(line.indexOf("(at:") + 5, line.lastIndexOf(")")));
                        break;
                    case 'D':
                        newTask = new Deadline(line.substring(10, line.indexOf("(by:") - 1),
                                line.substring(line.indexOf("(by:") + 5, line.lastIndexOf(")")));
                        break;
                    default:
                        newTask = null;
                        break;
                }
                tasks.add(newTask);
            }
        } catch (IOException x) {
            System.out.println("There was an error reading your existing tasks!");
            return tasks;
        } catch (NullPointerException e) {
            System.out.println("Your existing tasks are not formatted correctly!");
            return tasks;
        }
        return tasks;
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        duke.greet();
        boolean isRunning = true;

        while(isRunning) {
            try {
                String input = sc.nextLine();
                System.out.println(line);
                if (input.equals("bye")) {
                    isRunning = false;
                    sc.close();
                    duke.exit();
                } else if (input.equals("list")) {
                    duke.listTasks();
                } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                    try {
                        int index = Integer.parseInt(input.substring(input.lastIndexOf(" ") + 1)) - 1;
                        duke.changeTaskStatus(index, input.startsWith("mark"));
                    } catch (DukeException e) {
                        throw e;
                    } catch (Exception e) {
                        throw new DukeException("Please input a number!");
                    }
                } else if (input.startsWith("delete")) {
                    try {
                        int index = Integer.parseInt(input.substring(input.lastIndexOf(" ") + 1)) - 1;
                        duke.deleteTask(index);
                    } catch (DukeException e) {
                        throw e;
                    } catch (Exception e) {
                        throw new DukeException("Please input a number!");
                    }
                } else {
                    duke.addTask(input);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(line);
        }
    }
}

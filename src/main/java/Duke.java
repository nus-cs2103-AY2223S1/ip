import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    private final ArrayList<Task> tasks = new ArrayList<>();
    private int totalTasks = 0;
    private final Scanner input = new Scanner(System.in);
    public static String DIRECTORY = "./data/";
    public static String FILENAME = "todo.txt";


    private String receiveCommand() {
        return input.nextLine();
    }

    private void list() {
        for (int j = 0; j < totalTasks; j++) {
            System.out.println((j + 1) + ": " + tasks.get(j));
        }
    }

    private void markAsDone(int taskNumber) {
        //mark task number as done
        if (taskNumber < totalTasks && taskNumber >= 0) {
            tasks.get(taskNumber).markAsDone();
            System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(taskNumber));
        }
    }

    private void markAsUndone(int taskNumber) {
        //mark task number as undone
        if (taskNumber < totalTasks && taskNumber >= 0) {
            tasks.get(taskNumber).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.get(taskNumber));
        }
    }

    private String toPrintOnAdd(int taskNum) {
        return "Got it. I've added this task:\n  " + tasks.get(taskNum) + "\nNow you have " + totalTasks + " tasks in the list.";
    }

    private void addTask(Task task) {
        //add todo
        tasks.add(task);
        totalTasks += 1;
        System.out.println(toPrintOnAdd(totalTasks - 1));
    }

    private String toPrintOnDelete(int taskNum) {
        return "Noted. I've deleted this task:\n  " + tasks.get(taskNum) + "\nNow you have " + totalTasks + " tasks in the list.";
    }

    private void deleteTask(int taskNumber) {
        if (taskNumber >= 0 && taskNumber < totalTasks) {
            totalTasks -= 1;
            System.out.println(toPrintOnDelete(taskNumber));
            tasks.remove(taskNumber);
        }
    }

    private void parseCommand(String command) throws DukeException {
        if (command.equals("bye")) {
            //exit program
            System.out.println("Bye. Hope to see you again soon!");
        } else if (command.equals("list")) {
            list();
        } else if (command.startsWith("mark")) {
            try {
                int taskNumber = Integer.parseInt(command.split("\\s+")[1]) - 1;
                markAsDone(taskNumber);
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Please provide a number for this command");
            }
        } else if (command.startsWith("unmark")) {
            try {
                int taskNumber = Integer.parseInt(command.split("\\s+")[1]) - 1;
                markAsUndone(taskNumber);
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Please provide a number for this command");
            }
        } else if (command.startsWith("todo")) {
            try {
                String description = command.split(" ", 2)[1];
                addTask(new Todo(description));
            } catch (Exception e) {
                e.printStackTrace();
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (command.startsWith("deadline")) {
            try {
                String full = command.split(" ", 2)[1];
                String description = full.split(" /by ")[0];
                String deadline = full.split(" /by ")[1];
                addTask(new Deadline(description, deadline));
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Please format deadline request correctly.");
            }
        } else if (command.startsWith("event")) {
            try {
                String full = command.split(" ", 2)[1];
                String description = full.split(" /at ")[0];
                String at = full.split(" /at ")[1];
                addTask(new Event(description, at));
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Please format event request correctly.");
            }
        } else if (command.startsWith("delete")) {
            try {
                int taskNumber = Integer.parseInt(command.split("\\s+")[1]) - 1;
                deleteTask(taskNumber);
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Please provide a number for this command");
            }
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        saveFile(DIRECTORY, FILENAME);
    }

    private void greet() {
        String logo = """
                 ____        _       \s
                |  _ \\ _   _| | _____\s
                | | | | | | | |/ / _ \\
                | |_| | |_| |   <  __/
                |____/ \\__,_|_|\\_\\___|
                """;
        System.out.println("Hello from\n" + logo);
    }

    private void run() {
        String command;

        do {
            System.out.print("> ");
            command = receiveCommand();
            System.out.println();
            try {
                parseCommand(command);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            System.out.println();
        } while (!command.equals("bye"));
    }

    private void parseLine(String line) {
        String[] parts = line.split(" \\| ");
        Task task = null;

        switch (parts[0]) {
            case "T":
                task = new Todo(parts[2]);
                break;
            case "D":
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                task = new Event(parts[2], parts[3]);
                break;
        }

        switch (parts[1]) {
            case "0":
                task.markAsUndone();
                break;
            case "1":
                task.markAsDone();
                break;
        }
        tasks.add(task);
        totalTasks += 1;
    }

    private void loadFile(String directory, String filename) {
        File data = createFileWithDirIfNotExist(directory, filename);
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(data));
            String line = reader.readLine();
            while (line != null) {
                parseLine(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> tasksToString() {
        ArrayList<String> tasksAsStrings = new ArrayList<>();
        String currString;

        for (Task currTask : tasks) {
            currString = "";
            if (currTask instanceof Todo) {
                currString += "T | ";
                currString += currTask.isDone ? "1 | " : "0 | ";
                currString += currTask.description;
            } else if (currTask instanceof Deadline) {
                currString += "D | ";
                currString += currTask.isDone ? "1 | " : "0 | ";
                currString += currTask.description + " | ";
                currString += ((Deadline) currTask).by;
            } else if (currTask instanceof Event) {
                currString += "E | ";
                currString += currTask.isDone ? "1 | " : "0 | ";
                currString += currTask.description + " | ";
                currString += ((Event) currTask).at;
            }
            tasksAsStrings.add(currString);
        }

        return tasksAsStrings;
    }

    private void saveFile(String directory, String filename) {
        try {
            File data = createFileWithDirIfNotExist(directory, filename);
            data.delete();
            data = createFileWithDirIfNotExist(directory, filename);

            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(data));
            ArrayList<String> toWrite = tasksToString();

            for (String str : toWrite) {
                writer.write(str + System.lineSeparator());
            }

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    private File createFileWithDirIfNotExist(String directory, String filename) {
        File f = new File(directory + filename);
        try {
            f.getParentFile().mkdirs();
            f.createNewFile();
        } catch (Exception e) {
            System.out.println("Failed to create file, will not be able to save data.");
            return null;
        }
        return f;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.loadFile(DIRECTORY, FILENAME);
        duke.greet();
        duke.run();
    }
}
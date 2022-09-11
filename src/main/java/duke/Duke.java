package duke;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    List<Task> tasks = new ArrayList<>();
    static final String FILE_PATH = "data/duke.txt";

    Duke() {
        readDataFile();
    }

    private void readDataFile() {
        // reset tasks
        tasks = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.printf("File %s not found\n", FILE_PATH);
                return;
            }

            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String data = s.nextLine();
                String[] taskInfo = data.split(" \\| ");

                Task task;
                switch (taskInfo[0]) {
                    case "T":
                        task = new Todo(taskInfo[2]);
                        break;
                    case "E":
                        LocalDate date = LocalDate.parse(taskInfo[3]);
                        task = new Event(taskInfo[2], date);
                        break;
                    case "D":
                        date = LocalDate.parse(taskInfo[3]);
                        task = new Deadline(taskInfo[2], date);
                        break;
                    default:
                        throw new DukeException("Corrupt file");
                }
                if (taskInfo[1] == "1") {
                    task.mark();
                }
                tasks.add(task);
            }
            s.close();

            System.out.printf("Loaded tasks from %s\n\n", FILE_PATH);
        } catch (Exception e) {
            System.out.println("Error: Could not read from file");
            System.out.println(e);
        }
    }

    private void syncTasksToFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(FILE_PATH);
            ArrayList<String> taskStrings = new ArrayList<>();
            for (Task task : tasks) {
                String taskString = task.toFileString();
                taskStrings.add(taskString);
            }

            String fileString = String.join("\n", taskStrings);
            writer.write(fileString);
            writer.close();
        } catch (Exception e) {
            System.out.println("Error: could not write to file");
            System.out.println(e);
        }
    }

    private String markTask(String input, boolean done) throws DukeException {
        String[] splitInput = input.split(" ");
        if (splitInput.length < 2) {
            throw new DukeException("Please specify the number of the task");
        }
        try {
            int taskIdx = Integer.parseInt(splitInput[1]);
            Task task = tasks.get(taskIdx - 1);
            if (done) {
                task.mark();
            } else {
                task.unmark();
            }
            String successMessage = done
                    ? "Nice! I've marked this task as done:\n  %s\n"
                    : "OK, I've marked this task as not done yet:\n  %s\n";
            syncTasksToFile();
            return String.format(successMessage, task.toString());
        } catch (Exception e) {
            throw new DukeException("That is not a valid task number!");
        }
    }

    private String deleteTask(String input) throws DukeException {
        String[] splitInput = input.split("delete ");
        if (splitInput.length < 2) {
            throw new DukeException("Please specify the number of the task to delete");
        }
        assert splitInput.length >= 2;
        try {
            int taskIdx = Integer.parseInt(splitInput[1]);
            Task task = tasks.remove(taskIdx - 1);
            syncTasksToFile();
            return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.\n", task.toString(), tasks.size());
        } catch (Exception e) {
            throw new DukeException("That is not a valid task number!");
        }
    }

    private String printTasks() {
        if (tasks.size() == 0) {
            return "You currently have no tasks. ";
        } else {
            ArrayList<String> result = new ArrayList<>();
            result.add("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                result.add(String.format("%d. %s", i + 1, tasks.get(i).toString()));
            }
            return String.join("\n", result);
        }
    }

    public String getResponse(String input) {
        try {
            if (input.equals("list")) {
                return printTasks();
            } else if (input.startsWith("mark ")) {
                return markTask(input, true);
            } else if (input.startsWith("unmark ")) {
                return markTask(input, false);
            } else if (input.startsWith("delete ")) {
                return deleteTask(input);
            } else if (input.startsWith(TaskType.TODO.string)) {
                return addTask(input, TaskType.TODO);
            } else if (input.startsWith("deadline ")) {
                return addTask(input, TaskType.DEADLINE);
            } else if (input.startsWith("event ")) {
                return addTask(input, TaskType.EVENT);
            } else if (input.startsWith("find ")) {
                return searchTask(input);
            } else {
                throw new DukeException("what");
            }
        } catch (Exception e) {
            return (e.getMessage());
        }
    }

    enum TaskType {
        TODO("t "),
        DEADLINE("deadline "),
        EVENT("event ");

        public final String string;

        TaskType(String string) {
            this.string = string;
        }
    }

    private String addTask(String input, TaskType type) throws DukeException {
        String[] splitInput = input.split(type.string);
        String errorMessage = type == TaskType.TODO
                ? "Please add a description for the %s"
                : "Please add a description and date for the %s";
        if (splitInput.length < 2) {
            throw new DukeException(String.format(errorMessage, type.string));
        }
        Task task;
        if (type == TaskType.TODO) {
            String desc = splitInput[1];
            task = new Todo(desc);
        } else {
            splitInput = splitInput[1].split(type == TaskType.DEADLINE ? " /by " : " /at ");
            if (splitInput.length < 2) {
                throw new DukeException(String.format(errorMessage, type.string));
            }
            LocalDate date = LocalDate.parse(splitInput[1]);
            task = type == TaskType.DEADLINE
                    ? new Deadline(splitInput[0], date)
                    : new Event(splitInput[0], date);
        }
        tasks.add(task);
        syncTasksToFile();
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.\n", task.toString(), tasks.size());
    }

    private String searchTask(String input) {
        String searchString = input.split("find ")[1];
        ArrayList<String> result = new ArrayList<>();
        result.add("Here are the matching tasks in your list:");
        for (Task task : tasks) {
            if (task.description.contains(searchString)) {
                result.add(task.toString());
            }
        }
        return String.join("\n", result);
    }
}

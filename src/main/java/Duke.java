import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static File file = new File("duke.txt");

    private static String getOutput(String input) throws DukeException {
        if (input.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        } else if (input.equals("list")) {
            return "Here are the tasks in your list:" + listTasks();
        } else if (input.length() > 3 && input.substring(0, 4).equals("mark")) {
            int index = Integer.parseInt(input.substring(5)) - 1;
            Task currTask = taskList.get(index);
            currTask.markAsDone();
            updateFile(listTasks());
            return "Nice! I've marked this task as done\n  " + currTask.toString();
        } else if (input.length() > 5 && input.substring(0, 6).equals("unmark")) {
            int index = Integer.parseInt(input.substring(7)) - 1;
            Task currTask = taskList.get(index);
            currTask.markAsNotDone();
            updateFile(listTasks());
            return "OK, I've marked this task as not done yet:\n  " + currTask.toString();
        } else if (input.length() > 3 && input.substring(0, 4).equals("todo")) {
            if (input.length() < 6) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            Task newTask = new Todo(input.substring(5));
            addTask(newTask);
            updateFile(listTasks());
            return "Got it. I've added this task:\n  " + newTask.toString() +
                    "\nNow you have " + taskList.size() + " tasks in the list.";
        } else if (input.length() > 7 && input.substring(0, 8).equals("deadline")) {
            String taskInput = input.substring(9);
            String[] taskAndDate = taskInput.split("/by ");
            Task newTask = new Deadline(taskAndDate[0], handleDate(taskAndDate[1]));
            addTask(newTask);
            updateFile(listTasks());
            return "Got it. I've added this task:\n  " + newTask.toString() +
                    "\nNow you have " + taskList.size() + " tasks in the list.";
        } else if (input.length() > 4 && input.substring(0, 5).equals("event")) {
            String taskInput = input.substring(6);
            String[] taskAndDate = taskInput.split("/at ");
            Task newTask = new Event(taskAndDate[0], handleDate(taskAndDate[1]));
            addTask(newTask);
            updateFile(listTasks());
            return "Got it. I've added this task:\n  " + newTask.toString() +
                    "\nNow you have " + taskList.size() + " tasks in the list.";
        } else if (input.length() > 5 && input.substring(0, 6).equals("delete")) {
            int index = Integer.parseInt(input.substring(7)) - 1;
            Task currTask = taskList.get(index);
            taskList.remove(index);
            updateFile(listTasks());
            return "Noted. I've removed this task:\n  " + currTask.toString() +
                    "\nNow you have " + taskList.size() + " tasks in the list.";
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void addTask(Task task) {
        taskList.add(task);
    }

    private static String listTasks() {
        String tasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            tasks += "\n" + (i + 1) + ". " + taskList.get(i).toString();
        }
        return tasks;
    }

    private static void updateFile(String list) {
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.write(list);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Unable to write to file");
        }
    }

    private static LocalDateTime handleDate(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    public static void main(String[] args) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Unable to create file");
            }
        }

        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo + "\nHello! I'm Duke\n" + "What can I do for you?");
        String command = scanner.nextLine();
        while (true) {
            try {
                String output = getOutput(command);
                System.out.println(output);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
            if (command.equals("bye")) {
                break;
            } else {
                command = scanner.nextLine();
            }
        }
    }
}

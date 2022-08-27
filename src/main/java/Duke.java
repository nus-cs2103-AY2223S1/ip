import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileWriter;

public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        loadTasks();
        int state = 0;
        while (state >= 0) {
            String m = sc.nextLine();
            try {
                state = handleMessage(m);
            } catch (DukeException e) {
                printMessage(e.getMessage());
            }
        }
//        System.out.println(System.getProperty("user.dir"));
    }

    private static int handleMessage(String m) throws DukeException {
        if (m.equals("bye")) {
            // bye
            saveTasks();
            printMessage("Bye. Hope to see you again soon!");
            return -1;
        } else if (m.equals("list")) {
            // list
            String messageList = generateList();
            printMessage("Here are the tasks in your list:\n    " + messageList);
            return 1;
        } else if (m.startsWith("mark")) {
            // mark task
            if (m.length() < 6) {
                throw new DukeException("Please input a number.");
            }
            int id = Integer.parseInt(m.substring(5));
            if (id > taskList.size() || id < 0) {
                throw new DukeException("Please input a valid number. " + id + " is invalid.");
            }
            Task t = Duke.taskList.get(id - 1);
            t.done();
            printMessage("Nice! I've marked this task as done:\n    " + t);
            return 2;
        } else if (m.startsWith("unmark")) {
            // unmark task
            if (m.length() < 8) {
                throw new DukeException("Please input a number.");
            }
            int id = Integer.parseInt(m.substring(7));
            if (id > taskList.size() || id < 0) {
                throw new DukeException("Please input a valid number. " + id + " is invalid.");
            }
            Task t = Duke.taskList.get(id - 1);
            t.undone();
            printMessage("OK, I've marked this task as not done yet:\n    " + t);
            return 3;
        } else if (m.startsWith("deadline")) {
            // deadline
            if (m.length() <= 9) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            m = m.substring(9);
            if (!m.contains(" /by ")) {
                throw new DukeException("☹ OOPS!!! /by separator for deadline is missing.");
            }
            String[] split = m.split(" /by ");
            if (split.length < 2) {
                throw new DukeException("☹ OOPS!!! Target time for deadline is missing.");
            } else if (split.length > 2) {
                throw new DukeException("☹ OOPS!!! Multiple usage of /by separator is not allowed.");
            }
            try {
                Task t = new Deadline(split[0], split[1]);
                addTask(t);
            } catch (DateTimeParseException e) {
                throw new DukeException("Cannot parse date.");
            }
            return 4;
        } else if (m.startsWith("event")) {
            // event
            if (m.length() <= 6) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            if (!m.contains(" /at ")) {
                throw new DukeException("☹ OOPS!!! /at separator for event is missing.");
            }
            m = m.substring(6);
            String[] split = m.split(" /at ");
            if (split.length < 2) {
                throw new DukeException("☹ OOPS!!! Duration for event is missing.");
            } else if (split.length > 2) {
                throw new DukeException("☹ OOPS!!! Multiple usage of /at separator is not allowed.");
            }
            try {
                Task t = new Event(split[0], split[1]);
                addTask(t);
            } catch (DateTimeParseException e) {
                throw new DukeException("Cannot parse date.");
            }
            return 5;
        } else if (m.startsWith("todo")) {
            // to do
            if (m.length() <= 5) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            m = m.substring(5);
            Task t = new Todo(m);
            addTask(t);
            return 6;
        } else if (m.startsWith("delete")) {
            // delete task
            if (m.length() < 8) {
                throw new DukeException("Please input a number.");
            }
            int id = Integer.parseInt(m.substring(7));
            if (id > taskList.size() || id < 0) {
                throw new DukeException("Please input a valid number. " + id + " is invalid.");
            }
            deleteTask(id - 1);
            return 7;
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void printMessage(String m) {
        String message = "    ____________________________________________________________\n    "
                + m
                + "\n    ____________________________________________________________\n";
        System.out.println(message);
    }

    private static String generateList() {
        if (Duke.taskList.size() == 0) {
            return "";
        }
        String out = "";
        int count = 1;
        for (Task t: Duke.taskList) {
            out += "\n    " + count++ + ". " + t;
        }
        return out.substring(5);
    }

    private static void addTask(Task t) {
        Duke.taskList.add(t);
        printMessage("Got it. I've added this task:\n      " +
                t +
                "\n    Now you have " +
                Duke.taskList.size() +
                " tasks in the list.");
    }

    private static void deleteTask(int id) {
        Task t = Duke.taskList.remove(id);
        printMessage("Noted. I've removed this task:\n      " +
                t +
                "\n    Now you have " +
                Duke.taskList.size() +
                " tasks in the list.");
    }

    private static void loadTasks() {
        File file = new File("data/duke.txt");
        if (!file.exists()) {
            return;
        }
        try {
            String tasks = Files.readString(Path.of("data/duke.txt"));
            for (String s: tasks.split("\n")) {
                Task task = new Todo("Error");
                if (s.startsWith("[T]")) {
                    String description = s.substring(7);
                    task = new Todo(description);
                } else if (s.startsWith("[E]")) {
                    String[] split = s.substring(7).split(" \\(at: ");
                    String description = split[0];
                    String at = split[1];
                    at = at.substring(0, at.length() - 1); // remove closing )
                    task = new Event(description, at);
                } else if (s.startsWith("[D]")) {
                    String[] split = s.substring(7).split(" \\(by: ");
                    String description = split[0];
                    String by = split[1];
                    by = by.substring(0, by.length() - 1); // remove closing )
                    task = new Deadline(description, by);
                } else {
                    continue;
                }
                if (s.charAt(5) == 'X') {
                    task.done();
                }
                Duke.taskList.add(task);
            }
        } catch (java.io.IOException e) {
            System.out.println("Unable to read task list. Error: " + e);
        }
    }

    private static void saveTasks() {
        try {
            File file = new File("data");
            if (!file.exists()) {
                file.mkdir();
            }
            FileWriter output = new FileWriter("data/duke.txt");
            for (Task t : Duke.taskList) {
                output.write(t.toString());
                output.write("\n");
            }
            output.close();
        } catch (java.io.IOException e) {
            System.out.println("Unable to save file. Error: " + e);
        }
    }
}

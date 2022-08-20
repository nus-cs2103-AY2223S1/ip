import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Scanner;

public class Jduke {
    private static final String GREETING = "|  Welcome to JDuke -- Version 1.0\n"
            + "|  What can I do for you?";
    private static final String PROMPT = "jduke> ";
    private static final String GOODBYE = "|  Goodbye";
    private static final String TODO_FORMAT = "todo <description>";
    private static final String EVENT_FORMAT = "event <description> /at <dd/mm/yyyy> <hhmm | optional>";
    private static final String DEADLINE_FORMAT = "deadline <description> /by <dd/mm/yyyy> <hhmm | optional>";
    private static final String MARK_FORMAT = "mark <integer>";
    private static final String UNMARK_FORMAT = "unmark <integer>";
    private static final String DELETE_FORMAT = "delete <integer>";

    private static final String LIST_FORMAT = "list <dd/mm/yyyy | optional>";
    public enum Command {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    private static ArrayList<Task> tasks = new ArrayList<>();
    public static Command handleCommand(String input) throws JdukeException {
        Command mainCmd;
        try {
            mainCmd = Command.valueOf(input.split(" ", 2)[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new JdukeException("cannot understand command");
        }
        return mainCmd;
    }

    public static void parseStoredTasks() throws JdukeException {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                boolean wasDirectoryCreated = directory.mkdirs();
                if (!wasDirectoryCreated) {
                    throw new JdukeException("cannot create storage directory");
                }
            }
            File file = new File("data/jduke.txt");
            if (!file.exists()) {
                boolean isFileCreated = file.createNewFile();
                if (!isFileCreated) {
                    throw new JdukeException("cannot create storage file");
                }
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String task = sc.nextLine();
                String[] taskParams = task.split(" \\| ");
                switch (taskParams[0]) {
                case "T":
                    tasks.add(new ToDo(taskParams[2]));
                    break;
                case "D":
                    tasks.add(new Deadline(taskParams[2], taskParams[3]));
                    break;
                case "E":
                    tasks.add(new Event(taskParams[2], taskParams[3]));
                    break;
                }
                if (taskParams[1].equals("1")) {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new JdukeException("cannot find file");
        } catch (IOException e) {
            throw new JdukeException("cannot create file");
        }
    }

    public static void writeToStorage(String task) throws JdukeException {
        try {
            FileWriter fw = new FileWriter("data/jduke.txt", true);
            fw.write(String.format("%s%n", task));
            fw.close();
        } catch (IOException e) {
            throw new JdukeException("unable to write to storage");
        }
    }

    public static void updateStorage(int pos, Command type) throws JdukeException {
        try {
            File file = new File("data/jduke.txt");
            Scanner sc = new Scanner(file);
            ArrayList<String> temp = new ArrayList<>();
            while (sc.hasNext()) {
                String task = sc.nextLine();
                String[] taskParams = task.split(" \\| ", 3);
                if (temp.size() == pos) {
                    switch (type) {
                    case MARK:
                        temp.add(String.format(
                                "%s | 1 | %s%n", taskParams[0], taskParams[2]
                        ));
                        break;
                    case UNMARK:
                        temp.add(String.format(
                                "%s | 0 | %s%n", taskParams[0], taskParams[2]
                        ));
                        break;
                    case DELETE:
                        break;
                    }
                } else {
                    temp.add(task + "\n");
                }
            }
            FileWriter fw = new FileWriter("data/jduke.txt", false);
            StringBuilder sb = new StringBuilder();
            for (String task : temp) {
                sb.append(task);
            }
            fw.write(sb.toString());
            fw.close();
        } catch (FileNotFoundException e) {
            throw new JdukeException("cannot find file");
        } catch (IOException e) {
            throw new JdukeException("unable to write to storage");
        }
    }

    public static void printLastTask() {
        System.out.printf(
                "|  added task:%n|    %s%n|  %d%s in list%n",
                tasks.get(tasks.size() - 1),
                tasks.size(),
                (tasks.size() == 1 ? " task" : " tasks")
        );
    }
    public static void addTodo(String input) throws JdukeException {
        if (!input.toLowerCase().matches("todo [^ ](.*)")) {
            throw new JdukeException("invalid TODO format", TODO_FORMAT);
        }
        writeToStorage(String.format("T | 0 | %s", input.split(" ", 2)[1]));
        tasks.add(new ToDo(input.split(" ", 2)[1]));
        printLastTask();
    }

    public static void addDeadline(String input) throws JdukeException {
        if (!(input.toLowerCase().matches(
                "deadline [^ ](.*) /by [0-9]{1,2}/[0-9]{1,2}/[0-9]{4} [0-9]{4}")
                || input.toLowerCase().matches("deadline [^ ](.*) /by [0-9]{1,2}/[0-9]{1,2}/[0-9]{4}"))) {
            throw new JdukeException("invalid DEADLINE format", DEADLINE_FORMAT);
        }
        String details = input.split(" ", 2)[1];
        String[] deadlineParams = details.split(" /by ", 2);
        writeToStorage(String.format("D | 0 | %s | %s", deadlineParams[0], deadlineParams[1]));
        tasks.add(new Deadline(deadlineParams[0], deadlineParams[1]));
        printLastTask();
    }
    public static void addEvent(String input) throws JdukeException {
        if (!(input.toLowerCase().matches(
                "event [^ ](.*) /at [0-9]{1,2}/[0-9]{1,2}/[0-9]{4} [0-9]{4}")
                || input.toLowerCase().matches("event [^ ](.*) /at [0-9]{1,2}/[0-9]{1,2}/[0-9]{4}"))) {
            throw new JdukeException("invalid EVENT format", EVENT_FORMAT);
        }
        String details = input.split(" ", 2)[1];
        String[] eventParams = details.split(" /at ", 2);
        writeToStorage(String.format("E | 0 | %s | %s", eventParams[0], eventParams[1]));
        tasks.add(new Event(eventParams[0], eventParams[1]));
        printLastTask();
    }
    public static void listTasks(String input) throws JdukeException {
        if (!(input.matches("list [0-9]{1,2}/[0-9]{1,2}/[0-9]{4}")
                || input.matches("list"))) {
            throw new JdukeException("invalid LIST format", LIST_FORMAT);
        }
        if (tasks.size() == 0) {
            System.out.println("|  no tasks found");
        } else if (input.split(" ").length == 1) {
            for (int pos = 0; pos < tasks.size(); pos++) {
                System.out.printf("%d ==> %s%n", pos + 1, tasks.get(pos));
            }
        } else {
            LocalDate date = LocalDate.parse(input.split(" ")[1],
                    DateTimeFormatter.ofPattern("d/M/yyyy"));
            boolean hasTask = false;
            for (int pos = 0; pos < tasks.size(); pos++) {
                if (tasks.get(pos).isEqualDate(date)) {
                    hasTask = true;
                    System.out.printf("%d ==> %s%n", pos + 1, tasks.get(pos));
                }
            }
            if (!hasTask) {
                System.out.println("|  no tasks found");
            }
        }
    }
    public static int handleTaskPos(String input) throws JdukeException {
        String[] inputArr = input.split(" ");
        if (!input.matches("(.*) ([0-9]+)") || inputArr.length > 2) {
            switch (inputArr[0].toLowerCase()) {
            case "mark":
                throw new JdukeException("invalid MARK format", MARK_FORMAT);
            case "unmark":
                throw new JdukeException("invalid UNMARK format", UNMARK_FORMAT);
            case "delete":
                throw new JdukeException("invalid DELETE format", DELETE_FORMAT);
            }
        }
        int pos = Integer.parseInt(input.split(" ", 2)[1]) - 1;
        if (pos < 0 || tasks.size() <= pos) {
            throw new JdukeException(
                    "invalid task number",
                    String.format("max index is %d", tasks.size())
            );
        }
        return pos;
    }
    public static void unmarkTask(String input) throws JdukeException {
        int pos = handleTaskPos(input);
        tasks.get(pos).markAsUndone();
        updateStorage(pos, Command.UNMARK);
        System.out.printf("|  unmarked task:%n|    %s%n", tasks.get(pos));
    }
    public static void markTask(String input) throws JdukeException {
        int pos = handleTaskPos(input);
        tasks.get(pos).markAsDone();
        updateStorage(pos, Command.MARK);
        System.out.printf("|  marked task:%n|    %s%n", tasks.get(pos));
    }
    public static void deleteTask(String input) throws JdukeException {
        int pos = handleTaskPos(input);
        System.out.printf(
                "|  deleted task:%n|    %s%n|  %d%s in list%n",
                tasks.get(pos),
                (tasks.size() - 1),
                (tasks.size() == 2 ? " task" : " tasks")
                );
        updateStorage(pos, Command.DELETE);
        tasks.remove(pos);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(GREETING);
        System.out.printf("%n%s", PROMPT);
        try {
            parseStoredTasks();
        } catch (JdukeException e) {
            System.out.println(e.getMessage());
        }
        String input = scanner.nextLine().trim();
        while (!input.equals("bye")) {
            try {
                Command mainCmd = handleCommand(input);
                switch (mainCmd) {
                case LIST:
                    listTasks(input);
                    break;
                case MARK:
                    markTask(input);
                    break;
                case UNMARK:
                    unmarkTask(input);
                    break;
                case TODO:
                    addTodo(input);
                    break;
                case DEADLINE:
                    addDeadline(input);
                    break;
                case EVENT:
                    addEvent(input);
                    break;
                case DELETE:
                    deleteTask(input);
                    break;
                }
            } catch (JdukeException e) {
                System.out.printf("|  Error:%n%s%n", e.getMessage());
            } finally {
                System.out.printf("%n%s", PROMPT);
                input = scanner.nextLine().trim();
            }
        }
        System.out.printf("%s%n", GOODBYE);
        scanner.close();
    }
}

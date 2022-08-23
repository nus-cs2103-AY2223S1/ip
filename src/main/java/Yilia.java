import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

enum Type {
    TODO,
    DEADLINE,
    EVENT
}

class YiliaException extends Exception {
    @Override
    public String getMessage() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}

class DescriptionEmptyException extends Exception {
    private final Type type;
    DescriptionEmptyException(Type content) {
        this.type = content;
    }
    @Override
    public String getMessage() {
        return "☹ OOPS!!! The description of a " + type.toString().toLowerCase() + " cannot be empty.";
    }
}

class Task {
    public boolean isDone = false;
    private final String content;
    Task(String content) {
        this.content = content;
    }
    Task(String content, boolean isDone) {
        this(content);
        this.isDone = isDone;
    }
    public void check() {
        isDone = true;
    }
    public void uncheck() {
        isDone = false;
    }
    @Override
    public String toString() {
        return "[" + (isDone? 'X': ' ') + "] " + content;
    }
    public String parse() {
        return content;
    }
}

class Todo extends Task {
    Todo(String content) {
        super(content);
    }
    Todo(String content, boolean isDone) {
        super(content, isDone);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String parse() {
        return "T / " + (isDone ? "1" : "0") + " / " + super.parse();
    }
}

class Deadline extends Task {
    LocalDate date;
    Deadline(String content, String date) {
        super(content);
        this.date = LocalDate.parse(date);
    }
    Deadline(String content, boolean isDone, String date) {
        super(content, isDone);
        this.date = LocalDate.parse(date);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
    @Override
    public String parse() {
        return "D / " + (isDone ? "1" : "0") + " / " + super.parse() + " / " + date;
    }
}

class Event extends Task {
    LocalDate date;
    Event(String content, String date) {
        super(content);
        this.date = LocalDate.parse(date);
    }
    Event(String content, boolean isDone, String date) {
        super(content, isDone);
        this.date = LocalDate.parse(date);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
    @Override
    public String parse() {
        return "E / " + (isDone ? "1" : "0") + " / " + super.parse() + " / " + date;
    }
}

class Storage {
    private final String filePath;
    Storage(String filePath) {
        this.filePath = filePath;
    }
    public ArrayList<Task> load() throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine();
        ArrayList<Task> tasks = new ArrayList<>();
        while (line != null) {
            String[] info = line.split(" / ");
            switch (info[0]) {
                case "T":
                    tasks.add(new Todo(info[2], Parser.parseStringToBoolean(info[1])));
                    break;
                case "D":
                    tasks.add(new Deadline(info[2], Parser.parseStringToBoolean(info[1]), info[3]));
                    break;
                case "E":
                    tasks.add(new Event(info[2], Parser.parseStringToBoolean(info[1]), info[3]));
                    break;
            }
            line = reader.readLine();
        }
        reader.close();
        return tasks;
    }

    public void save(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).parse());
                writer.write('\n');
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot read the file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class TaskList {
    private final ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public int size() {
        return tasks.size();
    }
    public Task get(int index) {
        return tasks.get(index - 1);
    }
    public void add(Task task) {
        tasks.add(task);
    }
    public Task remove(int index) {
        return tasks.remove(index - 1);
    }
}

class Parser {
    public static Command parse(String text) throws YiliaException {
        String[] info = text.split(" ", 1);
        if (info[0].strip().equals("list")) {
            return new ListCommand(false);
        } else if (info[0].strip().equals("delete")) {
            return new DeleteCommand(Integer.parseInt(info[1]));
        } else if (info[0].strip().equals("mark")) {
            return new MarkCommand(Integer.parseInt(info[1]));
        } else if (info[0].strip().equals("unmark")) {
            return new UnmarkCommand(Integer.parseInt(info[1]));
        } else if (info[0].strip().equals("deadline")) {
            return new AddCommand(info[1], Type.DEADLINE);
        } else if (info[0].trim().equals("event")) {
            return new AddCommand(info[1], Type.EVENT);
        } else if (info[0].trim().equals("todo")) {
            return new AddCommand(info[1], Type.TODO);
        } else if (info[0].trim().equals("bye")) {
            return new ExitCommand();
        } else {
            throw new YiliaException();
        }
    }

    public static boolean parseStringToBoolean(String num) {
        return Integer.parseInt(num) == 1;
    }
}

abstract class Command {
    private boolean isExit = false;
    public Command(){

    }
    public Command(boolean isExit) {
        this.isExit = isExit;
    }
    public boolean isExit() {
        return isExit;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DescriptionEmptyException;
}

class ListCommand extends Command {
    public ListCommand(boolean isExit) {
        super(isExit);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + "." + tasks.get(i));
        }
    }
}

class AddCommand extends Command {
    private final String text;
    private final Type type;
    public AddCommand(String text, Type type) {
        this.text = text;
        this.type = type;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DescriptionEmptyException {
        if (text.isBlank()) {
            throw new DescriptionEmptyException(type);
        }
        if (type.equals(Type.TODO)) {
            Todo todo = new Todo(text);
            tasks.add(todo);
            ui.showAddStatus(tasks);
            return;
        }
        String info[] = text.split("/");
        if (info.length == 1 || info[1].isBlank()) {
            throw new DescriptionEmptyException(type);
        }
        if (type.equals(Type.DEADLINE)) {
            Deadline deadline = new Deadline(info[0].strip(), info[1].strip().substring(3));
            tasks.add(deadline);
            ui.showAddStatus(tasks);
        } else if (type.equals(Type.EVENT)) {
            Event event = new Event(info[0].strip(), info[1].strip().substring(3));
            tasks.add(event);
            ui.showAddStatus(tasks);
        }
    }
}

class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.get(index).check();
            ui.showMarkStatus(tasks.get(index));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index " + index + " out of bounds\nPlease input another index");
        }
    }
}

class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.get(index).uncheck();
            ui.showUnmarkStatus(tasks.get(index));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index " + index + " out of bounds\nPlease input another index");
        }
    }
}

class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
            Task task = tasks.remove(index);
            ui.showDeleteStatus(task, tasks);
    }
}

class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        ui.showBye();
    }
}

class Ui {
    public void showWelcome() {
        String logo = "  \\‾\\     /‾/  |‾|  |‾|          |‾|       /‾‾‾\\      \n" +
                "   \\ \\   / /   | |  | |          | |      / /‾\\ \\     \n" +
                "    \\ \\_/ /    | |  | |          | |     / /___\\ \\    \n" +
                "     ‾| |‾     | |  | |          | |    / /_____\\ \\   \n" +
                "      | |      | |  | |_______   | |   / /       \\ \\  \n" +
                "      |_|      |_|  |_________|  |_|  /_/         \\_\\ \n";
        System.out.println("Hello! I'm Yilia\n" + logo + "What can I do for you?");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("_______");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        System.out.println("The file cannot be loaded.");
    }

    public void showAddStatus(TaskList tasks) {
        System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1) + "\nNow you have " + tasks.size() + (tasks.size() < 2 ? " task" : " tasks") + " in the list.");
    }

    public void showMarkStatus(Task task) {
        System.out.println("Nice! I've marked this task as done:\n  " + task);
    }

    public void showUnmarkStatus(Task task) {
        System.out.println("Nice! I've marked this task as done:\n  " + task);
    }

    public void showDeleteStatus(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}

class Yilia {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Yilia(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (YiliaException e) {
                ui.showError(e.getMessage());
            } catch (DescriptionEmptyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args){
        new Yilia("data/yilia.txt").run();
    }
}
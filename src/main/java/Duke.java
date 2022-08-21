import java.io.FileWriter;
import java.sql.SQLSyntaxErrorException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ListIterator;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> all = new ArrayList<>();
    enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }
    private TaskList taskList;
    private Storage storage;
    private Parser parser;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        taskList = new TaskList();
        parser = new Parser();
        storage = new Storage("data/Duke.txt");
    }

    // abstractions

    private class Ui {
        private Scanner sc;

        public Ui() {
            sc = new Scanner(System.in);
        }

        public void welcome() {
            System.out.println("Hello! I am Duke");
            System.out.println("What can I do for you?");
        }

        public String readCommand() {
            if (sc.hasNext()) {
                return sc.nextLine();
            } else {
                return "";
            }
        }

        public void invalidInput() {
            System.out.println("I'm sorry, but I don't know what that means :-(");
        }

        public void showError(String s) {
            System.out.println(s);
        }

        public void marked(Task task) {
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task.toString());
        }

        public void unmarked(Task task) {
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task.toString());
        }

        public void added(Task task) {
            System.out.println("Got it. I've added this task:");
            System.out.printf("  added: %s\n", task.toString());
            System.out.printf("Now you have %s tasks in the list.\n", taskList.count());
        }

        public void deleted(Task task) {
            System.out.println("Got it. I've deleted this task:");
            System.out.printf("  added: %s\n", task.toString());
            System.out.printf("Now you have %s tasks in the list.\n", taskList.count());
        }

        public void exit() {
            System.out.println("Bye!");
        }
    }

    private class TaskList {
        private ArrayList<Task> all;

        public TaskList() {
            all = new ArrayList<>();
        }

        public void displayList() {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < count(); i++) {
                System.out.printf("%s. %s\n", i + 1, all.get(i).toString());
            }
        }

        public void addTask(Task task) {
            all.add(task);
            ui.added(task);
        }

        public void deleteTask(int index) {
            Task deleted = all.get(index - 1);
            all.remove(index - 1);
            ui.deleted(deleted);
        }

        public Task getTask(int index) {
            return all.get(index);
        }

        public int count() {
            return all.size();
        }
    }

    private class Storage {
        private String filePath;

        public Storage(String path) {
            filePath = path;
        }

        public void loadData() throws FileNotFoundException {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);

            while (sc.hasNext()) {
                String s = sc.nextLine();
                char type = s.charAt(1);
                boolean isDone = s.charAt(4) == 'X';
                String des = s.substring(7);
                Task newTask;

                if (type == 'T') {
                    newTask = new Todo(des);
                } else if (type == 'D') {
                    int i = des.indexOf('(');
                    newTask = new Deadline(des.substring(0, i - 1), parser.parseStringToDate(des.substring(i + 5)));
                } else if (type == 'E') {
                    int i = des.indexOf('(');
                    newTask = new Event(des.substring(0, i - 1), parser.parseStringToDate(des.substring(i + 5)));
                } else {
                    newTask = null;
                }
//                System.out.println(newTask);
                taskList.addTask(newTask);
            }
        }

        public void saveData() throws FileNotFoundException {
            try {
                FileWriter fw = new FileWriter(filePath);
                String s = "";
                for (int i = 0; i < all.size(); i++) {
                    s += all.get(i).toString() + "\n";
                }
                fw.write(s);
                fw.close();
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    private abstract class Command {
        public abstract void execute();

        public abstract boolean isExit();
    }

    private class AddCommand extends Command {
        private TaskType type;
        private String description;
        private LocalDate date;

        public AddCommand(TaskType type, String description ) {
            super();
            this.type = type;
            this.description = description;
        }

        @Override
        public void execute() {
            if (type == TaskType.TODO) {
                taskList.addTask(new Todo(description));
            } else if (type == TaskType.DEADLINE) {
                try {
                    taskList.addTask(new Deadline(parser.extractDescription(description),
                                                  parser.extractDate(description)));
                } catch (DukeException e) {
                    ui.showError("format incorrect!");
                }
            } else if (type == TaskType.EVENT) {
                try {
                    taskList.addTask(new Event(parser.extractDescription(description),
                            parser.extractDate(description)));
                } catch (DukeException e) {
                    ui.showError("format incorrect!");
                }
            } else {
                ui.showError("type not supported");
            }
        }

        @Override
        public boolean isExit () {
            return false;
        }
    }

    private class ListCommand extends Command {
        public ListCommand() { super(); }

        @Override
        public void execute()  {
            taskList.displayList();
        }

        @Override
        public boolean isExit () {
            return false;
        }
    }

    private class MarkCommand extends Command {
        private boolean isMark;
        private int index;

        public MarkCommand(boolean isMark, int index) {
            super();
            this.isMark = isMark;
            this.index = index;
        }

        @Override
        public void execute() {
            Task task = taskList.getTask(index - 1);

            if (isMark) {
                task.markAsDone();
                ui.marked(task);
            } else {
                task.markAsUndone();
                ui.unmarked(task);
            }
        }

        @Override
        public boolean isExit () {
            return false;
        }
    }

    private class DeleteCommand extends Command {
        private int index;

        public DeleteCommand(int index) {
            super();
            this.index = index;
        }

        @Override
        public void execute()  {
            if (index < 1 || index > taskList.count()) {
                ui.showError("index out of range");
            } else {
                taskList.deleteTask(index);
            }
        }

        @Override
        public boolean isExit () {
            return false;
        }
    }

    private class ExitCommand extends Command {
        public ExitCommand() { super(); }

        @Override
        public void execute()  {
            ui.exit();
        }

        @Override
        public boolean isExit () {
            return true;
        }
    }

    private class Parser {
        private String list, todo, ddl, event, mark, unmark, exit, delete;

        public Parser() {
            list = "list";
            todo = "todo";
            event = "event";
            ddl = "deadline";
            unmark = "unmark";
            mark = "mark";
            exit = "bye";
            delete = "delete";
        }

        public Command parse(String s) throws DukeException {
            int space = s.indexOf(" ");
            if (s.equals(list)) {
                return new ListCommand();
            } else if (s.equals(exit)) {
                return new ExitCommand();
            } else if (space == -1 || s.substring(0, space) == "") {
                throw new DukeException("Invalid input!");
            } else {
                String identifier = s.substring(0, space);
                String description = s.substring(space + 1);
                System.out.println(identifier);
                System.out.println(description);
                if (identifier.equals(todo)) {
                    return new AddCommand(TaskType.TODO, description);
                } else if (identifier.equals(event)) {
                    return new AddCommand(TaskType.EVENT, description);
                }  else if (identifier.equals(ddl)) {
                    return new AddCommand(TaskType.DEADLINE, description);
                } else if (identifier.equals(delete)) {
                    return new DeleteCommand(extractIndex(s));
                } else if (identifier.equals(mark)) {
                    return new MarkCommand(true, extractIndex(s));
                } else if (identifier.equals(unmark)) {
                    return new MarkCommand(false, extractIndex(s));
                } else {
                    throw new DukeException("Cannot find matching key word!");
                }
            }
        }

        public int extractIndex(String s) throws DukeException {
            int pos = s.indexOf(" ");
            if (pos == -1) throw new DukeException("Invalid structure, include the index after mark/unmark");

            int index;
            try {
                index = Integer.parseInt(s.substring(pos + 1));
            } catch (Exception e) {
                throw new DukeException("Invalid argument. only integers are accepted!");
            }
            return index;
        }

        public LocalDate extractDate(String s) throws DukeException {
            int pos = s.indexOf("/by");
            if (pos == -1) pos = s.indexOf("/at");
            if (pos == -1) throw new DukeException("Invalid structure, include /by or /at before the date");

//            String date = ;

            return parseStringToDate(s.substring(pos + 4));
        }

        public String extractDescription(String s) throws DukeException {
            int pos = s.indexOf("/by");
            if (pos == -1) pos = s.indexOf("/at");
            if (pos == -1) throw new DukeException("Invalid structure, include /by or /at before the date");
            return s.substring(0, pos - 1);
        }

        public LocalDate parseStringToDate(String s) {
            return LocalDate.parse(s.replace('/', '-'));
        }

        public String parseDateToString(LocalDate date) {
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }

    }





    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                //ui.showLine(); // show the divider line ("_______")
                Command c = parser.parse(fullCommand);
                c.execute();
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                //ui.showLine();
            }
        }
    }


//    private void loadData() throws FileNotFoundException {
//        File f = new File(filePath);
//        Scanner sc = new Scanner(f);
//
//        while (sc.hasNext()) {
//            String s = sc.nextLine();
//            char type = s.charAt(1);
//            boolean isDone = s.charAt(4) == 'X';
//            String des = s.substring(7);
//            Task newTask;
//
//            if (type == 'T') {
//                newTask = new Todo(des);
//            } else if (type == 'D') {
//                int i = des.indexOf('(');
//                newTask = new Deadline(des.substring(0, i - 1), des.substring(i + 5));
//            } else if (type == 'E') {
//                int i = des.indexOf('(');
//                newTask = new Event(des.substring(0, i - 1), des.substring(i + 5));
//            } else {
//                newTask = null;
//            }
//            System.out.println(newTask);
//            all.add(newTask);
//            count += 1;
//        }
//    }
//
//    private void saveData() throws FileNotFoundException {
//        try {
//            FileWriter fw = new FileWriter(filePath);
//            String s = "";
//            for (int i = 0; i < all.size(); i++) {
//                s += all.get(i).toString() + "\n";
//            }
//            fw.write(s);
//            fw.close();
//        } catch (IOException e) {
//            System.out.println("Something went wrong: " + e.getMessage());
//        }
//    }

//    private LocalDate parseDate(String s) {
//        return LocalDate.parse(s.replace('/', '-'));
//    }
//
//    private String dateToString(LocalDate date) {
//        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
//
//    }
//

//
////    private void displayList() {
////        System.out.println("Here are the tasks in your list:");
////        for (int i = 0; i < count; i++) {
////            System.out.printf("%s. %s\n", i + 1, all.get(i).toString());
////        }
////    }
////
////    private void deleteRecord(int index) {
////        System.out.println("Noted. I've removed this task:");
////        System.out.println(all.get(index - 1).toString());
////        count -= 1;
////        System.out.printf("Now you have %s tasks in the list.\n", count);
////        all.remove(index - 1);
////    }
////
////    private void store(String s) {
////        try {
////            all.add(Task.of(s));
////            System.out.println("Got it. I've added this task:");
////            System.out.printf("  added: %s\n", all.get(count).toString());
////            count += 1;
////            System.out.printf("Now you have %s tasks in the list.\n", count);
////        } catch (DukeException e) {
////            System.out.println(e.toString());
////        }
////
////
////    }
//
//    private boolean startWith(String s, String target) {
//        for (int i = 0; i < target.length(); i++) {
//            if (s.charAt(i) != target.charAt(i)) return false;
//        }
//
//        try {
//            int val = Integer.parseInt(s.substring(target.length() + 1));
//            return true;
//        } catch (NumberFormatException e) {
//            //System.out.println("Input String cannot be parsed to Integer.");
//            return false;
//        }
//
//    }
//
//    private int extractIndex(String s, String target) {
////        int i = 0;
////        try {
//        int i = Integer.parseInt(s.substring(target.length() + 1));
////        } catch (Exception e) {
////            System.out.printf("Must be a number following %s\n", target);
////        }
//        return i;
//    }

    public class Task {
        private String todo = "todo";
        private String ddl = "deadline";
        private String event = "event";
        protected String description;
        protected boolean isDone;

      //  public Task of(String s) throws DukeException {
//            int i = s.indexOf(" ");
//            String identifier;
//            String des = "";
//            if (i == -1) {
//                throw !isIdentifierValid(s) ? new DukeException("I'm sorry, but I don't know what that means :-(")
//                                           : new DukeException("The description of a " + s + " cannot be empty.");
//            } else {
//                identifier = s.substring(0, i);
//                des = s.substring(i + 1);
//            }
////            System.out.printf("%s\n", identifier);
////            System.out.printf("%s\n", s.substring(i + 1));
//            if (identifier.equals(todo)) {
//                return new Todo(des);
//            } else if (identifier.equals(ddl)) {
//                int j = s.indexOf("/by");
//                return new Deadline(s.substring(ddl.length() + 1, j - 1), s.substring(j + 4));
//            } else if (identifier.equals(event)) {
//                int j = s.indexOf("/at");
//                return new Event(s.substring(event.length() + 1, j - 1), s.substring(j + 4));
//            } else {
//                throw new DukeException("I'm sorry, but I don't know what that means :-(");
//            }
//        }

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        @Override
        public String toString() {
            return String.format("[%s] %s", getStatusIcon(), description);
        }

        public void markAsDone() {
            isDone = true;
        }

        public void markAsUndone() {
            isDone = false;
        }
    }

    private class Deadline extends Task {
        protected LocalDate by;

        public Deadline(String description, LocalDate by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + parser.parseDateToString(by) + ")";
        }
    }

    private class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    private class Event extends Task {
        private LocalDate at;

        public Event(String description, LocalDate at) {
            super(description);
            this.at = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + parser.parseDateToString(at) + ")";
        }
    }

    private class DukeException extends Exception {
        public DukeException(String message) {
            super("OOPS!!" + message);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
//        Scanner sc = new Scanner(System.in);
//
//        try {
//            loadData();
//        } catch (FileNotFoundException e) {
//            System.out.println("data file not found! create a text file duke.txt under folder data and try again!");
//        }
//
//
//
//        String s = "";
//        while (true) {
//            s = sc.nextLine();
//            if (s.equals("bye")) {
//                bye();
//                break;
//            } else if (s.equals("list")) {
//                displayList();
//            } else if (startWith(s, "mark")) {
//                int index = extractIndex(s, "mark");
//                //System.out.println(index);
//                if (index > count || index < 1) {
//                    System.out.println("index out of range");
//                } else {
//                    all.get(index - 1).markAsDone();
//                }
//            } else if (startWith(s, "unmark")) {
//                int index = extractIndex(s, "unmark");
//                //System.out.println(index);
//                if (index > count || index < 1) {
//                    System.out.println("index out of range");
//                } else {
//                    all.get(index - 1).markAsUndone();
//                }
//            } else if (startWith(s, "delete")) {
//                int index =extractIndex(s, "delete");
//                if (index > count || index < 1) {
//                    System.out.println("index out of range");
//                } else {
//                    deleteRecord(index);
//                }
//            } else {
//                store(s);
//            }
//
//            try {
//                saveData();
//            } catch (FileNotFoundException e) {
//                System.out.println("data file not found! create a text file duke.txt under folder data and try again!");
//            }
//        }
    }

}

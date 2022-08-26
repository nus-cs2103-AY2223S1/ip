import duke.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static duke.DukeConstants.KEY_SEPARATOR;

public class DukeBot {

    private TaskList taskList;
    //private final String KEY_SEPARATOR = "//";
    //private final String fileName = "bin/tasks.txt";
    private Storage storage;
    private Ui ui;

    public DukeBot() {
        ui = new Ui();
        storage = new Storage(DukeConstants.FILENAME);
        try {
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showError("file not found!");
            taskList = new TaskList();
        }

    }

//    public class Ui {
//
//        public final String ADDED = "oke i added this:";
//        public final String DELETED = "oke i deleted this:";
//        public final String MARKED = "oke this is done now:";
//        public final String UNMARKED = "oke this is undone now:";
//
//        Scanner scanner;
//
//        public Ui () {
//            System.out.println("----------------------");
//        }
//
//        public void showWelcome() {
//            System.out.println("hi im chompers what can i do for u today!");
//            scanner = new Scanner(System.in);
//        }
//
//        public void showExit() {
//            System.out.println("bye see u");
//            scanner.close();
//        }
//
//        public void displayTask(String message, Task task) {
//            System.out.println(message);
//            System.out.println(task);
//        }
//
//        public void printTasks() {
//            System.out.println("here! ur tasks:");
//            System.out.println(taskList.toString());
//
//        }
//
//        public void showTotalTasks() {
//            System.out.println("now u have " + taskList.getSize() + " task(s)!");
//        }
//        public void showError(String message) {
//            System.out.println("error! " + message);
//        }
//
//        public String readCommand() {
//            String str = "";
//            scanner = new Scanner(System.in);
//            str = scanner.nextLine();
//            //scanner.close();
//            return str;
//        }
//    }

//    public abstract class Command {
//
//        public Command() {
//
//        }
//
//        public abstract void execute(Ui ui, Storage storage);
//        public abstract boolean isExit();
//    }
//
//    public class AddCommand extends Command {
//        Task task;
//
//        public AddCommand(Task task) {
//            this.task = task;
//        }
//
//        @Override
//        public void execute(Ui ui, Storage storage) {
//            taskList.addTask(task);
//            ui.displayTask(ui.ADDED, task);
//            ui.showTotalTasks();
//            storage.save();
//        }
//
//        @Override
//        public boolean isExit() {
//            return false;
//        }
//    }
//
//    public class DeleteCommand extends Command {
//
//        private int index;
//        public DeleteCommand(int index) {
//            this.index = index;
//        }
//        @Override
//        public void execute(Ui ui, Storage storage) {
//            Task temp = taskList.getTask(index);
//            taskList.removeTask(index);
//            storage.save();
//            ui.displayTask(ui.DELETED, temp);
//            ui.showTotalTasks();
//        }
//        @Override
//        public boolean isExit() {
//            return false;
//        }
//    }
//
//    public class ExitCommand extends Command {
//
//        private boolean isExit;
//
//        public ExitCommand() {
//            this.isExit = false;
//        }
//
//        @Override
//        public void execute(Ui ui, Storage storage) {
//            this.isExit = true;
//            ui.showExit();
//        }
//
//        @Override
//        public boolean isExit() {
//            return this.isExit;
//        }
//    }
//
//    public class ListCommand extends Command {
//        public ListCommand() {
//
//        }
//
//        @Override
//        public void execute(Ui ui, Storage storage) {
//            ui.printTasks();
//        }
//
//        @Override
//        public boolean isExit() {
//            return false;
//        }
//    }
//
//    public class MarkCommand extends Command {
//        int index;
//        public MarkCommand(int index) {
//            this.index = index;
//        }
//
//        @Override
//        public void execute(Ui ui, Storage storage) {
//            taskList.markTask(index);
//            Task temp = taskList.getTask(index);
//            ui.displayTask(ui.MARKED, temp);
//            storage.save();
//        }
//
//        @Override
//        public boolean isExit() {
//            return false;
//        }
//    }
//
//    public class UnmarkCommand extends Command {
//        int index;
//        public UnmarkCommand(int index) {
//            this.index = index;
//        }
//
//        @Override
//        public void execute(Ui ui, Storage storage) {
//
//            taskList.unmarkTask(index);
//            Task temp = taskList.getTask(index);
//
//            System.out.println(ui.UNMARKED + temp);
//            storage.save();
//        }
//
//        @Override
//        public boolean isExit() {
//            return false;
//        }
//    }
//    public class Storage {
//        private String filePath;
//
//        public Storage(String filePath) {
//            this.filePath = filePath;
//        }
//
//        public void save() {
//            try {
//                FileWriter fw = new FileWriter(filePath, false);
//                fw.write(taskList.formatTasks());
//                fw.close();
//            } catch (IOException e) {
//                ui.showError("there is a problem with saving");
//                return;
//            }
//        }
//
//        public List<Task> load() throws FileNotFoundException {
//            File file = new File(filePath);
//            Scanner scanner = new Scanner(file);
//            List<Task> tasks= new ArrayList<>();
//            while(scanner.hasNext()) {
//                String dataStr = scanner.nextLine();
//                String[] taskStr = dataStr.split(KEY_SEPARATOR);
//                Task task;
//
//                switch (taskStr[0]) {
//                    case "T": task = new Todo(taskStr[2]);
//                        break;
//                    case "D": task = new Deadline(taskStr[2], LocalDate.parse(taskStr[3]));
//                        break;
//                    case "E": String[] timeDesc = taskStr[3].split(" ", 2);
//                        if(timeDesc.length > 1) {
//                            LocalDate date = LocalDate.parse(timeDesc[0]);
//                            task = new Event(taskStr[2], date, timeDesc[1]);
//                        } else {
//                            LocalDate date = LocalDate.parse(taskStr[3]);
//                            task = new Event(taskStr[2], date);
//                        }
//
//                        break;
//                    default: task = new Task(taskStr[2]);
//                        break;
//                }
//                if(Integer.parseInt(taskStr[1]) == 1) {
//                    task.markAsDone();
//                } else {
//                    task.markAsUndone();
//                }
//                tasks.add(task);
//            }
//            scanner.close();
//            return tasks;
//        }
//    }

//    public class Parser {
//        private final String exit = "bye";
//
//        public Parser() {
//
//        }
//
//        public Command parse(String input) throws DukeException {
//            input = input.toLowerCase();
//            if(input.equals(exit)) {
//                return new ExitCommand();
//            } else if (input.equals("list")) {
//                return new ListCommand();
//            } else {
//                String[] substr = input.split(" ", 2); // to identify the keyword used
//                Integer index;
//                Task temp;
//                switch (substr[0]) {
//                    case "mark":
//                        if(substr.length == 1) { // no number was given
//                            throw new DukeException(DukeException.MISSING_INDEX);
//                        }
//                        try {
//                            index = Integer.parseInt(substr[1]) - 1;
//                            if(index < 0 || index >= taskList.getSize()) { // to check if index is out of range
//                                throw new DukeException(DukeException.OUT_OF_RANGE);
//                                //System.out.println("thrs nth there :<");
//                            }
//
//                            return new MarkCommand(index);
//                        } catch (NumberFormatException e) {
//                            throw new DukeException(DukeException.WRONG_FORMAT);
//                            //ui.showError("Invalid input"); // if index given cannot be converted or was the wrong format
//                        }
//                    case "unmark":
//                        if(substr.length == 1) {
//                            throw new DukeException(DukeException.MISSING_INDEX);
//                        }
//                        try {
//                            index = Integer.parseInt(substr[1]) - 1;
//                            if(index < 0 || index >= taskList.getSize()) { // check if index is out of range
//                                throw new DukeException(DukeException.OUT_OF_RANGE);
//                            }
//                            return new UnmarkCommand(index);
//
//                        } catch (NumberFormatException e) {
//                            throw new DukeException(DukeException.WRONG_FORMAT);
//                           // ui.showError("Invalid input");
//                        }
//
//                    case "delete":
//                        if(substr.length == 1) {
//                            throw new DukeException(DukeException.MISSING_INDEX);
//                        }
//                        try {
//                            index = Integer.parseInt(substr[1]) - 1;
//                            if(index < 0 || index >= taskList.getSize()) {
//                                throw new DukeException(DukeException.OUT_OF_RANGE);
//
//                            }
//                            return new DeleteCommand(index);
//                        } catch (NumberFormatException e) {
//                            ui.showError("Invalid input");
//                        }
//                        break;
//                    case "todo":
//                        if(substr.length == 1) {
//                            throw new DukeException(DukeException.MISSING_DESCRIPTION);
//                        }
//                        temp = new Todo(substr[1]);
//                        return new AddCommand(temp);
//
//                    case "deadline":
//                        if(substr.length == 1) {
//                            throw new DukeException(DukeException.MISSING_DESCRIPTION);
//                        }
//                        String[] dlDesc = substr[1].split(" /by ", 2);
//                        if(dlDesc.length < 2) {
//                            throw new DukeException(DukeException.MISSING_DATE);
//                        }
//                        try {
//                            LocalDate date = LocalDate.parse(dlDesc[1]);
//                            temp = new Deadline(dlDesc[0], date);
//                            return new AddCommand(temp);
//                            //break;
//                        } catch (DateTimeParseException e) {
//                            throw new DukeException(DukeException.WRONG_FORMAT_DATE);
//                            //ui.showError("Please re-enter the task with the following deadline format: \nyyyy-mm-dd");
//
//                        }
//
//
//                    case "event":
//                        if(substr.length == 1) {
//                            throw new DukeException(DukeException.MISSING_DESCRIPTION);
//                        }
//                        String[] eventDesc = substr[1].split(" /at ", 2);
//                        if(eventDesc.length < 2) {
//                            throw new DukeException(DukeException.MISSING_DATE);
//                        }
//                        try {
//                            String[] timeDesc = eventDesc[1].split(" ", 2);
//                            if(timeDesc.length > 1) {
//                                LocalDate date = LocalDate.parse(timeDesc[0]);
//                                temp = new Event(eventDesc[0], date, timeDesc[1]);
//                            } else {
//                                LocalDate date = LocalDate.parse(eventDesc[1]);
//                                temp = new Event(eventDesc[0], date);
//                            }
//
//                            return new AddCommand(temp);
//                        } catch (DateTimeParseException e) {
//                            throw new DukeException(DukeException.WRONG_FORMAT_DATE);
//                            //ui.showError("Please re-enter the task with the following deadline format: \nyyyy-mm-dd");
//
//                        }
//                    default:
//                        throw new DukeException(DukeException.UNRECOGNISED_COMMAND);
//                }
//
//            }
//            return new ExitCommand();
//        }
//    }

//    public class TaskList {
//        private List<Task> tasks;
//
//        public TaskList() {
//            this.tasks = new ArrayList<>();
//        }
//
//        public TaskList(List<Task> tasks) {
//            this.tasks = tasks;
//        }
//
//        public void addTask(Task task) {
//            this.tasks.add(task);
//        }
//
//        public void removeTask(int index) {
//            this.tasks.remove(index);
//        }
//
//        public void markTask(int index) {
//            this.tasks.get(index).markAsDone();
//        }
//
//        public void unmarkTask(int index) {
//            this.tasks.get(index).markAsUndone();
//        }
//        public Task getTask(int index) {
//            return this.tasks.get(index);
//        }
//
//        public int getSize() {
//            return this.tasks.size();
//        }
//
//        public String formatTasks() {
//            String str = "";
//            for(int i = 0; i < getSize(); i++) {
//                str += getTask(i).formatToSave() + "\n";
//            }
//            return str;
//        }
//
//        @Override
//        public String toString() {
//            String str = "";
//            for(int i = 1; i <= this.getSize(); i++) {
//                Task task = this.getTask(i-1);
//                str += i + "." + task.toString() + "\n";
//            }
//            return str;
//        }
//    }

    /**
     * class for each task
     */
//    public class Task {
//        protected String description;
//        protected boolean isDone;
//
//        /**
//         * Takes in a description for the task
//         * @param description The task description
//         * */
//        public Task(String description) {
//            this.description = description;
//            this.isDone = false;
//        }
//
//        public String getStatusIcon() {
//            return (isDone ? "X" : " "); // mark done task with X
//        }
//
//        public void markAsDone() {
//            this.isDone = true;
//        }
//
//        public void markAsUndone() {
//            this.isDone = false;
//        }
//
//        public String formatToSave() {
//            return "";
//        }
//        /**
//         * Returns a String representation of the task
//         * @return string
//         */
//        @Override
//        public String toString() {
//            return "[" + getStatusIcon() + "] " + this.description;
//        }
//    }
//    public class Deadline extends Task {
//
//        protected LocalDate date;
//
//        /**
//         * Takes in a description and deadline for the task
//         * @param description task description
//         * @param date deadline of task
//         */
//        public Deadline(String description, LocalDate date) {
//            super(description);
//            this.date = date;
//        }
//
//        @Override
//        public String formatToSave() {
//            return isDone
//                    ? "D" + KEY_SEPARATOR + 1 + KEY_SEPARATOR + description + KEY_SEPARATOR + date
//                    : "D" + KEY_SEPARATOR + 0 + KEY_SEPARATOR + description + KEY_SEPARATOR + date;
//        }
//        /**
//         * Returns a String representation of the task
//         * @return string
//         */
//        @Override
//        public String toString() {
//            return "[D]" + super.toString() + " (by: " + date + ")";
//        }
//    }
//    public class Todo extends Task {
//
//        /**
//         * Takes in a description for the task
//         * @param description task description
//         */
//        public Todo(String description) {
//            super(description);
//        }
//
//        @Override
//        public String formatToSave() {
//            return isDone
//                    ? "T" + KEY_SEPARATOR + 1 + KEY_SEPARATOR + description
//                    : "T" + KEY_SEPARATOR + 0 + KEY_SEPARATOR + description;
//        }
//        /**
//         * Returns a String representation of the task
//         * @return string
//         */
//        @Override
//        public String toString() {
//            return "[T]" + super.toString();
//        }
//    }
//
//    public class Event extends Task {
//        protected LocalDate at;
//        private String time;
//
//        /**
//         * Takes in a description and time for the task
//         * @param description task description
//         * @param at time of task
//         */
//        public Event(String description, LocalDate at) {
//            super(description);
//            this.at = at;
//            this.time = "";
//        }
//
//        public Event(String description, LocalDate at, String time) {
//            super(description);
//            this.at = at;
//            this.time = time;
//        }
//
//        @Override
//        public String formatToSave() {
//            int value;
//            if(isDone) {
//                value = 1;
//            } else {
//                value = 0;
//            }
//            return "E" + KEY_SEPARATOR + value + KEY_SEPARATOR + description + KEY_SEPARATOR + at + " " + time;
//        }
//        /**
//         * Returns a String representation of the task
//         * @return string
//         */
//        @Override
//        public String toString() {
//            return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.time + ")";
//        }
//    }

    public static void main(String[] args) {
        new DukeBot().run();
    }

    public void run() {
        String reply = "";
        String exit = "bye"; // the keyword to exit
        Parser parser = new Parser();
        boolean isExit = false;
        ui.showWelcome();

        while(!isExit) {
//            Scanner scanIn = new Scanner(System.in);
//            reply = scanIn.nextLine(); // read from input
            try {
                reply = ui.readCommand();
                Command c = parser.parse(taskList, reply, ui);
                taskList = c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }


//            if(reply.equals(exit)) {
//                Command c = new ExitCommand();
//                c.execute(ui, storage);
//                break;
//            } else if (reply.equals("list")) {
//                ui.printTasks();
//            } else {
//                String[] substr = reply.split(" ", 2); // to identify the keyword used
//                Integer index;
//                Task temp;
//                switch (substr[0]) {
//                    case "mark":
//                        if(substr.length == 1) { // no number was given
//                            ui.showError("enter an index!");
//                            //System.out.println();
//                            break;
//                        }
//                        try {
//                            index = Integer.parseInt(substr[1]) - 1;
//                            if(index < 0 || index >= taskList.getSize()) { // to check if index is out of range
//                                ui.showError("thrs nth there :<");
//                                //System.out.println("thrs nth there :<");
//                                continue;
//                            }
//
//                            Command c = new MarkCommand(index);
//                            c.execute(ui, storage);
////                            taskList.markTask(index);
////                            temp = taskList.getTask(index);
////                            ui.displayTask(ui.MARKED, temp);
////                            storage.save();
//                        } catch (NumberFormatException e) {
//                            ui.showError("Invalid input"); // if index given cannot be converted or was the wrong format
//                        }
//                        break;
//                    case "unmark":
//                        if(substr.length == 1) {
//                            ui.showError("enter an index!");
//                            break;
//                        }
//                        try {
//                            index = Integer.parseInt(substr[1]) - 1;
//                            if(index < 0 || index >= taskList.getSize()) { // check if index is out of range
//                                ui.showError("thrs nth there :<");
//                                continue;
//                            }
//                            Command c = new UnmarkCommand(index);
//                            c.execute(ui, storage);
////                            taskList.unmarkTask(index);
////                            temp = taskList.getTask(index);
////
////                            System.out.println(ui.UNMARKED + temp);
////                            storage.save();
//                        } catch (NumberFormatException e) {
//                            ui.showError("Invalid input");
//                        }
//
//                        break;
//                    case "delete":
//                        if(substr.length == 1) {
//                            ui.showError("enter an index!");
//                            break;
//                        }
//                        try {
//                            index = Integer.parseInt(substr[1]) - 1;
//                            if(index < 0 || index >= taskList.getSize()) {
//                                ui.showError("thrs nth there :<");
//                                continue;
//                            }
//                            Command c = new DeleteCommand(index);
//                            c.execute(ui, storage);
////                            taskList.removeTask(index);
////                            temp = taskList.getTask(index);
////                            storage.save();
////                            ui.displayTask(ui.DELETED, temp);
//                        } catch (NumberFormatException e) {
//                            ui.showError("Invalid input");
//                        }
//                        break;
//                    case "todo":
//                        if(substr.length == 1) {
//                            ui.showError("The description cannot be empty!");
//                            break;
//                        }
//                        temp = new Todo(substr[1]);
//                        addTask(temp);
//                        break;
//                    case "deadline":
//                        if(substr.length == 1) {
//                            ui.showError("The description cannot be empty!");
//                            break;
//                        }
//                        String[] dlDesc = substr[1].split(" /by ", 2);
//                        if(dlDesc.length < 2) {
//                            ui.showError("The deadline cannot be empty");
//                            break;
//                        }
//                        try {
//                            LocalDate date = LocalDate.parse(dlDesc[1]);
//                            temp = new Deadline(dlDesc[0], date);
//                            addTask(temp);
//                            break;
//                        } catch (DateTimeParseException e) {
//                            ui.showError("Please re-enter the task with the following deadline format: \nyyyy-mm-dd");
//                            break;
//                        }
//
//
//                    case "event":
//                        if(substr.length == 1) {
//                            ui.showError("The description cannot be empty!");
//                            break;
//                        }
//                        String[] eventDesc = substr[1].split(" /at ", 2);
//                        if(eventDesc.length < 2) {
//                            ui.showError("The date cannot be empty");
//                            break;
//                        }
//
//                        temp = new Event(eventDesc[0], eventDesc[1]);
//                        addTask(temp);
//                        break;
//                    default:
//                        ui.showError("idk what that means :(");
//                        break;
//                }
//
//            }

        }
    }


}

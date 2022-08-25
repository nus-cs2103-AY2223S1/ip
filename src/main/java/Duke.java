import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private TaskList taskList;
    private final String KEY_SEPARATOR = "//";
    private final String fileName = "data/tasks.txt";
    private Storage storage;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage(fileName);
        try {
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showError("file not found!");
            taskList = new TaskList();
        }

    }
    public class Ui {

        public final String ADDED = "oke i added this:";
        public final String DELETED = "oke i deleted this:";
        public final String MARKED = "oke this is done now:";
        public final String UNMARKED = "oke this is undone now:";

        public Ui () {
            System.out.println("----------------------");
        }

        public void showWelcome() {
            System.out.println("hi im chompers what can i do for u today!");
        }

        public void showExit() {
            System.out.println("bye see u");
        }

        public void displayTask(String message, Task task) {
            System.out.println(message);
            System.out.println(task);
        }

        public void printTasks() {
            System.out.println("here! ur tasks:");
            System.out.println(taskList.toString());

        }

        public void showTotalTasks() {
            System.out.println("now u have " + taskList.getSize() + " task(s)!");
        }
        public void showError(String message) {
            System.out.println("error! " + message);
        }

        public String readCommand() {
            String str = "";
            Scanner scanner = new Scanner(System.in);
            str = scanner.nextLine();
            scanner.close();
            return str;
        }
    }

    public class Storage {
        private String filePath;

        public Storage(String filePath) {
            this.filePath = filePath;
        }

        public void save() {
            try {
                FileWriter fw = new FileWriter(filePath, false);
                fw.write(taskList.formatTasks());
                fw.close();
            } catch (IOException e) {
                ui.showError("file not found");
                return;
            }
        }

        public List<Task> load() throws FileNotFoundException {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            List<Task> tasks= new ArrayList<>();
            while(scanner.hasNext()) {
                String dataStr = scanner.nextLine();
                String[] taskStr = dataStr.split(KEY_SEPARATOR);
                Task task;

                switch (taskStr[0]) {
                    case "T": task = new Todo(taskStr[2]);
                        break;
                    case "D": task = new Deadline(taskStr[2], LocalDate.parse(taskStr[3]));
                        break;
                    case "E": task = new Event(taskStr[2], taskStr[3]);
                        break;
                    default: task = new Task(taskStr[2]);
                        break;
                }
                if(Integer.parseInt(taskStr[1]) == 1) {
                    task.markAsDone();
                } else {
                    task.markAsUndone();
                }
                tasks.add(task);
            }
            scanner.close();
            return tasks;
        }
    }

    public class Parser {
        private final String exit = "bye";

        public Parser() {

        }

        public String parse(String string) {
//            if(string.equals(exit)) {
//                ui.showExit();
//                return "exit";
//            } else if (string.equals("list")) {
//                ui.printTasks();
//                return "";
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
//                            taskList.markTask(index);
//                            temp = taskList.getTask(index);
//                            ui.displayTask(ui.MARKED, temp);
//                            storage.save();
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
//                            taskList.unmarkTask(index);
//                            temp = taskList.getTask(index);
//
//                            System.out.println(ui.UNMARKED + temp);
//                            storage.save();
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
//                            taskList.removeTask(index);
//                            temp = taskList.getTask(index);
//                            storage.save();
//                            ui.displayTask(ui.DELETED, temp);
//                            System.out.println("now u have " + taskList.getSize() + " task(s)!");
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
//            return "";
        }
    }

    public class TaskList {
        private List<Task> tasks;

        public TaskList() {
            this.tasks = new ArrayList<>();
        }

        public TaskList(List<Task> tasks) {
            this.tasks = tasks;
        }

        public void addTask(Task task) {
            this.tasks.add(task);
        }

        public void removeTask(int index) {
            this.tasks.remove(index);
        }

        public void markTask(int index) {
            this.tasks.get(index).markAsDone();
        }

        public void unmarkTask(int index) {
            this.tasks.get(index).markAsUndone();
        }
        public Task getTask(int index) {
            return this.tasks.get(index);
        }

        public int getSize() {
            return this.tasks.size();
        }

        public String formatTasks() {
            String str = "";
            for(int i = 0; i < getSize(); i++) {
                str += getTask(i).formatToSave() + "\n";
            }
            return str;
        }

        @Override
        public String toString() {
            String str = "";
            for(int i = 1; i <= this.getSize(); i++) {
                Task task = this.getTask(i-1);
                str += i + "." + task.toString() + "\n";
            }
            return str;
        }
    }

    /**
     * class for each task
     */
    public class Task {
        protected String description;
        protected boolean isDone;

        /**
         * Takes in a description for the task
         * @param description The task description
         * */
        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsUndone() {
            this.isDone = false;
        }

        public String formatToSave() {
            return "";
        }
        /**
         * Returns a String representation of the task
         * @return string
         */
        @Override
        public String toString() {
            return "[" + getStatusIcon() + "] " + this.description;
        }
    }
    public class Deadline extends Task {

        protected LocalDate date;

        /**
         * Takes in a description and deadline for the task
         * @param description task description
         * @param date deadline of task
         */
        public Deadline(String description, LocalDate date) {
            super(description);
            this.date = date;
        }

        @Override
        public String formatToSave() {
            return isDone
                    ? "D" + KEY_SEPARATOR + 1 + KEY_SEPARATOR + description + KEY_SEPARATOR + date
                    : "D" + KEY_SEPARATOR + 0 + KEY_SEPARATOR + description + KEY_SEPARATOR + date;
        }
        /**
         * Returns a String representation of the task
         * @return string
         */
        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + date + ")";
        }
    }
    public class Todo extends Task {

        /**
         * Takes in a description for the task
         * @param description task description
         */
        public Todo(String description) {
            super(description);
        }

        @Override
        public String formatToSave() {
            return isDone
                    ? "T" + KEY_SEPARATOR + 1 + KEY_SEPARATOR + description
                    : "T" + KEY_SEPARATOR + 0 + KEY_SEPARATOR + description;
        }
        /**
         * Returns a String representation of the task
         * @return string
         */
        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public class Event extends Task {
        protected String at;

        /**
         * Takes in a description and time for the task
         * @param description task description
         * @param at time of task
         */
        public Event(String description, String at) {
            super(description);
            this.at = at;
        }

        @Override
        public String formatToSave() {
            return isDone
                    ? "E" + KEY_SEPARATOR + 1 + KEY_SEPARATOR + description + KEY_SEPARATOR + at
                    : "E" + KEY_SEPARATOR + 0 + KEY_SEPARATOR + description + KEY_SEPARATOR + at;
        }
        /**
         * Returns a String representation of the task
         * @return string
         */
        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        String reply = "";
        String exit = "bye"; // the keyword to exit

        try {
            storage.load();
        } catch (FileNotFoundException e) {
            ui.showError("File not found");
            return;
        }
        ui = new Ui();
        ui.showWelcome();

        while(true) {
//            Scanner scanIn = new Scanner(System.in);
//            reply = scanIn.nextLine(); // read from input
            reply = ui.readCommand();

            if(reply.equals(exit)) {
                ui.showExit();
                //scanIn.close();
                break;
            } else if (reply.equals("list")) {
                ui.printTasks();
            } else {
                String[] substr = reply.split(" ", 2); // to identify the keyword used
                Integer index;
                Task temp;
                switch (substr[0]) {
                    case "mark":
                        if(substr.length == 1) { // no number was given
                            ui.showError("enter an index!");
                            //System.out.println();
                            break;
                        }
                        try {
                            index = Integer.parseInt(substr[1]) - 1;
                            if(index < 0 || index >= taskList.getSize()) { // to check if index is out of range
                                ui.showError("thrs nth there :<");
                                //System.out.println("thrs nth there :<");
                                continue;
                            }
                            taskList.markTask(index);
                            temp = taskList.getTask(index);
                            ui.displayTask(ui.MARKED, temp);
                            storage.save();
                        } catch (NumberFormatException e) {
                            ui.showError("Invalid input"); // if index given cannot be converted or was the wrong format
                        }
                        break;
                    case "unmark":
                        if(substr.length == 1) {
                            ui.showError("enter an index!");
                            break;
                        }
                        try {
                            index = Integer.parseInt(substr[1]) - 1;
                            if(index < 0 || index >= taskList.getSize()) { // check if index is out of range
                                ui.showError("thrs nth there :<");
                                continue;
                            }
                            taskList.unmarkTask(index);
                            temp = taskList.getTask(index);

                            System.out.println(ui.UNMARKED + temp);
                            storage.save();
                        } catch (NumberFormatException e) {
                            ui.showError("Invalid input");
                        }

                        break;
                    case "delete":
                        if(substr.length == 1) {
                            ui.showError("enter an index!");
                            break;
                        }
                        try {
                            index = Integer.parseInt(substr[1]) - 1;
                            if(index < 0 || index >= taskList.getSize()) {
                                ui.showError("thrs nth there :<");
                                continue;
                            }
                            taskList.removeTask(index);
                            temp = taskList.getTask(index);
                            storage.save();
                            ui.displayTask(ui.DELETED, temp);
                            System.out.println("now u have " + taskList.getSize() + " task(s)!");
                        } catch (NumberFormatException e) {
                            ui.showError("Invalid input");
                        }
                        break;
                    case "todo":
                        if(substr.length == 1) {
                            ui.showError("The description cannot be empty!");
                            break;
                        }
                        temp = new Todo(substr[1]);
                        addTask(temp);
                        break;
                    case "deadline":
                        if(substr.length == 1) {
                            ui.showError("The description cannot be empty!");
                            break;
                        }
                        String[] dlDesc = substr[1].split(" /by ", 2);
                        if(dlDesc.length < 2) {
                            ui.showError("The deadline cannot be empty");
                            break;
                        }
                        try {
                            LocalDate date = LocalDate.parse(dlDesc[1]);
                            temp = new Deadline(dlDesc[0], date);
                            addTask(temp);
                            break;
                        } catch (DateTimeParseException e) {
                            ui.showError("Please re-enter the task with the following deadline format: \nyyyy-mm-dd");
                            break;
                        }


                    case "event":
                        if(substr.length == 1) {
                            ui.showError("The description cannot be empty!");
                            break;
                        }
                        String[] eventDesc = substr[1].split(" /at ", 2);
                        if(eventDesc.length < 2) {
                            ui.showError("The date cannot be empty");
                            break;
                        }

                        temp = new Event(eventDesc[0], eventDesc[1]);
                        addTask(temp);
                        break;
                    default:
                        ui.showError("idk what that means :(");
                        break;
                }

            }

        }
    }

    /**
     * Adds the task to the list
     * @param task task to be added
     */
    public void addTask(Task task) {
        taskList.addTask(task);
        ui.displayTask(ui.ADDED, task);
        ui.showTotalTasks();
        storage.save();
    }

}

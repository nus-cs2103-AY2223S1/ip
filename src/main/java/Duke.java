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

    static TaskList taskList = new TaskList();
    private static String keySeparator = "//";
    private static String fileName = "data/tasks.txt";
    private static Storage storage = new Storage(fileName);
    private static Ui ui;

    public static class Ui {

        public final String ADDED = "oke i added this:";
        public final String DELETED = "oke i deleted this:";
        public final String MARKED = "oke this is done now:";
        public final String UNMARKED = "oke this is undone now:";

        public Ui () {

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
        public void showError(String message) {
            System.out.println("error! " + message);
        }

        public void readCommand() {

        }
    }

    public static class Storage {
        private String filePath;

        public Storage(String filePath) {
            this.filePath = filePath;
        }

        public void save() {
            try {
                FileWriter fw = new FileWriter(fileName, false);
                fw.write(taskList.formatTasks());
                fw.close();
            } catch (IOException e) {
                ui.showError("file not found");
                return;
            }
        }

        public void load() throws FileNotFoundException {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()) {
                String dataStr = scanner.nextLine();
                String[] taskStr = dataStr.split(keySeparator);
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
                taskList.addTask(task);
            }
            scanner.close();
        }
    }

    public static class Parser {

    }

    public static class TaskList {
        private List<Task> tasks;

        public TaskList() {
            this.tasks = new ArrayList<>();
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
    public static class Task {
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
    public static class Deadline extends Task {

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
                    ? "D" + keySeparator + 1 + keySeparator + description + keySeparator + date
                    : "D" + keySeparator + 0 + keySeparator + description + keySeparator + date;
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
    public static class Todo extends Task {

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
                    ? "T" + keySeparator + 1 + keySeparator + description
                    : "T" + keySeparator + 0 + keySeparator + description;
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

    public static class Event extends Task {
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
                    ? "E" + keySeparator + 1 + keySeparator + description + keySeparator + at
                    : "E" + keySeparator + 0 + keySeparator + description + keySeparator + at;
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
        String reply = "";
        String exit = "bye"; // the keyword to exit

        try {
            storage.load();
        } catch (FileNotFoundException e) {
            ui.showError("File not found");
            //System.out.println("File not found!");
            return;
        }
        ui = new Ui();
        ui.showWelcome();
        //System.out.println("hi im chompers what do u need!!!\n");

        while(true) {
            Scanner scanIn = new Scanner(System.in);
            reply = scanIn.nextLine(); // read from input

            if(reply.equals(exit)) {
                ui.showExit();
                //System.out.println("bye see u"); // exits the program
                scanIn.close();
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
    public static void addTask(Task task) {
        taskList.addTask(task);
        ui.displayTask(ui.ADDED, task);
        System.out.println("now u have " + taskList.getSize() + " task(s)!");
        storage.save();
    }

}

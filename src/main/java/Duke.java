import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public class Storage {
        private ArrayList<Task> list = new ArrayList<>();
        private int count = 0;

        public void loadFile() {

        };

        public void writeToFile(){
            try {
                new FileWriter("data/duke.txt", false).close();
                for (int i = 0; i < list.size(); i++) {
                    FileWriter fw = new FileWriter("data/duke.txt", true); // create a FileWriter in append mode
                    fw.write(list.get(i).getTask());
                    fw.write(System.lineSeparator());
                    fw.close();
                }
            } catch(IOException e) {
                System.out.println("File not found");
            }
        }
        public ArrayList<Duke.Task> getList() {
            return this.list;
        }



    }

    public class Ui {
        private String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String marked = "[X]";
        String unmarked = "[ ]";
        public void printText(String text) {
            System.out.println("________________________________________\n" +
                    text +
                    "\n________________________________________\n");
        }

        public void initialize() {
            System.out.println("Hello from\n" + logo);

            printText("Hello I'm Duke\nWhat can I do for you");
        }

        public void exit() {
            printText("Bye. hope to see you again soon!");
        }

    }

    public class TaskList {

        private ArrayList<Task> list;
        private Storage storage;
        private int count;
        public TaskList(Storage storage) {
            this.list = storage.getList();
            this.storage = storage;
            this.count = 0;
        }

        public void printTaskList() {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == null) {
                    break;
                }
                System.out.println(Integer.toString(i + 1) + ". " + list.get(i).getTask());
            }
        }

        public void setDone(int index) {
            try {
                list.get(index).setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list.get(index).getTask());
                storage.writeToFile();
            } catch (NullPointerException e) {
                System.out.println("Oops! Looks like the task number is incorrect :(");
            }
        }

        public void setUndone(int index) {
            try {
                list.get(index).setNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(list.get(index).getTask());
                storage.writeToFile();
            } catch (NullPointerException e) {
                System.out.println("Oops! Looks like the task number is incorrect :(");
            }
        }

        public void createDeadline(String item) {
            try {
                int slash = 0;
                for (int i = 0; i < item.length(); i++) {
                    if (item.charAt(i) == '/') {
                        slash = i;
                        break;
                    }
                }
                LocalDate date = LocalDate.parse(item.substring(slash + 4));
                list.add(new Deadline(item.substring(9, slash - 1), date));
                System.out.println("Got it. I've added this task:");
                System.out.println(list.get(list.size() - 1).getTask());
                storage.writeToFile();
                count = count + 1;
                System.out.println("Now you have " + Integer.toString(count) + " tasks in the list");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of a deadline has to be in the format" +
                        " deadline <task> /by <date and time>");
            } catch (DateTimeParseException e) {
                System.out.println("OOPS!! Format of the date is wrong");
            }
        }

        public void createEvent(String item) {
            try {
                int slash = 0;
                for (int i = 0; i < item.length(); i++) {
                    if (item.charAt(i) == '/') {
                        slash = i;
                        break;
                    }
                }
                list.add(new Event(item.substring(6, slash - 1), item.substring(slash + 4)));
                System.out.println("Got it. I've added this task:");
                System.out.println(list.get(list.size() - 1).getTask());
                storage.writeToFile();
                count = count + 1;
                System.out.println("Now you have " + Integer.toString(count) + " tasks in the list");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of a event has to be in the format event" +
                        " <task> /at <date and time>");
            }
        }

        public void createTask(String item) {
            try {
                list.add(new Todo(item.substring(5)));
                System.out.println("Got it. I've added this task:");
                System.out.println(list.get(list.size() - 1).getTask());
                storage.writeToFile();
                count = count + 1;
                System.out.println("Now you have " + Integer.toString(count) + " tasks in the list");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        }

        public void deleteTask(int index) {
            try {
                String text = list.get(index).getTask();
                list.remove(index);
                System.out.println("Noted. I've removed this task:");
                System.out.println(text);
                storage.writeToFile();
                count = count - 1;
                System.out.println("Now you have " + Integer.toString(count) + " tasks in the list");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Oops! Looks like the task number is incorrect :(");
            }
        }

    }

    public class Parser {
        private static Duke.TaskList taskList;

        public static void parse(String item, Duke.TaskList taskList) {
            if (item.equals("list")) {
                taskList.printTaskList();
            } else if (item.length() == 6 && item.substring(0, 4).equals("mark")) {
                int index = Integer.parseInt(item.substring(5)) - 1;
                taskList.setDone(index);
            } else if (item.length() == 8 && item.substring(0, 6).equals("unmark")) {
                int index = Integer.parseInt(item.substring(7)) - 1;
                taskList.setUndone(index);
            } else if (item.length() >= 8 && item.substring(0, 8).equals("deadline")) {
                taskList.createDeadline(item);
            } else if (item.length() >= 5 && item.substring(0, 5).equals("event")) {
                taskList.createEvent(item);
            } else if (item.length() >= 4 && item.substring(0, 4).equals("todo")) {
                taskList.createTask(item);
            } else if (item.length() >= 6 && item.substring(0, 6).equals("delete")) {
                int index = Integer.parseInt(item.substring(7)) - 1;
                taskList.deleteTask(index);
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

    }

    static abstract class Task {
        private boolean isDone = false;
        private String item;

        public void setDone() {
            this.isDone = true;
        }

        public void setNotDone() {
            this.isDone = false;
        }
        public abstract String getTask();

        public void setItem(String item) {
            this.item = item;
        }

        public String getItem() {
            return this.item;
        }

        public String getStatusIcon() {
            return (isDone ? "[X] " : "[ ] "); // mark done task with X
        }
    }

    static class Todo extends Task {
        public Todo(String item){
            this.setItem(item);
        }

        public String getTask() {
            return "[T] " + this.getStatusIcon() + this.getItem();
        }
    }

    static class Deadline extends Task {
        private LocalDate date;

        public Deadline(String item, LocalDate date) {
            this.setItem(item);
            this.date = date;
        }

        public String getTask() {
            String d = this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            return "[D] " + this.getStatusIcon() + this.getItem() + " (by: " + d + ")";
        }
    }

    static class Event extends Task {
        private String dateTime;

        public Event(String item, String dateTime) {
            this.setItem(item);
            this.dateTime = dateTime;
        }

        public String getTask() {
            return "[E] " + this.getStatusIcon() + this.getItem() + " (at: " + this.dateTime + ")";
        }

    }

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(storage);
    }

    public void run() {
        ui.initialize();
        Scanner scan = new Scanner(System.in);
        String item = scan.nextLine();
        while (!item.equals("bye")) {
            Parser.parse(item, tasks);
            item = scan.nextLine();
        }
        ui.exit();
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}
import java.io.FileWriter;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> all = new ArrayList<>();
    private static int count = 0;
    private static String filePath = "data/duke.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            loadData();
        } catch (FileNotFoundException e) {
            System.out.println("data file not found! create a text file duke.txt under folder data and try again!");
        }

        System.out.println("Hello! I am Duke");
        System.out.println("What can I do for you?");

        String s = "";
        while (true) {
            s = sc.nextLine();
            if (s.equals("bye")) {
                bye();
                break;
            } else if (s.equals("list")) {
                displayList();
            } else if (startWith(s, "mark")) {
                int index = extractIndex(s, "mark");
                //System.out.println(index);
                if (index > count || index < 1) {
                    System.out.println("index out of range");
                } else {
                    all.get(index - 1).markAsDone();
                }
            } else if (startWith(s, "unmark")) {
                int index = extractIndex(s, "unmark");
                //System.out.println(index);
                if (index > count || index < 1) {
                    System.out.println("index out of range");
                } else {
                    all.get(index - 1).markAsUndone();
                }
            } else if (startWith(s, "delete")) {
                int index =extractIndex(s, "delete");
                if (index > count || index < 1) {
                    System.out.println("index out of range");
                } else {
                    deleteRecord(index);
                }
            } else {
                store(s);
            }

            try {
                saveData();
            } catch (FileNotFoundException e) {
                System.out.println("data file not found! create a text file duke.txt under folder data and try again!");
            }
        }
    }

    private static void loadData() throws FileNotFoundException {
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
                newTask = new Deadline(des.substring(0, i - 1), des.substring(i + 5));
            } else if (type == 'E') {
                int i = des.indexOf('(');
                newTask = new Event(des.substring(0, i - 1), des.substring(i + 5));
            } else {
                newTask = null;
            }
            System.out.println(newTask);
            all.add(newTask);
            count += 1;
        }
    }

    private static void saveData() throws FileNotFoundException {
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

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static  void displayList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.printf("%s. %s\n", i + 1, all.get(i).toString());
        }
    }

    private static void deleteRecord(int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(all.get(index - 1).toString());
        count -= 1;
        System.out.printf("Now you have %s tasks in the list.\n", count);
        all.remove(index - 1);
    }

    private static void store(String s) {
        try {
            all.add(Task.of(s));
            System.out.println("Got it. I've added this task:");
            System.out.printf("  added: %s\n", all.get(count).toString());
            count += 1;
            System.out.printf("Now you have %s tasks in the list.\n", count);
        } catch (DukeException e) {
            System.out.println(e.toString());
        }


    }

    private static boolean startWith(String s, String target) {
        for (int i = 0; i < target.length(); i++) {
            if (s.charAt(i) != target.charAt(i)) return false;
        }

        try {
            int val = Integer.parseInt(s.substring(target.length() + 1));
            return true;
        } catch (NumberFormatException e) {
            //System.out.println("Input String cannot be parsed to Integer.");
            return false;
        }

    }

    private static int extractIndex(String s, String target) {
//        int i = 0;
//        try {
        int i = Integer.parseInt(s.substring(target.length() + 1));
//        } catch (Exception e) {
//            System.out.printf("Must be a number following %s\n", target);
//        }
        return i;
    }

    public static class Task {
        private static String todo = "todo";
        private static String ddl = "deadline";
        private static String event = "event";
        protected String description;
        protected boolean isDone;

        public static Task of(String s) throws DukeException {
            int i = s.indexOf(" ");
            String identifier;
            String description;
            if (i == -1) {
                throw !isIdentifierValid(s) ? new DukeException("I'm sorry, but I don't know what that means :-(")
                                           : new DukeException("The description of a " + s + " cannot be empty.");
            } else {
                identifier = s.substring(0, i);
                description = s.substring(i + 1);
            }
//            System.out.printf("%s\n", identifier);
//            System.out.printf("%s\n", s.substring(i + 1));
            if (identifier.equals(todo)) {
                return new Todo(description);
            } else if (identifier.equals(ddl)) {
                int j = s.indexOf("/by");
                return new Deadline(s.substring(ddl.length() + 1, j - 1), s.substring(j + 4));
            } else if (identifier.equals(event)) {
                int j = s.indexOf("/at");
                return new Event(s.substring(event.length() + 1, j - 1), s.substring(j + 4));
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        }

        private Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        private static boolean isIdentifierValid(String s) {
            return s.equals(todo) || s.equals(event) || s.equals(ddl);
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
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this.toString());
        }

        public void markAsUndone() {
            isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(this.toString());
        }
    }

    private static class Deadline extends Task {

        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    private static class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    private static class Event extends Task {
        private String at;

        public Event(String description, String at) {
            super(description);
            this.at = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }

    private static class DukeException extends Exception {
        public DukeException(String message) {
            super("OOPS!!" + message);
        }
    }

}

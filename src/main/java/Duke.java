import java.util.Scanner;
import java.util.ArrayList; 

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {

    private ArrayList<Task> list;

    public Duke() {
        this.list = new ArrayList<>();
    }

    public enum Commands {
        bye, list, mark, unmark, todo, deadline, event
    }

    private abstract class Task {
        protected String content;
        protected Boolean status;

        protected Task(String content) {
            this.content = content;
            this.status = false;
        }

        public void markComplete() {
            this.status = true;
        }

        public void unmarkComplete() {
            this.status = false;
        }

        @Override
        public String toString() {
            if (status) {
                return String.format("[x] %s", content);
            } else {
                return String.format("[ ] %s", content);
            }
        }

        public abstract String toFileData();
    }

    private class ToDo extends Task {
        public ToDo(String content) {
            super(content);
        }

        @Override
        public String toString() {
            return String.format("[T]%s", super.toString());
        }

        @Override
        public String toFileData() {
            return String.format("T | %d | %s", this.status ? 1 : 0, this.content);
        }
    }

    private class Deadline extends Task {
        private LocalDate date;
        private LocalTime time;

        public Deadline(String content, LocalDate date, LocalTime time) {
            super(content);
            this.date = date;
            this.time = time;
        }

        @Override 
        public String toString() {
            return String.format("[D]%s (by: %s %s)", super.toString(), 
                    this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")), 
                            this.time.format(DateTimeFormatter.ofPattern("hh:mm a")));
        }

        @Override
        public String toFileData() {
            return String.format("D | %d | %s | %s %s", this.status ? 1 : 0, this.content, 
                    this.date.toString(), this.time.toString());
        }
    }

    private class Event extends Task {
        private LocalDate date;
        private LocalTime time;

        public Event(String content, LocalDate date, LocalTime time) {
            super(content);
            this.date = date;
            this.time = time;
        }

        @Override 
        public String toString() {
            return String.format("[E]%s (at: %s %s)", super.toString(), 
                    this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")), 
                            this.time.format(DateTimeFormatter.ofPattern("hh:mm a")));
        }

        @Override
        public String toFileData() {
            return String.format("D | %d | %s | %s %s", this.status ? 1 : 0, this.content, 
                    this.date.toString(), this.time.toString());
        }
    }

    private class DukeException extends Exception {
        public DukeException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greeting();
        String path = "data/duke.txt";
        new File("data").mkdir();
        try {
            duke.loadData(path);
            System.out.println("\tYour previous tasks have been loaded!");
        } catch (FileNotFoundException e) {
            System.out.println("\tHello new user!");
        }
        Boolean quit = false;
        Scanner scanner = new Scanner(System.in);
        while (!quit) {
            System.out.println("Enter a command below:");
            String input = scanner.nextLine();
            try {
                quit = duke.FeedDuke(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                quit = false;
            }
        }
        try {
            duke.writeData(path);
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("\t☹ OOPS!!! I'm sorry, saving of tasks has failed :-(");
        }
        scanner.close();
        duke.goodbye();
    }

    public Boolean FeedDuke(String input) throws DukeException {
        if (input.contains("bye")) {
            return true;
            
        } else if (input.contains("list")) {
            this.printList();
            return false;

        } else if (input.contains("unmark")) {
            int index = Integer.parseInt(input.replace("unmark", "").trim()) - 1;
            this.unmarkTask(index);
            return false;

        } else if (input.contains("mark")) {
            int index = Integer.parseInt(input.replace("mark", "").trim()) - 1;
            this.markTask(index);
            return false;

        } else if (input.contains("todo")) {
            try {
                this.addToDo(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            return false;
        
        } else if (input.contains("deadline")) {
            try {
                this.addDeadline(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            return false;

        } else if (input.contains("event")) {
            try {
                this.addEvent(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            return false;
        } else if (input.contains("delete")) {
            int index = Integer.parseInt(input.replace("delete", "").trim()) - 1;
            this.deleteTask(index);
            return false;
        }
        throw new DukeException("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void greeting() {
        String logo = "\t\t\t  ____        _        \n"
        + "\t\t\t |  _ \\ _   _| | _____ \n"
        + "\t\t\t | | | | | | | |/ / _ \\\n"
        + "\t\t\t | |_| | |_| |   <  __/\n"
        + "\t\t\t |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke\n \tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }

    public void goodbye() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    public void unmarkTask(int index) {
        try {
            this.list.get(index).unmarkComplete();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t☹ OOPS!!! Task number to be unmarked does not exist.");
            return;
        }
        System.out.println("\t____________________________________________________________");
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t" + list.get(index).toString());
        System.out.println("\t____________________________________________________________");
    }

    public void markTask(int index) {
        try {
            this.list.get(index).markComplete();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t☹ OOPS!!! Task number to be marked does not exist.");
            return;
        }
        System.out.println("\t____________________________________________________________");
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + list.get(index).toString());
        System.out.println("\t____________________________________________________________");
    }

    public void printList() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(String.format("\t%d.%s", i + 1, list.get(i).toString()));
        }
        System.out.println("\t____________________________________________________________");
    }

    public void addToDo(String input) throws DukeException{
        String content = input.replace("todo", "").trim();
        if (content.length() == 0) {
            throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty.");
        }
        ToDo task = new ToDo(content);
        this.list.add(task);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tGot it. I've added this task:");
        System.out.println(String.format("\t  %s", task.toString()));
        System.out.println(String.format("\tNow you have %d tasks in the list.", this.list.size()));
        System.out.println("\t____________________________________________________________");
    }

    public void addDeadline(String input) throws DukeException{
        if (!input.contains(" by ")) {
            throw new DukeException("\t☹ OOPS!!! Formatting of deadline is incorrect.");
        }
        String[] split = input.replace("deadline", "").split(" by ");
        String content = split[0].trim();
        if (content.length() == 0 || split[1].length() == 0) {
            throw new DukeException("\t☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] dateTimeSplit = split[1].trim().split(" ");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        try {
            date = LocalDate.parse(dateTimeSplit[0]);
            time = LocalTime.parse(dateTimeSplit[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException("\t☹ OOPS!!! Formatting of date and time is incorrect.");
        }
        Deadline task = new Deadline(content, date, time);
        this.list.add(task);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tGot it. I've added this task:");
        System.out.println(String.format("\t  %s", task.toString()));
        System.out.println(String.format("\tNow you have %d tasks in the list.", this.list.size()));
        System.out.println("\t____________________________________________________________");
    }

    public void addEvent(String input) throws DukeException{
        if (!input.contains(" at ")) {
            throw new DukeException("\t☹ OOPS!!! Formatting of event is incorrect.");
        }
        String[] split = input.replace("event", "").split(" at ");
        String content = split[0].trim();
        if (content.length() == 0 || split[1].length() == 0) {
            throw new DukeException("\t☹ OOPS!!! The description of an event cannot be empty.");
        }
        String[] dateTimeSplit = split[1].trim().split(" ");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        try {
            date = LocalDate.parse(dateTimeSplit[0]);
            time = LocalTime.parse(dateTimeSplit[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException("\t☹ OOPS!!! Formatting of date and time is incorrect.");
        }
        Event task = new Event(content, date, time);
        this.list.add(task);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tGot it. I've added this task:");
        System.out.println(String.format("\t  %s", task.toString()));
        System.out.println(String.format("\tNow you have %d tasks in the list.", this.list.size()));
        System.out.println("\t____________________________________________________________");
    }

    public void deleteTask(int index) {
        Task task = null;
        try {
            task = this.list.get(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t☹ OOPS!!! Task number to be deleted does not exist.");
            return;
        }
        this.list.remove(index);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tNoted. I've removed this task:");
        System.out.println(String.format("\t  %s", task.toString()));
        System.out.println(String.format("\tNow you have %d tasks in the list.", this.list.size()));
        System.out.println("\t____________________________________________________________");
    }

    public void loadData(String path) throws FileNotFoundException {
        File f = new File(path);
        Scanner scanner = new Scanner(f);

        while (scanner.hasNext()) {
            String task = scanner.nextLine();
            if (task.charAt(0) == 'T') {
                Boolean status = task.charAt(4) == '1';
                String content = task.substring(8).trim();
                ToDo toDo = new ToDo(content);
                if (status) {
                    toDo.markComplete();
                }
                this.list.add(toDo);

            } else if (task.charAt(0) == 'D') {
                Boolean status = task.charAt(4) == '1';
                String[] split = task.substring(8).split(" \\| ");
                String content = split[0].trim();
                String[] dateTimeSplit = split[1].trim().split(" ");
                LocalDate date = LocalDate.parse(dateTimeSplit[0]);
                LocalTime time = LocalTime.parse(dateTimeSplit[1]);
                Deadline deadline = new Deadline(content, date, time);
                if (status) {
                    deadline.markComplete();
                }
                this.list.add(deadline);

            } else if (task.charAt(0) == 'E') {
                Boolean status = task.charAt(4) == '1';
                String[] split = task.substring(8).split(" \\| ");
                String content = split[0].trim();
                String[] dateTimeSplit = split[1].trim().split(" ");
                LocalDate date = LocalDate.parse(dateTimeSplit[0]);
                LocalTime time = LocalTime.parse(dateTimeSplit[1]);
                Event event = new Event(content, date, time);
                if (status) {
                    event.markComplete();
                }
                this.list.add(event);
            }
        }
        scanner.close();
    }

    public void writeData(String path) throws IOException {
        FileWriter clearFw = new FileWriter(path, false);
        clearFw.write("");
        clearFw.close();
        FileWriter fw = new FileWriter(path, true);
        for (int i = 0; i < this.list.size(); i++) {
            fw.write(list.get(i).toFileData());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static String divider() {
        return "--------------------";
    }

    private static void setup() {
//        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        System.out.println("Hello from " + "DUKE");
        System.out.println("What can I do for you?");
        System.out.println(divider());
    }

    private static String checkbox(Boolean mark) {
        return mark ? "[x]" : "[ ]";
    }

    static class EmptyInputException extends RuntimeException {
        public EmptyInputException() {
            super("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    static class InvalidInputException extends RuntimeException {
        public InvalidInputException() {
            super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void main(String[] args) {
        class Todo {
            class Task {
                protected String description;
                protected boolean isDone;

                public Task(String description) {
                    this.description = description;
                    this.isDone = false;
                }

                public boolean getIsDone() {
                    return isDone;
                }

                public String getDescription() {
                    return description;
                }

                public void mark() {
                    this.isDone = true;
                }

                public void unmark() {
                    this.isDone = false;
                }

                public String getSavedString() {
                    return String.format("T | %s | %s\n",this.isDone, this.description);
                }

                @Override
                public String toString() {
                    return String.format("[T] %s %s", checkbox(this.getIsDone()), this.getDescription());
                }
            }

            class Deadline extends Task {
                String deadline;
                LocalDate date;

                public Deadline(String description, String deadline) {
                    super(description);
                    this.deadline = deadline;
                }

                public Deadline(String description, LocalDate date) {
                    super(description);
                    this.date = date;
                }

                @Override
                public String getSavedString() {
                    return String.format("D | %s | %s | %s\n",this.getIsDone(), this.getDescription(), this.deadline);
                }

                @Override
                public String toString() {
                    return String.format("[D] %s %s (by: %s)", checkbox(this.getIsDone()), this.getDescription(), this.deadline);
                }
            }

            class Event extends Task {
                String time;

                public Event(String description, String time) {
                    super(description);
                    this.time = time;
                }

                @Override
                public String getSavedString() {
                    return String.format("E | %s | %s | %s\n",this.getIsDone(), this.getDescription(), this.time);
                }

                @Override
                public String toString() {
                    return String.format("[E] %s %s (at: %s)", checkbox(this.getIsDone()), this.getDescription(), this.time);
                }
            }

            int pointer = 0;
            protected ArrayList<Task> list;

            public Todo(int length) {
                this.list = new ArrayList<>(length);
            }

            public Task addTask(String description) {
                Task val = new Task(description);
                this.list.add(val);
//                this.list[pointer] = new Task(description);
                pointer++;
                return val;
            }

            public Deadline addDeadline(String desc, String deadline) {
                Deadline val = new Deadline(desc, deadline);
                this.list.add(val);
//                this.list[pointer] = new Deadline(desc, deadline);
                pointer++;
                return val;
            }

            public Deadline addDeadline(String desc, LocalDate date) {
                Deadline val = new Deadline(desc, date);
                this.list.add(val);
                pointer++;
                return val;
            }

            public Event addEvent(String desc, String time) {
                Event val = new Event(desc, time);
                this.list.add(val);
//                this.list[pointer] = new Event(desc, time);
                pointer++;
                return val;
            }

            public void load() {
                try {
                    File file = new File("./data/duke.txt");
                    file.createNewFile();

                    BufferedReader reader = new BufferedReader(new FileReader("./data/duke.txt"));
                    String line = reader.readLine();
                    while (line != null) {
//                        System.out.println(line);

                        String[] raw = line.split(" \\| ");
//                        System.out.println(String.format("%s, %s, %s", raw[0], raw[1], raw[2]));
                        Task curr = null;
                        switch(raw[0]) {
                            case "T":
                                curr = this.addTask(raw[2]);
                                break;
                            case "D":
                                curr = this.addDeadline(raw[2], raw[3]);
                                break;
                            case "E":
                                curr = this.addEvent(raw[2], raw[3]);
                                break;
                        }
                        if (raw[1].equals("true") && curr != null) {
                            curr.mark();
                        }
                        line = reader.readLine();
                    }

                    printData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public void save() {
                try {
                    File file = new File("./data/duke.txt");
                    file.createNewFile();
                    FileWriter writer = new FileWriter("./data/duke.txt", false);
//                    System.out.println(this.saveData());
                    writer.write(this.saveData());
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public int getLength() {
                return this.list.size();
            }

            protected Task get(int index) {
                return this.list.get(index);
            }

            public void delete(int index) {
                this.list.remove(index);
            }

            public void mark(int index) {
                this.get(index).mark();
            }

            public void unmark(int index) {
                this.get(index).unmark();
            }

            private String saveData() {
                String result = "";
                for (Task task : this.list) {
                    result += String.format("%s", task.getSavedString());
                }
                return result;
            }

            private void printData() {
                this.list.forEach(x -> System.out.println(String.format("%s. %s", this.list.indexOf(x) + 1, x)));
            }
        }

        Scanner scanner = new Scanner(System.in);
        Todo todolist = new Todo(100);
        todolist.load();
        setup();

        while (true) {
            try {
                String line = scanner.nextLine();
                System.out.println(divider());

                switch (line) {
                    case "":
                        throw new EmptyInputException();

                    case "bye":
                        System.out.println("See you again!");
                        return;

                    case "list":
                        todolist.printData();
                        break;

                    case "save":
                        todolist.save();
                        break;

                    default:
//                  We can't use switch statements since we want regex matching
                        if (line.matches("mark \\d+")) {
                            int index = Integer.parseInt(line.split(" ")[1]);
                            if (index > todolist.getLength()) {
                                break;
                            }

                            todolist.mark(index - 1);
                            todolist.printData();
                            break;
                        }

                        if (line.matches("unmark \\d+")) {
                            int index = Integer.parseInt(line.split(" ")[1]);
                            if (index > todolist.getLength()) {
                                break;
                            }

                            todolist.unmark(index - 1);
                            todolist.printData();
                            break;
                        }

                        if (line.matches("deadline .* by .*")) {
                            String[] res = line.substring(9).split(" by ");
                            String desc = res[0];
                            String deadline = res[1];

                            todolist.addDeadline(desc, deadline);
                            System.out.println(String.format("Added: %s (by: %s)", desc, deadline));
                            break;
                        }

                        if (line.matches("deadline .* /by .*")) {
                            String[] res = line.substring(9).split(" /by ");
                            String desc = res[0];
                            LocalDate date = LocalDate.parse(res[1]);

                            todolist.addDeadline(desc, date);
                            System.out.println(String.format("Added: %s (by: %s)", desc, date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))));
                            break;
                        }

                        if (line.matches("event .* at .*")) {
                            String[] res = line.substring(6).split(" at ");
                            String desc = res[0];
                            String time = res[1];

                            todolist.addEvent(desc, time);
                            System.out.println(String.format("Added: %s (at: %s)", desc, time));
                            break;
                        }

                        if (line.matches("todo .*")) {
                            line = line.substring(5);
                            todolist.addTask(line);
                            System.out.println("Added: " + line);
                            break;
                        }

                        if (line.matches("delete \\d+")) {
                            int index = Integer.parseInt(line.split(" ")[1]);
                            if (index > todolist.getLength()) {
                                break;
                            }
                            todolist.delete(index - 1);
                            System.out.println("Noted. I've removed this task");
                            System.out.println(String.format("You now have %s tasks in your list.", todolist.getLength()));
                            break;
                        }

                        throw new InvalidInputException();
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}

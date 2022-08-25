import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static class Task {
        protected String description;
        protected boolean isDone;
        protected LocalDate date;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }


        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon()+"]" +" "+ this.description;
        }

        public void setStatus(boolean value) {
            this.isDone = value;
        }
    }

    public static class DukeException extends Exception {
        public DukeException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class Todo extends Task {
        public Todo (String description) {
            super(description);
        }


        @Override
        public String toString() {
            return "[T]" + super.toString();
        }

    }

    public static class Deadline extends Task {

        protected String by;
        protected LocalDate by2;
        protected String time;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }
        public Deadline(String description, LocalDate by2) {
            super(description);
            this.by2 = by2;

        }
        @Override
        public String toString() {
            if (this.by != null) {
                return "[D]" + super.toString() + " (by: " + by + ")";
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                return "[D]" + super.toString() + " (by: " + by2.format(formatter) + ")";
            }
        }
    }

    public static class Event extends Task {

        protected String at;
        protected LocalDate at2;



        public Event(String description, String at) {
            super(description);
            this.at = at;
        }

        public Event(String description, LocalDate at2) {
            super(description);
            this.at2 = at2;
        }

        @Override
        public String toString() {

            if (this.at != null) {
                return "[E]" + super.toString() + " (at: " + at + ")";
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                return "[E]" + super.toString() + " (at: " + at2.format(formatter) + ")";
            }
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    private static void deleteFromFile(String filePath, ArrayList<Task> iterate) throws IOException {
        FileWriter fw = new FileWriter("temp.txt",true);
        File f = new File("temp.txt");
        for (int i = 0; i< iterate.size(); i++) {
            fw.write(iterate.get(i).toString()+ System.lineSeparator());
        }
        fw.close();
        Files.delete(Paths.get(filePath));
        File g = new File(filePath);
        f.renameTo(g);

    }
    public static void main(String[] args) {

        System.out.println("What can I do for you?");
        ArrayList<Task> ls = new ArrayList<>();
        ArrayList<String> tested = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String line = "";
        while (true) {
            try {
                line = sc.nextLine();

                if (line.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (line.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < ls.size(); i++) {
                        System.out.println(i + 1 + "." + " " + ls.get(i).toString());
                    }

                } else if (line.contains("unmark")) {
                    if (line.equals("unmark")) {
                        throw new DukeException("☹ OOPS!!! The description of a unmark cannot be empty.");
                    } else {
                        int num = Integer.parseInt(line.substring(7));
                        ls.get(num - 1).setStatus(false);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(ls.get(num - 1).toString());
                    }
                } else if (line.contains("mark")) {
                    if (line.equals("mark")) {
                        throw new DukeException("☹ OOPS!!! The description of a mark cannot be empty.");
                    } else {
                        int num = Integer.parseInt(line.substring(5));
                        ls.get(num - 1).setStatus(true);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(ls.get(num - 1).toString());

                    }
                } else if (line.contains("todo")) {
                    if (line.equals("todo")) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        String d1 = line.substring(5);
                        Todo test = new Todo(d1);
                        ls.add(test);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(test.toString());
                        String file2 = "duke.txt";
                        try {
                            writeToFile(file2, test.toString() + System.lineSeparator());
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                        System.out.println("Now you have" + " " + ls.size() + " " + "tasks in the list.");
                    }
                } else if (line.contains("deadline")) {
                    if (line.equals("deadline")) {
                        throw new DukeException("☹ OOPS!!! The description of a unmark cannot be empty.");
                    } else {
                        try {
                            String description = line.substring(9, line.indexOf("/")-1);
                            String var = line.substring(line.indexOf("/")+4,line.length());
                            LocalDate d1 = LocalDate.parse(var);
                            Deadline test = new Deadline(description, d1);
                            ls.add(test);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(test.toString());
                            String file2 = "duke.txt";
                            try {
                                writeToFile(file2, test.toString() + System.lineSeparator());
                            } catch (IOException e) {
                                System.out.println("Something went wrong: " + e.getMessage());
                            }
                            System.out.println("Now you have" + " " + ls.size() + " " + "tasks in the list.");
                        } catch (DateTimeParseException e) {
                            String description = line.substring(9, line.indexOf("/") - 1);
                            String by = line.substring(line.indexOf("/") + 4, line.length());
                            String time = line.substring(line.length()-4, line.length());
                            Deadline test = new Deadline(description, by);
                            ls.add(test);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(test.toString());
                            String file2 = "duke.txt";
                            try {
                                writeToFile(file2, test.toString() + System.lineSeparator());
                            } catch (IOException f) {
                                System.out.println("Something went wrong: " + f.getMessage());
                            }
                            System.out.println("Now you have" + " " + ls.size() + " " + "tasks in the list.");
                        }

                    }
                } else if (line.contains("event")) {
                    if (line.equals("event")) {
                        throw new DukeException("☹ OOPS!!! The description of a unmark cannot be empty.");

                    } else {
                        try {
                            String description = line.substring(6, line.indexOf("/")-1);
                            LocalDate d1 = LocalDate.parse(line.substring(line.indexOf("/")+4));
                            Event test = new Event(description, d1);
                            ls.add(test);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(test.toString());
                            String file2 = "duke.txt";
                            try {
                                writeToFile(file2, test.toString() + System.lineSeparator());
                            } catch (IOException e) {
                                System.out.println("Something went wrong: " + e.getMessage());
                            }
                            System.out.println("Now you have" + " " + ls.size() + " " + "tasks in the list.");
                        } catch (DateTimeParseException e) {
                            String description = line.substring(6, line.indexOf("/") - 1);
                            String at = line.substring(line.indexOf("/") + 4);
                            Event test = new Event(description, at);
                            ls.add(test);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(test.toString());
                            String file2 = "duke.txt";
                            try {
                                writeToFile(file2, test.toString() + System.lineSeparator());
                            } catch (IOException f) {
                                System.out.println("Something went wrong: " + f.getMessage());
                            }
                            System.out.println("Now you have" + " " + ls.size() + " " + "tasks in the list.");
                        }
                    }
                } else if (line.contains("delete")) {
                    if (line.equals("delete")) {
                        throw new DukeException("☹ OOPS!!! The description of a delete cannot be empty.");

                    } else {
                        int removal = Integer.parseInt(line.substring(7));
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(ls.get(removal - 1).toString());
                        String file2 = "duke.txt";
                        ls.remove(removal - 1);
                        try {
                            deleteFromFile(file2, ls);
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                        System.out.println("Now you have" + " " + ls.size() + " " + "tasks in the list.");

                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-()");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }


        }


    }


}

import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static class Task {
        protected String description;
        protected boolean isDone;

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

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public static class Event extends Task {

        protected String at;

        public Event(String description, String at) {
            super(description);
            this.at = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
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
                        Todo test = new Todo(line.substring(5));
                        ls.add(test);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(test.toString());
                        System.out.println("Now you have" + " " + ls.size() + " " + "tasks in the list.");

                    }
                } else if (line.contains("deadline")) {
                    if (line.equals("deadline")) {
                        throw new DukeException("☹ OOPS!!! The description of a unmark cannot be empty.");
                    } else {
                        String description = line.substring(9, line.indexOf("/")-1);
                        String by = line.substring(line.indexOf("/")+4,line.length());
                        Deadline test = new Deadline(description, by);
                        ls.add(test);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(test.toString());
                        System.out.println("Now you have" + " " + ls.size() + " " + "tasks in the list.");

                    }
                } else if (line.contains("event")) {
                    if (line.equals("event")) {
                        throw new DukeException("☹ OOPS!!! The description of a unmark cannot be empty.");

                    } else {
                        String description = line.substring(6, line.indexOf("/")-1);
                        String at = line.substring(line.indexOf("/")+4);
                        Event test = new Event(description, at);
                        ls.add(test);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(test.toString());
                        System.out.println("Now you have" + " " + ls.size() + " " + "tasks in the list.");

                    }
                } else if (line.contains("delete")) {
                    if (line.equals("delete")) {
                        throw new DukeException("☹ OOPS!!! The description of a delete cannot be empty.");

                    } else {
                        int removal = Integer.parseInt(line.substring(7));
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(ls.get(removal - 1).toString());
                        ls.remove(removal - 1);
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

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws Exception {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        class DukeException extends Exception {
            public DukeException(String error) {
                super(error);
            }
        }

        class Task {
            protected String description;
            protected boolean isDone;

            public Task(String description) {
                this.description = description;
                this.isDone = false;
            }

            public String getStatusIcon() {
                return (isDone ? "[X]" : "[ ]");
            }

            public void mark() {
                this.isDone = true;
            }

            public void unmark() {
                this.isDone = false;
            }

            public String toString() {
                return this.getStatusIcon() + " " + this.description;
            }
        }

        class Deadline extends Task {
            protected String by;

            public Deadline(String description, String by) {
                super(description);
                this.by = by;
            }

            @Override
            public String toString() {
                return "[D]" + super.toString() + "(by:" + by + ")";
            }

        }

        class Todo extends Task {
            protected String by;

            public Todo(String description) {
                super(description);
            }

            @Override
            public String toString() {
                return "[T]" + super.toString();
            }
        }

        class Event extends Task {
            protected String at;

            public Event(String description, String at) {
                super(description);
                this.at = at;
            }

            @Override
            public String toString() {
                return "[E]" + super.toString() + "(at:" + at + ")";
            }
        }

        ArrayList<Task> inputList = new ArrayList<>();

        while(true) {

            Scanner input = new Scanner(System.in);
            String s = input.nextLine();

            if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon.");
                break;
            }

            String[] word = s.split(" ");

            if (s.equals("list")) {
                System.out.println("Here are the tasks in your list:\n");
                for (Task item : inputList) {
                    System.out.println(inputList.indexOf(item) + 1 + "." + item);
                }

            } else if (word[0].equals("mark")) {
                System.out.println("Nice! I've marked this task as done:");
                Integer num = Integer.parseInt(word[1]);
                inputList.get(num - 1).mark();
                System.out.println(inputList.get(num - 1));

            } else if (word[0].equals("unmark")) {
                System.out.println("Nice! I've marked this task as done:");
                Integer num = Integer.parseInt(word[1]);
                inputList.get(num - 1).unmark();
                System.out.println(inputList.get(num - 1));


            } else if (word[0].equals("todo")) {
                if (s.endsWith("todo")) {
                    throw new DukeException("Ooops, the description of todo cannot be empty!");
                }

                String substringtd = s.replaceAll("todo ", "");
                Todo td = new Todo(substringtd);
                System.out.println("Got it. I've added this task:");
                System.out.println(td);
                inputList.add(td);
                System.out.println("Now you have " + inputList.size() + " tasks in the list.");

            } else if (word[0].equals("deadline")) {
                if (s.endsWith("deadline")) {
                    throw new DukeException("Ooops, the description of deadline cannot be empty!");
                }

                String[] phrase = s.split("/by");
                String substringdl1 = phrase[0].replaceAll("deadline", "");
                String substringdl2 = phrase[1];
                Deadline dl = new Deadline(substringdl1, substringdl2);
                System.out.println("Got it. I've added this task:");
                System.out.println(dl);
                inputList.add(dl);
                System.out.println("Now you have " + inputList.size() + " tasks in the list.");


            } else if (word[0].equals("event")) {
                if (s.endsWith("event")) {
                    throw new DukeException("Ooops, the description of event cannot be empty!");
                }

                String[] phrase = s.split("/at");
                String substringdl1 = phrase[0].replaceAll("event", "");
                String substringdl2 = phrase[1];
                Event ev = new Event(substringdl1, substringdl2);
                System.out.println("Got it. I've added this task:");
                System.out.println(ev);
                inputList.add(ev);
                System.out.println("Now you have " + inputList.size() + " tasks in the list.");

            } else if (word[0].equals("delete")) {
                Integer num = Integer.parseInt(word[1]);

                System.out.println("Noted I have removed this task:");
                System.out.println(inputList.get(num - 1));
                Integer newSize = inputList.size() - 1;
                inputList.remove(num-1);
                System.out.println("Now you have " + newSize + " tasks in the list.");

            } else {
                throw new DukeException("Oooops, sorry I don't know what you are talking about :(");
            }

        }

    }
}

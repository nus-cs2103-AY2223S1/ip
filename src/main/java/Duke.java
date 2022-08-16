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
            return this.description;
        }

        public void setStatus(boolean value) {
            this.isDone = value;
        }
    }

    public static class Todo extends Task {
        public Todo (String description) {
            super(description);
        }

    }

    public static class Deadline extends Task {
        public Deadline (String description) {
            super(description);
        }


        @Override
        public String toString() {
            return this.description.replace("/","(") + ")";
        }

    }

    public static class Event extends Task {
        public Event (String description) {
            super(description);
        }


        @Override
        public String toString() {
            return this.description.replace("/","(").replace("at", "at:")+")";
        }

    }
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        ArrayList<Task> ls = new ArrayList<>();
        ArrayList<String> tested = new ArrayList<>();
        while (true) {
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            else if(line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < ls.size(); i++) {
                    if (ls.get(i) instanceof Todo) {
                        System.out.println(i + 1 + "." + " " + "[T]" + "[" + ls.get(i).getStatusIcon() + "]" + " " + ls.get(i).toString());
                    } else if(ls.get(i) instanceof Deadline) {
                        System.out.println(i + 1 + "." + " " + "[D]" + "[" + ls.get(i).getStatusIcon() + "]" + " " + ls.get(i).toString());
                    } else if (ls.get(i) instanceof Event) {
                        System.out.println(i + 1 + "." + " " + "[E]" + "[" + ls.get(i).getStatusIcon() + "]" + " " + ls.get(i).toString());
                    } else {
                        System.out.println(i + 1 + "." + " " + "[" + ls.get(i).getStatusIcon() + "]" + " " + ls.get(i).toString());
                    }
                }
            }

            else if(line.contains("unmark")) {
                if (line.equals("unmark")) {
                    System.out.println("☹ OOPS!!! The description of a unmark cannot be empty.");
                } else {
                    int num = Integer.parseInt(line.substring(7));
                    ls.get(num - 1).setStatus(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[" + ls.get(num - 1).getStatusIcon() + "]" + " " + ls.get(num - 1).toString());
                }
            }

            else if(line.contains("mark")) {
                if (line.equals("mark")) {
                    System.out.println("☹ OOPS!!! The description of a mark cannot be empty.");
                } else {
                    int num = Integer.parseInt(line.substring(5));
                    ls.get(num - 1).setStatus(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + ls.get(num - 1).getStatusIcon() + "]" + " " + ls.get(num - 1).toString());
                }
            }

            else if (line.contains("todo")) {
                if (line.equals("todo")) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                } else {
                    Todo test = new Todo(line.substring(5));
                    ls.add(test);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("[T][ ]" + " " + line.substring(5));
                    System.out.println("Now you have" + " " + ls.size() + " " + "tasks in the list.");
                }
            }

            else if (line.contains("deadline")) {
                if (line.equals("deadline")) {
                    System.out.println("☹ OOPS!!! The description of a unmark cannot be empty.");
                } else {
                    Deadline test = new Deadline(line.substring(9));
                    ls.add(test);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("[D][ ]" + " " + test.toString());
                    System.out.println("Now you have" + " " + ls.size() + " " + "tasks in the list.");
                }
            }

            else if (line.contains("event")) {
                if (line.equals("event")) {
                    System.out.println("☹ OOPS!!! The description of a unmark cannot be empty.");

                } else {
                    Event test = new Event(line.substring(6));
                    ls.add(test);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("[E][ ]" + " " + test.toString());
                    System.out.println("Now you have" + " " + ls.size() + " " + "tasks in the list.");
                }
            }

            else if (line.contains("delete")) {
                if (line.equals("delete")) {
                    System.out.println("☹ OOPS!!! The description of a delete cannot be empty.");

                } else {
                    int removal = Integer.parseInt(line.substring(7));

                    System.out.println("Noted. I've removed this task:");
                    if (ls.get(removal-1) instanceof Todo) {
                        System.out.println("[T][ ]" +" "+ ls.get(removal-1).toString());
                    } else if (ls.get(removal-1) instanceof Deadline) {
                        System.out.println("[D][ ]" + " "+ls.get(removal-1).toString());
                    } else if (ls.get(removal-1) instanceof Event) {
                        System.out.println("[E][ ]" + " "+ls.get(removal-1).toString());
                    } else {
                        System.out.println(ls.get(removal-1).toString());
                    }
                    ls.remove(removal-1);
                    System.out.println("Now you have" + " " + ls.size() + " " + "tasks in the list.");
                }
            }

            else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-()");
            }


        }


    }


}

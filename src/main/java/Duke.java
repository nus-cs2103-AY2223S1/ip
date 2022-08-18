import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        abstract class Task {
            private boolean isDone = false;
            private String item;

            public void setDone() {
                this.isDone = true;
            }

            public void setNotDone() {
                this.isDone = false;
            }

            public String getStatusIcon() {
                return (isDone ? "[X] " : "[ ] "); // mark done task with X
            }

            public abstract String getTask();

            public void setItem(String item) {
                this.item = item;
            }

            public String getItem() {
                return this.item;
            }
        }

        class Todo extends Task {
            public Todo(String item){
                this.setItem(item);
            }

            public String getTask() {
                return "[T] " + this.getStatusIcon() + this.getItem();
            }
        }

        class Deadline extends Task {
            private String dateTime;

            public Deadline(String item, String dateTime) {
                this.setItem(item);
                this.dateTime = dateTime;
            }

            public String getTask() {
                return "[D] " + this.getStatusIcon() + this.getItem() + " (by: " + this.dateTime + ")";
            }

        }

        class Event extends Task {
            private String dateTime;

            public Event(String item, String dateTime) {
                this.setItem(item);
                this.dateTime = dateTime;
            }

            public String getTask() {
                return "[E] " + this.getStatusIcon() + this.getItem() + " (at: " + this.dateTime + ")";
            }

        }

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Task[] list = new Task[500];
        int count = 0;
        String marked = "[X]";
        String unmarked = "[ ]";

        System.out.println("Hello from\n" + logo);

        Scanner scan = new Scanner(System.in);
        System.out.println("Hello I'm Duke\nWhat can I do for you");
        System.out.print("You: ");
        String item = scan.nextLine();

        while (!item.equals("bye")) {
            if (item.equals("list")) {
                for(int i = 0; i < list.length; i++){
                    if (list[i] == null) {
                        break;
                    }
                    System.out.println(Integer.toString(i + 1) + ". " + list[i].getTask());
                }
            }
            else if(item.length() == 6 &&  item.substring(0,4).equals("mark")) {
                try {
                    int index = Integer.parseInt(item.substring(5)) - 1;
                    list[index].setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list[index].getTask());
                } catch (NullPointerException e){
                    System.out.println("Oops! Looks like the task number is incorrect :(");
                }
            }
            else if(item.length() == 8 &&  item.substring(0,6).equals("unmark")) {
                try {
                int index = Integer.parseInt(item.substring(7)) - 1;
                list[index].setNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(list[index].getTask());
                } catch (NullPointerException e){
                    System.out.println("Oops! Looks like the task number is incorrect :(");
                }
            }
            else if(item.length() >= 8 &&  item.substring(0,8).equals("deadline")) {
                try {
                    int slash = 0;
                    for (int i = 0; i < item.length(); i++) {
                        if (item.charAt(i) == '/') {
                            slash = i;
                            break;
                        }
                    }
                    list[count] = new Deadline(item.substring(9, slash - 1), item.substring(slash + 4));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list[count].getTask());
                    System.out.println("Now you have " + Integer.toString(count + 1) + " tasks in the list");
                    count = count + 1;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a deadline has to be in the format" +
                            " deadline <task> /by <date and time>");
                }
            }
            else if(item.length() >= 5 &&  item.substring(0,5).equals("event")) {
                try {
                    int slash = 0;
                    for (int i = 0; i < item.length(); i++) {
                        if (item.charAt(i) == '/') {
                            slash = i;
                            break;
                        }
                    }
                    list[count] = new Event(item.substring(6, slash - 1), item.substring(slash + 4));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list[count].getTask());
                    System.out.println("Now you have " + Integer.toString(count + 1) + " tasks in the list");
                    count = count + 1;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a event has to be in the format event" +
                            " <task> /at <date and time>");
                }
            }
            else if(item.length() >= 4 &&  item.substring(0,4).equals("todo")) {
                try {
                    list[count] = new Todo(item.substring(5));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list[count].getTask());
                    System.out.println("Now you have " + Integer.toString(count + 1) + " tasks in the list");
                    count = count + 1;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            }
            else{
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.print("You: ");
            item = scan.nextLine();
        }
        System.out.println("Duke: Bye. hope to see you again soon!");
    }
}

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
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


    static class Todo extends Task {
        public Todo(String item){
            this.setItem(item);
        }

        public String getTask() {
            return "[T] " + this.getStatusIcon() + this.getItem();
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
    private static void writeToFile(ArrayList<Task> arr){
        try {
            new FileWriter("data/duke.txt", false).close();
            for (int i = 0; i < arr.size(); i++) {
                FileWriter fw = new FileWriter("data/duke.txt", true); // create a FileWriter in append mode
                fw.write(arr.get(i).getTask());
                fw.write(System.lineSeparator());
                fw.close();
            }
        } catch(IOException e) {
            System.out.println("File not found");
        }
    }
    public static void main(String[] args) throws IOException {


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        ArrayList<Task> list = new ArrayList<>();

        int count = 0;
        String marked = "[X]";
        String unmarked = "[ ]";

        System.out.println("Hello from\n" + logo);

        Scanner scan = new Scanner(System.in);
        System.out.println("Hello I'm Duke\nWhat can I do for you");
        String item = scan.nextLine();

        while (!item.equals("bye")) {
            if (item.equals("list")) {
                for(int i = 0; i < list.size(); i++){
                    if (list.get(i) == null) {
                        break;
                    }
                    System.out.println(Integer.toString(i + 1) + ". " + list.get(i).getTask());
                }
            }
            else if(item.length() == 6 &&  item.substring(0,4).equals("mark")) {
                try {
                    int index = Integer.parseInt(item.substring(5)) - 1;
                    list.get(index).setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list.get(index).getTask());
                    writeToFile(list);
                } catch (NullPointerException e){
                    System.out.println("Oops! Looks like the task number is incorrect :(");
                }
            }
            else if(item.length() == 8 &&  item.substring(0,6).equals("unmark")) {
                try {
                int index = Integer.parseInt(item.substring(7)) - 1;
                list.get(index).setNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(list.get(index).getTask());
                writeToFile(list);
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
                    LocalDate date = LocalDate.parse(item.substring(slash + 4));
                    list.add(new Deadline(item.substring(9, slash - 1), date));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(list.size() -1).getTask());
                    writeToFile(list);
                    count = count + 1;
                    System.out.println("Now you have " + Integer.toString(count) + " tasks in the list");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a deadline has to be in the format" +
                            " deadline <task> /by <date and time>");
                } catch (DateTimeParseException e) {
                    System.out.println("OOPS!! Format of the date is wrong");
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
                    list.add(new Event(item.substring(6, slash - 1), item.substring(slash + 4)));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(list.size() - 1).getTask());
                    writeToFile(list);
                    count = count + 1;
                    System.out.println("Now you have " + Integer.toString(count) + " tasks in the list");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a event has to be in the format event" +
                            " <task> /at <date and time>");
                }
            }
            else if(item.length() >= 4 &&  item.substring(0,4).equals("todo")) {
                try {
                    list.add(new Todo(item.substring(5)));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(list.size() - 1).getTask());
                    writeToFile(list);
                    count = count + 1;
                    System.out.println("Now you have " + Integer.toString(count) + " tasks in the list");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            }
            else if(item.length() >= 6 &&  item.substring(0,6).equals("delete")) {
                try {
                    int index = Integer.parseInt(item.substring(7)) - 1;
                    String text = list.get(index).getTask();
                    list.remove(index);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(text);
                    writeToFile(list);
                    count = count - 1;
                    System.out.println("Now you have " + Integer.toString(count) + " tasks in the list");
                } catch (IndexOutOfBoundsException e){
                    System.out.println("Oops! Looks like the task number is incorrect :(");
                }
            }
            else{
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            item = scan.nextLine();
        }
        System.out.println("Bye. hope to see you again soon!");
    }
}

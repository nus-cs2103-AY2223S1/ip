import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        Scanner sc = new Scanner(System.in);

        String[] strArray = sc.nextLine().split(" ");
        String str = strArray[0];
        ArrayList<Task> arrayList = new ArrayList<>();

        while (!str.equals("bye")) {

            if (str.equals("list")) {
                System.out.println("Here are the tasks in your current list:");

                if (arrayList.size() == 0) {
                    System.out.println("  Wow! You have no tasks to do currently!!");
                }

                for (int i = 0; i < arrayList.size(); i++) {
                    int j = i + 1;
                    System.out.println(j + "." + arrayList.get(i));
                }
            } else if (str.equals("mark")) {
                int i = Integer.parseInt(strArray[1]) - 1;
                arrayList.get(i).markDone();

                System.out.println("Nice! You actually did something:");
                System.out.println(" " + arrayList.get(i));
            } else if (str.equals("unmark")) {
                int i = Integer.parseInt(strArray[1]) - 1;
                arrayList.get(i).markNotDone();

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" " + arrayList.get(i));
            } else if (str.equals("delete")) {
                int i = Integer.parseInt(strArray[1]) - 1;
                Task deletedTask = arrayList.remove(i);

                System.out.println("Noted. I've removed this task:");
                System.out.println(" " + deletedTask);
                System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");
            } else if (str.equals("todo") || str.equals("deadline") || str.equals("event")) {

                String taskname = "";
                String date = "";
                int i = 1;

                while (i < strArray.length && strArray[i].charAt(0) != '/') {
                    taskname += strArray[i] + " ";
                    i++;
                }

                while (i < strArray.length) { //means now strArray[i] == /by or /at

                    if (strArray[i].charAt(0) != '/') {
                        date += strArray[i] + " ";
                    }

                    i++;
                }

                if (taskname.equals("")) {
                    throw new DukeException("Name of task cannot be empty!");
                }

                if (str.equals("todo")) {
                    Task todo = new Todo(taskname.trim());
                    arrayList.add(todo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + todo);
                    System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");

                } else {
                    if (date.equals("")) {
                        throw new DukeException("Date/Time cannot be empty!");
                    }

                    String[] tArray = date.trim().split(" ");
                    String time = "";
                    if (tArray.length > 2) {
                        throw new DukeException("Date and time has too many spaces!");
                    } else if (tArray.length == 2) {
                        time = tArray[1];
                    }

                    date = tArray[0];

                    //matches the yyyy-MMM-d format
                    if (!date.trim().matches("[0-9]{4}-((Jan)|(Feb)|(Mar)|(Apr)|(May)|(Jun)|(Jul)|(Aug)|(Sep)|(Oct)|(Nov)|(Dec))-[0-9]{1,2}")) {
                        throw new DukeException("Date/Time must match the YYYY-MMM-DD format!");
                    }

                    LocalDate d = null;
                    LocalDateTime dateTime = null; //no time functionality for now

                    try {
                        d = LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern("yyyy-MMM-d"));
                    } catch (DateTimeParseException e) {
                        System.out.println("Please provide a sensible date and time! Exiting...");
                        return;
                    }

                    if (str.equals("deadline")) {
                        Task deadline = new Deadline(taskname.trim(), d);
                        arrayList.add(deadline);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(" " + deadline);
                        System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");
                    } else {
                        Task event = new Event(taskname.trim(), d);
                        arrayList.add(event);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(" " + event);
                        System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");
                    }
                }
            } else {
                throw new DukeException("Please enter a valid input");
            }

            strArray = sc.nextLine().split(" ");
            str = strArray[0];
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}

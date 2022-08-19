import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class
public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static int i = 0;
    private static Scanner input = new Scanner(System.in);

    private static String receiveCommand() {
        String task = input.nextLine();  // Read user input
        return task;
    }

    private static void list() {
        //list out all tasks
        for (int j = 0; j < i; j++) {
            System.out.println(j+1 + ": " + tasks.get(j));
        }
        return;
    }

    private static void markAsDone(int taskNumber) {
        //mark task number as done
        if(taskNumber < i && taskNumber >= 0){
            tasks.get(taskNumber).markAsDone();
            System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(taskNumber));
        }
        return;
    }

    private static void markAsUndone(int taskNumber) {
        //mark task number as undone
        if(taskNumber < i && taskNumber >= 0){
            tasks.get(taskNumber).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.get(taskNumber));
        }
        return;
    }

    private static String toPrintOnAdd(int taskNum) {
        return "Got it. I've added this task:\n  " + tasks.get(taskNum) + "\nNow you have " + i + " tasks in the list.";
    }

    private static void addTask(Task task) {
        //add todo
        tasks.add(task);
        i+=1;
        System.out.println(toPrintOnAdd(i-1));
    }

    private static String toPrintOnDelete(int taskNum) {
        return "Noted. I've deleted this task:\n  " + tasks.get(taskNum) + "\nNow you have " + i + " tasks in the list.";
    }
    private static void deleteTask(int taskNumber) {
        if(taskNumber >=0 && taskNumber < i) {
            i -= 1;
            System.out.println(toPrintOnDelete(taskNumber));
            tasks.remove(taskNumber);
        }
    }
    private static void parseCommand(String command) throws DukeException {
        if(command.equals("bye")) {
            //exit program
            System.out.println("Bye. Hope to see you again soon!");
        } else if (command.equals("list")) {
            list();
        } else if (command.startsWith("mark")) {
            try {
                int taskNumber = Integer.parseInt(command.split("\\s+")[1]) - 1;
                markAsDone(taskNumber);
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Please provide a number for this command");
            }
        } else if (command.startsWith("unmark")) {
            try {
                int taskNumber = Integer.parseInt(command.split("\\s+")[1]) - 1;
                markAsUndone(taskNumber);
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Please provide a number for this command");
            }
        } else if (command.startsWith("todo")) {
            try {
                String description = command.split(" ", 2)[1];
                addTask(new Todo(description));
            } catch(Exception e) {
                System.out.println(e);
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (command.startsWith("deadline")) {
            try {
                String full = command.split(" ", 2)[1];
                String description = full.split(" /by ")[0];
                String deadline = full.split(" /by ")[1];
                addTask(new Deadline(description, deadline));
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Please format deadline request correctly.");
            }
        } else if (command.startsWith("event")) {
            try {
                String full = command.split(" ", 2)[1];
                String description = full.split(" /at ")[0];
                String at = full.split(" /at ")[1];
                addTask(new Event(description, at));
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Please format event request correctly.");
            }
        } else if (command.startsWith("delete")) {
            try {
                int taskNumber = Integer.parseInt(command.split("\\s+")[1]) - 1;
                deleteTask(taskNumber);
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Please provide a number for this command");
            }
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return;
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String command = "";
        do {
            System.out.print("> ");
            command = receiveCommand();
            System.out.println("");
            try {
                parseCommand(command);
            } catch (DukeException e) {
               System.out.println(e.getMessage());
            }
            System.out.println("");
        } while (!command.equals("bye"));
    }
}
import java.util.Scanner;  // Import the Scanner class
public class Duke {

    private static Task[] tasks = new Task[100];
    private static int i = 0;
    private static Scanner input = new Scanner(System.in);

    private static String receiveCommand() {
        String task = input.nextLine();  // Read user input
        return task;
    }

    private static void list() {
        //list out all tasks
        for (int j = 0; j < i; j++) {
            System.out.println(j+1 + ": " + tasks[j]);
        }
        return;
    }

    private static void markAsDone(int taskNumber) {
        //mark task number as done
        if(taskNumber < i && taskNumber >= 0){
            tasks[taskNumber].markAsDone();
            System.out.println("Nice! I've marked this task as done:\n  " + tasks[taskNumber]);
        }
        return;
    }

    private static void markAsUndone(int taskNumber) {
        //mark task number as undone
        if(taskNumber < i && taskNumber >= 0){
            tasks[taskNumber].markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:\n  " + tasks[taskNumber]);
        }
        return;
    }

    private static String toPrintOnAdd(int taskNum) {
        return "Got it. I've added this task:\n  " + tasks[taskNum] + "\nNow you have " + i + " tasks in the list.";
    }

    private static void addTask(String description) {
        //add task
        tasks[i] = new Task(description);
        i += 1;
        System.out.println(toPrintOnAdd(i-1));
        return;
    }

    private static void addTodo(String description) {
        //add todo
        tasks[i] = new Todo(description);
        i+=1;
        System.out.println(toPrintOnAdd(i-1));
    }

    private static void addDeadline(String description, String deadline) {
        tasks[i] = new Deadline(description, deadline);
        i+=1;
        System.out.println(toPrintOnAdd(i-1));
    }

    private static void addEvent(String description, String at) {
        tasks[i] = new Event(description, at);
        i+=1;
        System.out.println(toPrintOnAdd(i-1));
    }

    private static void parseCommand(String command) {
        if(command.equals("bye")) {
            //exit program
            System.out.println("Bye. Hope to see you again soon!");
        } else if (command.equals("list")) {
            list();
        } else if (command.startsWith("mark")) {
            int taskNumber = Integer.parseInt(command.split("\\s+")[1]) - 1;
            markAsDone(taskNumber);
        } else if (command.startsWith("unmark")) {
            int taskNumber = Integer.parseInt(command.split("\\s+")[1]) - 1;
            markAsUndone(taskNumber);
        } else if (command.startsWith("todo")) {
            String description = command.split(" ", 2)[1];
            addTodo(description);
        } else if (command.startsWith("deadline")) {
            String full = command.split(" ", 2)[1];
            String description = full.split(" /by ")[0];
            String deadline = full.split(" /by ")[1];
            addDeadline(description, deadline);
        } else if (command.startsWith("event")) {
            String full = command.split(" ", 2)[1];
            String description = full.split(" /at ")[0];
            String at = full.split(" /at ")[1];
            addEvent(description, at);
        } else {
            addTask(command);
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
            parseCommand(command);
            System.out.println("");
        } while (!command.equals("bye"));
    }
}
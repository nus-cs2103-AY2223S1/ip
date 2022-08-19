import java.util.Scanner;  // Import the Scanner class
public class Duke {

    private static Task[] tasks = new Task[100];
    private static int i = 0;
    public static String receiveCommand() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String task = myObj.nextLine();  // Read user input
        return task;
    }

    public static void parseCommand(String command) {
        if(command.equals("bye")) {
            //exit program
            System.out.println("Bye. Hope to see you again soon!");
        } else if (command.equals("list")) {
            //list out all tasks
            for (int j = 0; j < i; j++) {
                System.out.println(j+1 + ": " + tasks[j]);
            }
        } else if (command.startsWith("mark")) {
            //mark task number as done
            int taskNumber = Integer.parseInt(command.split("\\s+")[1]) - 1;

            if(taskNumber < i && taskNumber > 0){
                tasks[taskNumber].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  " + tasks[taskNumber]);
            }
        } else if (command.startsWith("unmark")) {
            //mark task number as undone
            int taskNumber = Integer.parseInt(command.split("\\s+")[1]) - 1;

            if(taskNumber < i && taskNumber > 0) {
                tasks[taskNumber].markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:\n  " + tasks[taskNumber]);
            }
        } else {
            //add task
            tasks[i] = new Task(command);
            i += 1;

            System.out.println("Ok, I have added: " + command);
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
            parseCommand(command);
        } while (!command.equals("bye"));
    }
}
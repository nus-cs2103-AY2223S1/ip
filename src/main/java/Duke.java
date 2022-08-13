import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine();
            switch (command) {
                case "list": {
                    System.out.println(taskList);
                    break;
                }
                case "bye": {
                    System.out.println("Bye! Hope to see you again soon!");
                    return;
                }
                default: {
                    taskList.addTask(command);
                    System.out.println("added: " + command);
                    break;
                }
            }
        }
    }
}

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> TaskList = new ArrayList<>();

    public static void mark(int number) {
        Task task =  TaskList.get(number - 1);
        task.setCompleted();
        String taskCompletion = "____________________________________________________________\n" +
                "Nice! I've marked this task as done: \n" + "  " + task.toString() + "\n" +
                "____________________________________________________________\n";
        System.out.println(taskCompletion);
    }

    public static void unmark(int number) {
        Task task =  TaskList.get(number - 1);
        task.setUncompleted();
        String taskUnCompletion = "____________________________________________________________\n" +
                "Ok, I've marked this task as not done yet: \n" + "  " + task.toString() + "\n" +
                "____________________________________________________________\n";
        System.out.println(taskUnCompletion);
    }
    public static void echo(String command) {
        if (command.toUpperCase().equals("BYE")) {
            String bye = "____________________________________________________________\n" +
                    "Bye. Hope to see you again soon!\n" +
                    "____________________________________________________________\n";
            System.out.println(bye);
        } else if (command.toUpperCase().equals("LIST")) {
            String newList = "Here are the tasks in your list: \n";
            int count = 1;
            for (Task item: TaskList) {
                newList += (count + "." + item.toString() + "\n");
                count++;
            }
            String updatedList = "____________________________________________________________\n" + newList +
                    "____________________________________________________________\n";
            System.out.println(updatedList);

        } else {
            Task newTask = new Task(command);
            TaskList.add(newTask);
            String echo = "____________________________________________________________\n" +
                    "added: " + command + "\n" + "____________________________________________________________\n";
            System.out.println(echo);
        }
    }
    public static void main(String[] args) {
        String logo = "____________________________________________________________\n" + "Hello from\n" +
                " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n" +
                "How may I assist you? \n" +
                "____________________________________________________________\n";
        System.out.println(logo);

        Scanner myScanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please enter your command: ");
            String command = myScanner.nextLine();
            String [] commandArr = command.split(" ");
            if (commandArr[0].equals("mark")) {
                int number = Integer.parseInt(commandArr[1]);
                mark(number);

            } else if (commandArr[0].equals("unmark")) {
                int number = Integer.parseInt(commandArr[1]);
                unmark(number);
            } else {
                echo(command);
                if (command.toUpperCase().equals("BYE")) {
                    break;
                }

            }

        }
    }
}

import java.util.Scanner;

public class Duke {
    private static final String line = "    ____________________________________________________________";
    private static int index = 0;
    private static Task[] arr = new Task[100];
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        printGreetings();
        String str = sc.nextLine().trim();
        while(!str.equals("bye")) {
            try {
                processInput(str);
            } catch (DukeException e) {
                System.out.println(e);
            }
            str = sc.nextLine().trim();
        }
        printGoodbye();
    }

    private static void processInput(String str) throws DukeException {
        String[] splitStr = str.split(" ", 2);
        switch (splitStr[0]) {
            case "list":
                int k = 0;
                int i = 1;
                System.out.println(line);
                System.out.println("     Here are the tasks in your list");
                while (arr[k] != null) {
                    System.out.println("     " + i + "." + arr[k]);
                    k++;
                    i++;
                }
                System.out.println(line);
                break;
            case "mark":
                if (splitStr.length < 2) {
                    throw new DukeException("Please specify task number to mark.");
                }
                int markNo;
                try {
                    markNo = Integer.parseInt(splitStr[1]);
                } catch (NumberFormatException e) {
                    throw new DukeException("Please indicate a task number to mark.");
                }
                if (markNo <= 0) {
                    throw new DukeException("Invalid task number.");
                }
                if (markNo > index) {
                    throw new DukeException("There are not that many tasks!");
                }
                arr[markNo - 1].markAsDone();
                System.out.println(line);
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + arr[markNo - 1]);
                System.out.println(line);
                break;
            case "unmark":
                if (splitStr.length < 2) {
                    throw new DukeException("Please specify task number to unmark.");
                }
                int unmarkNo;
                try {
                    unmarkNo = Integer.parseInt(splitStr[1]);
                } catch (NumberFormatException e) {
                    throw new DukeException("Please indicate a task number to mark.");
                }
                if (unmarkNo <= 0) {
                    throw new DukeException("Invalid task number.");
                }
                if (unmarkNo > index) {
                    throw new DukeException("There are not that many tasks!");
                }
                arr[unmarkNo - 1].markAsUnDone();
                System.out.println(line);
                System.out.println("     Nice! I've marked this task as not done yet:");
                System.out.println("       " + arr[unmarkNo - 1]);
                System.out.println(line);
                break;
            case "todo":
                if (splitStr.length < 2) {
                    throw new DukeException("The description of a todo cannot be empty");
                }
                arr[index] = new Todo(splitStr[1]);
                System.out.println(line);
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + arr[index]);
                index++;
                System.out.println("     Now you have " + index + " tasks in the list");
                System.out.println(line);
                break;
            case "deadline":
                if (splitStr.length < 2) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
                String[] strDeadline = splitStr[1].split(" /by ", 2);
                if (strDeadline.length < 2 || strDeadline[1].equals("")) {
                    throw new DukeException("Please also specify the date and time.");
                }
                arr[index] = new Deadline(strDeadline[0], strDeadline[1]);
                System.out.println(line);
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + arr[index]);
                index++;
                System.out.println("     Now you have " + index + " tasks in the list");
                System.out.println(line);
                break;
            case "event":
                if (splitStr.length < 2) {
                    throw new DukeException("The description of a event cannot be empty");
                }
                String[] strEvent = splitStr[1].split(" /at ", 2);
                if (strEvent.length < 2 || strEvent[1].equals("")) {
                    throw new DukeException("Please also specify the date and time.");
                }
                arr[index] = new Event(strEvent[0], strEvent[1]);
                System.out.println(line);
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + arr[index]);
                index++;
                System.out.println("     Now you have " + index + " tasks in the list");
                System.out.println(line);
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means!");
        }
    }

    private static void printGreetings() {
        String greeting = line + "\n     Hello! I'm Duke\n     What can I do for you?\n" + line;
        System.out.println(greeting);
    }

    private static void printGoodbye() {
        String goodbye = line + "\n     Bye. Hope to see you again soon!\n" + line;
        System.out.println(goodbye);
    }
}

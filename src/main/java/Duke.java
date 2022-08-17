import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        int index = 0;
        String line = "    ____________________________________________________________";
        Scanner sc = new Scanner(System.in);
        Task[] arr = new Task[100];

        String greeting = line + "\n     Hello! I'm Duke\n     What can I do for you?\n" + line;
        System.out.println(greeting);
        String str = sc.nextLine();
        while(!str.equals("bye")) {
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
                    int markNo = Integer.parseInt(splitStr[1]);
                    arr[markNo - 1].markAsDone();
                    System.out.println(line);
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("       " + arr[markNo - 1]);
                    System.out.println(line);
                    break;
                case "unmark":
                    int unmarkNo = Integer.parseInt(splitStr[1]);
                    arr[unmarkNo - 1].markAsUnDone();
                    System.out.println(line);
                    System.out.println("     Nice! I've marked this task as not done yet:");
                    System.out.println("       " + arr[unmarkNo - 1]);
                    System.out.println(line);
                    break;
                case "todo":
                    arr[index] = new Todo(str);
                    System.out.println(line);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + arr[index]);
                    index++;
                    System.out.println("     Now you have " + index + " tasks in the list");
                    System.out.println(line);
                    break;
                case "deadline":
                    String[] strDeadline = splitStr[1].split(" /by ", 2);
                    arr[index] = new Deadline(strDeadline[0], strDeadline[1]);
                    System.out.println(line);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + arr[index]);
                    index++;
                    System.out.println("     Now you have " + index + " tasks in the list");
                    System.out.println(line);
                    break;
                case "event":
                    String[] strEvent = splitStr[1].split(" /at ", 2);
                    arr[index] = new Event(strEvent[0], strEvent[1]);
                    System.out.println(line);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + arr[index]);
                    index++;
                    System.out.println("     Now you have " + index + " tasks in the list");
                    System.out.println(line);
            }
            str = sc.nextLine();
        }
        String goodbye = line + "\n     Bye. Hope to see you again soon!\n" + line;
        System.out.println(goodbye);
    }
}

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] arr = new Task[100];
        int count = 0;
        String line = "    ____________________________________________________________";
        String greeting = line + "\n     Hello! I'm Duke\n     What can I do for you?\n" + line;
        System.out.println(greeting);
        String str = sc.nextLine();
        while(!str.equals("bye")) {
            String[] splitStr = str.split("\\s+");
            if (str.equals("list")) {
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
            } else if (splitStr[0].equals("mark")) {
                int num = Integer.parseInt(splitStr[1]);
                arr[num - 1].markAsDone();
                System.out.println(line);
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + arr[num - 1]);
                System.out.println(line);
            } else if (splitStr[0].equals("unmark")) {
                    int num = Integer.parseInt(splitStr[1]);
                    arr[num - 1].markAsUnDone();
                    System.out.println(line);
                    System.out.println("     Nice! I've marked this task as not done yet:");
                    System.out.println("       " + arr[num - 1]);
                    System.out.println(line);
            } else {
                arr[count] = new Task(str);
                count++;
                String message = "     added: " + str;
                System.out.println(line);
                System.out.println(message);
                System.out.println(line);
            }
            str = sc.nextLine();
        }
        String goodbye = line + "\n     Bye. Hope to see you again soon!\n" + line;
        System.out.println(goodbye);
    }
}

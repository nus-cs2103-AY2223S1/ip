import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke What can I do for you?");
        Task[] array = new Task[100];
        int counter = 0;
        boolean flag = false;
        Scanner in = new Scanner(System.in);
        while (!flag) {
            String output = in.nextLine();
            String arr[] = output.split(" ",2);
            String firstword = arr[0];

            if (output.equals("bye")) {
                System.out.println("_________________________________________________________________________");
                System.out.println("        Bye. Hope to see you again soon!");
                System.out.println("_________________________________________________________________________");
                flag = true;

            }
            else if(output.equals("list")) {
                System.out.println("_________________________________________________________________________");
                for (int i = 0; i < 100; i++) {
                    if (array[i] != null) {
                        int j = i + 1;
                        System.out.println(j + ". " + array[i].getName());
                    }
                }
                System.out.println("_________________________________________________________________________");
            }

            else if(firstword.equals("mark")) {
                int num = Integer.parseInt(arr[1]);
                array[num - 1].markAsDone();
                System.out.println("_________________________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(array[num-1].getName());
                System.out.println("_________________________________________________________________________");
            }
            else if(firstword.equals("unmark")) {
                int num = Integer.parseInt(arr[1]);
                array[num - 1].markAsNotDone();
                System.out.println("_________________________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(array[num-1].getName());
                System.out.println("_________________________________________________________________________");
            }
            else {
                array[counter] = new Task(output);
                counter++;
                System.out.println("_________________________________________________________________________");
                System.out.println("       added: " + output);
                System.out.println("_________________________________________________________________________");
            }
        }
    }
}

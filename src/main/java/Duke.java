import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke What can I do for you?");
        ArrayList<Task> array = new ArrayList<>();
        int counter = 0;
        boolean flag = false;
        Scanner in = new Scanner(System.in);
        DukeOperations ops = new DukeOperations();
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
                ops.displayList(array);

            }
            else if(firstword.equals("todo")) {
                try {
                    System.out.println("_________________________________________________________________________");
                    ops.todo(array, arr, counter);
                    System.out.println("_________________________________________________________________________");
                    counter++;
                }
                catch (DukeException e1) {
                    System.out.println(e1.toString());
                }

            }

            else if(firstword.equals("deadline")) {
                System.out.println("_________________________________________________________________________");
                String arr2[] = arr[1].split("/by",2);
                ops.deadline(array,arr2[0],arr2[1],counter);
                System.out.println("_________________________________________________________________________");
                counter++;
            }
            else if(firstword.equals("event")) {
                System.out.println("_________________________________________________________________________");
                String arr2[] = arr[1].split("/at",2);
                ops.event(array,arr2[0],arr2[1],counter);
                System.out.println("_________________________________________________________________________");
                counter++;
            }

            else if(firstword.equals("mark")) {
                int num = Integer.parseInt(arr[1]);
                ops.mark(array,num);
            }
            else if(firstword.equals("unmark")) {
                int num = Integer.parseInt(arr[1]);
                ops.mark(array,num);
            }
            else {
                try {
                    ops.randomword(output);
                }
                catch(DukeException e2) {
                    System.out.println("_________________________________________________________________________");
                    System.out.println(e2.toString());
                    System.out.println("_________________________________________________________________________");
                }
            }
        }
    }
}

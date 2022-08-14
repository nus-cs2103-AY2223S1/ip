import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke What can I do for you?");
        String[] array = new String[100];
        int counter = 0;
        boolean flag = false;
        Scanner in = new Scanner(System.in);
        while (!flag) {
            String output = in.nextLine();
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
                        System.out.println(j + ". " + array[i]);
                    }
                }
                System.out.println("_________________________________________________________________________");
            }
            else {
                array[counter] = output;
                counter++;
                System.out.println("_________________________________________________________________________");
                System.out.println("       added: " + output);
                System.out.println("_________________________________________________________________________");
            }
        }
    }
}

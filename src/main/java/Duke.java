import java.util.Objects;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arr = new String[100];
        int count = 0;
        String line = "    ____________________________________________________________";
        String greeting = line + "\n     Hello! I'm Duke\n     What can I do for you?\n" + line;
        System.out.println(greeting);
        String str = sc.nextLine();
        while(!str.equals("bye")) {
            if (str.equals("list")) {
                int k = 0;
                int i = 1;
                System.out.println(line);
                while (arr[k] != null) {
                    System.out.println("     " + i + ". " + arr[k]);
                    k++;
                    i++;
                }
                System.out.println(line);
            } else {
                arr[count] = str;
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

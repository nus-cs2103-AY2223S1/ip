import java.util.Objects;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "____________________________________________________________\n";
        String greeting = line +
                          "     Hello! I'm Duke\n" +
                          "     What can I do for you?\n" +
                          line;
        System.out.println(greeting);
        String str = sc.nextLine();
        while(!str.equals("bye")) {
            System.out.println(str);
            str = sc.nextLine();
        }
        String goodbye = line + "      Bye. Hope to see you again soon!\n" + line;
        System.out.println(goodbye);
    }
}

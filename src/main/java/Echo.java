// Level 1
import java.util.Scanner;

public class Echo {
    public static void echo() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        if (str.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            sc.close();
        } else {
            System.out.println(str);
            Echo.echo();
        }
    }
}

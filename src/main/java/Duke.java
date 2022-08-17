import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Action greet = new Greet();
        System.out.println(greet);
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        while(!s.equals("bye")) {
            Action echo = new Echo(s);
            System.out.println(echo);
            s = in.nextLine();
        }
        Action bye = new Bye();
        System.out.println(bye);
    }
}

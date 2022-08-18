import java.util.*;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String input = sc.nextLine();
        while(!input.equals("bye")) {
            System.out.println(input);
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

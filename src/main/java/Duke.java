import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String lines = "     ____________________________________________________________";
        System.out.println(lines);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(lines);

        Scanner sc = new Scanner(System.in);

        while(true) {
            String input = sc.nextLine();
            if(input.equals("bye")) {
                System.out.println(lines);
                System.out.println("     Bye! Hope to see you again soon!");
                System.out.println(lines);
                sc.close();
                break;
            } else {
                System.out.println(lines);
                System.out.println("     " + input);
                System.out.println(lines);
            }
        }
    }
}

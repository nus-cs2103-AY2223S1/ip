import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "Hello";
        String bye = "Goodbye";
        System.out.println(greeting);

        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(input);
            input = sc.nextLine();
        }
        System.out.println(bye);
        return;
    }
}

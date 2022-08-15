import java.util.Scanner;

public class Jamie {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hi, I'm Jamie.\n What do you want?");

        String input = sc.next();
        while (!input.equals("bye")) {
            System.out.println(input);
            input = sc.next();
        }

        System.out.println("bye >:(");

    }
}

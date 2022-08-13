import java.util.Scanner;

public class Jude {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Jude.");
        System.out.println("What can I do for you?");

        // Solution below adapted from
        // https://stackoverflow.com/questions/5287538/how-to-get-the-user-input-in-java
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.print("> ");
            String str = sc.nextLine();
            if (str.equals("bye")) {
                System.out.println("Goodbye! Have a nice day!");
                break;
            }
            System.out.println(str);
        }
    }
}

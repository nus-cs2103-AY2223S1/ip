import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hi... I'm Bishop... ");
        System.out.println("What can I do for you today?");

        Scanner scanner = new Scanner(System.in);
        String ans;
        do {
            ans = scanner.nextLine();
            System.out.println(ans);
        } while (!ans.equals("bye"));

        System.out.println("Goodbye...");

    }
}

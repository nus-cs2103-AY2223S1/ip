import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hey AhBeng here. What you want?");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(userInput);
            userInput = sc.nextLine();
        }
        System.out.println("Bye bye go away!");
    }
}

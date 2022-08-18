import java.util.Scanner;

public class Scruffles {
    public static void main(String[] args) {

        System.out.println("woof! I'm scruffles the task tracking doggo\n" + "what can I woof for you today?");

        Scanner sc = new Scanner(System.in);
        String input = "";

        while (true) {

            input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("woof see you again woof!");
                break;
            } else {
                System.out.println(input);
            }
        }
    }
}
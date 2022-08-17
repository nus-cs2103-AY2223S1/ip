import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello there! My name's Duck");
        System.out.println("Please type in a command...");
        Scanner input = new Scanner(System.in);
        while (true) {
            String inputLine = input.nextLine();
            String[] inputArr = inputLine.split(" ");
            if (inputLine.equals("bye")) {
                System.out.println("Bye! See you next time!");
                input.close();
                return;
            }
            System.out.println(inputLine);
        }
    }
}

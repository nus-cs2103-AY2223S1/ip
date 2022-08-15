import java.io.BufferedReader;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String divider = "\n____________________________________________________________";
        System.out.println("Hello I'm Karen. What do you want?" + divider);
        Scanner input = new Scanner(System.in);
        while (true) {
            String text = input.nextLine();
            if (text.equals("bye")) {
            System.out.println("Bye~ See you never" + divider);
                break;
            }
            System.out.println(text + divider);
        }
    }
}

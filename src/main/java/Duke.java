import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<String> texts = new ArrayList<>();

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
            } else if (inputLine.equals("list")) {
                int id = 1;
                for (String text: texts) {
                    System.out.println(id + ". " + text);
                    id += 1;
                }
            }
            else {
                System.out.println("added: " + inputLine);
                texts.add(inputLine);
            }
        }
    }
}

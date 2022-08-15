import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<String> textList = new ArrayList<>();

        String divider = "____________________________________________________________";
        System.out.println("Hello I'm Karen. What do you want?\n" + divider);
        Scanner input = new Scanner(System.in);
        while (true) {
            String text = input.nextLine();
            if (text.equals("bye")) {
            System.out.println("Bye. See you never\n" + divider);
                break;
            }
            if (text.equals("list")) {
                for (int i = 0; i < textList.size(); i++) {
                    System.out.println((i + 1) + ". " + textList.get(i));
                }
                System.out.println(divider);
                continue;
            }
            textList.add(text);
            System.out.println("K. added: " + text + "\n" + divider);
        }
    }
}

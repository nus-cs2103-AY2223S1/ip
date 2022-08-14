import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------");
        System.out.println("Hello from Phil");
        System.out.println("How may I assist you on this fine day?");
        System.out.println("-------------------------------------------");

        String in = "";
        ArrayList<String> list = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        while (true) {
            in = sc.nextLine();
            if (in.equals("bye")) {
                break;
            } else if (in.equals("list")) {
                int index = 0;
                String item;
                while (index < list.size()) {
                    item = list.get(index);
                    System.out.println((index + 1) + ". " + item);
                    index++;
                }
            } else {
                list.add(in);
                System.out.println("added: " + in);
            }
            System.out.println("-------------------------------------------");
        }
        System.out.println("See you later alligator!");
        System.out.println("-------------------------------------------");
    }
}

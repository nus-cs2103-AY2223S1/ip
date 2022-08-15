import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {

        ArrayList<String> mylist = new ArrayList<>();

        boolean bye = false;

        Scanner scanner = new Scanner(System.in);
//        String input = scanner.nextLine();
        System.out.println("HELLO");

        while (!bye) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                bye = true;
                System.out.println("bai bai");
            } else if (input.equals("list")) {
                for (int i = 0; i < mylist.size(); i++) {
                    System.out.println(i + 1 + ": " + mylist.get(i));
                }
            }
            else {
                mylist.add(input);
                System.out.println("added :" + input);
            }
        }
    }
}

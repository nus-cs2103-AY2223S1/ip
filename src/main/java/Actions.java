import java.util.*;

public class Actions {
    public static void greetEcho() {
        String input = "";
        System.out.println("Hello! I'm Duke, what's up today?");
        while (input != "bye") {
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("See ya!");
                return;
            }
            else {
                System.out.println(input);
            }
        }
    }
    public static void addList() {
        ArrayList<String> ls = new ArrayList();
        String input = "";
        System.out.println("Hello! I'm Duke, what's up today?");
        while (input != "bye") {
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("See ya! Come again~");
                return;
            }
            else if (input.equals("list")) {
                for (int i = 0; i < ls.size(); i++) {
                    System.out.println(i + 1 + ". " + ls.get(i));
                }
            }
            else {
                ls.add(input);
                System.out.println("added: " + input);
            }
        }
    }
}

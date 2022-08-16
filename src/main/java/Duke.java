import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static String greetings = "Hello! I'm Ekud \n" + "What can I do for you?";
    private static String banner = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static ArrayList<String> tasks = new ArrayList<>();

    private static void print(String msg) {
        System.out.println(banner);
        System.out.println(msg);
        System.out.println(banner);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        print(greetings);

        while(true) {
            String input = sc.nextLine();
            switch (input) {
                case "bye":
                    print("Cya!");
                    return;

                case "list":
                    String list = "";
                    for (int i = 0; i < tasks.size(); i++) {
                        list += (i + 1) + ". " + tasks.get(i);
                        if (i != tasks.size() - 1) list += "\n";
                    }
                    print(list);
                    break;

                default:
                    print("added: " + input);
                    tasks.add(input);
            }
        }
    }
}

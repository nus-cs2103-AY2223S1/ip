import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String introMsg = "Baby Yoda I am\n\tFor you, what can I do?";

        prettyPrint(introMsg);

        Scanner in = new Scanner(System.in);
        String s = "";

        while (true) {
            s = in.nextLine();
            if (s.equals("bye"))
                break;
            if (s.equals("list")) {
                StringBuilder list = new StringBuilder();
                for (int i = 0; i < tasks.size(); i++) {
                    list.append(i + 1).append(" ").append(tasks.get(i)).append(i != tasks.size() - 1 ? "\n\t" : "");
                }
                prettyPrint(list.toString());
            } else {
                tasks.add(s);
                prettyPrint("added: " + s);
            }
        }

        String byeMsg = "See you soon, I will";
        prettyPrint(byeMsg);
    }

    private static void prettyPrint(String s) {
        String divider = "____________________________________________________________\n";
        System.out.println(divider + "\t" + s + "\n" + divider);
    }
}

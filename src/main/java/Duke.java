import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println();

        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        ArrayList<String> arrayList = new ArrayList<>();

        while (!str.equals("bye")) {

            if (str.equals("list")) {
                for (int i = 0; i < arrayList.size(); i++) {
                    int j = i + 1;
                    System.out.println(j + ". " + arrayList.get(i));
                }
            } else {
                arrayList.add(str);
                System.out.println("added: " + str);
            }

            str = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}

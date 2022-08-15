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
        Scanner in = new Scanner(System.in);
        System.out.println("Oi, What u want?");
        ArrayList<String> list = new ArrayList<>();
        while (in.hasNext()) {
            String next = in.nextLine();
            if (next.equals("bye")) {
                System.out.println("Bye! Don't Come back!");
                return;
            } else if (next.equals("list")) {
                for(int i = 0; i < list.size(); i++) {
                    System.out.println(i+1 + ". " + list.get(i));
                }
            }
            else {
                System.out.println("added: " + next);
                list.add(next);
            }
        }


    }
}

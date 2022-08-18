import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Duke {

    public static String breaker = "____________________________________________________________\n";


    public static void main(String[] args) {

        String start = "Hello! I'm Duke\nWhat can I do for you?";
        String end = "Bye. Hope to see you again soon!";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        msg(start);

        ArrayList<String> list = new ArrayList<String>();
        String text = "";
        while (!"bye".equals(text)) {
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            text = reader.nextLine();
            if ("bye".equals(text)) {
                msg(end);
                reader.close();
            } else if ("list".equals(text)) {
                list(list);
            } else {
                add(text, list);
            }
        }
    }

    public static void msg(String s) {
        System.out.println(breaker + s + "\n" + breaker);
    }

    public static void add(String s, ArrayList<String> l) {
        l.add(s);
        msg("added: " + s);
    }

    public static void list(ArrayList<String> l) {
        String result = "";
        if (l.isEmpty()) {
            msg("");
            return;
        }
        for (int i = 0; i < l.size()-1; i++) {
            result += (i+1) + ". " + l.get(i) + "\n";
        }
        result += (l.size()) + ". " + l.get(l.size()-1);
        msg(result);
    }
}

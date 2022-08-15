import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {
    public static void main(String[] args) {
        print("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        scanner: while (sc.hasNext()) {
            String methodName = sc.next();
            switch (methodName) {
                case "list":
                    list();
                    break;

                case "blah":
                    blah();
                    break;
                
                default:
                    bye();
                    sc.close();
                    break scanner;
            }
        }
    }

    public static void print(String s) {
        String seperator = "    ____________________________________________________________\n";
        System.out.println(seperator + Stream.of(s.split("\n")).map(x -> "     " + x).reduce("", (x,y) -> x + "\n" + y) + "\n" + seperator);
    }

    public static void list() {
        print("list");
    }

    public static void blah() {
        print("blah");
    }

    public static void bye() {
        print("Bye. Hope to see you again soon!");
    }
}

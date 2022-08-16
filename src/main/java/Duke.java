import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Duke {
    public static List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        print("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        scanner: while (sc.hasNextLine()) {
            String input = sc.nextLine();
            switch (input) {
                case "list":
                    list();
                    break;

                case "blah":
                    blah();
                    break;
                
                case "bye":
                    bye();
                    sc.close();
                    break scanner;
            
                default:
                    add(input);
                    break;
            }
        }
    }

    public static void print(String s) {
        String seperator = "    ____________________________________________________________\n";
        System.out.println(seperator + Stream.of(s.split("\n")).map(x -> "     " + x + "\n").reduce("", (x,y) -> x + y) + seperator);
    }

    public static void list() {
        print(IntStream.range(0, tasks.size()).mapToObj(x -> String.format("%d. %s\n", x + 1, tasks.get(x))).reduce("", (x,y) -> x + y));
    }

    public static void blah() {
        print("blah");
    }

    public static void bye() {
        print("Bye. Hope to see you again soon!");
    }

    public static void add(String task) {
        tasks.add(task);
        print("added: " + task);
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class Bloop {

    private static final String hiMessage = "Hey! I'm Bloop\n" + "\tWhat can I do for you?";

    private static final String byeMessage = "Goodbye! Hope to see you soon :)";

    private static final String separator = "\t------------------------------------------------------";

    private static ArrayList<Task> list = new ArrayList<Task>();

    private static void chat() {
        System.out.println(separator + "\n\t" + hiMessage + "\n" + separator);
        Scanner sc = new Scanner(System.in);
        while(true) {
            String s = sc.nextLine();
            System.out.println(separator);
            if (s.compareTo("bye") == 0) {
                break;
            }
            if (s.compareTo("list") == 0) {
                listOut();
            } else if (s.length() > 6 && s.substring(0, 6).equals("unmark")) {
                list.get(s.charAt(s.length() - 1) - 48 - 1).unmark();
                System.out.println(separator);
            } else if (s.length() > 4 && s.substring(0, 4).equals("mark")) {
                list.get(s.charAt(s.length() - 1) - 48 - 1).mark();
                System.out.println(separator);
            } else {
                list.add(new Task(s));
                System.out.println("\t" + s + "\n" + separator);
            }
        }
        System.out.println("\t" + byeMessage + "\n" + separator);
    }

    private static void listOut() {
        System.out.println("Tasks in your list -");
        for(Task a : list) {
            System.out.println("\t" + a.getId() + ". " +a.getStatus() + " " + a.getTask());
        }
        System.out.println(separator);
    }

    public static void main(String[] args) {
        chat();
    }
}

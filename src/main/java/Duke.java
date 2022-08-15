import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    private static Pattern checkString = Pattern.compile("-?\\d+");

    private static ArrayList<Task> storage = new ArrayList<Task>();

    public static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        return checkString.matcher(strNum).matches();
    }

    public static void reply() {
        Scanner echo = new Scanner(System.in);
        String response = echo.nextLine();
        Pattern markPattern = Pattern.compile("^mark ", Pattern.CASE_INSENSITIVE);
        Matcher markMatcher = markPattern.matcher(response);
        boolean mark = markMatcher.find();
        Pattern unmarkPattern = Pattern.compile("^unmark ", Pattern.CASE_INSENSITIVE);
        Matcher unmarkMatcher = unmarkPattern.matcher(response);
        boolean unmark = unmarkMatcher.find();
        Task task = new Task(response);
        if (response.equals("bye")) {
            System.out.println("-----------------------------------------------");
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("-----------------------------------------------");
            storage.clear();
            return;
        } else if (response.equals("list")) {
            System.out.println("-----------------------------------------------");
            for (int i = 0; i < storage.size(); i++) {
                System.out.println((i + 1) + ".["+ storage.get(i).getStatusIcon() + "] "
                        + storage.get(i).getDescription());
            }
            System.out.println("-----------------------------------------------");
            reply();
        } else if(mark) {
            String[] parts = response.split(" ");
            String part2 = parts[1];
            if(isInteger(part2) && parts.length == 2) {
                int number = Integer.parseInt(part2);
                storage.get(number- 1).mark();
                System.out.println("-----------------------------------------------");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("["+ storage.get(number -1).getStatusIcon() + "] "
                        + storage.get(number - 1).getDescription());
                System.out.println("-----------------------------------------------");
                reply();
            } else {
                storage.add(task);
                System.out.println("-----------------------------------------------");
                System.out.println("added: " + response);
                System.out.println("-----------------------------------------------");
                reply();
            }
        } else if(unmark ) {
            String[] parts = response.split(" ");
            String part2 = parts[1];
            if(isInteger(part2) && parts.length == 2) {
                int number = Integer.parseInt(part2);
                storage.get(number- 1).unMark();
                System.out.println("-----------------------------------------------");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("["+ storage.get(number -1).getStatusIcon() + "] "
                        + storage.get(number - 1).getDescription());
                System.out.println("-----------------------------------------------");
                reply();
            } else {
                storage.add(task);
                System.out.println("-----------------------------------------------");
                System.out.println("added: " + response);
                System.out.println("-----------------------------------------------");
                reply();
            }
        } else {
            storage.add(task);
            System.out.println("-----------------------------------------------");
            System.out.println("added: " + response);
            System.out.println("-----------------------------------------------");
            reply();
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n" );
        reply();
    }
}

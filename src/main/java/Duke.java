import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        final int MAXSIZE = 100;
        String[] storedItems = new String[MAXSIZE];
        boolean[] status = new boolean[MAXSIZE];
        int index = 0;
        while (true) {
            String str = sc.nextLine();
            if ("bye".equals(str)) {
                System.out.println("Bye! Hope to see you again soon");
                break;
            } else if(str.length() > 5 && str.substring(0, 5).equals("mark ")) {
                String[] s = str.split(" ");
                if (s.length > 1) {
                    int i = Integer.parseInt(s[1]) - 1;
                    status[i] = true;
                    System.out.println("Marked task " + (i + 1) + " as done!");
                    System.out.printf("%d. [%s] %s\n", i + 1, status[i] ? "x" : " ", storedItems[i]);
                }
            } else if(str.length() > 7 && str.substring(0, 7).equals("unmark ")) {
                String[] s = str.split(" ");
                if (s.length > 1) {
                    int i = Integer.parseInt(s[1]) - 1;
                    status[i] = false;
                    System.out.println("Marked task " + (i + 1) + " as not done!");
                    System.out.printf("%d. [%s] %s\n", i + 1, status[i] ? "x" : " ", storedItems[i]);
                }
            } else if ("list".equals(str)) {
                int p = 0;
                while (p < MAXSIZE && storedItems[p] != null) {
                    System.out.printf("%d. [%s] %s\n", p + 1, status[p] ? "x" : " ", storedItems[p]);
                    p++;
                }
            } else {
                storedItems[index++] = str;
                System.out.println("Added: " + str);
            }
        }
    }
}


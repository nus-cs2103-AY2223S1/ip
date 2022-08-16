import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        int counter = 0;
        String[] list = new String[100];
        while (counter < 100) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (str.equals("list")) {
                for (int x = 0; x < counter; x++) {
                    System.out.println((x+1) + ". " + list[x]);
                }
                continue;
            }
            list[counter] = str;
            counter++;
        }
    }
}

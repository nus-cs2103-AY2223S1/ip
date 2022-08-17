import java.util.Objects;
import java.util.Scanner;

public class Duke {

    public static void echoUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t-----------------------------------------------");
        System.out.println("\tHello! I'm Duke Dukem\n\tWhat can I do for you?");
        System.out.println("\t-----------------------------------------------");

        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            if (line.equals("bye")) {
                scanner.close();
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t-----------------------------------------------");
                break;
            } else {
                System.out.println("\t-----------------------------------------------");
                System.out.println("\t" + line);
                System.out.println("\t-----------------------------------------------");
            }
        }
    }

    public static void echoBackList() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t-----------------------------------------------");
        System.out.println("\tHello! I'm Duke Dukem\n\tWhat can I do for you?");
        System.out.println("\t-----------------------------------------------");

        String[] lst = new String[100];
        int counter = 0;

        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            if (line.equals("bye")) {
                scanner.close();
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t-----------------------------------------------");
                break;
            } else if (line.equals("list")) {
              if (counter > 0) {
                  System.out.println("\t-----------------------------------------------");
                  for (int i = 0; i < counter; i++) {
                      System.out.println("\t"+ (i+1) + ". " + lst[i] + "\n");
                  }
                  System.out.println("\t-----------------------------------------------");
              } else { // lst is not initalized
                  System.out.println("\t-----------------------------------------------");
                  System.out.println("\tList is empty!");
                  System.out.println("\t-----------------------------------------------");
              }
            } else {
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tadded: " + line);
                System.out.println("\t-----------------------------------------------");
                if (counter == 0) {
                    lst = new String[100];
                }
                lst[counter] = line;
                counter++;
            }
        }

    }
    public static void main(String[] args) {
        echoBackList();
    }
}

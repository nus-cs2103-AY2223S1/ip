import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] listOfItems = new String[100];
        int indexOfNextItem = 0;
        System.out.println("Hello! I'm Don\nHow may I help you?");
        while (true) {
            String response = sc.nextLine();
            if (response.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (response.equals("list")) {
                for (int index = 0; index < indexOfNextItem; index++) {
                    System.out.println(index+1 + ". " + listOfItems[index]);
                }
            }
            else {
                listOfItems[indexOfNextItem++] = response;
                System.out.println("added: " + response);
            }
        }
    }
}

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Hello! I'm Snoopy\n");
        System.out.println("What can I do for you?\n");
        String response = myObj.nextLine();
        String[] tasks = new String[100];
        int i=0;
        while (!response.equals("bye")) {
            if (response.equals("list")) {
                for (int a=1; a<=i; a++) {
                    String output = a + ". " + tasks[a-1];
                    System.out.println(output);
                }
            } else {
                tasks[i] = response;
                System.out.println("added: " + response);
                i++;
            }
            response = myObj.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

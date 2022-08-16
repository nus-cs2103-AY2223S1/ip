import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Hello! I'm Snoopy");
        System.out.println("What can I do for you?");
        String response = myObj.nextLine();
        Task[] tasks = new Task[100];
        int i=0;
        while (!response.equals("bye")) {
            if (response.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int a=1; a<=i; a++) {
                    String output = a + ".[" + tasks[a-1].getStatusIcon() + "] " + tasks[a-1].description;
                    System.out.println(output);
                }
            } else {
                tasks[i] = new Task(response);
                System.out.println("added: " + response);
                i++;
            }
            response = myObj.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

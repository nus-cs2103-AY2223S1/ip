import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Hello! I'm Snoopy");
        System.out.println("What can I do for you?");
        String input = myObj.nextLine();
        String[] words = input.split(" ");
        String response = words[0];
        Task[] tasks = new Task[100];
        int i=0;
        while (!response.equals("bye")) {
            if (response.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int a=1; a<=i; a++) {
                    String output = a + ".[" + tasks[a-1].getStatusIcon() + "] " + tasks[a-1].description;
                    System.out.println(output);
                }
            } else if (response.equals("mark")) {
                String taskNumber = words[1];
                int number = Integer.parseInt(taskNumber);
                tasks[number-1].markAsDone();
            } else if (response.equals("unmark")) {
                String taskNumber = words[1];
                int number = Integer.parseInt(taskNumber);
                tasks[number-1].markNotDone();
            } else {
                tasks[i] = new Task(input);
                System.out.println("added: " + input);
                i++;
            }
            input = myObj.nextLine();
            words = input.split(" ");
            response = words[0];
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Task[] arr = new Task[100];
        int count = 0;

        System.out.println("Hello I'm Duke" + "\nWhat can I do for you?");

        //scanner
        Scanner sc = new Scanner(System.in);
        String input = "";


        while (true) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i ++) {
                    System.out.println((i + 1) + ". " + "[" + arr[i].getStatusIcon() + "] " + arr[i].description);
                }
            } else if (input.contains("mark")) {
                int index = -1;
                input = input.replaceAll("mark ", "");
                index = Integer.parseInt(input) - 1;
                arr[index].setDone();

                System.out.println("Nice! I've marked this task as done:\n" +
                        "[" + arr[index].getStatusIcon() + "] " + arr[index].description);
            } else {
                arr[count] = new Task(input);
                System.out.println("added: " + input);
                count++;
            }
        }
        sc.close();


    }
}
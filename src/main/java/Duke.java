import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String[] arr = new String[100];
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
                    System.out.println((i + 1) + ". " + arr[i]);
                }
            } else {
                arr[count] = input;
                System.out.println("added: " + input);
                count++;
            }
        }
        sc.close();


    }
}
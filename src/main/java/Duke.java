import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        String input = "";
        int index = 0;
        Task[] items = new Task[100];

        while(!input.equals("bye")) {
            input = sc.nextLine();
            String words[] = input.split(" "); // splits sentences

            if (input.equals("list")) {
                // when user request list
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < items.length; i++) {
                    if ((items[i]) == null) {
                        break;
                    }
                    System.out.println(i+1 + ". " + items[i]);
                }
            }
            else if (words[0].equals("mark")) {
                // when user wants to mark as done
                int num = Integer.valueOf(words[1]) - 1;
                if (items[num] == null) {
                    System.out.println("No such task");
                }
                else {
                    System.out.println(items[num].markAsDone());
                }
            }
            else if (words[0].equals("unmark")) {
                // when user wants to mark as not done
                int num = Integer.valueOf(words[1]) - 1;
                if (items[num] == null) {
                    System.out.println("No such task");
                }
                else {
                    System.out.println(items[num].markAsNotDone());
                }
            }
            else if (!input.equals("list") && !input.equals("bye")) {
                // when user wants to add task
                items[index++] = new Task(input);
                System.out.println("added: "+ input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

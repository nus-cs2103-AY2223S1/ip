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
            String phrases[] = input.split(" /.. ", 2); // splits sentence and removes by/at
            String words[] = phrases[0].split(" ", 2);

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
            else {
                switch(words[0]) {
                    case "mark":
                        // when user wants to mark as done
                        int num = Integer.valueOf(words[1]) - 1;
                        if (items[num] == null) {
                            System.out.println("No such task");
                        }
                        else {
                            System.out.println(items[num].markAsDone());
                        }
                        break;

                    case "unmark":
                        // when user wants to mark as not done
                        int numb = Integer.valueOf(words[1]) - 1;
                        if (items[numb] == null) {
                            System.out.println("No such task");
                        }
                        else {
                            System.out.println(items[numb].markAsNotDone());
                        }
                        break;

                    case "todo":
                        // when user wants to add todo task
                        items[index] = new Todo(words[1]);
                        System.out.println("Got it. I've added this task:\n  " + items[index++] +"\n" + Task.getCount());
                        break;

                    case "deadline":
                        // when user wants to add deadline task
                        items[index] = new Deadline(words[1], phrases[1]);
                        System.out.println("Got it. I've added this task:\n  " + items[index++] +"\n" + Task.getCount());
                        break;

                    case "event":
                        // when user wants to add event task
                        items[index] = new Event(words[1], phrases[1]);
                        System.out.println("Got it. I've added this task:\n  " + items[index++] +"\n" + Task.getCount());
                        break;

                    case "bye":
                        break;

                    default:
                        System.out.println("No such command");

                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

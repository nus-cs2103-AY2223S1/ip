import java.util.Scanner;
public class Duke {
    private static Task[] storage = new Task[100];
    private static int i = 0;
    public static void main(String[] args) {
        System.out.println("Hey Dude here. What you want?");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.startsWith("mark ")) {
                int intCollect = Integer.parseInt(userInput.substring(5));
                storage[intCollect - 1].mark();
            } else if (userInput.startsWith("unmark ")) {
                int intCollect = Integer.parseInt(userInput.substring(7));
                storage[intCollect - 1].unmark();
            } else if (userInput.equals("list")) {
                int j = 0;
                while (storage[j] != null) {
                    System.out.println((j + 1) + ". " + storage[j].toString());
                    j++;
                }
            } else if (userInput.startsWith("todo ")) {
                storage[i] = new ToDo(userInput.substring(5));
                i++;
                System.out.println("Good luck... You already have " + i + " tasks in your list :p");
            } else if (userInput.startsWith("deadline ")) {
                String[] box = userInput.substring(9).split(" /by ");
                storage[i] = new Deadline(box[0], box[1]);
                i++;
                System.out.println("Good luck... You already have " + i + " tasks in your list :p");
            } else if (userInput.startsWith("event ")) {
                String[] box = userInput.substring(6).split(" /at ");
                storage[i] = new Event(box[0], box[1]);
                i++;
                System.out.println("Good luck... You already have " + i + " tasks in your list :p");
            }
            else {
                storage[i] = new Task(userInput);
                i++;
                System.out.println("added: " + userInput);
            }
            userInput = sc.nextLine();
        }
        sc.close();
        System.out.println("Bye bye go away!");
    }
}

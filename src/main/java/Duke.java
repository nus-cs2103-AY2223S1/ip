import java.util.Scanner;
public class Duke {
    private static Task[] storage = new Task[100];
    private static int i = 0;
    public static void main(String[] args) {
        System.out.println("Hey AhBeng here. What you want?");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.startsWith("mark ")) {
                int intCollect = Integer.parseInt(userInput.substring(5));
                storage[intCollect - 1].mark();
            } else if (userInput.startsWith("unmark ")) {
                int intCollect = Integer.parseInt(userInput.substring(7));
                storage[intCollect - 1].unmark();
            }
            else if (userInput.equals("list")) {
                int j = 0;
                while (storage[j] != null) {
                    System.out.println((j + 1) + ". " + storage[j].toString());
                    j++;
                }
            } else {
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

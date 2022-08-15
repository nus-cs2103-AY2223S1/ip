import java.util.Scanner;
public class Duke {
    private static Task[] storage = new Task[100];
    private static int i = 0;
    public static void main(String[] args) {
        System.out.println("Hey AhBeng here. What you want?");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.length() > 5 && userInput.substring(0, 5).equals("mark ")) {
                int intCollect = Integer.parseInt(userInput.substring(5));
                storage[intCollect - 1].mark();
                System.out.println("Helped you to mark already! Happy?\n"
                        + storage[intCollect - 1].toString());
            } else if (userInput.length() > 7 && userInput.substring(0, 7).equals("unmark ")) {
                int intCollect = Integer.parseInt(userInput.substring(7));
                storage[intCollect - 1].unmark();
                System.out.println("Troublesome... Unmarked for you already.\n"
                        + storage[intCollect - 1].toString());
            } else if (userInput.equals("list")) {
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

import java.util.Scanner;
public class Duke {
    private static String[] storage = new String[100];
    private static int i = 0;
    public static void main(String[] args) {
        System.out.println("Hey AhBeng here. What you want?");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                int j = 0;
                while (storage[j] != null) {
                    System.out.println((j+1) + ". " + storage[j]);
                    j++;
                }
                userInput = sc.nextLine();
            } else {
                storage[i] = userInput;
                i++;
                System.out.println("added: " + userInput);
                userInput = sc.nextLine();
            }
        }
        sc.close();
        System.out.println("Bye bye go away!");
    }
}

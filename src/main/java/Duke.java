import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hi... I'm Bishop... ");
        System.out.println("What can I do for you today?");

        String[] list = new String[100];
        int index = 0;

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            if (ans.equals("bye")) {
                System.out.println("Goodbye...");
                break;
            } else if (ans.equals("list")) {
                for (int i = 0; i < index; i++) System.out.println((i + 1) + ". " + list[i]);
            } else {
                list[index] = ans;
                index++;
                System.out.println("added: " + ans);
            }
        };

        scanner.close();
    }
}

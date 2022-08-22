import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "Hello";
        String bye = "Goodbye";
        System.out.println(greeting);

        String[] list = new String[100];
        int items = 0;

        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 1; i < items + 1; i++) {
                    System.out.println(i + ". " + list[i - 1]);
                }
            } else {
                list[items] = input;
                items++;
                System.out.println("added: " + input);
            }
            input = sc.nextLine();
        }
        System.out.println(bye);
        return;
    }
}

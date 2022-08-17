import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Bot bot = new Bot();
        Scanner sc = new Scanner(System.in);

        System.out.println(bot.introduce());
        while (true) {
            String input = sc.nextLine();
            System.out.println(bot.answer(input));
            if (input.equals("bye")) {
                break;
            }
        }
    }
}


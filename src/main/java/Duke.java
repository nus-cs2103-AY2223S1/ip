import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
      ChatBot dukeChatBot = new ChatBot();

      Scanner sc = new Scanner(System.in);
      while (true) {
          System.out.println("Enter your command:");
          String input = sc.nextLine();
          String message = dukeChatBot.echo(input);
          if ("bye".equalsIgnoreCase(message)) {
              break;
          }
      }
      sc.close();
    }
}

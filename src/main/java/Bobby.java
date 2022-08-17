import java.util.Scanner;

public class Bobby {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BobbyChat.sayHello();

        while(true) {
            String chat = scanner.nextLine();
            switch(chat) {
                case "bye" :
                    BobbyChat.sayBye();
                    break;
                default:
                    BobbyChat.echo(chat);
            }
        }
    }
}

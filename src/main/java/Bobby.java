import java.util.Scanner;

public class Bobby {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BobbyList bobbyList = new BobbyList();

        BobbyChat.sayHello();

        while(true) {
            String chat = scanner.nextLine();
            switch(chat) {
                case "bye" :
                    BobbyChat.sayBye();
                    break;
                case "list" :
                    BobbyChat.echo(bobbyList.toString());
                    break;
                default:
                    bobbyList.add(chat);
                    BobbyChat.added(chat);
            }
        }
    }
}

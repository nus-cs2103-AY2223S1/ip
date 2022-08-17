import java.util.Scanner;

public class Dobby {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DobbyList dobbyList = new DobbyList();

        DobbyChat.sayHello();

        while(true) {
            String chat = scanner.nextLine();

            if (chat.equals("bye")) {
                DobbyChat.sayBye();
                break;

            } else if (chat.equals("list")) {
                DobbyChat.echo(dobbyList.toString());

            } else if (chat.startsWith("mark")) {
                int toMark = Integer.parseInt(chat.substring(5));
                dobbyList.mark(toMark);
                DobbyChat.marked(dobbyList.getTask(toMark));
            } else if (chat.startsWith("unmark")){
                int toUnmark = Integer.parseInt(chat.substring(7));
                dobbyList.mark(toUnmark);
                DobbyChat.unmarked(dobbyList.getTask(toUnmark));
            } else {
                dobbyList.add(chat);
                DobbyChat.added(chat);
            }
        }
    }
}

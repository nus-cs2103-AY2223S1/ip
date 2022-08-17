import java.util.Scanner;

public class Dobby {
    private static String getDate(String s, String task) {
        if(task == "deadline") {
            int i = s.indexOf("/by");
            if(i == -1) {
                DobbyChat.noDeadlineDate();
            }
            return s.substring(i + 4);

        } else{
            int i = s.indexOf("/at");
            if(i == - 1) {
                DobbyChat.noEventDate();
            }
            return s.substring(i + 4);
        }
    }
    private static String getTask(String s, String task) {
        if(task == "todo") {
            return s.substring(5);
        } else if(task == "deadline") {
            return s.substring(9);
        } else{
            return s.substring(6);
        }
    }
/*
    //future to-add: specify whith list to add to?
    private static void addTask(Task task, DobbyList list) {}

 */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DobbyList dobbyList = new DobbyList();

        DobbyChat.sayHello();

        while(true) {
            String chat = scanner.nextLine();

            if(chat.equals("bye")) {
                DobbyChat.sayBye();
                break;

            } else if(chat.equals("list")) {
                DobbyChat.echo(dobbyList.toString());

            } else if(chat.startsWith("mark")) {
                int toMark = Integer.parseInt(chat.substring(5));
                dobbyList.mark(toMark);
                DobbyChat.marked(dobbyList.getTask(toMark));

            } else if(chat.startsWith("unmark")) {
                int toUnmark = Integer.parseInt(chat.substring(7));
                dobbyList.mark(toUnmark);
                DobbyChat.unmarked(dobbyList.getTask(toUnmark));

            } else if(chat.startsWith("todo")) {
                String task = getTask(chat, "todo");
                Todo newTodo = new Todo(chat);

                dobbyList.add(newTodo);
                DobbyChat.added(newTodo, dobbyList);

            } else if(chat.startsWith("deadline")) {
                String task = getTask(chat, "deadline");
                String date = getDate(chat, "deadline");
                Deadline newDeadline = new Deadline(task, date);

                dobbyList.add(newDeadline);
                DobbyChat.added(newDeadline, dobbyList);

            } else if(chat.startsWith("event")) {
                String task = getTask(chat, "event");
                String date = getDate(chat, "event");
                Event newEvent = new Event(task, date);

                dobbyList.add(newEvent);
                DobbyChat.added(newEvent, dobbyList);

            }
        }
    }
}

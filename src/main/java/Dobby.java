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
    //future to-add: specify which list to add to?
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
                try {
                    int toMark = Integer.parseInt(chat.substring(5));
                    dobbyList.mark(toMark);
                    DobbyChat.marked(dobbyList.getTaskString(toMark));

                } catch(StringIndexOutOfBoundsException e) {
                    DobbyChat.noTaskNumber();
                    continue;

                } catch(NumberFormatException e) {
                    DobbyChat.noNumber();
                }
            } else if(chat.startsWith("unmark")) {
                try {
                    int toUnmark = Integer.parseInt(chat.substring(7));
                    dobbyList.mark(toUnmark);
                    DobbyChat.unmarked(dobbyList.getTaskString(toUnmark));

                } catch(StringIndexOutOfBoundsException e) {
                    DobbyChat.noTaskNumber();
                    continue;
                } catch(NumberFormatException e) {
                    DobbyChat.noNumber();
                }
            } else if(chat.startsWith("delete")) {
                try {
                    int toDelete = Integer.parseInt(chat.substring(7)) - 1;
                    DobbyChat.deleted(dobbyList.getTask(toDelete), dobbyList);
                    dobbyList.delete(toDelete);

                } catch(StringIndexOutOfBoundsException e) {
                    DobbyChat.noTaskNumber();
                    continue;
                } catch(NumberFormatException e) {
                    DobbyChat.noNumber();
                } catch(IndexOutOfBoundsException e) {
                    DobbyChat.tooLittleTasks();
                }
            } else if(chat.startsWith("todo")) {
                try {
                    String task = getTask(chat, "todo");
                    Todo newTodo = new Todo(chat);

                    if(task.isBlank()) {
                        DobbyChat.noTask();
                    } else {
                        dobbyList.add(newTodo);
                        DobbyChat.added(newTodo, dobbyList);
                    }
                } catch(StringIndexOutOfBoundsException e) {
                    DobbyChat.noTask();
                    continue;
                }
            } else if(chat.startsWith("deadline")) {
                String task = getTask(chat, "deadline");
                String date = getDate(chat, "deadline");

                if(task.isEmpty()) {
                    DobbyChat.noTask();
                } else if(date.isBlank()) {
                    DobbyChat.noDeadlineDate();
                } else {
                    Deadline newDeadline = new Deadline(task, date);
                    dobbyList.add(newDeadline);
                    DobbyChat.added(newDeadline, dobbyList);
                }

            } else if(chat.startsWith("event")) {
                String task = getTask(chat, "event");
                String date = getDate(chat, "event");

                if(task.isEmpty()) {
                    DobbyChat.noTask();
                } else if(date.isBlank()) {
                    DobbyChat.noDeadlineDate();
                } else {
                    Event newEvent = new Event(task, date);
                    dobbyList.add(newEvent);
                    DobbyChat.added(newEvent, dobbyList);
                }
            } else {
                DobbyChat.unknown();

            }
        }
    }
}

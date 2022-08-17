import java.util.Scanner;

public class Dobby {
    private static Scanner scanner = new Scanner(System.in);
    private static DobbyList dobbyList = new DobbyList();

    private static String getDate(String s, String task) {
        int i = s.indexOf(task =="deadline" ? "/by" : "/at");
        if (i == -1) {
            return "noDate";
        }
        return s.substring(i + 4);
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
    private static void toList(String s) {
        DobbyChat.echo(dobbyList.toString());
    }
    private static void toMark(String s) {
        try {
            int toMark = Integer.parseInt(s.substring(5));
            if(toMark <= 0) {
                DobbyChat.wrongTaskNumber();
            } else if(dobbyList.getTask(toMark).isMarked()) {
                DobbyChat.alreadyMarked();
            } else {
                dobbyList.mark(toMark);
                DobbyChat.marked(dobbyList.getTaskString(toMark - 1));
            }
        } catch(StringIndexOutOfBoundsException e) {
            DobbyChat.noTaskNumber();
        } catch(NumberFormatException e) {
            DobbyChat.noNumber();
        } catch(IndexOutOfBoundsException e) {
            DobbyChat.tooLittleTasks();
        }
    }
    private static void toUnmark(String s) {
        try {
            int toUnmark = Integer.parseInt(s.substring(7));
            if(toUnmark <= 0) {
                DobbyChat.wrongTaskNumber();
            } else if(!(dobbyList.getTask(toUnmark).isMarked())) {
                DobbyChat.alreadyUnmarked();
            }else {
                dobbyList.unmark(toUnmark);
                DobbyChat.unmarked(dobbyList.getTaskString(toUnmark - 1));
            }
        } catch(StringIndexOutOfBoundsException e) {
            DobbyChat.noTaskNumber();
        } catch(NumberFormatException e) {
            DobbyChat.noNumber();
        } catch(IndexOutOfBoundsException e) {
            DobbyChat.tooLittleTasks();
        }

    }
    private static void toDelete(String s) {
        try {
            int toDelete = Integer.parseInt(s.substring(7));
            if(dobbyList.getLength() == 0) {
                DobbyChat.noTaskToDelete();
            } else if(toDelete > dobbyList.getLength()) {
                DobbyChat.tooLittleTasks();
            } else if(toDelete <= 0) {
                DobbyChat.wrongTaskNumber();
            } else {
                DobbyChat.deleted(dobbyList.getTask(toDelete), dobbyList);
                dobbyList.delete(toDelete);
            }
        } catch(StringIndexOutOfBoundsException e) {
            DobbyChat.noTaskNumber();
        } catch(NumberFormatException e) {
            DobbyChat.noNumber();
        }
    }
    private static void toAddTodo(String s) {
        try {
            String task = getTask(s, "todo");
            Todo newTodo = new Todo(task);

            if(task.isBlank()) {
                DobbyChat.noTask();
            } else {
                dobbyList.add(newTodo);
                DobbyChat.added(newTodo, dobbyList);
            }
        } catch(StringIndexOutOfBoundsException e) {
            DobbyChat.noTask();
        }
    }
    private static void toAddDeadline(String s) {
        try {
            String task = getTask(s, "deadline");
            String date = getDate(s, "deadline");
            Deadline newDeadline = new Deadline(task, date);

            if(task.isBlank()) {
                DobbyChat.noTask();
            } else if(date == "noDate") {
                DobbyChat.noDeadlineDate();
            } else {
                dobbyList.add(newDeadline);
                DobbyChat.added(newDeadline, dobbyList);
            }
            //only when there is no space behind "deadline", else is handled above
        } catch(StringIndexOutOfBoundsException e) {
            DobbyChat.noTask();
        }
    }
    private static void toAddEvent(String s) {
        try {
            String task = getTask(s, "event");
            String date = getDate(s, "event");
            Event newEvent = new Event(task, date);

            if(task.isEmpty()) {
                DobbyChat.noTask();
            } else if(date == "noDate") {
                DobbyChat.noEventDate();
            } else {
                dobbyList.add(newEvent);
                DobbyChat.added(newEvent, dobbyList);
            }
            //only when there is no space behind "deadline", else is handled above
        } catch(StringIndexOutOfBoundsException e) {
            DobbyChat.noTask();
        }
    }

    public static void main(String[] args) {


        DobbyChat.sayHello();

        while(true) {
            String command = scanner.nextLine();

            if(command.equals("bye")) {
                DobbyChat.sayBye();
                break;
            } else if(command.equals("list")) {
                toList(command);
            } else if(command.startsWith("mark")) {
                toMark(command);
            } else if(command.startsWith("unmark")) {
                toUnmark(command);
            } else if(command.startsWith("delete")) {
                toDelete(command);
            } else if(command.startsWith("todo")) {
                toAddTodo(command);
            } else if(command.startsWith("deadline")) {
                toAddDeadline(command);
            } else if(command.startsWith("event")) {
                toAddEvent(command);
            } else {
                DobbyChat.unknown();
            }
        }
    }
}

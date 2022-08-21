import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Dobby {
    private static Scanner scanner = new Scanner(System.in);
    private static DobbyList dobbyList = new DobbyList();

//    //DONE
//    private static String getDate(String s, String task) throws DateTimeParseException {
//        String dateFormatted = "";
//        try {
//            int i = s.indexOf(task == "deadline" ? "/by" : "/at");
//            if (i == -1) {
//                return "noDate";
//            }
//            String dateString = s.substring(i + 4);
//            DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
//            LocalDateTime date = LocalDateTime.parse(dateString, form);
//            dateFormatted = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
//            return dateFormatted;
//        } catch (DateTimeParseException e) {
//            DobbyChat.wrongDateFormat();
//        }
//        return "wrongDateFormat";
//    }

//    //DONE
//    private static String getTask(String s, String task) {
//
//        if(task == "todo") {
//            return s.substring(5);
//        } else {
//            int startIndex = task.length() + 1;
//            int endIndex = s.indexOf("/");
//
//            return s.substring(startIndex, endIndex);
//        }
//    }

//    //DONE
//    private static void toList(String s) {
//        if (dobbyList.isEmpty()) {
//            DobbyChat.listEmpty();
//        } else {
//            DobbyChat.echo(dobbyList.toString());
//        }
//    }

//    //DONE
//    private static void toMark(String s) {
//        try {
//            int toMark = Integer.parseInt(s.substring(5));
//            if(toMark <= 0) {
//                DobbyChat.wrongTaskNumber();
//            } else if(dobbyList.getTask(toMark).isMarked()) {
//                DobbyChat.alreadyMarked();
//            } else {
//                dobbyList.mark(toMark);
//                DobbyChat.marked(dobbyList.getTaskString(toMark - 1));
//            }
//        } catch(StringIndexOutOfBoundsException e) {
//            DobbyChat.noTaskNumber();
//        } catch(NumberFormatException e) {
//            DobbyChat.noNumber();
//        } catch(IndexOutOfBoundsException e) {
//            DobbyChat.tooLittleTasks();
//        }
//    }
//    private static void toMark() {
//        int listLen = dobbyList.getLength();
//        for(int i = 1; i <= listLen; i++) {
//            dobbyList.mark(i);
//        }
//        DobbyChat.allMarked();
//    }

//    //done
//    private static void toUnmark(String s) {
//        try {
//            int toUnmark = Integer.parseInt(s.substring(7));
//            if(toUnmark <= 0) {
//                DobbyChat.wrongTaskNumber();
//            } else if(!(dobbyList.getTask(toUnmark).isDone())) {
//                DobbyChat.alreadyUnmarked();
//            }else {
//                dobbyList.unmark(toUnmark);
//                DobbyChat.unmarked(dobbyList.getTaskString(toUnmark - 1));
//            }
//        } catch(StringIndexOutOfBoundsException e) {
//            DobbyChat.noTaskNumber();
//        } catch(NumberFormatException e) {
//            DobbyChat.noNumber();
//        } catch(IndexOutOfBoundsException e) {
//            DobbyChat.tooLittleTasks();
//        }
//
//    }
//    private static void toUnmark() {
//        int listLen = dobbyList.getLength();
//        for(int i = 1; i <= listLen; i++) {
//            dobbyList.unmark(i);
//        }
//        DobbyChat.allUnmarked();
//    }

//    //DONE
//    private static void toDelete(String s) {
//        try {
//            int toDelete = Integer.parseInt(s.substring(7));
//            if(dobbyList.getLength() == 0) {
//                DobbyChat.noTaskToDelete();
//            } else if(toDelete > dobbyList.getLength()) {
//                DobbyChat.tooLittleTasks();
//            } else if(toDelete <= 0) {
//                DobbyChat.wrongTaskNumber();
//            } else {
//                DobbyChat.deleted(dobbyList.getTask(toDelete), dobbyList);
//                dobbyList.delete(toDelete);
//            }
//        } catch(StringIndexOutOfBoundsException e) {
//            DobbyChat.noTaskNumber();
//        } catch(NumberFormatException e) {
//            DobbyChat.noNumber();
//        }
//    }
//    //delete all feature to be added
//    private static void toDelete() {
//        int listLen = dobbyList.getLength();
//        for(int i  = 1; i <= listLen; i++) {
//            dobbyList.delete(i);
//        }
//    }

//    //DONE
//    private static void toAddTodo(String s) {
//        try {
//            String task = getTask(s, "todo");
//            Todo newTodo = new Todo(task);
//
//            if(task.isBlank()) {
//                DobbyChat.noTaskDesc();
//            } else {
//                dobbyList.add(newTodo);
//                DobbyChat.added(newTodo, dobbyList);
//            }
//        } catch(StringIndexOutOfBoundsException e) {
//            DobbyChat.noTaskDesc();
//        }
//    }
//    private static void toAddDeadline(String s) {
//        try {
//            String task = getTask(s, "deadline");
//            String date = getDate(s, "deadline");
//            Deadline newDeadline = new Deadline(task, date);
//
//            if(task.isBlank()) {
//                DobbyChat.noTaskDesc();
//            } else if(date == "wrongDateFormat") {
//
//            }else if(date == "noDate") {
//                DobbyChat.noDeadlineDate();
//            }  else {
//                dobbyList.add(newDeadline);
//                DobbyChat.added(newDeadline, dobbyList);
//            }
//            //only when there is no space behind "deadline", else is handled above
//        } catch(StringIndexOutOfBoundsException e) {
//            DobbyChat.noTaskDesc();
//        }
//    }

//    //solve getDate (solved)
//    private static void toAddEvent(String s) {
//        try {
//            String task = getTask(s, "event");
//            String date = getDate(s, "event");
//            Event newEvent = new Event(task, date);
//
//            if(task.isEmpty()) {
//                DobbyChat.noTaskDesc();
//            } else if(date == "wrongDateFormat") {
//
//            } else if(date == "noDate") {
//                DobbyChat.noEventDate();
//            } else {
//                dobbyList.add(newEvent);
//                DobbyChat.added(newEvent, dobbyList);
//            }
//            //only when there is no space behind "deadline", else is handled above
//        } catch(StringIndexOutOfBoundsException e) {
//            DobbyChat.noTaskDesc();
//        }
//    }

    private static void dobbyStart() throws IOException {
        DobbyChat.sayHello();
        DobbyIO.load(dobbyList);
        while(true) {
            String command = scanner.nextLine();

            if(command.equals("bye") || command.equals("end") || command.equals("quit")) {
                DobbyIO.save(dobbyList);
                DobbyChat.sayBye();
                break;
            } else if(command.equals("list")) {
                toList(command);
            } else if(command.startsWith("mark")) {
                if(command.contains("all")) {
                    toMark();
                } else {
                    toMark(command);
                }
            } else if(command.startsWith("unmark")) {
                if(command.contains("all")) {
                    toUnmark();
                } else {
                    toUnmark(command);
                }
            } else if(command.startsWith("delete") || command.startsWith("remove")) {
//                delete all feature to be added
//                if(command.contains("all")) {
//                    toDelete();
//                } else {
//                    toDelete(command);
//                }
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

    public static void main(String[] args) throws IOException {
        dobbyStart();
    }
}
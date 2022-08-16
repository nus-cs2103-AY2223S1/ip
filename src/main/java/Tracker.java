import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class Tracker {
    public static List<Task> list = new ArrayList<>();

    public void printList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }


    public void markDone(String num) throws DukeException{
        int index = Integer.parseInt(num);
        if (index < 0 || index > list.size()) {
            throw new InvalidInputException(num, "mark");
        }
        Task task = list.get(index-1);
        task.complete();
    }

    public void markUndone(String num) throws DukeException{
        int index = Integer.parseInt(num);
        if (index < 0 || index > list.size()) {
            throw new InvalidInputException("unmark", num);
        }
        Task task = list.get(index-1);
        task.undo();
    }

    public void addDeadline(String[] arr) throws DukeException {
        int i = 1;
        while (i < arr.length && !arr[i].equals("/by") ) {
            i++;
        }
        if (arr.length == 1 ) {
            throw new MissingInputException("description", arr[0]);
        } else if (arr.length - 1 == i) {
            throw new MissingInputException("date", arr[0]);
        }
        String description = String.join(" ", Arrays.copyOfRange(arr, 1, i));
        String dueDate = String.join(" ", Arrays.copyOfRange(arr, i + 1, arr.length));
        String[] command = {description, dueDate};
        Deadline deadline = new Deadline(command);
        deadline.add();
    }

    public void addTodo(String[] arr) throws DukeException {
        if (arr.length == 1) {
            throw new MissingInputException("description", arr[0]);
        }
        Todo todo = new Todo(String.join(" ", Arrays.copyOfRange(arr, 1, arr.length)));
        todo.add();
    }

    public void addEvent(String[] arr) throws DukeException {
        int i = 1;
        while (i < arr.length && !arr[i].equals("/at") ) {
            i++;
        }
        if (arr.length == 1) {
            throw new MissingInputException("description", arr[0]);
        } else if (arr.length - 1 == i) {
            throw new MissingInputException("date", arr[0]);
        }
        String description = String.join(" ", Arrays.copyOfRange(arr, 1, i));
        String dueDate = String.join(" ", Arrays.copyOfRange(arr, i + 1, arr.length));
        String[] command = {description, dueDate};
        Event event = new Event(command);
        event.add();
    }

    public Tracker() {
    }

    public void simulate() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList();
            } else {
                String[] command = input.split(" ");
                try {
                    if (command.length >= 1) {
                        switch (command[0]) {
                            case "mark":
                                markDone(command[1]);
                                break;
                            case "unmark":
                                markUndone(command[1]);
                                break;
                            case "deadline":
                                addDeadline(command);
                                break;
                            case "todo":
                                addTodo(command);
                                break;
                            case "event":
                                addEvent(command);
                                break;
                            default:
                                throw new UnknownCommand();
                        }
                    }
                } catch (DukeException e) {
                    System.out.println(e);
                }
            }
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();

    }

}

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Parser class to make sense of user input.
 * @author lauralee
 */
public class Parser {

    private Task[] taskArr;

    public Parser() {
        this.taskArr = new Task[100];
    }

    public void userInput() {
        // create scanner to receive user input
        Scanner sc = new Scanner(System.in);
        Ui.printIntro();
        String a = sc.nextLine();

        // if input received is anything but "bye" system will output what the user
        // inputted
        while (!a.equals("bye")) {
            if (a.equals("list")) {
                //lists out all elements in task list
                Ui.printList(this.taskArr);
                a = sc.nextLine();
            } else if (a.contains("unmark")) {
                // if unmark, update status
                char b = a.charAt(7);
                int c = Character.getNumericValue(b);
                this.taskArr[c].unMark();
                a = sc.nextLine();
            } else if (a.contains("mark")) {
                char b = a.charAt(5);
                int c = Character.getNumericValue(b);
                this.taskArr[c].mark();
                a = sc.nextLine();
            } else if (a.contains("delete")) {
                char b = a.charAt(7);
                int c = Character.getNumericValue(b);
                int numberTasksLeft = Task.getNumberTasks() - 1;
                Task deletedTask = this.taskArr[c];
                Ui.printDelete(deletedTask, numberTasksLeft);

                /**
                 * Shifts tasks in task array behind the deleted task one unit
                 * down to replace the deleted task.
                 */
                Task.numberTasks = Task.getNumberTasks() - 1;
                for (int i = (c - 1); i <= numberTasksLeft; i++) {
                    this.taskArr[i] = this.taskArr[i + 1];
                }

                a = sc.nextLine();
            } else if (a.contains("todo")) {
                try {
                    a.substring(5);
                } catch (Exception StringIndexOutOfBoundsException) {
                    DukeException.todoException();
                    a = sc.nextLine();
                }
                String description = a.substring(5);
                Todo newTask = new Todo(description);
                this.taskArr[Task.getNumberTasks()] = newTask;
                Ui.printToDo(newTask);
                a = sc.nextLine();
            } else if (a.contains("deadline")) {
                String description = a.substring(9, a.lastIndexOf("/") - 1);
                String day = a.substring(a.lastIndexOf("/by") + 4);
                String dayDescription = " (by: " + day + ")";
                try {
                    LocalDate.parse(day);
                } catch (DateTimeParseException exception) {
                    DukeException.dateTimeException();
                    a = sc.nextLine();
                }
                Deadline newTask = new Deadline(description, day);
                this.taskArr[Task.getNumberTasks()] = newTask;
                Ui.printDeadline(newTask);
                a = sc.nextLine();
            } else if (a.contains("event")) {
                String description = a.substring(6, a.lastIndexOf("/") - 1);
                String time = a.substring(a.lastIndexOf("/at") + 4);
                String timeDescription = " (at: " + time + ")";
                Event newTask = new Event(description, time);
                this.taskArr[Task.getNumberTasks()] = newTask;
                Ui.printEvent(newTask);
                a = sc.nextLine();
            } else {
                // else
                //                arr[pos] = a;
                //                status[pos] = 0;
                //                pos++;
                //                System.out.println("added: " + a);
                //                a = sc.nextLine();
                DukeException.taskException();
                a = sc.nextLine();
            }
        }
        Ui.printBye();
    }

    public Task[] getTaskArr() {
        return this.taskArr;
    }

}

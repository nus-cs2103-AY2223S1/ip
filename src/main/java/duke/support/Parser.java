package duke.support;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import duke.functions.TaskList;
import duke.functions.Ui;
import duke.tasks.*;

/**
 * Parser class to make sense of user input.
 * @author lauralee
 */
public class Parser {

    private TaskList taskList;

    public Parser() {
        this.taskList = new TaskList();
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
                Ui.printList(this.taskList);
                a = sc.nextLine();
            } else if (a.contains("unmark")) {
                // if unmark, update status
                char b = a.charAt(7);
                int c = Character.getNumericValue(b);
                this.taskList.markIncomplete(c);
                a = sc.nextLine();
            } else if (a.contains("mark")) {
                char b = a.charAt(5);
                int c = Character.getNumericValue(b);
                this.taskList.markComplete(c);
                a = sc.nextLine();
            } else if (a.contains("delete")) {
                char b = a.charAt(7);
                int c = Character.getNumericValue(b);
                int numberTasksLeft = Task.getNumberTasks() - 1;
                Task deletedTask = this.taskList.getTaskArr()[c];
                Ui.printDelete(deletedTask, numberTasksLeft);
                this.taskList.deleteTask(c, numberTasksLeft);
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
                // add task to this TaskList
                this.taskList.addTask(newTask);
                Ui.printToDo(newTask);
                a = sc.nextLine();
            } else if (a.contains("deadline")) {
                String description = a.substring(9, a.lastIndexOf("/") - 1);
                String day = a.substring(a.lastIndexOf("/by") + 4);
                try {
                    LocalDate.parse(day);
                } catch (DateTimeParseException exception) {
                    DukeException.dateTimeException();
                    a = sc.nextLine();
                }
                Deadline newTask = new Deadline(description, day);
                // add task to this TaskList
                this.taskList.addTask(newTask);
                Ui.printDeadline(newTask);
                a = sc.nextLine();
            } else if (a.contains("event")) {
                String description = a.substring(6, a.lastIndexOf("/") - 1);
                String time = a.substring(a.lastIndexOf("/at") + 4);
                Event newTask = new Event(description, time);
                // add task to this TaskList
                this.taskList.addTask(newTask);
                Ui.printEvent(newTask);
                a = sc.nextLine();
            } else if (a.contains("find")) {
                String word = a.substring(5);
                int index = 1;
                Ui.printFind();
                for (int i = 1; i <= Task.getNumberTasks(); i++) {
                    if (this.taskList.getTaskArr()[i].output().contains(word)) {
                        Ui.printFindTasks(index, this.taskList.getTaskArr()[i].output());
                        index++;
                    }
                }
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

    public TaskList getTaskList() {
        return this.taskList;
    }

}

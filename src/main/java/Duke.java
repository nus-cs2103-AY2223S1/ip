import exceptions.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int currEmpty = 0;
    private final static Scanner myScanner = new Scanner(System.in);
    private final static String SEPARATOR = "------------------------------------";

    private void add(Task task) {

        if (currEmpty == 100) {
            System.out.println("List is Already Full, Cannot add anymore item");
            return;
        }
        taskList.add(task);
        System.out.println("added: " + task.toString());
        currEmpty++;
    }

    private void read() {
        if (currEmpty == 0) {
            System.out.println("You have no task");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task curr = taskList.get(i);
            if (curr != null) {
                System.out.println(i + 1 + "." + curr.toString());
            }
        }
    }

    private void mark(int index) throws DukeMissingIndexException {
        if (index >= currEmpty) {
            throw new DukeMissingIndexException();
        }
        taskList.get(index).setDone();
    }

    private void unMark(int index) throws DukeMissingIndexException {
        if (index >= currEmpty) {
            throw new DukeMissingIndexException();
        }
        taskList.get(index).setNotDone();
    }

    private void delete(int index) throws DukeMissingIndexException {
        if (index >= currEmpty) {
            throw new DukeMissingIndexException();
        }
        Task task = taskList.get(index);
        System.out.println("Removed the task \n" + task.toString());
        taskList.remove(index);
    }

    public static void main(String[] args) {

        Duke duke = new Duke();

        System.out.println("Hello! i am Duke");


        while (true) {


            try {
                System.out.println(SEPARATOR);
                System.out.println("What do you want me to do?");
                String command = myScanner.next();
                switch (command) {
                case "list":
                    if (!myScanner.nextLine().isBlank()) {
                        throw new DukeTooManyArgumentException();
                    }

                    System.out.println(SEPARATOR);
                    duke.read();
                    break;
                case "mark":
                    String unParsedIndex = myScanner.nextLine();
                    if (unParsedIndex.isBlank()) {
                        throw new DukeEmptyCommandException();
                    }

                    String[] split = unParsedIndex.split(" ", 3);

                    if (split.length != 2) {
                        throw new DukeTooManyArgumentException();
                    }

                    int index = Integer.parseInt(split[1]);

                    System.out.println(SEPARATOR);

                    if (index <= 0) {
                        throw new DukeArrayOutOfBoundException();
                    } else {
                        duke.mark(index - 1);
                    }

                    break;
                case "unmark":
                    String unParsedIndex1 = myScanner.nextLine();
                    if (unParsedIndex1.isBlank()) {
                        throw new DukeEmptyCommandException();
                    }

                    String[] split1 = unParsedIndex1.split(" ", 3);

                    if (split1.length != 2) {
                        throw new DukeTooManyArgumentException();
                    }

                    int index1 = Integer.parseInt(split1[1]);

                    System.out.println(SEPARATOR);

                    if (index1 < 0) {
                        throw new DukeArrayOutOfBoundException();
                    } else {
                        duke.unMark(index1 - 1);
                    }

                    break;
                case "delete":
                    String unParsedIndex2 = myScanner.nextLine();
                    if (unParsedIndex2.isBlank()) {
                        throw new DukeEmptyCommandException();
                    }

                    String[] split2 = unParsedIndex2.split(" ", 3);

                    if (split2.length != 2) {
                        throw new DukeTooManyArgumentException();
                    }

                    int index2 = Integer.parseInt(split2[1]);

                    System.out.println(SEPARATOR);

                    if (index2 < 0) {
                        throw new DukeArrayOutOfBoundException();
                    } else {
                        duke.delete(index2 - 1);
                    }

                    break;
                case "deadline":
                    String unParsed = myScanner.nextLine();
                    if (unParsed.isBlank()) {
                        throw new DukeEmptyDescriptionException();
                    }
                    String[] descriptionAndBy =  unParsed.split("/by", 2);

                    if (descriptionAndBy.length != 2) {
                        throw new DukeInvalidDescriptionException();
                    }
                    System.out.println(SEPARATOR);

                    Deadline newDeadLine = new Deadline(descriptionAndBy[0], descriptionAndBy[1]);
                    duke.add(newDeadLine);

                    break;
                case "todo":
                    String description = myScanner.nextLine();

                    if (description.isBlank()) {
                        throw new DukeEmptyDescriptionException();
                    }

                    System.out.println(SEPARATOR);

                    ToDo newToDo = new ToDo(description);
                    duke.add(newToDo);

                    break;
                case "event":
                    String unParsed1 = myScanner.nextLine();
                    if (unParsed1.isBlank()) {
                        throw new DukeEmptyDescriptionException();
                    }
                    String[] descriptionAndBy1 =  unParsed1.split("/at", 2);

                    if (descriptionAndBy1.length != 2) {
                        throw new DukeInvalidDescriptionException();
                    }

                    System.out.println(SEPARATOR);

                    Event newDeadLine1 = new Event(descriptionAndBy1[0], descriptionAndBy1[1]);
                    duke.add(newDeadLine1);

                    break;
                case "bye":
                    System.out.println(SEPARATOR);

                    System.out.println("See you later :)");
                    System.exit(0);

                    break;
                default:
                    throw new DukeUnknownCommandException();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Index can only be Integer");
            }


        }


    }

}

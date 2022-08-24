package duke;

import duke.exceptions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private final static Scanner myScanner = new Scanner(System.in);

    public Duke(String filePath) {
        storage = new Storage(filePath);
        new TaskList();
        try {
            storage.load();
            Ui.printSuccessfulLoad();
        } catch (IOException e) {
            Ui.printFailedLoad();
        }
    }

    public static void main(String[] args) {

        Duke duke = new Duke("data/tasks.txt");

        Ui.printWelcome();

        while (true) {

            try {
                Ui.printAskForCommand();
                String line = myScanner.nextLine().trim();
                if (line.equals("list")) {
                    if (line.length() > 4) {
                        throw new DukeTooManyArgumentException();
                    }

                    Ui.printRead();
                    continue;
                } else if (line.startsWith("mark")) {
                    if (line.length() <= 5) {
                        throw new DukeEmptyCommandException();
                    }

                    int index = Integer.parseInt(line.substring(5));

                    if (index <= 0) {
                        throw new DukeArrayOutOfBoundException();
                    }
                    TaskList.mark(index - 1);
                    Ui.printTaskIsDone(index - 1);

                    continue;
                } else if (line.startsWith("unmark")) {
                    if (line.length() <= 7) {
                        throw new DukeEmptyCommandException();
                    }

                    int index = Integer.parseInt(line.substring(7));

                    if (index < 0) {
                        throw new DukeArrayOutOfBoundException();
                    }
                    TaskList.unMark(index - 1);
                    Ui.printTaskIsUndone(index - 1);

                    continue;
                } else if (line.startsWith("delete")) {
                    if (line.length() <= 7) {
                        throw new DukeEmptyCommandException();
                    }

                    int index = Integer.parseInt(line.substring(7));

                    if (index <= 0) {
                        throw new DukeArrayOutOfBoundException();
                    }
                    Ui.printDeleteTask(index - 1);
                    TaskList.delete(index - 1);

                    continue;
                } else if (line.startsWith("deadline")) {
                    if (line.length() <= 9) {
                        throw new DukeEmptyCommandException();
                    }

                    String[] information = line.substring(9).split(" /by ", 3);

                    if (information.length != 2) {
                        throw new DukeInvalidDescriptionException();
                    }

                    Task newTask = new Deadline(information[0], information[1]);
                    TaskList.add(newTask);
                    Ui.printAddTask(newTask);
                    continue;
                } else if (line.startsWith("event")) {
                    if (line.length() <= 6) {
                        throw new DukeEmptyCommandException();
                    }

                    String[] information = line.substring(9).split(" /at ", 3);

                    if (information.length != 2) {
                        throw new DukeInvalidDescriptionException();
                    }

                    Task newTask = new Event(information[0], information[1]);
                    TaskList.add(newTask);
                    Ui.printAddTask(newTask);
                    continue;
                } else if (line.startsWith("todo")) {
                    if (line.length() <= 5) {
                        throw new DukeEmptyCommandException();
                    }
                    String description = line.substring(5);

                    Task newTask = new ToDo(description);
                    TaskList.add(newTask);
                    Ui.printAddTask(newTask);
                    continue;
                } else if (line.equals("bye")) {

                    duke.storage.save();
                    Ui.printExit();
                    System.exit(0);
                } else {
                    throw new DukeUnknownCommandException();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Index can only be Integer");
            } catch (DateTimeException e) {
                System.out.println("Sorry i could not recognize the date. Pls use this format \"YYYY-MM-DD\"");
            }


        }


    }

}

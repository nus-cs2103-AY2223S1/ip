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
    private final static String SEPARATOR = "------------------------------------";

    public Duke(String filePath) {
        storage = new Storage(filePath);
        new TaskList();
        try {
            storage.load();
            System.out.println("Successfully retrieved most recent TaskList");
        } catch (IOException e) {
            System.out.println("Error loading file, an empty TaskList is initialised");
        }

    }

    public static void main(String[] args) {

        Duke duke = new Duke("data/tasks.txt");


        System.out.println("Hello! i am duke.Duke");


//        try {
//            duke.load();
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            System.out.println("Initiating an Empty duke.Task List");
//       }

        while (true) {

            try {
                System.out.println(SEPARATOR);
                System.out.println("What do you want me to do?");
                String line = myScanner.nextLine().trim();
                if (line.equals("list")) {
                    if (line.length() > 4) {
                        throw new DukeTooManyArgumentException();
                    }

                    System.out.println(SEPARATOR);
                    TaskList.read();
                    continue;
                } else if (line.startsWith("mark")) {
                    if (line.length() <= 5) {
                        throw new DukeEmptyCommandException();
                    }

                    int index = Integer.parseInt(line.substring(5));

                    System.out.println(SEPARATOR);

                    if (index <= 0) {
                        throw new DukeArrayOutOfBoundException();
                    }
                    TaskList.mark(index - 1);

                    continue;
                } else if (line.startsWith("unmark")) {
                    if (line.length() <= 7) {
                        throw new DukeEmptyCommandException();
                    }

                    int index = Integer.parseInt(line.substring(7));

                    System.out.println(SEPARATOR);

                    if (index < 0) {
                        throw new DukeArrayOutOfBoundException();
                    }
                    TaskList.unMark(index - 1);

                    continue;
                } else if (line.startsWith("delete")) {
                    if (line.length() <= 7) {
                        throw new DukeEmptyCommandException();
                    }

                    int index = Integer.parseInt(line.substring(7));

                    System.out.println(SEPARATOR);

                    if (index < 0) {
                        throw new DukeArrayOutOfBoundException();
                    }
                    TaskList.delete(index - 1);


                    continue;
                } else if (line.startsWith("deadline")) {
                    if (line.length() <= 9) {
                        throw new DukeEmptyCommandException();
                    }

                    System.out.println(line.substring(9));
                    String[] information = line.substring(9).split(" /by ", 3);

                    if (information.length != 2) {
                        throw new DukeInvalidDescriptionException();
                    }
                    System.out.println(SEPARATOR);

                    Task newTask = new Deadline(information[0], information[1]);
                    TaskList.add(newTask);
                    continue;
                } else if (line.startsWith("event")) {
                    if (line.length() <= 6) {
                        throw new DukeEmptyCommandException();
                    }

                    String[] information = line.substring(9).split(" /at ", 3);

                    if (information.length != 2) {
                        throw new DukeInvalidDescriptionException();
                    }
                    System.out.println(SEPARATOR);

                    Task newTask = new Event(information[0], information[1]);
                    TaskList.add(newTask);
                    continue;
                } else if (line.startsWith("todo")) {
                    if (line.length() <= 5) {
                        throw new DukeEmptyCommandException();
                    }
                    String description = line.substring(5);

                    System.out.println(SEPARATOR);
                    Task newTask = new ToDo(description);
                    TaskList.add(newTask);
                    continue;
                } else if (line.equals("bye")) {
                    System.out.println(SEPARATOR);

                    duke.storage.save();
                    System.out.println("See you later :)");
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

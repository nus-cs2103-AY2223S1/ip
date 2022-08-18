package DukeProgram;

import Utilities.StringUtilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static DukeProgram.Duke.*;

public class Checklist {
    private static final int ALL = -1;
    private static final ArrayList<Job> checklist = new ArrayList<>(100);

    /***
     * Provides functionality to use a checklist in Duke.
     */
    public static void Use() {
        String[] input = askForInput("").split(" ");

        while (!input[0].toLowerCase(Locale.ROOT).equals("bye")) {
            switch (input[0]) {
                case "list":
                    listChecklist();
                    break;

                case "mark": case "unmark":
                    markChecklist(input);
                    break;

                case "delete":
                    deleteFromChecklist(input);
                    break;

                default:
                    try {
                        addToChecklist(input);
                    } catch (InvalidJobException ex) {
                        printInStyle(ex.getMessage());
                    }
            }
            input = askForInput("").split(" ");
        }
    }

    private static void listChecklist() {
        printInStyle(IntStream
                .range(0, checklist.size())
                .mapToObj(i -> String.format("%d: %s", i + 1, checklist.get(i).toString()))
        );
    }

    /***
     * Takes in a command in the format from System.in and mark or unmarks
     * jobs on the checklist
     * @param input the command given from System.in
     */
    private static void markChecklist(String[] input) {
        if (input.length < 2) {
            printInStyle("Please specify which task you want to mark or unmark " +
                    "after the command. Otherwise, you may also specify all.");
            return;
        }

        if (input[1].equals("all")) {
            if (input[0].equals("mark")) {
                for (Job job : checklist) {
                    job.MarkJobState(true);
                }
                printInStyle("Ok, I've marked all items as completed!");
            } else if (input[0].equals("unmark")) {
                for (Job job : checklist) {
                    job.MarkJobState(false);
                }
                printInStyle("Ok, I've unmarked all items as undone.");
            }
            return;
        }

        int index;
        try {
            index = Integer.parseInt(input[1]) - 1;
        } catch (NumberFormatException ex) {
            printInStyle(String.format("I cannot understand what %s means in this context.", input[1]));
            return;
        }

        if (index < checklist.size() && index >= 0) {
            boolean isMark = input[0].equals("mark");
            checklist.get(index).MarkJobState(isMark);

            String response = isMark ?
                    "Nice! I've marked this task as done:" :
                    "Ok, I've marked this task as not done yet:";

            printInStyle(
                    response,
                    checklist.get(index).toString()
            );

        } else if (checklist.isEmpty()) {
            printInStyle(String.format("%d is out of range, " +
                    "you need to first add some items into the To-Do List", index + 1));

        } else {
            printInStyle(
                    String.format("%d is out of range, please choose an integer between 1 to %d",
                            index + 1,
                            checklist.size()));
        }
    }

    /**
     * Takes in a command in the format from System.in
     * and deletes the job with the associated index.
     * @param input command input from system
     */
    private static void deleteFromChecklist(String[] input) {
        if (input.length < 1) {
            printInStyle("Please specify the index of the job you want to delete.");
            return;
        }

        if (input[1].equals("all")) {
            checklist.clear();
            printInStyle("Alright. I've cleared all your tasks from the checklist");
            return;
        }

        int index;
        try {
            index = Integer.parseInt(input[1]) - 1;
        } catch (NumberFormatException ex) {
            printInStyle("Please only specify numbers starting from 1!");
            return;
        }

        if (index <= -1) {
            printInStyle("Sorry, the number can only be more than or equals to 1!");
            return;
        }

        if (index >= checklist.size()) {
            printInStyle(String.format("You wanted to delete task %d but there were only %d jobs in the list.",
                    index + 1, checklist.size()));
            return;
        }

        Job jobRemoved = checklist.get(index);
        checklist.remove(index);
        printInStyle("Noted. I've removed this task:",
                jobRemoved.toString(),
                String.format("Now you have %d tasks in the list.", checklist.size()));
    }

    /***
     * Takes in a command in the format from System.in and add
     * jobs on the checklist based on their type
     * @param input the command given from System.in
     */
    private static void addToChecklist(String[] input) throws InvalidJobException{
        Job job;
        String[][] nameAndDate;

        switch (input[0]) {
            case "todo":
                try {
                    job = new ToDo(concatName(input));
                } catch (JobNameException ex) {
                    printInStyle(ex.getMessage());
                    return;
                }
                break;

            case "deadline":
                nameAndDate = StringUtilities.splitStringArray(input, "/by");
                if (nameAndDate.length != 2) {
                    printInStyle("Please use /by to set a time");
                    return;
                }

                try {
                    job = new Deadline(concatName(nameAndDate[0]), String.join(" ", nameAndDate[1]));
                } catch (JobNameException ex) {
                    printInStyle(ex.getMessage());
                    return;
                }
                break;

            case "event":
                nameAndDate = StringUtilities.splitStringArray(input, "/at");
                if (nameAndDate.length != 2) {
                    printInStyle("Please use /at to set a time");
                    return;
                }

                try {
                    job = new Event(concatName(nameAndDate[0]), String.join(" ", nameAndDate[1]));
                } catch (JobNameException ex) {
                    printInStyle(ex.getMessage());
                    return;
                }
                break;
            default:
                throw new InvalidJobException();
        }
        checklist.add(job);
        printInStyle(
                "Got it. I've added this task:",
                job.toString(),
                String.format("Now you have %d tasks in the list", checklist.size()));
    }

    /**
     * A helper method to concat a string array given in the format from System.in
     * @param input The string array given in the format from System.in
     * @return the actual name of the job to be created
     * @throws JobNameException if there is no name provided
     */
    private static String concatName(String[] input) throws JobNameException {
        String name = Arrays.stream(input).skip(1).collect(Collectors.joining(" "));
        if (name.equals("")) {
            throw new JobNameException(input[0]);
        }
        return name;
    }
}

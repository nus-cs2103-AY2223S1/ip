package duke.ui;

import java.util.Scanner;

import duke.task.Task.TaskType;

public class Ui {
    public static final String START = '\u2619' + " ";
    public static final String SAD_FACE = '\u2639' + " ";
    private Scanner sc = new Scanner(System.in);

    public void fileLoadError() {
        System.out.println(
                SAD_FACE + "hmm, i was not able to create a file to store your list.\n"
                + "  note that your list will not be saved if you quit and restart duke.Duke!"
        );
    }

    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(START + "hi, i'm Duke!\n  what would you like to do today?");
    }

    public String requestInput() {
        System.out.print("> ");
        return sc.nextLine();
    }

    public void invalidMarkInput() {
        System.out.println(SAD_FACE + "please enter an integer so i know which task to mark!");
    }

    public void invalidUnmarkInput() {
        System.out.println(SAD_FACE + "please enter an integer so i know which task to unmark!");
    }

    public void invalidTaskInput(TaskType t) {
        if (t == TaskType.TODO) {
            System.out.println(SAD_FACE + "please tell me the name of the todo task.");
        } else if (t == TaskType.DEADLINE) {
            System.out.println(
                    SAD_FACE + "for tasks with deadlines, please tell me the name of the task, followed "
                            + "by '/by',\n  and then the date/time it needs to be completed by\n"
                            + "  in DD/MM/YYYY HHMM (time optional) format e.g. 27/02/2023 2359.");
        } else {
            System.out.println(
                    SAD_FACE + "for events, please tell me the name of the event, when it starts and when it ends.");
        }
    }

    public void emptyDateInput() {
        System.out.println(
                SAD_FACE + "please enter the date you would like to search for in the format DD/MM/YYYY.");
    }

    public void invalidDateInput() {
        System.out.println(SAD_FACE + "please enter a valid date in the format DD/MM/YYYY.");
    }

    public void invalidDateTimeInput() {
        System.out.println(
                SAD_FACE + "please input a valid date and time in the format DD/MM/YYYY HHMM (time optional)");
    }

    public void invalidStartEndDateInput() {
        System.out.println(
                SAD_FACE + "please input valid starting and ending dates and times in the format\n"
                        + "  DD/MM/YYYY HHMM - DD/MM/YYYY HHMM (ending date optional)"
        );
    }

    public void invalidDeleteInput() {
        System.out.println(SAD_FACE + "please enter an integer so i know which task to delete!");
    }

    public void invalidInput() {
        System.out.println(SAD_FACE + "sorry, i don't know what that means :(");
    }

    public void saveFail() {
        System.out.println(
                START + "bye! i was unable to save your list to the hard drive, but i hope to see you again soon! :)");
    }

    public void goodbye() {
        System.out.println(START + "bye! i hope to see you again soon! :)");
    }

}

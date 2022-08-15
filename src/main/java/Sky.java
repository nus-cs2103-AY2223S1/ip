import java.util.Scanner;

public class Sky {
    public static void main(String[] args) {
        System.out.println("Hello from Sky! Your heavenly personal assistant chatbot to help you track your things.");

        Task[] taskArr = new Task[100];
        // index stores the pointer index of arr array
        int index = 0;
        // Label the while loop
        outerLoop:
        while (true) {
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            String firstWord = s.indexOf(' ') == -1 ? s : s.substring(0, s.indexOf(' '));
            System.out.println("  ____________________________________________________________");
            switch(s) {
                // Closes the application
                case "bye":
                    System.out.println("  Bye. May all your endeavours fly high!");
                    System.out.println("  ____________________________________________________________");
                    break outerLoop;
                // Prints the list of tasks to do
                case "list":
                    for (int i = 0; i < taskArr.length; i++) {
                        if (taskArr[i] == null) {
                            break;
                        }
                        System.out.println("  " + (i + 1) + ".[" + taskArr[i].getStatusIcon() + "] " + taskArr[i].description);
                    }
                    break;
                // In the event user types "mark" or "unmark" without specifying a number, treat it as a task
                case "mark":
                case "unmark":
                    System.out.println("  added: " + s);
                    Task t1 = new Task(s);
                    taskArr[index++] = t1;
                    break;
                default:
                    switch(firstWord) {
                        // Mark a task as complete
                        case "mark":
                            String numInStringFormatForMark = s.substring(Math.min(s.length(), firstWord.length() + 1));
                            int indexOfTaskToMark = Integer.parseInt(numInStringFormatForMark) - 1;
                            taskArr[indexOfTaskToMark].markAsDone();
                            System.out.println("  Wow... who would have thought you had it in you... I've marked this task as done: \n" +
                                    "    [" + taskArr[indexOfTaskToMark].getStatusIcon() + "] " + taskArr[indexOfTaskToMark].description);
                            break;
                        // Mark a task as incomplete
                        case "unmark":
                            String numInStringFormatForUnmark = s.substring(Math.min(s.length(), firstWord.length() + 1));
                            int indexOfTaskToUnmark = Integer.parseInt(numInStringFormatForUnmark) - 1;
                            taskArr[indexOfTaskToUnmark].markAsUndone();
                            System.out.println("  Well, that's disappointing. I've marked this task as undone: \n" +
                                    "    [" + taskArr[indexOfTaskToUnmark].getStatusIcon() + "] " + taskArr[indexOfTaskToUnmark].description);
                            break;
                        // Adds a task to the list
                        default:
                            System.out.println("  added: " + s);
                            Task t2 = new Task(s);
                            taskArr[index++] = t2;
                            break;
                    }
            }
            System.out.println("  ____________________________________________________________");
        }
    }
}
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import dukeExceptions.MissingDescriptionException;
import dukeExceptions.UnknownCommandException;

public class Duke {
    public static void main(String[] args) {
        List<Task> userData = new ArrayList<>();
        boolean isActive = true;

        System.out.println("Hello! I'm Duke!\nWhat can I do for you?");
        System.out.println("-----------------------------------");
        while (isActive) {
            try {
                Scanner input = new Scanner(System.in);
                String line = input.nextLine();

                String userInputs[] = line.split(" ");
                String mainCommand = userInputs[0].toLowerCase();

                switch (mainCommand) {
                    case "bye": {
                        System.out.println("Bye. Hope to see you again soon!");
                        isActive = false;
                        break;
                    }
                    case "list": {
                        for (int i = 1; i < userData.size() + 1; i++) {
                            System.out.format("%s. %s\n", i, userData.get(i - 1));
                        }
                        break;
                    }
                    case "mark": {
                        userData.get(Integer.valueOf(userInputs[1])).setIsComplete(true);
                        System.out.format("Nice! I've marked this task as done: %s\n", userData.get(Integer.valueOf(userInputs[1])).toString());
                        break;
                    }
                    case "unmark": {
                        userData.get(Integer.valueOf(userInputs[1])).setIsComplete(false);
                        System.out.format("OK, I've marked this task as not done yet: %s\n", userData.get(Integer.valueOf(userInputs[1])).toString());
                        break;
                    }
                    case "todo": {
                        userInputs = Arrays.copyOfRange(userInputs, 1, userInputs.length);

                        // ERROR HANDLING: Check for empty ToDo description
                        if (userInputs.length == 0) {
                           throw new MissingDescriptionException("todo");
                        }

                        ToDo newToDo = new ToDo(String.join(" ", userInputs));
                        userData.add(newToDo);
                        System.out.format("Got it. I've added this task:\n  %s\nNow you have %s %s in the list.\n", newToDo, userData.size(), userData.size() > 1 ? "tasks" : "task");
                        break;
                    }
                    case "deadline": {
                        userInputs = Arrays.copyOfRange(userInputs, 1, userInputs.length);

                        String description = splitArrayIntoSubstrings(userInputs, "/by").get(0);
                        // ERROR HANDLING: Check for empty Deadline description
                        if (description.equalsIgnoreCase("")) {
                            throw new MissingDescriptionException("deadline");
                        }

                        // ERROR HANDLING: Check for missing "by" deadline
                        String deadline = splitArrayIntoSubstrings(userInputs, "/by").get(1);
                        System.out.println(deadline);

                        Deadline newDeadline = new Deadline(description, deadline);
                        userData.add(newDeadline);
                        System.out.format("Got it. I've added this task:\n  %s\nNow you have %s %s in the list.\n", newDeadline, userData.size(), userData.size() > 1 ? "tasks" : "task");
                        break;
                    }
                    case "event": {
                        userInputs = Arrays.copyOfRange(userInputs, 1, userInputs.length);

                        String description = splitArrayIntoSubstrings(userInputs, "/at").get(0);

                        // ERROR HANDLING: Check for empty Deadline description
                        if (description.equalsIgnoreCase("")) {
                            throw new MissingDescriptionException("event");
                        }
                        String timeRange = splitArrayIntoSubstrings(userInputs, "/at").get(1);

                        Event newEvent = new Event(description, timeRange);
                        userData.add(newEvent);
                        System.out.format("Got it. I've added this task:\n  %s\nNow you have %s %s in the list.\n", newEvent, userData.size(), userData.size() > 1 ? "tasks" : "task");
                        break;
                    }
                    default: {
                        throw new UnknownCommandException();
                    }
                }
                System.out.println("-----------------------------------");
            } catch (UnknownCommandException err) {
                System.out.println(err);
            } catch (MissingDescriptionException err) {
                System.out.println(err);
            }
        }
    }

    /**
     * Splits an array into subarrays at a given delimiter, and concatenates the substrings
     * @param arr Array of strings to be split
     * @param delimiter Array is split into two subarrays at the delimiter,
     *                  and each subarray's elements are concatenated with a " " between each element
     * @return An array of two substrings
     */
    public static List<String> splitArrayIntoSubstrings(String[] arr, String delimiter) {
        StringBuilder builder = new StringBuilder();
        List<String> res = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equalsIgnoreCase(delimiter)) {
                res.add(builder.toString().trim());
                builder = new StringBuilder();
            } else {
                builder.append(arr[i]).append(" ");
            }
        }
        res.add(builder.toString().trim());
        return res;
    }

}

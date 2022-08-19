import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String logo = " ____                 \n"
                + "|  _ \\ _ _ _ __ _____ \n"
                + "| | | |  _  | |/ / _ \\\n"
                + "| |_| | |_| |   /  __/\n"
                + "|____/ \\__,_|\\_/ \\___|\n";
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("What can I do for you?");

        // Scanner to get input
        Scanner scan = new Scanner(System.in);

        ArrayList<Task> log = new ArrayList<>();

        System.out.println("--------------------------------------");

        /*
        int indexOfSpace;
        String s;
        */
        String firstWord = "";
        String restWord = "";

        boolean isMultipleWords = false;

        while(true) {
            try {
                String s = scan.nextLine();
                int indexOfSpace = s.indexOf(' ');
                isMultipleWords = indexOfSpace > -1;
                firstWord = s;
                restWord = "";
                if (isMultipleWords) {
                    firstWord = s.substring(0, indexOfSpace);
                    restWord = s.substring(indexOfSpace).trim();
                }

                System.out.println("--------------------------------------");
                if (s.equals("bye")) {
                    scan.close();
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (s.equals("list")) {
                    int count = 1;
                    System.out.println("Here are the tasks in your list:");
                    for (Task item : log) {
                        System.out.println(count + ". " + item.toString());
                        count++;
                    }
                } else {
                    if (firstWord.equals("mark")) {
                        if (!isMultipleWords) {
                            throw new DukeException("Index of task to mark required");
                        }
                        int index = Integer.parseInt(restWord) - 1; //array starts from 0
                        Task temp = log.get(index);
                        temp.Mark();
                        System.out.println("This task is now done: \n" + temp);
                    } else if (firstWord.equals("unmark")) {
                        if (!isMultipleWords) {
                            throw new DukeException("Index of task to unmark required");
                        }
                        int index = Integer.parseInt(restWord) - 1; //array starts from 0
                        Task temp = log.get(index);
                        temp.Unmark();
                        System.out.println("This task is now not done: \n" + temp);
                    } else if (firstWord.equals("delete")) {
                        if (!isMultipleWords) {
                            throw new DukeException("Index of task to delete required");
                        }
                        Task temp = log.get(Integer.parseInt(restWord) - 1);
                        log.remove(Integer.parseInt(restWord) - 1);
                        System.out.println("This task is now removed: \n" + temp);
                    } else {
                        if (s.equals("todo") || firstWord.equals("todo")) {
                            log.add(new Todo(restWord, false));
                            System.out.println("added todo: " + restWord);
                        } else if (firstWord.equals("deadline") && restWord.contains("/by")) {
                            String by = dateFinder(restWord, "/by");
                            String name = nameFinder(restWord, "/by");
                            log.add(new Deadline(name, false, by));
                            System.out.println("added deadline: " + name);
                        } else if (firstWord.equals("event") && restWord.contains("/at")) {
                            String at = dateFinder(restWord, "/at");
                            String name = nameFinder(restWord, "/at");
                            log.add(new Event(name, false, at));
                            System.out.println("added event: " + name);
                        } else {
                            throw new DukeException("?? Unrecognised command");
                        }
                    }
                }
            } catch (DukeException e) {
                System.out.println("Error occurred " + e);
            } catch (NumberFormatException e) {
                System.out.println("Error occurred: Could not identify index");
            } finally {
                System.out.println("--------------------------------------");
            }
        }

    }

    private static String dateFinder(String restWord, String flag) {
        return restWord.substring(restWord.indexOf(flag) + flag.length()).trim();
    }

    private static String nameFinder(String restWord, String flag) {
        return restWord.substring(0, restWord.indexOf(flag)).trim();
    }
}


import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for the Poolsheen CLI program.
 * @author Ong Wee, Marcus (Tut Grp 03)
 * @version CS2103 AY22/23 Sem 1
 */

public class Poolsheen {
    public static final String startReply = "      ";

    private static final String logo = "\n" +
            "POOLSHEEN";

    private static final String welcomeMessage = "Hello from" + logo + "\n" +
            "Type something in for Poolsheen to respond to you:";

    private static final String lastReply = "meow *_*";

    private static final String horizontalLine = "---------------";

    private static final String exitMessage = "MeoAww... See you next time :(";

    private static final String endMessage = "THE POOLSHEEN PROGRAM HAS STOPPED RUNNING";

    /** Whether if this poolsheen object has stopped running */
    private boolean hasExited;

    /** The current input that the poolsheen object has received */
    private String userInput;

    /** The scanner object which this poolsheen uses */
    private Scanner scanner;

    /** The list of tasks that the poolsheen object has */
    private ArrayList<Task> listOfTasks;

    /**
     * A private constructor to initialise the Poolsheen object.
     */
    private Poolsheen() {
        this.listOfTasks = new ArrayList<>(100);
        this.hasExited = false;
        this.userInput = "";
        this.scanner = new Scanner(System.in);
    }

    /**
     * The first method to be run for Poolsheen to listen to our user.
     */
    private void run() {
        while (!this.hasExited) {
            try {
                this.userInput = this.scanner.nextLine();
                //parse a string
                String[] arr = this.userInput.split(" ");
                //to convert primitive string array to arraylist<string>
                ArrayList<String> arl = new ArrayList<>();
                for (String s : arr) {
                    arl.add(s);
                }
                String command = arl.get(0);
                arl.remove(0);

                switch (command.toUpperCase()) {
                    case "BYE":
                        if (!arl.isEmpty()) {
                            throw new IncompleteCommandException("this.userInput", "bye", "Were you trying to enter 'bye'?");
                        } else {
                            this.exit();
                        }
                        break;
                    case "LIST":
                        if (!arl.isEmpty()) {
                            throw new IncompleteCommandException("this.userInput", "list", "Were you trying to enter 'list'?");
                        } else {
                            this.displayList();
                        }
                        break;
                    case "MARK":
                        if (arl.isEmpty() || arl.size() != 1) {
                            throw new IncompleteCommandException(this.userInput, "mark", "Please enter 1 appropriate integer");
                        } else {
                            this.mark(java.lang.Integer.parseInt(arl.get(0)));
                        }
                        break;
                    case "UNMARK":
                        if (arl.isEmpty()) {
                            throw new IncompleteCommandException(this.userInput, "unmark", "Please enter 1 appropriate integer");
                        } else {
                            this.unmark(java.lang.Integer.parseInt(arl.get(0)));
                        }
                        break;
                    case "DELETE":
                        if (arl.isEmpty()) {
                            throw new IncompleteCommandException(this.userInput, "delete", "Please enter 1 appropriate integer");
                        } else {
                            this.deleteTask(java.lang.Integer.parseInt(arl.get(0)));
                        }
                        break;
                    case "TODO":
                        if (arl.isEmpty()) {
                            throw new IncompleteCommandException(this.userInput, "todo", "The description of a todo cannot be empty");
                        } else {
                            String descTD = String.join(" ", arl);
                            ToDo t = new ToDo(descTD);
                            this.listOfTasks.add(t);
                            this.say("Poolsheen now remembers: " + descTD);
                        }
                        break;
                    case "DEADLINE":
                        if (arl.isEmpty()) {
                            throw new IncompleteCommandException(this.userInput, "deadline", "Deadlines need a description and time");
                        } else if (!arl.contains("/by")) {
                            throw new IncompleteCommandException(this.userInput, "deadline", "deadline commands need a '/by'");
                        } else {
                            String descD = String.join(" ", arl.subList(0, arl.indexOf("/by")));
                            String timeD = String.join(" ", arl.subList(arl.indexOf("/by") + 1, arl.size()));
                            if (descD.length() == 0 || timeD.length() == 0) {
                                throw new IncompleteCommandException(this.userInput, "deadline", "deadline commands must specify a description and time");
                            } else {
                                Deadline d = new Deadline(descD, timeD);
                                this.listOfTasks.add(d);
                                this.say("Poolsheen now remembers: " + descD);
                            }
                        }
                        break;
                    case "EVENT":
                        if (arl.isEmpty()) {
                            throw new IncompleteCommandException(this.userInput, "event", "Events need a description and time");
                        } else if (!arl.contains("/at")) {
                            throw new IncompleteCommandException(this.userInput, "event", "event commands need an '/at'");
                        } else {
                            String descE = String.join(" ", arl.subList(0, arl.indexOf("/at")));
                            String timeE = String.join(" ", arl.subList(arl.indexOf("/at") + 1, arl.size()));
                            if (descE.length() == 0 || timeE.length() == 0) {
                                throw new IncompleteCommandException(this.userInput, "event", "event commands must specify a description and time");
                            } else {
                                Event e = new Event(descE, timeE);
                                this.listOfTasks.add(e);
                                this.say("Poolsheen now remembers: " + descE);
                            }
                        }
                        break;
                    default:
                        throw new UnknownCommandException(this.userInput);
                }
            } catch (UnknownCommandException e) {
                this.say(e.toString() + "\n" + Poolsheen.startReply + "Please try again!");
            } catch (IncompleteCommandException e) {
                this.say(e.toString());
            } catch (IndexOutOfBoundsException e) {
                this.say("Poolsheen thinks no task has this position. Please try another number!");
            } catch (NumberFormatException e) {
                this.say("Poolsheen believes this command needs an integer. Please try again!");
            } catch (Exception e) {
                System.out.println("An unexpected error has occurred and the program will end.");
                System.out.println("Error is: " + e.toString());
                this.exit();
            }
        }
    }

    /**
     * The last method to be run for Poolsheen to stop listening to our user.
     */
    private void exit() {
        this.hasExited = true;
        this.say(Poolsheen.exitMessage);
    }

    /**
     * Prints the list of tasks this Poolsheen remembers.
     */
    private void displayList() {
        if (this.listOfTasks.isEmpty()) {
            this.say("Poolsheen thinks back... " +
                    "and remembers you said nothing :(");
        } else {
            String displayStr = "Poolsheen thinks back... " +
                    "and remembers you said:";
            int currPos = 1;
            for (Task task : this.listOfTasks) {
                if (task != null) {
                    String line = currPos + ". " + task.toString();
                    displayStr += "\n" + Poolsheen.startReply + line;
                    currPos += 1;
                }
            }
            this.say(displayStr);
        }
    }

    /**
     * A method to format and print a message by Poolsheen.
     * @param message The message to be printed.
     */
    private void say(String message) {
        System.out.println(Poolsheen.horizontalLine + "\n" +
                Poolsheen.startReply + message + "\n" +
                Poolsheen.startReply +
                Poolsheen.lastReply +
                "\n" + Poolsheen.horizontalLine);
    }

    /**
     * Marks a task as done assuming the user input is correct.
     * @param pos The index position of the task in the list.
     */
    private void mark(int pos) {
         Task selectedTask = this.listOfTasks.get(pos-1);
         selectedTask.markAsDone();
         this.say("Poolsheen thinks you are done with "
                 + selectedTask.description);
    }

    /**
     * Marks a task as not done assuming the user input is correct.
     * @param pos The index position of the task in the list.
     */
    private void unmark(int pos) {
        Task selectedTask = this.listOfTasks.get(pos-1);
        selectedTask.markAsNotDone();
        this.say("Poolsheen thinks you are not done with "
                + selectedTask.description);
    }

    /**
     * Removes a task from the list of tasks that Poolsheen remembers.
     * @param pos The index+1 position of the task that is to be deleted.
     */
    private void deleteTask(int pos) {
        Task t = this.listOfTasks.get(pos - 1);
        this.listOfTasks.remove(pos-1);
        this.say("Poolsheen has forgot: " + t.description + " and you now have " + this.listOfTasks.size() + " tasks left");
    }

    public static void main(String[] args) {
        Poolsheen ps = new Poolsheen();
        System.out.println(ps.welcomeMessage);
        ps.run();
        System.out.println(ps.endMessage);
    }
}

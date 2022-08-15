import java.util.Scanner;
import java.util.ArrayList;

public class Blink {

    private static final String SPACING = "---------------------------";
    private ArrayList<Task> store = new ArrayList<>();
    private boolean endSession = false;

    public void welcome() {
        System.out.println(Blink.SPACING + "\n" +
                "Hello! Blink here\n" +
                "What can I do for you today?\n" +
                Blink.SPACING);
    }

    public void goodbye() {
        System.out.println("Bye bye~ Glad to be of service :D");
    }

    public void list() {
        System.out.println("Here are the tasks in your list:");
        for(int x = 0; x < this.store.size(); x++) {
            System.out.println(x+1 + ". " + this.store.get(x));
        }
    }

    public void unMark(String[] input) throws BlinkException {
        if (input.length == 2) {
            int pos = Integer.parseInt(input[1]);
            if (pos < 1 || pos > this.store.size()) {
                throw new BlinkException("Number input does not exist");
            }
            this.store.get(pos - 1).unMark();
        } else {
            throw new BlinkException("There is an error in command inputted");
        }
    }

    public void mark(String[] input) throws BlinkException {
        if (input.length == 2) {
            int pos = Integer.parseInt(input[1]);
            if (pos < 1 || pos > this.store.size()) {
                throw new BlinkException("Number input does not exist");
            }
            this.store.get(pos - 1).mark();
        } else {
            throw new BlinkException("There is an error in command inputted");
        }
    }

    public void delete(String[] input) throws BlinkException {
        if (input.length == 2) {
            int pos = Integer.parseInt(input[1]);
            if (pos < 1 || pos > this.store.size()) {
                throw new BlinkException("Number input does not exist");
            }
            Task temp = this.store.get(pos - 1);
            this.store.remove(pos - 1);
            System.out.println("Task have been removed successfully\n" +
                    temp + "\nNow you have " + this.store.size() + " task remaining");
        } else {
            throw new BlinkException("There is an error in command inputted");
        }
    }

    public void toDos(String[] input) throws BlinkException {
        if (input.length != 2) {
            throw new BlinkException("☹ OOPS!!! Missing description of todo.");
        }
        ToDos temp = new ToDos(input[1].strip());
        this.store.add(temp);
        System.out.println("Alright, this task has been successfully added!\n" +
                temp +
                "\nTotal of " + this.store.size() + " tasks now");
    }

    public void deadlines(String[] input) throws BlinkException {
        if (input.length != 2) {
            throw new BlinkException("☹ OOPS!!! Missing description of deadline.");
        }
        String[] sep = input[1].split("/by");
        if (sep.length != 2) {
            throw new BlinkException("Error in command for deadline");
        }
        Deadlines temp = new Deadlines(sep[0].strip(), sep[1].strip());
        this.store.add(temp);
        System.out.println("Alright, this task has been successfully added!\n" +
                temp +
                "\nTotal of " + this.store.size() + " tasks now");
    }

    public void events(String[] input) throws BlinkException {
        if (input.length != 2) {
            throw new BlinkException("☹ OOPS!!! Missing description of event.");
        }
        String[] sep = input[1].split("/at");
        if (sep.length != 2) {
            throw new BlinkException("Error in command for event");
        }
        Events temp = new Events(sep[0].strip(), sep[1].strip());
        this.store.add(temp);
        System.out.println("Alright, this task has been successfully added!\n" +
                temp +
                "\nTotal of " + this.store.size() + " tasks now");
    }

    public void start(Scanner sc) {
        this.welcome();

        while(sc.hasNext()) {
            try {
                String[] input = sc.nextLine().strip().split(" ", 2);
                if (input[0].isEmpty()) {
                    continue;
                }
                switch (input[0]) {
                    case "bye":
                        this.goodbye();
                        this.endSession = true;
                        break;
                    case "list":
                        this.list();
                        break;
                    case "unmark":
                        this.unMark(input);
                        break;
                    case "mark":
                        this.mark(input);
                        break;
                    case "event":
                        this.events(input);
                        break;
                    case "deadline":
                        this.deadlines(input);
                        break;
                    case "todo":
                        this.toDos(input);
                        break;
                    case "delete":
                        this.delete(input);
                        break;
                    default:
                        throw new BlinkException("Unknown command input");
                }
            } catch (BlinkException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Number is expected");
            }
            System.out.println(Blink.SPACING);
            if (this.endSession) {
                break;
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Blink blink = new Blink();
        blink.start(scanner);
    }
}


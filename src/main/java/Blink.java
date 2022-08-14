import java.util.Scanner;

public class Blink {

    private static final String SPACING = "---------------------------";
    private Task[] store = new Task[100];
    private int count = 0;
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
        for(int x = 0; x < this.count; x++) {
            System.out.println(x+1 + ". " + this.store[x].toString());
        }
    }

    public void unMark(String[] input) {
        if (input.length == 2) {
            int pos = Integer.parseInt(input[1]);
            if (pos < 1 || pos > count) {
                System.out.println("Number input into command not accepted.");
                return;
            }
            this.store[pos - 1].unMark();
        } else {
            System.out.println("Incorrect command. Missing task number.");
        }
    }

    public void mark(String[] input) {
        if (input.length == 2) {
            int pos = Integer.parseInt(input[1]);
            if (pos < 1 || pos > count) {
                System.out.println("Number input into command not accepted.");
                return;
            }
            this.store[pos - 1].mark();
        } else {
            System.out.println("Incorrect command. Missing task number.");
        }
    }

    public void toDos(String input) {
        ToDos temp = new ToDos(input.strip());
        this.store[count] = temp;
        this.count++;
        System.out.println("Alright, this task has been successfully added!\n" +
                temp + "\n" +
                "Total of " + this.count + " tasks now");
    }

    public void deadlines(String input) {
        String[] sep = input.split("/by");
        Deadlines temp = new Deadlines(sep[0].strip(), sep[1].strip());
        this.store[count] = temp;
        this.count++;
        System.out.println("Alright, this task has been successfully added!\n" +
                temp + "\n" +
                "Total of " + this.count + " tasks now");
    }

    public void events(String input) {
        String[] sep = input.split("/at");
        Events temp = new Events(sep[0].strip(), sep[1].strip());
        this.store[count] = temp;
        this.count++;
        System.out.println("Alright, this task has been successfully added!\n" +
                temp + "\n" +
                "Total of " + this.count + " tasks now");
    }

    public void start(Scanner sc) {
        this.welcome();

        while(sc.hasNext()) {
            String[] input = sc.nextLine().strip().split(" ",2);
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
                    this.events(input[1]);
                    break;
                case "deadline":
                    this.deadlines(input[1]);
                    break;
                case "todo":
                    this.toDos(input[1]);
                    break;
                default:
                    System.out.println("Unknown command inputted");
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


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

    public void start(Scanner sc) {
        this.welcome();

        while(sc.hasNext()) {
            String[] input = sc.nextLine().strip().split(" ");
            switch (input[0]) {
                case "bye":
                    this.goodbye();
                    this.endSession = true;
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for(int x = 0; x < this.count; x++) {
                        System.out.println(x+1 + ". " + this.store[x].toString());
                    }
                    break;
                case "unmark":
                    if (input.length == 2) {
                        int pos = Integer.parseInt(input[1]);
                        if (pos < 1 || pos > count) {
                            System.out.println("Number input into command not accepted.");
                            break;
                        }
                        this.store[pos - 1].unMark();
                    } else {
                        System.out.println("Incorrect command. Missing task number.");
                    }
                    break;
                case "mark":
                    if (input.length == 2) {
                        int pos = Integer.parseInt(input[1]);
                        if (pos < 1 || pos > count) {
                            System.out.println("Number input into command not accepted.");
                            break;
                        }
                        this.store[pos - 1].mark();
                    } else {
                        System.out.println("Incorrect command. Missing task number.");
                    }
                    break;
                default:
                    String attach = String.join(" ",input);
                    System.out.println(attach);
                    this.store[count] = new Task(attach);
                    this.count++;
                    System.out.println("added task: " + attach);
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


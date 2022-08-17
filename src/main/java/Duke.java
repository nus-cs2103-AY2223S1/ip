import java.util.Scanner;

public class Duke {
    private String[] storedInfo;
    private int counter;

    public Duke() {
        this.storedInfo = new String[100];
        this.counter = 0;
    }

    public String addItem(String item) {
        this.storedInfo[this.counter] = item;
        this.counter++;
        return "    Alright! I've added '" + item + "' to our list Dattebayo!";
    }

    public String getList() {
        StringBuilder list = new StringBuilder("    Here's the list you asked for Dattebayo:");
        for (int count = 0; count < counter; count++){
            list.append("\n").append("    ").append(count + 1).append(". ").append(storedInfo[count]);
        }
        return list.toString();
    }

    public String bye() {
        return "    Looks like this is the end for now, till we meet again! Ja Ne!";
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Naruto and one day I will be Hokage! \nWhat can i do for you?");
        Scanner scanner = new Scanner(System.in);
        Duke naruto = new Duke();
        String lineBreak = "━".repeat(20);

        boolean breakLoop = false;
        while (!breakLoop) {
            String input = scanner.nextLine();
            System.out.println(lineBreak);
            switch (input) {
                case "list":
                    System.out.println(naruto.getList());
                    break;

                case "bye":
                    System.out.println(naruto.bye());
                    breakLoop = true;
                    break;

                default:
                    System.out.println(naruto.addItem(input));
            }
            System.out.println(lineBreak);
        }

    }
}

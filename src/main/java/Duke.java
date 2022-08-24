import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello there! I'm Duke!");
        System.out.println("How can I help you?");

        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList(new Storage());

        while (true) {
            try {
                Instruction instruction = Parser.parse(sc.nextLine());
                instruction.execute(taskList);
                if (instruction.endsProgram()) {
                    break;
                }
            } catch (InvalidCommandException e) {
                System.out.printf("Sorry, I don't understand what %s means. :/%n", e.getCommandString());
            }
        }
    }
}

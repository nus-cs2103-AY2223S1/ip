public class Main {
    public static void main(String[] args) {
        Duke duke = new Duke();
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            Instruction instruction = new Instruction(userInput);
            if (duke.runInstruction(instruction)) {
                break;
            }
        }
    }
}

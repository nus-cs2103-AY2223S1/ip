public class Main {
    public static void main(String[] args) {
        Henry henry = new Henry();
        while (henry.isActivated()) {
            System.out.print("\n> ");
            henry.parseCommand(henry.getInput());
        }
    }
}

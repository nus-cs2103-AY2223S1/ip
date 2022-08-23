
public class Duke {

    public void run() {
        UI.getGREETING();
        UI.getLINE();
        Parser.parseInput();
    }


    public static void main(String[] args) {
        new Duke().run();
    }
}

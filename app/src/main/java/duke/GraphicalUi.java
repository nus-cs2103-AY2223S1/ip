package duke;

public class GraphicalUi extends Ui {
    private boolean isAlive;

    /**
     * Creates a new GUI handler.
     */
    public GraphicalUi(InputAcceptor ia) {
        super(ia);
    }

    @Override
    public void runInputLoop() {
        isAlive = true;
    }

    @Override
    public void stopInputLoop() {
        isAlive = false;
    }

    @Override
    public void respond(String response) {
        //
    }
}

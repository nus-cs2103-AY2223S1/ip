package duke;

import duke.ui.Ui;

public class Duke {

    private final Ui ui;

    public Duke() {
        this.ui = new Ui(System.in, System.out);
    }

    public void interact() {
        this.ui.introduceDuke();
        this.ui.readAndRespond();
    }

    /**
     * Main method run to converse with Duke Aemon of Old.
     */
    public static void main(String[] args) {
       Duke AemonT = new Duke();
       AemonT.interact();
    }
}
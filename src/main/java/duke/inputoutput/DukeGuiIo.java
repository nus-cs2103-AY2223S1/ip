package duke.inputoutput;

import java.util.function.Function;

import duke.util.StringParser;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * Duke IO class to handle interactions with the GUI
 */
public class DukeGuiIo extends DukeAbstractIo {

    private final Pane container;
    private final Function<String, Node> makeChild;

    /**
     * Create an instance of the gui interaction object specifying a way to make a
     * new child
     *
     * @param container the container which contains the spawned child
     * @param makeChild function to create a new child
     */
    public DukeGuiIo(Pane container, Function<String, Node> makeChild) {
        this.container = container;
        this.makeChild = makeChild;
    }

    private void addMsgToContainer(String msg) {
        container.getChildren().add(makeChild.apply(msg));
    }

    @Override
    public void printTask(String txt, int features) {
        if (isBitFlag(features, DukeCliSettings.INDENT)) {
            txt = StringParser.addIndent(txt);
        }
        addMsgToContainer(txt);
    }

    @Override
    public void printTask(String txt) {
        printTask(txt, DukeCliSettings.INDENT);
    }

    @Override
    public String readLine() {
        return null;
    }
}

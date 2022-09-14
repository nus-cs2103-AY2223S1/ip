package duke.inputoutput;

import java.util.function.Function;
import duke.util.StringParser;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class DukeGuiIo extends DukeAbstractIo {

    private final Pane container;
    private final Function<String, Node> makeChild;

    public DukeGuiIo(Pane container, Function<String, Node> makeChild) {
        this.container = container;
        this.makeChild = makeChild;
    }

    private void addMsgToContainer(String msg) {
        container.getChildren().add(makeChild.apply(msg));
    }

    @Override
    public void printTask(String txt, int features) {
        if ((features & 2) == 2) {
            txt = StringParser.addIndent(txt);
        }
        addMsgToContainer(txt);
    }

    @Override
    public void printTask(String txt) {
        printTask(txt, 3);
    }

    @Override
    public String readLine() {
        return null;
    }
}

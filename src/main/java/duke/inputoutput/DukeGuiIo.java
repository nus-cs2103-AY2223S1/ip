package duke.inputoutput;

import java.util.function.Function;
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
        // TODO Auto-generated method stub

    }

    @Override
    public void printTask(String txt) {
        // TODO Auto-generated method stub

    }

    @Override
    public String readLine() {
        return null;
    }
}

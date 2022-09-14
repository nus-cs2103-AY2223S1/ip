package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import henry.Henry;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class TextInputTest extends ApplicationTest {

    private static final String EXPECTED = "OK, I added this task to my list:\n [T][ ] hello";

    /**
     * Will be called with {@code @Before} semantics, i.e. before each test method.
     *
     * @param stage - Will be injected by the test runner.
     */
    @Start
    public void start(Stage stage) {
        Henry henry = new Henry();
        henry.start(stage);
    }

    @Test
    public void testTodoCommand() {
        FxRobot robot = new FxRobot();
        robot.clickOn("#userInput").write("todo hello");
        robot.clickOn("#sendButton");
        Set<Node> nodes = lookup("#dialog").queryAll();
        for (int i = 0; i < nodes.size(); i++) {
            if (i == 2) {
                assertEquals(EXPECTED, ((Label) nodes.toArray()[i]).getText());
            }
        }
    }
}

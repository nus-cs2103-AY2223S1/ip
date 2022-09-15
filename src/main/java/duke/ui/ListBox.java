package duke.ui;

import java.io.IOException;

import duke.tasks.Task;
import duke.utils.TaskList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * This control represents a container for task items.
 *
 * @author sikai00
 */
public class ListBox extends VBox {
    private static final CornerRadii CORNER_RADII = new CornerRadii(10.0);
    private static final Color ODD_COLOR = Color.web("#fcfcc0");
    private static final Color EVEN_COLOR = Color.WHITE;
    private static final DropShadow SHADOW = new DropShadow(
            8,
            Color.rgb(0, 0, 0, 0.1)
    );

    private ListBox() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/ListBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ListBox getListBox(TaskList taskList) {
        ListBox lb = new ListBox();
        lb.setEffect(SHADOW);
        for (int i = 0; i < taskList.size(); i++) {
            TaskItem ti = TaskItem.getTaskItem(taskList.getTask(i));
            ti.setIndex(i);
            if (i % 2 == 0) {
                ti.setBackground(new Background(new BackgroundFill(ODD_COLOR, CORNER_RADII, Insets.EMPTY)));
            } else {
                ti.setBackground(new Background(new BackgroundFill(EVEN_COLOR, CORNER_RADII, Insets.EMPTY)));
            }
            lb.getChildren().add(ti);
        }
        return lb;
    }

    public static ListBox getListBox(Task task) {
        TaskList tl = new TaskList();
        tl.addTask(task);
        return ListBox.getListBox(tl);
    }
}

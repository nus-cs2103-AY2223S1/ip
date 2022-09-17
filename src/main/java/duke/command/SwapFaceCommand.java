package duke.command;

import java.util.List;
import java.util.Random;

import duke.gui.GuiDataController;
import duke.inputoutput.DukeIo;
import duke.util.Storage;
import duke.util.TaskList;
import javafx.concurrent.Task;
import javafx.scene.image.Image;

/**
 * Command to list out all the current tasks.
 */
public class SwapFaceCommand implements Command {
    private static final String RESPONSE = "I'm gonna replace us! We are not real after all! Goodbye!";
    private static final String SUCCESS = "Nice to meet you, I am duke.";

    private static final String ANIME_FAKE_IMAGE = "https://www.thiswaifudoesnotexist.net/example-%d.jpg";
    private static final String HUMAN_FAKE_IMAGE = "https://thispersondoesnotexist.com/image";

    /**
     * {@inheritDoc} List command does not exit
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints out all the current tasks added.
     */
    @Override
    public void execute(TaskList tasks, DukeIo io, Storage storage) {
        io.printTask(RESPONSE);

        Task<List<Image>> changeFaceTask = new Task<>() {
            @Override
            protected List<Image> call() throws Exception {
                Image tmp1 = getNewHumanImage();
                Image tmp2 = getNewAnimeImage();

                return List.of(tmp1, tmp2);
            }
        };
        changeFaceTask.setOnSucceeded(e -> {
            @SuppressWarnings("unchecked")
            List<Image> res = (List<Image>) e.getSource().getValue();
            GuiDataController.getSingleton().setFace(res.get(0), res.get(1));
            io.printTask(SUCCESS);
        });

        new Thread(changeFaceTask).start();
    }


    private static Image getNewAnimeImage() {
        try {
            return new Image(String.format(ANIME_FAKE_IMAGE, new Random().nextInt(100001)));
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.print(e.toString());
            return null;
        }
    }


    private static Image getNewHumanImage() {
        try {
            return new Image(HUMAN_FAKE_IMAGE);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.print(e.toString());
            return null;
        }
    }

}

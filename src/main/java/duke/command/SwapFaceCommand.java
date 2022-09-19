package duke.command;

import java.util.List;
import java.util.Random;

import duke.exceptions.GuiOnlyException;
import duke.exceptions.ImageDownloadFailedException;
import duke.exceptions.OperatonIsStillRunningException;
import duke.exceptions.UnknownCommandException;
import duke.gui.GuiDataController;
import duke.inputoutput.DukeGuiIo;
import duke.inputoutput.DukeIo;
import duke.util.ParsedData;
import duke.util.Storage;
import duke.util.TaskList;
import javafx.concurrent.Task;
import javafx.scene.image.Image;

/**
 * Command to list out all the current tasks.
 */
public class SwapFaceCommand extends NoParamCommand {
    private static final String RESPONSE = "I'm gonna replace us! We are not real after all! Goodbye!";
    private static final String SUCCESS = "Nice to meet you, I am duke.";
    private static final String ANIME_FAKE_IMAGE = "https://www.thiswaifudoesnotexist.net/example-%d.jpg";
    private static final String HUMAN_FAKE_IMAGE = "https://thispersondoesnotexist.com/image";

    private static boolean isRunning = false;

    public SwapFaceCommand(ParsedData data) {
        super(data);
    }

    /**
     * {@inheritDoc} List command does not exit
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints out all the current tasks added.
     *
     * @throws GuiOnlyException when called via CLI mode
     * @throws OperatonIsStillRunningException when another swap face command is running
     * @throws UnknownCommandException when extra parameters is included
     */
    @Override
    public void execute(TaskList tasks, DukeIo io, Storage storage)
            throws GuiOnlyException, OperatonIsStillRunningException, UnknownCommandException {

        checkSingleArgumentGuard();
        if (!(io instanceof DukeGuiIo)) {
            throw new GuiOnlyException();
        }
        if (isRunning) {
            throw new OperatonIsStillRunningException();
        }

        assert !isRunning;
        io.printTask(RESPONSE);
        isRunning = true;

        Task<List<Image>> changeFaceTask = createChangeFaceTask(io);
        new Thread(changeFaceTask).start();
    }

    private static Task<List<Image>> createChangeFaceTask(DukeIo io) {
        Task<List<Image>> changeFaceTask = new Task<List<Image>>() {
            @Override
            protected List<Image> call() throws Exception {
                Image tmp1 = getNewHumanImage();
                Image tmp2 = getNewAnimeImage();
                if (tmp1.isError()) {
                    io.printError(new ImageDownloadFailedException("you"));
                }
                if (tmp2.isError()) {
                    io.printError(new ImageDownloadFailedException("me"));
                }
                return List.of(tmp1, tmp2);
            }
        };
        changeFaceTask.setOnSucceeded(e -> {
            @SuppressWarnings("unchecked")
            List<Image> res = (List<Image>) e.getSource().getValue();
            assert res.size() == 2;
            GuiDataController.getSingleton().setFace(res.get(0), res.get(1));
            io.printTask(SUCCESS);
            isRunning = false;
        });
        changeFaceTask.setOnFailed(e -> {
            io.printError(new ImageDownloadFailedException("both of us"));
            isRunning = false;
        });
        return changeFaceTask;
    }


    private static Image getNewAnimeImage() {
        try {
            return new Image(String.format(ANIME_FAKE_IMAGE, new Random().nextInt(100001)));
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.print(e.toString());
            return null;
        }
    }


    private static Image getNewHumanImage() throws ImageDownloadFailedException {
        try {
            return new Image(HUMAN_FAKE_IMAGE);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.print(e.toString());
            return null;
        }
    }

}

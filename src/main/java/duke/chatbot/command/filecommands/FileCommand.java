package duke.chatbot.command.filecommands;

import java.io.File;

import duke.chatbot.command.Command;
import duke.chatbot.storage.Storage;

/**
 * Encapsulates a command that executes on files.
 */
public abstract class FileCommand extends Command {
    protected File getFile() {
        return new File(String.format("%s/%s.txt", Storage.DATA_STORAGE_PATH_PREFIX, arguments));
    }
}

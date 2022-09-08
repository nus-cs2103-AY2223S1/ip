package duke.chatbot.command.filecommands;

import static duke.chatbot.common.Message.MESSAGE_CANNOT_DELETE_FILE;
import static duke.chatbot.common.Message.MESSAGE_DELETE_FILE;

import java.io.File;

import duke.chatbot.command.CommandResult;
import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.storage.Storage;

/**
 * A command that deletes a file.
 */
public class DeleteFileCommand extends FileCommand {
    /**
     * The command word to invoke this command.
     */
    public static final String COMMAND_WORD = "filedel";

    public DeleteFileCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    protected String buildMessage() {
        messageBuilder.buildLines(MESSAGE_DELETE_FILE, arguments);
        return messageBuilder.toString();
    }

    @Override
    public CommandResult execute() throws InvalidInputException {
        File file = getFile();
        Storage dukeStorage = duke.getStorage();
        if (dukeStorage.hasSameFile(file)) {
            throw new InvalidInputException(MESSAGE_CANNOT_DELETE_FILE);
        }
        file.delete();
        return super.execute();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof DeleteFileCommand;
    }
}

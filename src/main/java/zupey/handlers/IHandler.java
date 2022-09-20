package zupey.handlers;

import zupey.exceptions.ZupeyException;
import zupey.service.Service;

/**
 * Interface representing a Handler.
 */
public interface IHandler {
    /**
     * Handles the command
     * @param s Service object of the application
     * @throws ZupeyException
     */
    String handle(Service s) throws ZupeyException;
}

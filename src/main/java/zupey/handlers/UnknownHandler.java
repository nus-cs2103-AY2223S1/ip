package zupey.handlers;

import zupey.exceptions.ZupeyException;
import zupey.service.Service;

/**
 * Handler to handle commands that are unknown.
 */
public class UnknownHandler implements IHandler {
    @Override
    public String handle(Service s) throws ZupeyException {
        throw new ZupeyException("Unknown command! Please try again.");
    }
}


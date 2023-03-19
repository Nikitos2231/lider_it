package test.example.leader_it.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import test.example.leader_it.exceptions.bad_request_exceptions.EmptyRequestBodyException;
import test.example.leader_it.exceptions.bad_request_exceptions.FilterRequestException;
import test.example.leader_it.exceptions.bad_request_exceptions.InvalidDataForEntityException;

@Component
public class RequestValidator {

    private static final Logger logger = LogManager.getLogger(RequestValidator.class);

    public <T> void validateEntityRequest(BindingResult bindingResult, T t) throws EmptyRequestBodyException, InvalidDataForEntityException {
        validateNullRequest(t);
        if (bindingResult.hasErrors()) {
            logger.warn("User entered invalid data for modification entity request");
            throw new InvalidDataForEntityException(BindingResultFiller.fillBindingResult(bindingResult));
        }
    }

    public void validateRequest(BindingResult bindingResult) throws FilterRequestException {
        if (bindingResult.hasErrors()) {
            logger.warn("User entered invalid data for filter request");
            throw new FilterRequestException(BindingResultFiller.fillBindingResult(bindingResult));
        }
    }

    private <T> void validateNullRequest(T t) throws EmptyRequestBodyException {
        if (t == null) {
            logger.warn("User entered null request");
            throw new EmptyRequestBodyException("request body can't be empty!");
        }
    }
}

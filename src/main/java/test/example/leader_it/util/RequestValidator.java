package test.example.leader_it.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import test.example.leader_it.exceptions.bad_request_exceptions.EmptyRequestBodyException;
import test.example.leader_it.exceptions.bad_request_exceptions.InvalidDataForEntityException;
import test.example.leader_it.exceptions.bad_request_exceptions.FilterRequestException;

@Component
public class RequestValidator {

    public <T> void validateEntityRequest(BindingResult bindingResult, T t) throws EmptyRequestBodyException, InvalidDataForEntityException {
        validateNullRequest(t);
        if (bindingResult.hasErrors()) {
            throw new InvalidDataForEntityException(BindingResultFiller.fillBindingResult(bindingResult));
        }
    }

    public void validateRequest(BindingResult bindingResult) throws FilterRequestException {
        if (bindingResult.hasErrors()) {
            throw new FilterRequestException(BindingResultFiller.fillBindingResult(bindingResult));
        }
    }

    private <T> void validateNullRequest(T t) throws EmptyRequestBodyException {
        if (t == null) {
            throw new EmptyRequestBodyException("request body can't be empty!");
        }
    }
}

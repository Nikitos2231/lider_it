package test.example.leader_it.exceptions.bad_request_exceptions;

public class EmptyRequestBodyException extends BadRequestException {

    public EmptyRequestBodyException(String message) {
        super(message);
    }
}

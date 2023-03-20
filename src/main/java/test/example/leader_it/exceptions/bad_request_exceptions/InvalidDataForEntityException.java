package test.example.leader_it.exceptions.bad_request_exceptions;

public class InvalidDataForEntityException extends BadRequestException {

    public InvalidDataForEntityException(String message) {
        super(message);
    }
}

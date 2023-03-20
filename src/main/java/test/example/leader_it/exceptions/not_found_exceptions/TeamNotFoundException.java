package test.example.leader_it.exceptions.not_found_exceptions;

public class TeamNotFoundException extends NotFoundException {

    public TeamNotFoundException(String message) {
        super(message);
    }
}

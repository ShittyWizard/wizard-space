package app.record.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends Exception {
    private UUID id;

    public RecordNotFoundException(UUID id) {
        super("Record with id " + id + " is not found.");
        this.id = id;
    }
}

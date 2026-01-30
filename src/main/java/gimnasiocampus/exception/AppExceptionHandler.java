package gimnasiocampus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(RegistroNoEncontradoException.class)
    public ResponseEntity<MyError> handleRegistroNoEncontrado(RegistroNoEncontradoException ex) {
        MyError error = new MyError("Registro no encontrado", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictDbException.class)
    public ResponseEntity<MyError> handleConflictDb(ConflictDbException ex) {
        MyError error = new MyError("Conflicto en la base de datos", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyError> handleGeneral(Exception ex) {
        MyError error = new MyError("Error interno del servidor", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

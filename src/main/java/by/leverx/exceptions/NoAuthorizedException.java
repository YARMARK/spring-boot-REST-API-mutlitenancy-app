package by.leverx.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.FORBIDDEN)
public class NoAuthorizedException extends RuntimeException{

  public NoAuthorizedException(String message) {
    super(message);
  }
}

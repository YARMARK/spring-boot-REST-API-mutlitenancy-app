package by.leverx.controller;

import com.sap.cloud.security.xsuaa.token.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "")
public class HelloController {

  @GetMapping(path = "")
  public ResponseEntity<String> readAll(@AuthenticationPrincipal Token token) {
//    if (!token.getAuthorities().contains(new SimpleGrantedAuthority("Display"))) {
//      throw new NoAuthorizedException("This operation requires \"Display\" scope");
//    }
    return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
  }
}

package by.leverx.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "")
public class HelloController {

  @GetMapping(path = "")
  public ResponseEntity<String> getHello() {
    return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
  }
}

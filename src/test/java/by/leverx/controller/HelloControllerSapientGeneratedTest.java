package by.leverx.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;

import com.sap.cloud.security.xsuaa.token.Token;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class HelloControllerSapientGeneratedTest {

  @Test()
  void readAllTest() {
    HelloController target = new HelloController();
    Token tokenMock = mock(Token.class);
    ResponseEntity<String> result = target.readAll(tokenMock);
    ResponseEntity<String> responseEntity = new ResponseEntity<>("Hello World!", HttpStatus.OK);
    assertAll("result", () -> assertThat(result, equalTo(responseEntity)));
  }
}

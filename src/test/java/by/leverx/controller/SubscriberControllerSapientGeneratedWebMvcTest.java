package by.leverx.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import by.leverx.model.SubscriberEntity;
import by.leverx.service.SubscriberService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest()
@ContextConfiguration(classes = SubscriberController.class)
class SubscriberControllerSapientGeneratedWebMvcTest {

  @Autowired()
  private MockMvc mockMvc;

  @MockBean(name = "service")
  private SubscriberService serviceMock;

  private AutoCloseable autoCloseableMocks;

  @BeforeEach()
  public void beforeTest() {
    autoCloseableMocks = MockitoAnnotations.openMocks(this);
  }

  @AfterEach()
  public void afterTest() throws Exception {
    if (autoCloseableMocks != null) {
      autoCloseableMocks.close();
    }
  }

  //Sapient generated method id: ${a3cb2962-999b-3110-a2fe-1621c5e844cb}
  @Test()
  void getAllSubscribersWhenAllSubscribersIsEmpty() throws Exception {
    /* Branches:* (allSubscribers.isEmpty()) : true** TODO: Help needed! This method is not unit testable!*  Following variables could not be isolated/mocked: org.springframework.http.ResponseEntity$HeadersBuilder*  Suggestions:*  You can change the initialization of above variables and make it injectable or*  adjust the input/test parameter values manually to satisfy the requirements of the given test scenario.*  The test code, including the assertion statements, has been successfully generated.*/
    List<SubscriberEntity> subscriberEntityList = new ArrayList<>();
    doReturn(subscriberEntityList).when(serviceMock).getAllSubscribers();
    ResultActions resultActions = this.mockMvc.perform(
        get("/subscribers").contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));
    ResponseEntity.HeadersBuilder headersBuilder = ResponseEntity.noContent();
    ResponseEntity<List<SubscriberEntity>> responseEntity = headersBuilder.build();
    assertAll("result",
        () -> resultActions.andExpect(status().is(responseEntity.getStatusCode().value())));
  }

  //Sapient generated method id: ${3d364454-9f0a-3b83-9b14-d0011c92a8cb}
  @Test()
  void getAllSubscribersWhenAllSubscribersNotIsEmpty() throws Exception {
    /* Branches:* (allSubscribers.isEmpty()) : false*/
    SubscriberEntity subscriberEntity = new SubscriberEntity();
    List<SubscriberEntity> subscriberEntityList = new ArrayList<>();
    subscriberEntityList.add(subscriberEntity);
    doReturn(subscriberEntityList).when(serviceMock).getAllSubscribers();
    ResultActions resultActions = this.mockMvc.perform(
        get("/subscribers").contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));
    assertAll("result", () -> resultActions.andExpect(status().isOk()));
  }

  @SpringBootApplication(scanBasePackageClasses = SubscriberController.class)
  static class SubscriberControllerSapientGeneratedWebMvcTestConfig {

  }
}

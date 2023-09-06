package by.leverx.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

import by.leverx.dto.SubscriberDto;
import by.leverx.dto.response.SubscriberResponse;
import by.leverx.model.SubscriberEntity;
import by.leverx.service.SubscriberService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class SubscriberControllerSapientGeneratedTest {

  @Test()
  void getAllSubscribersWhenAllSubscribersIsEmpty() {
    // given
    SubscriberService subscriberServiceMock = mock(SubscriberService.class);
    SubscriberController target = new SubscriberController(subscriberServiceMock);
    List<SubscriberEntity> subscriberEntityList = new ArrayList<>();
    // when
    doReturn(subscriberEntityList).when(subscriberServiceMock).getAllSubscribers();
    ResponseEntity<List<SubscriberEntity>> result = target.getAllSubscribers();
    ResponseEntity.HeadersBuilder headersBuilder = ResponseEntity.noContent();
    ResponseEntity<List<SubscriberEntity>> responseEntity = headersBuilder.build();
    // then
    assertAll("result", () -> {
      assertThat(result, equalTo(responseEntity));
      verify(subscriberServiceMock).getAllSubscribers();
    });
  }

  @Test()
  void getAllSubscribersWhenAllSubscribersNotIsEmpty() {
    // given
    ResponseEntity<List<SubscriberEntity>> responseEntityMock = mock(ResponseEntity.class);
    SubscriberEntity subscriberEntityMock = mock(SubscriberEntity.class);
    SubscriberService subscriberServiceMock = mock(SubscriberService.class);
    // when
    try (MockedStatic<ResponseEntity> responseEntity = mockStatic(ResponseEntity.class)) {
      List<SubscriberEntity> subscriberEntityList = new ArrayList<>();
      subscriberEntityList.add(subscriberEntityMock);
      responseEntity.when(() -> ResponseEntity.ok(subscriberEntityList))
          .thenReturn(responseEntityMock);
      SubscriberController target = new SubscriberController(subscriberServiceMock);
      doReturn(subscriberEntityList).when(subscriberServiceMock).getAllSubscribers();
      ResponseEntity<List<SubscriberEntity>> result = target.getAllSubscribers();
      // then
      assertAll("result", () -> {
        assertThat(result, equalTo(responseEntityMock));
        responseEntity.verify(() -> ResponseEntity.ok(subscriberEntityList), atLeast(1));
        verify(subscriberServiceMock).getAllSubscribers();
      });
    }
  }

  @Test()
  void getSubscriberTest() {
    // given
    SubscriberResponse subscriberResponseMock = mock(SubscriberResponse.class);
    SubscriberEntity subscriberEntityMock = mock(SubscriberEntity.class);
    ResponseEntity<SubscriberResponse> responseEntityMock = mock(ResponseEntity.class);
    SubscriberService subscriberServiceMock = mock(SubscriberService.class);
    // when
    try (MockedStatic<ResponseEntity> responseEntity = mockStatic(ResponseEntity.class);
        MockedStatic<SubscriberResponse> subscriberResponse = mockStatic(
            SubscriberResponse.class)) {
      subscriberResponse.when(() -> SubscriberResponse.toResponse(subscriberEntityMock))
          .thenReturn(subscriberResponseMock);
      responseEntity.when(() -> ResponseEntity.ok(subscriberResponseMock))
          .thenReturn(responseEntityMock);
      SubscriberController target = new SubscriberController(subscriberServiceMock);
      doReturn(subscriberEntityMock).when(subscriberServiceMock).getSubscriber(0L);
      ResponseEntity<SubscriberResponse> result = target.getSubscriber(0L);
      // then
      assertAll("result", () -> {
        assertThat(result, equalTo(responseEntityMock));
        subscriberResponse.verify(() -> SubscriberResponse.toResponse(subscriberEntityMock),
            atLeast(1));
        responseEntity.verify(() -> ResponseEntity.ok(subscriberResponseMock), atLeast(1));
        verify(subscriberServiceMock).getSubscriber(0L);
      });
    }
  }

  @Test()
  void deleteBookTest() {
    // given
    SubscriberService subscriberServiceMock = mock(SubscriberService.class);
    SubscriberController target = new SubscriberController(subscriberServiceMock);
    // when
    doNothing().when(subscriberServiceMock).delete(0L);
    ResponseEntity<SubscriberResponse> result = target.deleteBook(0L);
    ResponseEntity<SubscriberResponse> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // then
    assertAll("result", () -> {
      assertThat(result, equalTo(responseEntity));
      verify(subscriberServiceMock).delete(0L);
    });
  }

  @Test()
  void createBookTest() {
    // given
    SubscriberResponse subscriberResponseMock = mock(SubscriberResponse.class);
    SubscriberEntity subscriberEntityMock = mock(SubscriberEntity.class);
    ResponseEntity<SubscriberResponse> responseEntityMock = mock(ResponseEntity.class);
    SubscriberService subscriberServiceMock = mock(SubscriberService.class);
    SubscriberDto subscriberDtoMock = mock(SubscriberDto.class);
    // when
    try (MockedStatic<ResponseEntity> responseEntity = mockStatic(ResponseEntity.class);
        MockedStatic<SubscriberResponse> subscriberResponse = mockStatic(
            SubscriberResponse.class)) {
      subscriberResponse.when(() -> SubscriberResponse.toResponse(subscriberEntityMock))
          .thenReturn(subscriberResponseMock);
      responseEntity.when(() -> ResponseEntity.ok(subscriberResponseMock))
          .thenReturn(responseEntityMock);
      SubscriberController target = new SubscriberController(subscriberServiceMock);
      doReturn(subscriberEntityMock).when(subscriberServiceMock).create(subscriberDtoMock);
      ResponseEntity<SubscriberResponse> result = target.createBook(subscriberDtoMock);
      // then
      assertAll("result", () -> {
        assertThat(result, equalTo(responseEntityMock));
        subscriberResponse.verify(() -> SubscriberResponse.toResponse(subscriberEntityMock),
            atLeast(1));
        responseEntity.verify(() -> ResponseEntity.ok(subscriberResponseMock), atLeast(1));
        verify(subscriberServiceMock).create(subscriberDtoMock);
      });
    }
  }

  @Test()
  void updateBookTest() {
    // then
    SubscriberResponse subscriberResponseMock = mock(SubscriberResponse.class);
    SubscriberEntity subscriberEntityMock = mock(SubscriberEntity.class);
    ResponseEntity<SubscriberResponse> responseEntityMock = mock(ResponseEntity.class);
    SubscriberService subscriberServiceMock = mock(SubscriberService.class);
    SubscriberDto subscriberDtoMock = mock(SubscriberDto.class);
    // when
    try (MockedStatic<ResponseEntity> responseEntity = mockStatic(ResponseEntity.class);
        MockedStatic<SubscriberResponse> subscriberResponse = mockStatic(
            SubscriberResponse.class)) {
      subscriberResponse.when(() -> SubscriberResponse.toResponse(subscriberEntityMock))
          .thenReturn(subscriberResponseMock);
      responseEntity.when(() -> ResponseEntity.ok(subscriberResponseMock))
          .thenReturn(responseEntityMock);
      SubscriberController target = new SubscriberController(subscriberServiceMock);
      doReturn(subscriberEntityMock).when(subscriberServiceMock).update(subscriberDtoMock, 0L);
      ResponseEntity<SubscriberResponse> result = target.updateBook(subscriberDtoMock, 0L);
      // then
      assertAll("result", () -> {
        assertThat(result, equalTo(responseEntityMock));
        subscriberResponse.verify(() -> SubscriberResponse.toResponse(subscriberEntityMock),
            atLeast(1));
        responseEntity.verify(() -> ResponseEntity.ok(subscriberResponseMock), atLeast(1));
        verify(subscriberServiceMock).update(subscriberDtoMock, 0L);
      });
    }
  }
}

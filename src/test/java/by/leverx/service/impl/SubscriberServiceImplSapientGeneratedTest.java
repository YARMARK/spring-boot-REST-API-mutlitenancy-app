package by.leverx.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import by.leverx.dto.SubscriberDto;
import by.leverx.model.SubscriberEntity;
import by.leverx.repository.SubscriberRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SubscriberServiceImplSapientGeneratedTest {

  @Test()
  void getSubscriberWhenByIdIsPresent() {
    // given
    SubscriberRepository subscriberRepositoryMock = mock(SubscriberRepository.class);
    SubscriberServiceImpl target = new SubscriberServiceImpl(subscriberRepositoryMock);
    SubscriberEntity subscriberEntityMock = mock(SubscriberEntity.class);
    doReturn(Optional.of(subscriberEntityMock)).when(subscriberRepositoryMock).findById(0L);
    // when
    SubscriberEntity result = target.getSubscriber(0L);
    //then
    assertAll("result", () -> {
      assertThat(result, equalTo(subscriberEntityMock));
      verify(subscriberRepositoryMock).findById(0L);
    });
  }

  @Test()
  void getSubscriberWhenByIdNotIsPresentThrowsRuntimeException() {
    // given
    SubscriberRepository subscriberRepositoryMock = mock(SubscriberRepository.class);
    SubscriberServiceImpl target = new SubscriberServiceImpl(subscriberRepositoryMock);
    RuntimeException runtimeException = new RuntimeException("Subscriber with id 1 is not found");
    doReturn(Optional.empty()).when(subscriberRepositoryMock).findById(1L);
    // when
    final RuntimeException result = assertThrows(RuntimeException.class,
        () -> target.getSubscriber(1L));
    // then
    assertAll("result", () -> {
      assertThat(result, is(notNullValue()));
      assertThat(result.getMessage(), equalTo(runtimeException.getMessage()));
      verify(subscriberRepositoryMock).findById(1L);
    });
  }

  @Test()
  void getAllSubscribersTest() {
    // given
    /** TODO: Help needed! Please adjust the input/test parameter values manually to satisfy the requirements of the given test scenario.*  The test code, including the assertion statements, has been successfully generated.*/
    SubscriberRepository subscriberRepositoryMock = mock(SubscriberRepository.class);
    SubscriberServiceImpl target = new SubscriberServiceImpl(subscriberRepositoryMock);
    var iterableMock = mock(Iterable.class);
    doReturn(iterableMock).when(subscriberRepositoryMock).findAll();
    // when
    List<SubscriberEntity> result = target.getAllSubscribers();
    // then
    assertAll("result", () -> {
      assertThat(result.size(), equalTo(0));
      verify(subscriberRepositoryMock).findAll();
    });
  }

  @Test()
  void updateWhenByIdIsPresent() {
    // given
    SubscriberRepository subscriberRepositoryMock = mock(SubscriberRepository.class);
    SubscriberServiceImpl target = new SubscriberServiceImpl(subscriberRepositoryMock);
    SubscriberEntity subscriberEntity = new SubscriberEntity();
    doReturn(Optional.of(subscriberEntity)).when(subscriberRepositoryMock).findById(0L);
    subscriberEntity.setName("name1");
      SubscriberEntity objectMock = mock(SubscriberEntity.class);
    when(subscriberRepositoryMock.save(any())).thenReturn(objectMock);
    SubscriberDto subscriberDto = new SubscriberDto();
    subscriberDto.setName("name1");
    // when
    SubscriberEntity result = target.update(subscriberDto, 0L);
    // then
    assertAll("result", () -> {
      assertThat(result, equalTo(subscriberEntity));
      verify(subscriberRepositoryMock).findById(0L);
      verify(subscriberRepositoryMock).save(any());
    });
  }

  @Test()
  void updateWhenByIdNotIsPresentThrowsRuntimeException() {
    // given
    SubscriberRepository subscriberRepositoryMock = mock(SubscriberRepository.class);
    SubscriberServiceImpl target = new SubscriberServiceImpl(subscriberRepositoryMock);
    SubscriberDto subscriberDtoMock = mock(SubscriberDto.class);
    RuntimeException runtimeException = new RuntimeException("Subscriber with id 1 is not found");
    doReturn(Optional.empty()).when(subscriberRepositoryMock).findById(1L);
    // when
    final RuntimeException result = assertThrows(RuntimeException.class,
        () -> target.update(subscriberDtoMock, 1L));
    // then
    assertAll("result", () -> {
      assertThat(result, is(notNullValue()));
      assertThat(result.getMessage(), equalTo(runtimeException.getMessage()));
      verify(subscriberRepositoryMock).findById(1L);
    });
  }

  @Test()
  void deleteWhenByIdIsPresentThrowsRuntimeException() {
    // given
    SubscriberRepository subscriberRepositoryMock = mock(SubscriberRepository.class);
    SubscriberServiceImpl target = new SubscriberServiceImpl(subscriberRepositoryMock);
    SubscriberEntity subscriberEntityMock = mock(SubscriberEntity.class);
    RuntimeException runtimeException = new RuntimeException("Subscriber with id 1 is not found");
    doReturn(Optional.of(subscriberEntityMock)).when(subscriberRepositoryMock).findById(1L);
    doNothing().when(subscriberRepositoryMock).delete(subscriberEntityMock);
    // when
    final RuntimeException result = assertThrows(RuntimeException.class, () ->
        target.delete(1L));
    // then
    assertAll("result", () -> {
      assertThat(result, is(notNullValue()));
      assertThat(result.getMessage(), equalTo(runtimeException.getMessage()));
      verify(subscriberRepositoryMock).findById(1L);
      verify(subscriberRepositoryMock).delete(subscriberEntityMock);
    });
  }

  @Test()
  void createTest() {
    // given
    SubscriberRepository subscriberRepositoryMock = mock(SubscriberRepository.class);
    SubscriberServiceImpl target = new SubscriberServiceImpl(subscriberRepositoryMock);
    SubscriberEntity objectMock = mock(SubscriberEntity.class);
    SubscriberDto subscriberDto = new SubscriberDto();
    subscriberDto.setName("name1");
    SubscriberEntity subscriberEntity = new SubscriberEntity();
    subscriberEntity.setName("name1");
    when(subscriberRepositoryMock.save(any())).thenReturn(objectMock);
    // when
    SubscriberEntity result = target.create(subscriberDto);
    //TODO: Please implement equals method in SubscriberEntity for verification to succeed or you need to adjust respective assertion statements
    assertAll("result", () -> {
      assertThat(result, equalTo(subscriberEntity));
      verify(subscriberRepositoryMock).save(any());
    });
  }

}

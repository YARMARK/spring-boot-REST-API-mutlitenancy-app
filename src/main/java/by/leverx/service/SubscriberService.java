package by.leverx.service;

import by.leverx.dto.SubscriberDto;
import by.leverx.model.SubscriberEntity;
import java.util.List;

public interface SubscriberService {
  SubscriberEntity getSubscriber(Long id);

  List<SubscriberEntity> getAllSubscribers();

  SubscriberEntity update(SubscriberDto dto, Long id);

  void delete(Long id);

  SubscriberEntity create(SubscriberDto book);
}

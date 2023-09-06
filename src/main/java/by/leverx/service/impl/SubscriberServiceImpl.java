package by.leverx.service.impl;

import by.leverx.dto.SubscriberDto;
import by.leverx.model.SubscriberEntity;
import by.leverx.repository.SubscriberRepository;
import by.leverx.service.SubscriberService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriberServiceImpl implements SubscriberService {

  private SubscriberRepository repository;

  @Autowired
  public SubscriberServiceImpl(SubscriberRepository repository) {
    this.repository = repository;
  }

  @Override
  public SubscriberEntity getSubscriber(Long id) {
    Optional<SubscriberEntity> byId = repository.findById(id);
    if(byId.isPresent()){
      return byId.get();
    }
    throw new RuntimeException(String.format("Subscriber with id %s is not found", id.toString()));
  }

  @Override
  public List<SubscriberEntity> getAllSubscribers() {
    List<SubscriberEntity> list = new ArrayList<>();
   repository.findAll().forEach(list::add);
   return list;
  }

  @Override
  public SubscriberEntity update(SubscriberDto dto, Long id) {
    Optional<SubscriberEntity> byId = repository.findById(id);
    if (byId.isPresent()){
      SubscriberEntity subscriberEntity = byId.get();
      subscriberEntity.setName(dto.getName());
      repository.save(subscriberEntity);
      return subscriberEntity;
    }
    throw new RuntimeException(String.format("Subscriber with id %s is not found", id.toString()));
  }

  @Override
  public void delete(Long id) {
    Optional<SubscriberEntity> byId = repository.findById(id);
    byId.ifPresent(subscriberEntity -> repository.delete(subscriberEntity));
    throw new RuntimeException(String.format("Subscriber with id %s is not found", id.toString()));
  }

  @Override
  public SubscriberEntity create(SubscriberDto dto) {
    SubscriberEntity entity = new SubscriberEntity();
    entity.setName(dto.getName());
    repository.save(entity);
    return entity;
  }
}

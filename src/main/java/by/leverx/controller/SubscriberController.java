package by.leverx.controller;

import by.leverx.dto.SubscriberDto;
import by.leverx.dto.response.SubscriberResponse;
import by.leverx.model.SubscriberEntity;
import by.leverx.service.SubscriberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscribers")
@RequiredArgsConstructor
public class SubscriberController {

  private SubscriberService service;

  @Autowired
  public SubscriberController(SubscriberService service) {
    this.service = service;
  }

  public static Logger logger = LoggerFactory.getLogger(SubscriberController.class);

  @GetMapping
  public ResponseEntity<List<SubscriberEntity>> getAllSubscribers() {
    logger.info("GET SUB");
    List<SubscriberEntity> allSubscribers = service.getAllSubscribers();
    return allSubscribers.isEmpty() ? ResponseEntity.noContent().build()
        : ResponseEntity.ok(allSubscribers);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SubscriberResponse> getSubscriber(@PathVariable Long id) {
    logger.info("GET SUB BY ID");
    return ResponseEntity.ok(SubscriberResponse.toResponse(service.getSubscriber(id)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<SubscriberResponse> deleteBook(@PathVariable Long id) {
    logger.info("DELETE SUB BY ID");
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PostMapping
  public ResponseEntity<SubscriberResponse> createBook(@RequestBody SubscriberDto dto) {
    logger.info("POST SUB");
    return ResponseEntity.ok(SubscriberResponse.toResponse(service.create(dto)));
  }

  @PutMapping("/{id}")
  public ResponseEntity<SubscriberResponse> updateBook(@RequestBody SubscriberDto dto,
      @PathVariable Long id) {
    logger.info("PUT SUB");
    return ResponseEntity.ok(SubscriberResponse.toResponse(service.update(dto, id)));
  }
}

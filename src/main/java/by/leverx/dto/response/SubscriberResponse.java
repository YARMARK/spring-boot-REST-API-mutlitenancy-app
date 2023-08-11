package by.leverx.dto.response;

import by.leverx.model.SubscriberEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriberResponse {

  private String id;
  private String name;

  public static SubscriberResponse toResponse(SubscriberEntity entity) {
    return SubscriberResponse.builder()
        .id(String.valueOf(entity.getId()))
        .name(entity.getName())
        .build();
  }
}

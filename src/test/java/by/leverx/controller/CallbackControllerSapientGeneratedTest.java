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

import by.leverx.service.impl.DefaultTenantProvisioningService;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class CallbackControllerSapientGeneratedTest {

  @Test()
  void subscribeTenantTest() {
    // given
    JsonNode requestBodyMock = mock(JsonNode.class);
    JsonNode jsonNodeMock = mock(JsonNode.class);
    ResponseEntity<String> responseEntityMock = mock(ResponseEntity.class);
    DefaultTenantProvisioningService defaultTenantProvisioningServiceMock = mock(
        DefaultTenantProvisioningService.class);
    // when
    try (MockedStatic<ResponseEntity> responseEntity = mockStatic(ResponseEntity.class)) {
      doReturn(jsonNodeMock).when(requestBodyMock).get("subscribedSubdomain");
      doReturn("A").when(jsonNodeMock).asText();
      responseEntity.when(() -> ResponseEntity.ok(
              "https://A-613633eatrial-dev-approuter.cfapps.us10-001.hana.ondemand.com"))
          .thenReturn(responseEntityMock);
      CallbackController target = new CallbackController(defaultTenantProvisioningServiceMock);
      doNothing().when(defaultTenantProvisioningServiceMock).subscribeTenant("tenantId1");
      ResponseEntity<String> result = target.subscribeTenant(requestBodyMock, "tenantId1");
      // then
      assertAll("result", () -> {
        assertThat(result, equalTo(responseEntityMock));
        verify(requestBodyMock).get("subscribedSubdomain");
        verify(jsonNodeMock).asText();
        responseEntity.verify(() -> ResponseEntity.ok(
            "https://A-613633eatrial-dev-approuter.cfapps.us10-001.hana.ondemand.com"), atLeast(1));
        verify(defaultTenantProvisioningServiceMock).subscribeTenant("tenantId1");
      });
    }
  }

  @Test()
  void unsubscribeTenantTest() {
    // given
    DefaultTenantProvisioningService defaultTenantProvisioningServiceMock = mock(
        DefaultTenantProvisioningService.class);
    CallbackController target = new CallbackController(defaultTenantProvisioningServiceMock);
    // when
    doNothing().when(defaultTenantProvisioningServiceMock).unsubscribeTenant("tenantId1");
    ResponseEntity<Void> result = target.unsubscribeTenant("tenantId1");
    ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // then
    assertAll("result", () -> {
      assertThat(result, equalTo(responseEntity));
      verify(defaultTenantProvisioningServiceMock).unsubscribeTenant("tenantId1");
    });
  }
}

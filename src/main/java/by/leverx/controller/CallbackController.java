package by.leverx.controller;

import by.leverx.service.TenantService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/callback/v1.0/tenants")
public class CallbackController {

  private static final String APPROUTER_DOMAIN = "-approuter-reflective-chipmunk-lq.cfapps.us10-001.hana.ondemand.com";
  private static final String HTTPS = "https://";
  private static final String SUBDOMAIN_KEY = "subscribedSubdomain";
  private final TenantService tenantService;

  @Autowired
  public CallbackController(TenantService tenantService) {
    this.tenantService = tenantService;
  }

  @PutMapping("/{tenantId}")
  public ResponseEntity<String> subscribeTenant(@RequestBody JsonNode requestBody,
      @PathVariable(value = "tenantId") String tenantId) {
    tenantService.subscribe(tenantId);
    String tenantUrl = HTTPS + requestBody.get(SUBDOMAIN_KEY).asText() + APPROUTER_DOMAIN;
    return ResponseEntity.ok(tenantUrl);
  }

  @DeleteMapping("/{tenantId}")
  public ResponseEntity<Void> unsubscribeTenant(@PathVariable("tenantId") String tenantId) {
    tenantService.unsubscribe(tenantId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}

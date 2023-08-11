package by.leverx.controller;

import by.leverx.service.impl.DefaultTenantProvisioningService;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestScope
@RequestMapping(path = "/callback/v1.0/tenants")
public class CallbackController {

  private static final String APPROUTER_DOMAIN = "-approuter-reflective-chipmunk-lq.cfapps.us10-001.hana.ondemand.com";
  private static final String HTTPS = "https://";
  private static final String SUBDOMAIN_KEY ="subscribedSubdomain";
  private final DefaultTenantProvisioningService defaultTenantProvisioningService;

  public static Logger logger = LoggerFactory.getLogger(CallbackController.class);

  @Autowired
  public CallbackController(DefaultTenantProvisioningService defaultTenantProvisioningService) {
    this.defaultTenantProvisioningService = defaultTenantProvisioningService;
  }

  @PutMapping("/{tenantId}")
  public ResponseEntity<String> subscribeTenant(@RequestBody JsonNode requestBody,
      @PathVariable(value = "tenantId") String tenantId) {
    logger.info("TENANT SUBSCRIBE CONTROLLER {}", tenantId);
    defaultTenantProvisioningService.subscribeTenant(tenantId);
    String tenantUrl = HTTPS + requestBody.get(SUBDOMAIN_KEY).asText() + APPROUTER_DOMAIN;
    return ResponseEntity.ok(tenantUrl);
  }

  @DeleteMapping("/{tenantId}")
  public ResponseEntity<Void> unsubscribeTenant(@PathVariable("tenantId") String tenantId) {
    logger.info("TENANT UNSUBSCRIBE CONTROLLER {}", tenantId);
    defaultTenantProvisioningService.unsubscribeTenant(tenantId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}

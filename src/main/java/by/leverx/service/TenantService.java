package by.leverx.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TenantService {

  public static Logger logger = LoggerFactory.getLogger(TenantService.class);
  public void subscribe(final String tenantId) {
    logger.info("SUBSCRIBE - {}"+ tenantId);
  }


  public void unsubscribe(final String tenantId) {
    logger.info("UNSUBSCRIPTION - {}" + tenantId);
  }

}

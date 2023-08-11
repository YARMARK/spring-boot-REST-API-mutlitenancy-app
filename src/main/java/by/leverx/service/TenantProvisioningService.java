package by.leverx.service;

public interface TenantProvisioningService {
  void subscribeTenant(String tenantId);
  void unsubscribeTenant(String tenantId);
}

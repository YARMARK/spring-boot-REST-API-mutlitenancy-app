package by.leverx.service.impl;

import by.leverx.service.TenantProvisioningService;
import by.leverx.util.TenantUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;
import javax.sql.DataSource;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultTenantProvisioningService implements TenantProvisioningService {

  @Value("${spring.liquibase.change-log}")
  public String LIQUIBASE_PATH;
  private static final Pattern TENANT_PATTERN = Pattern.compile("[-\\w]+");
  private final DataSource dataSource;

  public static Logger logger = LoggerFactory.getLogger(DefaultTenantProvisioningService.class);

  @Override
  public void subscribeTenant(final String tenantId) {
    logger.info("TENANT SUB SERVICE {}", tenantId);
    String defaultSchemaName;
    try {
      Validate.isTrue(isValidTenantId(tenantId),
          String.format("Invalid tenant id: \"%s\"", tenantId));
      final String schemaName = TenantUtil.createSchemaName(tenantId);

      final Connection connection = dataSource.getConnection();
      final Database database = DatabaseFactory.getInstance()
          .findCorrectDatabaseImplementation(new JdbcConnection(connection));
      try (Statement statement = connection.createStatement()) {
        statement.execute(String.format("CREATE SCHEMA IF NOT EXISTS \"%s\"", schemaName));
        connection.commit();

        defaultSchemaName = database.getDefaultSchemaName();
        database.setDefaultSchemaName(schemaName);

        final Liquibase liquibase = new liquibase.Liquibase(LIQUIBASE_PATH,
            new ClassLoaderResourceAccessor(), database);

        liquibase.update(new Contexts(), new LabelExpression());
        database.setDefaultSchemaName(defaultSchemaName);
      }

    } catch (SQLException | LiquibaseException | IllegalArgumentException e) {
      logger.error("Tenant subscription failed for {}.", tenantId, e);
    }
  }

  @Override
  public void unsubscribeTenant(final String tenantId) {
    logger.info("TENANT UNSAB {}",tenantId);
    try {
      Validate.isTrue(isValidTenantId(tenantId),
          String.format("Invalid tenant id: \"%s\"", tenantId));
      final String schemaName = TenantUtil.createSchemaName(tenantId);
      final Connection connection = dataSource.getConnection();
      try (Statement statement = connection.createStatement()) {
        statement.execute(String.format("DROP SCHEMA IF EXISTS \"%s\" CASCADE", schemaName));
      }
    } catch (SQLException | IllegalArgumentException e) {
      logger.error("Tenant unsubscription failed for {}.", tenantId, e);
    }
  }

  private boolean isValidTenantId(final String tenantId) {
    return tenantId != null && TENANT_PATTERN.matcher(tenantId).matches();
  }
}

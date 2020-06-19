package linwu.tenant.extend;

import com.zaxxer.hikari.HikariDataSource;
import linwu.tenant.utils.ApplicationContextUtil;
import linwu.tenant.utils.TenantHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：林雾
 * @date ：2020/06/19
 * @description :
 */
@Slf4j
public class TenantDataSourceExtend extends AbstractRoutingDataSource {

  private Map<String, DataSource> dataSources = new ConcurrentHashMap<>(10);

  @Override
  protected Object determineCurrentLookupKey() {
    return "";
  }

  @Override
  protected DataSource determineTargetDataSource() {
    String tenantId = TenantHolder.getTenantId();

    DataSource dataSource;
    if (tenantId == null || tenantId.isEmpty()) {
      dataSource = ApplicationContextUtil.getBean("mainDatasource", DataSource.class);
    } else {
      dataSource = dataSources.get(tenantId);
    }

    HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
    log.info("当前dataSource:{}", hikariDataSource.getUsername());
    return dataSource;
  }

  @Override
  public void afterPropertiesSet() {}

  public void putDataSource(String tanentId, DataSource dataSource) {
    dataSources.put(tanentId, dataSource);
  }
}

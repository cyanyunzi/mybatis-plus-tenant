//package linwu.tenant.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import linwu.tenant.entity.common.TanentDb;
//import linwu.tenant.extend.TenantDataSourceExtend;
//import linwu.tenant.service.common.TanentDbService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//import java.util.List;
//
///**
// * @author ：林雾
// * @date ：2020/06/19
// * @description :租户数据源初始化
// */
//@Slf4j
//@Component
//public class TenantDataSourceInit implements CommandLineRunner {
//  @Autowired private TanentDbService tanentDbService;
//  @Autowired private TenantDataSourceExtend tenantDataSourceExtend;
//
//  @Override
//  public void run(String... args) {
//    List<TanentDb> list = tanentDbService.list();
//
//    for (TanentDb tanentDb : list) {
//      DataSource dataSource = createDataSource(tanentDb);
//      tenantDataSourceExtend.putDataSource(tanentDb.getTanentId().toString(), dataSource);
//    }
//
//    log.info("租户数据源初始化完毕....");
//
//  }
//
//  private DataSource createDataSource(TanentDb tanentDb) {
//    HikariDataSource hikariDataSource = new HikariDataSource();
//    hikariDataSource.setIdleTimeout(180000);
//    hikariDataSource.setMaxLifetime(1800000);
//    hikariDataSource.setMaximumPoolSize(10);
//    hikariDataSource.setMinimumIdle(5);
//    hikariDataSource.setPassword(tanentDb.getDbAccount());
//    hikariDataSource.setUsername(tanentDb.getDbPassword());
//    hikariDataSource.setValidationTimeout(30000);
//    hikariDataSource.setConnectionTestQuery("SELECT 1");
//    hikariDataSource.setDriverClassName("com.mysql.jdbc.Driver");
//
//    String JdbcUrl =
//        String.format(
//            "jdbc:mysql://localhost:3306/%s?characterEncoding=utf-8&serverTimezone=Asia/Shanghai",
//            tanentDb.getDatabse());
//
//    hikariDataSource.setJdbcUrl(JdbcUrl);
//    hikariDataSource.setAutoCommit(true);
//    return hikariDataSource;
//  }
//}

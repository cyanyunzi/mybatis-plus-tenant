package linwu.tenant.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import linwu.tenant.extend.TenantDataSourceExtend;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author ：林雾
 * @date ：2020/06/18
 * @description :
 */
@Configuration
@MapperScan("linwu.tenant.mapper.*")
public class MybatisPlusConfig {

  @Bean("mainDatasource")
  @ConfigurationProperties("spring.datasource.my")
  public DataSource localDataSource() {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }

  /**
   * mybatis-plus分页插件<br>
   * 文档：http://mp.baomidou.com<br>
   */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
    return paginationInterceptor;
  }

  /**
   * 动态数据源配置
   *
   * @return
   */
  @Bean
  @Primary
  public DataSource multipleDataSource() {
    TenantDataSourceExtend dynamicDataSource = new TenantDataSourceExtend();
    return dynamicDataSource;
  }

  @Bean("sqlSessionFactory")
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
    sqlSessionFactory.setDataSource(multipleDataSource());
    // 开启XML
    sqlSessionFactory.setMapperLocations(
        new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/*Mapper.xml"));

    MybatisConfiguration configuration = new MybatisConfiguration();
    // 开启XML
    configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
    configuration.setJdbcTypeForNull(JdbcType.NULL);
    configuration.setMapUnderscoreToCamelCase(true);
    configuration.setCacheEnabled(false);
    sqlSessionFactory.setConfiguration(configuration);
    sqlSessionFactory.setPlugins(paginationInterceptor());

    return sqlSessionFactory.getObject();
  }
}

package linwu.tenant.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import com.zaxxer.hikari.HikariDataSource;
import linwu.tenant.utils.CurrentTenantHolder;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.ValueListExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

// import linwu.tenant.extend.TenantDataSourceExtend;

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



  @Bean
  public PaginationInterceptor paginationInterceptor() {
    PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
    /*
     * 【测试多租户】 SQL 解析处理拦截器<br>
     * 这里固定写成住户 1 实际情况你可以从cookie读取，因此数据看不到 【 麻花藤 】 这条记录（ 注意观察 SQL ）<br>
     */
    List<ISqlParser> sqlParserList = new ArrayList<>();
    TenantSqlParser tenantSqlParser = new TenantSqlParser();
    tenantSqlParser.setTenantHandler(
        new TenantHandler() {
          @Override
          public Expression getTenantId(boolean select) {
            // select since: 3.3.2，参数 true 表示为 select 下的 where 条件,false 表示 insert/update/delete 下的条件
            // 只有 select 下才允许多参(ValueListExpression),否则只支持单参
            if (!select) {
              return new LongValue(CurrentTenantHolder.getTenantId());
            }

            ValueListExpression expression = new ValueListExpression();
            ExpressionList list =
                new ExpressionList(new LongValue(CurrentTenantHolder.getTenantId()));
            expression.setExpressionList(list);
            return expression;
          }

          @Override
          public String getTenantIdColumn() {
            return "tenant_id";
          }

          @Override
          public boolean doTableFilter(String tableName) {
            // 这里可以判断是否过滤表
            if (!"plus_tenant".equals(tableName)) {
                return true;
            }
            return false;
          }
        });
    sqlParserList.add(tenantSqlParser);
    paginationInterceptor.setSqlParserList(sqlParserList);
    paginationInterceptor.setSqlParserFilter(
        new ISqlParserFilter() {
          @Override
          public boolean doFilter(MetaObject metaObject) {
            MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
            // 需要租户信息的表,自定义查询的时候不需要加租户ID的sql
            if ("linwu.tenant.mapper.common.PlusTenantMapper.selectOneByQuery".equals(ms.getId())) {
              return true;
            }
            return false;
          }
        });
    return paginationInterceptor;
  }

  //  /**
  //   * 动态数据源配置
  //   *
  //   * @return
  //   */
  //  @Bean
  //  @Primary
  //  public DataSource multipleDataSource() {
  //    TenantDataSourceExtend dynamicDataSource = new TenantDataSourceExtend();
  //    return dynamicDataSource;
  //  }

  //  @Bean("sqlSessionFactory")
  //  public SqlSessionFactory sqlSessionFactory() throws Exception {
  //    MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
  //    sqlSessionFactory.setDataSource(multipleDataSource());
  //    // 开启XML
  //    sqlSessionFactory.setMapperLocations(
  //        new
  // PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/*Mapper.xml"));
  //
  //    MybatisConfiguration configuration = new MybatisConfiguration();
  //    // 开启XML
  //    configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
  //    configuration.setJdbcTypeForNull(JdbcType.NULL);
  //    configuration.setMapUnderscoreToCamelCase(true);
  //    configuration.setCacheEnabled(false);
  //    sqlSessionFactory.setConfiguration(configuration);
  //    sqlSessionFactory.setPlugins(paginationInterceptor());
  //
  //    return sqlSessionFactory.getObject();
  //  }
}

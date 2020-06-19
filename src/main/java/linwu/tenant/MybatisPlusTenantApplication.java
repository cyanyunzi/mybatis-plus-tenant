package linwu.tenant;

import linwu.tenant.utils.ApplicationContextUtil;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：林雾
 * @date ：2020/06/18
 * @description :
 */
@Configuration
@SpringBootApplication
public class MybatisPlusTenantApplication extends SpringBootServletInitializer
    implements ApplicationContextAware {
  public static void main(String[] args) throws Exception {
    SpringApplication.run(MybatisPlusTenantApplication.class, args);
    System.out.println("http://localhost:8080/swagger-ui.html");
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    setRegisterErrorPageFilter(false);
    return builder.sources(MybatisPlusTenantApplication.class);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ApplicationContextUtil.setApplicationContext(applicationContext);
  }
}

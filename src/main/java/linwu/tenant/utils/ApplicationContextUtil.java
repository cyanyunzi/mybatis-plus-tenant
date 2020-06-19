package linwu.tenant.utils;

import org.springframework.context.ApplicationContext;

public class ApplicationContextUtil {
  private static ApplicationContext applicationContext;

  public static void setApplicationContext(ApplicationContext applicationContext) {
    ApplicationContextUtil.applicationContext = applicationContext;
  }

  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  public static <T> T getBean(String beanName, Class<T> clazz) {
    return getApplicationContext().getBean(beanName, clazz);
  }

  public static <T> T getBean(Class<T> clazz) {
    return getApplicationContext().getBean(clazz);
  }
}

package linwu.tenant.utils;

/**
 * @author ：林雾
 * @date ：2020/06/19
 * @description :
 */
public class TenantHolder {
  private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

  public static String getTenantId() {
    return threadLocal.get();
  }
  
  public static void setTenantId(String tenantId) {
    threadLocal.set(tenantId);
  }

  public static void removeTenantId() {
    threadLocal.remove();
  }
}

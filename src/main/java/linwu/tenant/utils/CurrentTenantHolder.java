package linwu.tenant.utils;

/**
 * @author ：林雾
 * @date ：2020/06/22
 * @description :
 */
public class CurrentTenantHolder {
  private static ThreadLocal<Integer> threadLocal = new ThreadLocal();

  public static void setTenantId(Integer tenantId) {
    threadLocal.set(tenantId);
  }

  public static Integer getTenantId() {
    return threadLocal.get();
  }

  public static void removeTenantId() {
    threadLocal.remove();
  }
}

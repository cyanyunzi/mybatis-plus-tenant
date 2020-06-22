//package linwu.tenant.extend;
//
//import org.springframework.stereotype.Component;
//
///**
// * @author ：林雾
// * @date ：2020/06/19
// * @description :
// */
//@Component
//public class TenantDataSourceHolder {
//  public static final ThreadLocal<String> THREAD_LOCAL =
//      ThreadLocal.withInitial(() -> "");
//
//  public void setTenantId(String tenantId){
//    THREAD_LOCAL.set(tenantId);
//  }
//
//  public  String getTenantId(){
//    return THREAD_LOCAL.get();
//  }
//}

//package linwu.tenant.interceptor;
//
//import linwu.tenant.annotation.TanentDataSource;
//import linwu.tenant.extend.TenantDataSourceHolder;
//import linwu.tenant.utils.RequestUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Map;
//
///**
// * @author ：林雾
// * @date ：2020/06/19
// * @description :
// */
//@Slf4j
//public class TanentDataSourceInterceptor implements HandlerInterceptor {
//  @Autowired
//  private TenantDataSourceHolder tenantDataSourceHolder;
//
//  @Override
//  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) {
//    if (!(obj instanceof HandlerMethod)) {
//      return true;
//    }
//
//    HandlerMethod method = ((HandlerMethod) obj);
//    TanentDataSource annotation = method.getMethodAnnotation(TanentDataSource.class);
//
//    // 没有登陆注解放行
//    if (null == annotation) {
//      return true;
//    }
//
//    Map<String, String> headers = RequestUtils.getHeaders();
//    String tanentId = headers.get("tanent_id");
//    if (null == tanentId || tanentId.isEmpty()) {
//      return false;
//    }
//
//    tenantDataSourceHolder.setTenantId(tanentId);
//    return true;
//  }
//
//  @Override
//  public void postHandle(
//      HttpServletRequest request,
//      HttpServletResponse response,
//      Object handler,
//      ModelAndView modelAndView) {
//  }
//}

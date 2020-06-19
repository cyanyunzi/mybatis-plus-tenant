package linwu.tenant.config.aop;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import linwu.tenant.extend.TenantDataSourceHolder;
import linwu.tenant.utils.RequestUtils;
import linwu.tenant.utils.TenantHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author ：林雾
 * @date ：2020/06/19
 * @description :
 */
@Aspect
@Component
public class TenantDataSourceAop {
    @Autowired private TenantDataSourceHolder tenantDataSourceHolder;

    @Pointcut("@annotation(linwu.tenant.annotation.TanentDataSource)")
    public void tanentDataSource() {}

    @Before("tanentDataSource()")
    public void deBefore() {
        Map<String, String> headers = RequestUtils.getHeaders();
        String tanentId = headers.get("tanent_id");
        TenantHolder.setTenantId(tanentId);
    }


    @After("tanentDataSource()")
    public void after() {
        TenantHolder.removeTenantId();
    }
}

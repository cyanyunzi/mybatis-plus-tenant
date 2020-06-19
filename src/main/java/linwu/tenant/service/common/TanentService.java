package linwu.tenant.service.common;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import linwu.tenant.entity.common.Tanent;
import linwu.tenant.mapper.common.TanentMapper;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author linwu
 * @since 2020-06-19
 */
@Service
public class TanentService extends ServiceImpl<TanentMapper, Tanent> {}

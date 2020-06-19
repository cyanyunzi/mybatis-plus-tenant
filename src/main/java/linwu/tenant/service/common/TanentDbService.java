package linwu.tenant.service.common;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import linwu.tenant.entity.common.TanentDb;
import linwu.tenant.mapper.common.TanentDbMapper;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author linwu
 * @since 2020-06-19
 */
@Service
public class TanentDbService extends ServiceImpl<TanentDbMapper, TanentDb> {}

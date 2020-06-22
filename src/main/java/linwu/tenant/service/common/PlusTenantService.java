package linwu.tenant.service.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import linwu.tenant.entity.common.PlusTenant;
import linwu.tenant.mapper.common.PlusTenantMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linwu
 * @since 2020-06-22
 */
@Service
public class PlusTenantService extends ServiceImpl<PlusTenantMapper, PlusTenant> {
    public List<PlusTenant> selectListByQuery(int page, int size){
        return baseMapper.selectListByQuery(new Page<>(page,size));
    }

    public PlusTenant selectOneByQuery(){
        return baseMapper.selectOneByQuery();
    }
}

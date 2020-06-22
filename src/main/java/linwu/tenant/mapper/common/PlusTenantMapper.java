package linwu.tenant.mapper.common;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import linwu.tenant.entity.common.PlusTenant;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author linwu
 * @since 2020-06-22
 */
public interface PlusTenantMapper extends BaseMapper<PlusTenant> {

    List<PlusTenant> selectListByQuery(Page<?> page);

    PlusTenant selectOneByQuery();

}

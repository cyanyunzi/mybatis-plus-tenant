package linwu.tenant.service.tenant;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import linwu.tenant.entity.tenant.Product;
import linwu.tenant.mapper.tenant.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务实现类
 *
 * @author linwu
 * @since 2020-06-19
 */
@Service
public class ProductService extends ServiceImpl<ProductMapper, Product> {
  @Transactional(rollbackFor = Exception.class)
  public void saveProduct(Product product) {
    save(product);
    throw new NullPointerException();
  }

  public void updateProduct(Product product) {
    updateById(product);
  }

  public Product getProduct(int id) {
    return getById(id);
  }

  public Page<Product> products(int page, int size) {
    Wrapper<Product> wrapper = new QueryWrapper<>();
    return baseMapper.selectPage(new Page<>(page, size), wrapper);
  }
}

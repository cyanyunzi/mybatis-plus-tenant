package linwu.tenant.controller.tenant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import linwu.tenant.annotation.TanentDataSource;
import linwu.tenant.entity.tenant.Product;
import linwu.tenant.service.tenant.ProductService;
import linwu.tenant.utils.ApplicationContextUtil;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.web.bind.annotation.*;

/**
 * 前端控制器
 *
 * @author linwu
 * @since 2020-06-19
 */
@RestController
@RequestMapping("/tenant/product")
public class TanentProductController {
  @Autowired private ProductService productService;


  @TanentDataSource
  @ApiOperation("新增")
  @PostMapping
  public String add(@RequestBody Product product) {
    productService.saveProduct(product);
    return "SUCCESS";
  }

  @TanentDataSource
  @ApiOperation("修改")
  @PutMapping
  public String update(@RequestBody Product product) {
    productService.updateProduct(product);
    return "SUCCESS";
  }

  @TanentDataSource
  @ApiOperation("分页")
  @GetMapping
  public Page<Product> page(@RequestParam int page, @RequestParam int size) {
    Page<Product> products = productService.products(page, size);
    return products;
  }

  @TanentDataSource
  @ApiOperation("详情")
  @GetMapping("/{id}")
  public Product detail(@PathVariable int id) {
    return productService.getProduct(id);
  }
}

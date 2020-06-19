package linwu.tenant.controller.common;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import linwu.tenant.entity.common.Tanent;
import linwu.tenant.service.common.TanentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 前端控制器
 *
 * @author linwu
 * @since 2020-06-19
 */
@RestController
@RequestMapping("/tenant/tanent")
public class TanentController {
  @Autowired private TanentService tanentService;


  @ApiOperation("新增")
  @PostMapping
  public String add(@RequestBody Tanent tanent) {
    tanentService.save(tanent);
    return "SUCCESS";
  }

  @ApiOperation("新增")
  @PutMapping
  public String update(@RequestBody Tanent tanent) {
    tanentService.updateById(tanent);
    return "SUCCESS";
  }

  @ApiOperation("分页")
  @GetMapping
  public Page<Tanent> page(@RequestParam int page, @RequestParam int size) {
    Wrapper<Tanent> wrapper = new QueryWrapper<>();
    return tanentService.page(new Page<>(page, size), wrapper);
  }

  @ApiOperation("详情")
  @GetMapping("/{id}")
  public Tanent detail(@PathVariable int id) {
    return tanentService.getById(id);
  }
}

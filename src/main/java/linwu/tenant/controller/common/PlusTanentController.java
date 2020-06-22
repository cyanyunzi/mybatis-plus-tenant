package linwu.tenant.controller.common;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import linwu.tenant.entity.common.PlusTenant;
import linwu.tenant.service.common.PlusTenantService;
import linwu.tenant.utils.CurrentTenantHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前端控制器
 *
 * @author linwu
 * @since 2020-06-19
 */
@RestController
@RequestMapping("/plus/tanent")
public class PlusTanentController {
  @Autowired private PlusTenantService tanentService;

  @ApiOperation("新增")
  @PostMapping("/{tenantId}")
  public String add(@PathVariable Integer tenantId,@RequestBody PlusTenant tanent) {
    CurrentTenantHolder.setTenantId(tenantId);
    tanentService.save(tanent);
    CurrentTenantHolder.removeTenantId();
    return "SUCCESS";
  }

  @ApiOperation("新增")
  @PutMapping("/{tenantId}")
  public String update(@PathVariable Integer tenantId,@RequestBody PlusTenant tanent) {
    CurrentTenantHolder.setTenantId(tenantId);
    tanentService.updateById(tanent);
    CurrentTenantHolder.removeTenantId();
    return "SUCCESS";
  }

  @ApiOperation("分页")
  @GetMapping("/{tenantId}")
  public Page<PlusTenant> page(@PathVariable Integer tenantId,@RequestParam int page, @RequestParam int size) {
    CurrentTenantHolder.setTenantId(tenantId);
    Wrapper<PlusTenant> wrapper = new QueryWrapper<>();

    Page<PlusTenant> page1 = tanentService.page(new Page<>(page, size), wrapper);
    CurrentTenantHolder.removeTenantId();
    return page1;
  }

  @ApiOperation("详情")
  @GetMapping("/{tenantId}/{id}")
  public PlusTenant detail(@PathVariable Integer tenantId,@PathVariable int id) {
    CurrentTenantHolder.setTenantId(tenantId);
    PlusTenant byId = tanentService.getById(id);
    CurrentTenantHolder.removeTenantId();
    return byId;
  }


  @ApiOperation("自定义sql详情")
  @GetMapping("/{tenantId}/query")
  public PlusTenant query(@PathVariable Integer tenantId) {
    CurrentTenantHolder.setTenantId(tenantId);
    PlusTenant byId = tanentService.selectOneByQuery();
    CurrentTenantHolder.removeTenantId();
    return byId;
  }

  @ApiOperation("自定义sql分页")
  @GetMapping("/{tenantId}/page")
  public List<PlusTenant> queryPage(@PathVariable Integer tenantId,@RequestParam int page, @RequestParam int size) {
    CurrentTenantHolder.setTenantId(tenantId);
    List<PlusTenant> plusTenants = tanentService.selectListByQuery(page, size);
    CurrentTenantHolder.removeTenantId();
    return plusTenants;
  }
}

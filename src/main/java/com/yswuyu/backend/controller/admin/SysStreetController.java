package com.yswuyu.backend.controller.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageInfo;
import com.yswuyu.backend.common.base.BaseController;
import com.yswuyu.backend.common.domain.AjaxResult;
import com.yswuyu.backend.model.auto.SysStreet;
import com.yswuyu.backend.model.custom.TableSplitResult;
import com.yswuyu.backend.model.custom.Tablepar;
import com.yswuyu.backend.model.custom.TitleVo;
import com.yswuyu.backend.service.SysStreetService;
import io.swagger.annotations.Api;

/**
 * 街道Controller
 * @ClassName: SysStreetController
 * @author Hankin
 * @date 2019-11-20 22:32
 */
@Api(value = "街道设置")
@Controller
@RequestMapping("/SysStreetController")
public class SysStreetController extends BaseController{
	
	private String prefix = "admin/province/sysStreet";
	@Autowired
	private SysStreetService sysStreetService;
	
	/**
	 * 展示跳转
	 * @param model
	 * @return
	 */
	@GetMapping("/view")
	@RequiresPermissions("gen:sysStreet:view")
    public String view(ModelMap model)
    {	
		String str="街道设置";
		setTitle(model, new TitleVo("列表", str+"管理", true,"欢迎进入"+str+"页面", true, false));
        return prefix + "/list";
    }
	
	/**
	 * list查询
	 * @param tablepar
	 * @param searchText
	 * @return
	 */
	//@Log(title = "街道设置集合查询", action = "111")
	@PostMapping("/list")
	@RequiresPermissions("gen:sysStreet:list")
	@ResponseBody
	public Object list(Tablepar tablepar,String searchText){
		PageInfo<SysStreet> page=sysStreetService.list(tablepar,searchText) ; 
		TableSplitResult<SysStreet> result=new TableSplitResult<SysStreet>(page.getPageNum(), page.getTotal(), page.getList()); 
		return  result;
	}
	
	/**
	 * 新增跳转
	 * @param modelMap
	 * @return
	 */
    @GetMapping("/add")
    public String add(ModelMap modelMap)
    {
        return prefix + "/add";
    }
	
    /**
     * 新增保存
     * @param sysStreet
     * @return
     * @author Hankin
     * @date 2019年11月11日 下午4:13:37
     */
	//@Log(title = "街道设置新增", action = "111")
	@PostMapping("/add")
	@RequiresPermissions("gen:sysStreet:add")
	@ResponseBody
	public AjaxResult add(SysStreet sysStreet){
		int b=sysStreetService.insertSelective(sysStreet);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	//@Log(title = "街道设置删除", action = "111")
	@PostMapping("/remove")
	@RequiresPermissions("gen:sysStreet:remove")
	@ResponseBody
	public AjaxResult remove(String ids){
		int b=sysStreetService.deleteByPrimaryKey(ids);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	/**
	 * 检查
	 * @param tsysUser
	 * @return
	 */
	@PostMapping("/checkNameUnique")
	@ResponseBody
	public int checkNameUnique(SysStreet sysStreet){
		int b=sysStreetService.checkNameUnique(sysStreet);
		if(b>0){
			return 1;
		}else{
			return 0;
		}
	}
	
	
	/**
	 * 修改跳转
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("SysStreet", sysStreetService.selectByPrimaryKey(id));

        return prefix + "/edit";
    }
	/**
	 * 修改保存
	 * @param record
	 * @return
	 */
    //@Log(title = "街道设置修改", action = "111")
    @RequiresPermissions("gen:sysStreet:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysStreet record)
    {
        return toAjax(sysStreetService.updateByPrimaryKeySelective(record));
    }

    
    

	
}

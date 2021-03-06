package com.yswuyu.backend.controller.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageInfo;
import com.yswuyu.backend.common.base.BaseController;
import com.yswuyu.backend.common.domain.AjaxResult;
import com.yswuyu.backend.model.auto.TSysDictType;
import com.yswuyu.backend.model.custom.TableSplitResult;
import com.yswuyu.backend.model.custom.Tablepar;
import com.yswuyu.backend.model.custom.TitleVo;
import com.yswuyu.backend.service.SysDictTypeService;
import io.swagger.annotations.Api;

/**
 * 字典类型Controller
 * @ClassName: DictTypeController
 * @author Hankin
 * @date 2019-11-20 22:45
 */
@Api(value = "字典类型表")
@Controller
@RequestMapping("/DictTypeController")
public class DictTypeController extends BaseController{
	
	private String prefix = "admin/dict_type";
	@Autowired
	private SysDictTypeService tSysDictTypeService;
	
	/**
	 * 分页list页面
	 * @param model
	 * @param dictId
	 * @return
	 */
	@GetMapping("/view")
	@RequiresPermissions("system:dictType:view")
    public String view(ModelMap model)
    {	
		String str="字典类型表";
		setTitle(model, new TitleVo("列表", str+"管理", true,"欢迎进入"+str+"页面", true, false));
        return prefix + "/list";
    }
	
	/**
	 * 字典类型表集合查询
	 * @param tablepar
	 * @param searchText
	 * @return
	 */
	//@Log(title = "字典类型表集合查询", action = "111")
	@PostMapping("/list")
	@RequiresPermissions("system:dictType:list")
	@ResponseBody
	public Object list(Tablepar tablepar,String searchText){
		PageInfo<TSysDictType> page=tSysDictTypeService.list(tablepar,searchText) ; 
		TableSplitResult<TSysDictType> result=new TableSplitResult<TSysDictType>(page.getPageNum(), page.getTotal(), page.getList()); 
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
     * TODO(请说明这个方法的作用).
     * @param tSysDictType
     * @param model
     * @return
     */
	//@Log(title = "字典类型表新增", action = "111")
	@PostMapping("/add")
	@RequiresPermissions("system:dictType:add")
	@ResponseBody
	public AjaxResult add(TSysDictType tSysDictType,Model model){
		int b=tSysDictTypeService.insertSelective(tSysDictType);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	/**
	 * 删除字典类型
	 * @param ids
	 * @return
	 */
	//@Log(title = "字典类型表删除", action = "111")
	@PostMapping("/remove")
	@RequiresPermissions("system:dictType:remove")
	@ResponseBody
	public AjaxResult remove(String ids){
		int b=tSysDictTypeService.deleteByPrimaryKey(ids);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	/**
	 * 检查字典类型名字相同
	 * @param tsysUser
	 * @return
	 */
	@PostMapping("/checkNameUnique")
	@ResponseBody
	public int checkNameUnique(TSysDictType tSysDictType){
		int b=tSysDictTypeService.checkNameUnique(tSysDictType);
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
        mmap.put("TSysDictType", tSysDictTypeService.selectByPrimaryKey(id));

        return prefix + "/edit";
    }
	
	/**
     * 修改保存
     */
    //@Log(title = "字典类型表修改", action = "111")
    @RequiresPermissions("system:dictType:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TSysDictType record)
    {
        return toAjax(tSysDictTypeService.updateByPrimaryKeySelective(record));
    }

	
}

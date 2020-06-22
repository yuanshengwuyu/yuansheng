package com.yswuyu.backend.controller.admin;

import com.yswuyu.backend.common.base.BaseController;
import com.yswuyu.backend.model.custom.TitleVo;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 定时任务表达式工具生成Controller
 * @ClassName: QuartzController
 * @author Hankin
 * @date 2019-11-20 22:44
 */
@Api(value = "定时任务工具类")
@Controller
@RequestMapping("/quartz")
public class QuartzController extends BaseController{

	private String prefix = "admin/quartz";

	/**
	 * 定时页面展示
	 * TODO(请说明这个方法的作用).
	 * @param model
	 * @return
	 * @author Hankin
	 * @date 2019年11月11日 下午3:45:53
	 */
	@GetMapping("/view")
    public String view(ModelMap model)
    {	
		String str="定时器";
		setTitle(model, new TitleVo("表达式生成", str+"管理", true,"欢迎进入"+str+"页面", true, false));
        return prefix + "/list";
    }
}

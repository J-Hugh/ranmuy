package com.neo.admin.system.controller.wa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neo.admin.common.value.ActResult;
import com.neo.admin.system.controller.BaseController;
import com.neo.admin.system.facade.IWaFacade;
import com.neo.admin.system.modular.monitor.domain.ReportData;

/**
 * LBS相关控制器
 * 
 * @author luoyulin
 *
 */
@Controller
@RequestMapping(value = "wa")
public class WaController extends BaseController {

	@Autowired
	private IWaFacade waFacade;
	
	
	// 数据量记录数据
	@RequestMapping(value = "report")
	@ResponseBody
	public ActResult<?> didichartdate(ReportData data) throws Exception {
		ActResult<?> act = new ActResult<>();
		if(data.check()){
			String msg = waFacade.write(data);
			handleRet(act, msg);
		}
		return act;
	}
	



	
}

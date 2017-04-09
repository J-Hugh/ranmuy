package com.neo.admin.system.facade.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.neo.admin.common.tools.FileUtil;
import com.neo.admin.common.value.WebConfig;
import com.neo.admin.system.facade.IWaFacade;
import com.neo.admin.system.modular.monitor.domain.ReportData;
import com.neo.admin.system.modular.monitor.service.MonitorService;

@Service
public class WaFacadeImpl implements IWaFacade {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	@Override
	public String write(ReportData data) {

		String filePath = WebConfig.LOG_FILEPATH + File.separator + sdf.format(new Date())+ File.separator ;
		
		String fileName = "["+data.getIp()+"][" + data.getCity() + "]["+data.getProjectName()+ "]"+".rep";

		try {
			fileName= new String(fileName.getBytes() ,"UTF-8");
			FileUtil.write(filePath+fileName, data.toString());
			return "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}

	@Override
	public void scanning() throws Exception {

		String regex = "^(\\[.*\\]){3}.rep$";
		
		File root = new File(WebConfig.LOG_FILEPATH + File.separator + sdf.format(new Date()));

		if (root.isDirectory()) {
			File[] files = root.listFiles();
			int length = files.length;
			for (int i = 0; i < length; i++) {
				String filePath = files[i].getName();
				if(filePath.matches(regex)){
					new Thread(new MonitorService(files[i])).start();
				}
			}
		}
	}
}

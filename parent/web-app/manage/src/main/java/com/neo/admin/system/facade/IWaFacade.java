package com.neo.admin.system.facade;

import com.neo.admin.system.modular.monitor.domain.ReportData;

/**
 * 网安相关接口
 * @author luoyulin
 *
 */
public interface IWaFacade {

	/**
	 * 保存统计记录
	 * @param data
	 * @return
	 */
	public String write(ReportData data);
	
	/**
	 * 扫描记录文件
	 * @throws Exception
	 */
	public void scanning() throws Exception;
}

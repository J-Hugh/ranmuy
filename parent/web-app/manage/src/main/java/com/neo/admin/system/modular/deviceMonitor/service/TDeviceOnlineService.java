package com.neo.admin.system.modular.deviceMonitor.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.admin.system.modular.deviceMonitor.dao.TDeviceOnlineDao;
import com.neo.admin.system.modular.deviceMonitor.domain.TDeviceOnline;

@Service
public class TDeviceOnlineService {

	@Autowired
	private TDeviceOnlineDao tDeviceOnlineDao;
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<TDeviceOnline> searchByPage(Map<String,Object> searchParams) throws Exception {
		return (List<TDeviceOnline>) tDeviceOnlineDao.searchByPage(searchParams);
	}

	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<TDeviceOnline> search(Map<String,Object> searchParams) throws Exception  {
		return (List<TDeviceOnline>) tDeviceOnlineDao.search(searchParams);
	}
	public List<TDeviceOnline> recently(Map<String,Object> searchParams) throws Exception  {
		return (List<TDeviceOnline>) tDeviceOnlineDao.recently(searchParams);
	}
	
	/**
	 * 获取所有记录
	 * @return
	 */
	public List<TDeviceOnline> findAll() throws Exception {
		return (List<TDeviceOnline>) tDeviceOnlineDao.findAll();
	}
	
	/**
	 * 通过获取一条记录
	 * @param  主键
	 * @return
	 */
	public TDeviceOnline findById(String iD) throws Exception {
		TDeviceOnline tDeviceOnline = tDeviceOnlineDao.findById(iD);
		if(tDeviceOnline == null){
			tDeviceOnline = new TDeviceOnline();
		}
		return tDeviceOnline;
	}
}

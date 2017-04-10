package com.neo.admin.system.modular.deviceMonitor.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.admin.common.value.BootstrapTable;
import com.neo.admin.system.modular.deviceMonitor.dao.TDeviceMonitorDao;
import com.neo.admin.system.modular.deviceMonitor.domain.TDeviceMonitor;

@Service
public class TDeviceMonitorService {

	@Autowired
	private TDeviceMonitorDao tDeviceMonitorDao;
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public BootstrapTable<TDeviceMonitor> searchByPage(Map<String,Object> searchParams) throws Exception {
		 List<TDeviceMonitor> rows =  tDeviceMonitorDao.searchByPage(searchParams);
		 int total = tDeviceMonitorDao.total(searchParams);
		 return new BootstrapTable<>(total, rows);
	}

	
	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<TDeviceMonitor> search(Map<String,Object> searchParams) throws Exception  {
		return (List<TDeviceMonitor>) tDeviceMonitorDao.search(searchParams);
	}
	/**
	 * 查询近几天的数据
	 * @param searchParams
	 * @return
	 * @throws Exception
	 */
	public List<TDeviceMonitor> recently(Map<String,Object> searchParams) throws Exception  {
		return (List<TDeviceMonitor>) tDeviceMonitorDao.recently(searchParams);
	}
	/**
	 * 获取所有记录
	 * @return
	 */
	public List<TDeviceMonitor> findAll() throws Exception {
		return (List<TDeviceMonitor>) tDeviceMonitorDao.findAll();
	}
	
	/**
	 * 通过获取一条记录
	 * @param  主键
	 * @return
	 */
	public TDeviceMonitor findById(String iD) throws Exception {
		TDeviceMonitor tDeviceMonitor = tDeviceMonitorDao.findById(iD);
		if(tDeviceMonitor == null){
			tDeviceMonitor = new TDeviceMonitor();
		}
		return tDeviceMonitor;
	}

	/**
	 * 保存一条记录
	 * @param tDeviceMonitor
	 */
	public void save(TDeviceMonitor tDeviceMonitor) throws Exception {
		tDeviceMonitorDao.save(tDeviceMonitor);
	}

	/**
	 * 通过  删除一条记录
	 * @param  主键
	 */
	public void delete(String iD) throws Exception {
		tDeviceMonitorDao.delete(iD);

	}
	
	/**
	 * 修改记录
	 * @param tDeviceMonitor
	 */
	public void update(TDeviceMonitor tDeviceMonitor) throws Exception {
		tDeviceMonitorDao.update(tDeviceMonitor);
	}
}

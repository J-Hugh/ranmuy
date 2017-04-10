package com.neo.admin.system.modular.deviceMonitor.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.admin.system.modular.deviceMonitor.dao.TLineavgMonitorDao;
import com.neo.admin.system.modular.deviceMonitor.domain.TLineavgMonitor;

@Service
public class TLineavgMonitorService {

	@Autowired
	private TLineavgMonitorDao tLineavgMonitorDao;
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<TLineavgMonitor> searchByPage(Map<String,Object> searchParams) throws Exception {
		return (List<TLineavgMonitor>) tLineavgMonitorDao.searchByPage(searchParams);
	}

	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<TLineavgMonitor> search(Map<String,Object> searchParams) throws Exception  {
		return (List<TLineavgMonitor>) tLineavgMonitorDao.search(searchParams);
	}

	/**
	 * 获取所有记录
	 * @return
	 */
	public List<TLineavgMonitor> findAll() throws Exception {
		return (List<TLineavgMonitor>) tLineavgMonitorDao.findAll();
	}
	
	/**
	 * 通过获取一条记录
	 * @param  主键
	 * @return
	 */
	public TLineavgMonitor findById(String iD) throws Exception {
		TLineavgMonitor tLineavgMonitor = tLineavgMonitorDao.findById(iD);
		if(tLineavgMonitor == null){
			tLineavgMonitor = new TLineavgMonitor();
		}
		return tLineavgMonitor;
	}

	/**
	 * 保存一条记录
	 * @param tLineavgMonitor
	 */
	public void save(TLineavgMonitor tLineavgMonitor) throws Exception {
		tLineavgMonitorDao.save(tLineavgMonitor);
	}

	/**
	 * 通过  删除一条记录
	 * @param  主键
	 */
	public void delete(String iD) throws Exception {
		tLineavgMonitorDao.delete(iD);
	}
	
	/**
	 * 修改记录
	 * @param tLineavgMonitor
	 */
	public void update(TLineavgMonitor tLineavgMonitor) throws Exception {
		tLineavgMonitorDao.update(tLineavgMonitor);
	}
}

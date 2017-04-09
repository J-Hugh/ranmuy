package com.neo.admin.system.modular.lbs.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.admin.system.modular.lbs.dao.TLbsMinuteDao;
import com.neo.admin.system.modular.lbs.domain.TLbsMinute;

@Service
public class TLbsMinuteService {

	@Autowired
	private TLbsMinuteDao tLbsMinuteDao;
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<TLbsMinute> searchByPage(Map<String,Object> searchParams) throws Exception {
		return (List<TLbsMinute>) tLbsMinuteDao.searchByPage(searchParams);
	}

	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<TLbsMinute> search(Map<String,Object> searchParams) throws Exception  {
		return (List<TLbsMinute>) tLbsMinuteDao.search(searchParams);
	}
	
	public List<TLbsMinute> searchByMonth(Map<String,Object> searchParams) throws Exception  {
		return (List<TLbsMinute>) tLbsMinuteDao.searchByMonth(searchParams);
	}

	/**
	 * 获取所有记录
	 * @return
	 */
	public List<TLbsMinute> findAll() throws Exception {
		return (List<TLbsMinute>) tLbsMinuteDao.findAll();
	}
	
	/**
	 * 通过获取一条记录
	 * @param  主键
	 * @return
	 */
	public TLbsMinute findById(String iD) throws Exception {
		TLbsMinute tLbsMinute = tLbsMinuteDao.findById(iD);
		if(tLbsMinute == null){
			tLbsMinute = new TLbsMinute();
		}
		return tLbsMinute;
	}

	/**
	 * 保存一条记录
	 * @param tLbsMinute
	 */
	public void save(TLbsMinute tLbsMinute) throws Exception {
		tLbsMinuteDao.save(tLbsMinute);
	}

	/**
	 * 通过  删除一条记录
	 * @param  主键
	 */
	public void delete(String iD) throws Exception {
		tLbsMinuteDao.delete(iD);

	}
	public void deleteByDate(String date) throws Exception {
		tLbsMinuteDao.deleteByDate(date);
	}
	
	/**
	 * 修改记录
	 * @param tLbsMinute
	 */
	public void update(TLbsMinute tLbsMinute) throws Exception {
		tLbsMinuteDao.update(tLbsMinute);
	}
}

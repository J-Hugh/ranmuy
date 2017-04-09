package com.neo.admin.system.modular.lbs.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.admin.system.modular.lbs.dao.MooLbsStationDao;
import com.neo.admin.system.modular.lbs.domain.MooLbsStation;

@Service
public class MooLbsStationService {

	@Autowired
	private MooLbsStationDao mooLbsStationDao;
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<MooLbsStation> searchByPage(Map<String,Object> searchParams) throws Exception {
		return (List<MooLbsStation>) mooLbsStationDao.searchByPage(searchParams);
	}

	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<MooLbsStation> search(Map<String,Object> searchParams) throws Exception  {
		return (List<MooLbsStation>) mooLbsStationDao.search(searchParams);
	}

	/**
	 * 获取所有记录
	 * @return
	 */
	public List<MooLbsStation> findAll() throws Exception {
		return (List<MooLbsStation>) mooLbsStationDao.findAll();
	}
	
	/**
	 * 通过获取一条记录
	 * @param  主键
	 * @return
	 */
	public MooLbsStation findById(String iD) throws Exception {
		MooLbsStation mooLbsStation = mooLbsStationDao.findById(iD);
		if(mooLbsStation == null){
			mooLbsStation = new MooLbsStation();
		}
		return mooLbsStation;
	}

	/**
	 * 保存一条记录
	 * @param mooLbsStation
	 */
	public void save(MooLbsStation mooLbsStation) throws Exception {
		mooLbsStationDao.save(mooLbsStation);
	}

	/**
	 * 通过  删除一条记录
	 * @param  主键
	 */
	public void delete(String iD) throws Exception {
		mooLbsStationDao.delete(iD);

	}
	
	/**
	 * 修改记录
	 * @param mooLbsStation
	 */
	public void update(MooLbsStation mooLbsStation) throws Exception {
		mooLbsStationDao.update(mooLbsStation);
	}
}

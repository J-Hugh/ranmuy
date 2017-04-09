package com.neo.admin.system.modular.lbs.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.admin.system.modular.lbs.dao.MooLbsVagueLineDao;
import com.neo.admin.system.modular.lbs.domain.MooLbsVagueLine;

@Service
public class MooLbsVagueLineService {

	@Autowired
	private MooLbsVagueLineDao mooLbsVagueLineDao;
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<MooLbsVagueLine> searchByPage(Map<String,Object> searchParams) throws Exception {
		return (List<MooLbsVagueLine>) mooLbsVagueLineDao.searchByPage(searchParams);
	}

	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<MooLbsVagueLine> search(Map<String,Object> searchParams) throws Exception  {
		return (List<MooLbsVagueLine>) mooLbsVagueLineDao.search(searchParams);
	}

	/**
	 * 获取所有记录
	 * @return
	 */
	public List<MooLbsVagueLine> findAll() throws Exception {
		return (List<MooLbsVagueLine>) mooLbsVagueLineDao.findAll();
	}
	
	/**
	 * 通过获取一条记录
	 * @param  主键
	 * @return
	 */
	public MooLbsVagueLine findById(String iD) throws Exception {
		MooLbsVagueLine mooLbsVagueLine = mooLbsVagueLineDao.findById(iD);
		if(mooLbsVagueLine == null){
			mooLbsVagueLine = new MooLbsVagueLine();
		}
		return mooLbsVagueLine;
	}

	/**
	 * 保存一条记录
	 * @param mooLbsVagueLine
	 */
	public void save(MooLbsVagueLine mooLbsVagueLine) throws Exception {
		mooLbsVagueLineDao.save(mooLbsVagueLine);
	}

	/**
	 * 通过  删除一条记录
	 * @param  主键
	 */
	public void delete(String iD) throws Exception {
		mooLbsVagueLineDao.delete(iD);

	}
	
	/**
	 * 修改记录
	 * @param mooLbsVagueLine
	 */
	public void update(MooLbsVagueLine mooLbsVagueLine) throws Exception {
		mooLbsVagueLineDao.update(mooLbsVagueLine);
	}
}

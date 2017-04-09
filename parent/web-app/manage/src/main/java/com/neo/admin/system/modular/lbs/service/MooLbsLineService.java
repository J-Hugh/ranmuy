package com.neo.admin.system.modular.lbs.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.admin.system.modular.lbs.dao.MooLbsLineDao;
import com.neo.admin.system.modular.lbs.domain.MooLbsLine;

@Service
public class MooLbsLineService {

	@Autowired
	private MooLbsLineDao mooLbsLineDao;
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<MooLbsLine> searchByPage(Map<String,Object> searchParams) throws Exception {
		return (List<MooLbsLine>) mooLbsLineDao.searchByPage(searchParams);
	}

	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<MooLbsLine> search(Map<String,Object> searchParams) throws Exception  {
		return (List<MooLbsLine>) mooLbsLineDao.search(searchParams);
	}

	/**
	 * 获取所有记录
	 * @return
	 */
	public List<MooLbsLine> findAll() throws Exception {
		return (List<MooLbsLine>) mooLbsLineDao.findAll();
	}
	
	/**
	 * 通过获取一条记录
	 * @param  主键
	 * @return
	 */
	public MooLbsLine findById(String iD) throws Exception {
		MooLbsLine mooLbsLine = mooLbsLineDao.findById(iD);
		if(mooLbsLine == null){
			mooLbsLine = new MooLbsLine();
		}
		return mooLbsLine;
	}

	/**
	 * 保存一条记录
	 * @param mooLbsLine
	 */
	public void save(MooLbsLine mooLbsLine) throws Exception {
		mooLbsLineDao.save(mooLbsLine);
	}

	/**
	 * 通过  删除一条记录
	 * @param  主键
	 */
	public void delete(String iD) throws Exception {
		mooLbsLineDao.delete(iD);

	}
	
	/**
	 * 修改记录
	 * @param mooLbsLine
	 */
	public void update(MooLbsLine mooLbsLine) throws Exception {
		mooLbsLineDao.update(mooLbsLine);
	}
}

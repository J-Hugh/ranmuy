package com.neo.admin.system.modular.lbs.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.admin.common.value.BootstrapTable;
import com.neo.admin.system.modular.lbs.dao.MooLbsDayDao;
import com.neo.admin.system.modular.lbs.domain.MooLbsDay;

@Service
public class MooLbsDayService {

	@Autowired
	private MooLbsDayDao mooLbsDayDao;
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public BootstrapTable<MooLbsDay> searchByPage(Map<String,Object> searchParams) throws Exception {
		List<MooLbsDay> rows = mooLbsDayDao.searchByPage(searchParams);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		for(int i = 0 ;i<rows.size();i++){
			MooLbsDay ld = rows.get(i);
			try {
				Date start = sdf.parse(ld.getStatrTime());
				Date end = sdf.parse(ld.getEndTime());
				ld.setTheoryActive((int)((end.getTime() - start.getTime())/1000));
				ld.setEffectiveRate(ld.getActive() == null ? 0 : ld.getActive() /(double)ld.getTheoryActive());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Integer total = mooLbsDayDao.total(searchParams);
		BootstrapTable<MooLbsDay> ret = new BootstrapTable<>(total, rows);
		return ret;
	}
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		
		Date start = sdf.parse("23:16");
		Date end = sdf.parse("23:16");
		
		System.out.println(end.getTime() - start.getTime());
		
		
	}
	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<MooLbsDay> search(Map<String,Object> searchParams) throws Exception  {
		return (List<MooLbsDay>) mooLbsDayDao.search(searchParams);
	}

	/**
	 * 获取所有记录
	 * @return
	 */
	public List<MooLbsDay> findAll() throws Exception {
		return (List<MooLbsDay>) mooLbsDayDao.findAll();
	}
	
	/**
	 * 通过获取一条记录
	 * @param  主键
	 * @return
	 */
	public MooLbsDay findById(String iD) throws Exception {
		MooLbsDay mooLbsDay = mooLbsDayDao.findById(iD);
		return mooLbsDay;
	}

	/**
	 * 保存一条记录
	 * @param mooLbsDay
	 */
	public void save(MooLbsDay mooLbsDay) throws Exception {
		mooLbsDayDao.save(mooLbsDay);
	}

	/**
	 * 通过  删除一条记录
	 * @param  主键
	 */
	public void delete(String iD) throws Exception {
		mooLbsDayDao.delete(iD);

	}
	
	public void deleteByDate(String date) {
		mooLbsDayDao.deleteByDate(date);
		
	}
	
	/**
	 * 修改记录
	 * @param mooLbsDay
	 */
	public void update(MooLbsDay mooLbsDay) throws Exception {
		mooLbsDayDao.update(mooLbsDay);
	}
}

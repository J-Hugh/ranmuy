package com.neo.espionage.modular.wechatRecord.service;

import com.neo.espionage.modular.wechatRecord.dao.WechatRecordDao;
import com.neo.espionage.modular.wechatRecord.domain.WechatRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class WechatRecordService {

	@Autowired
	private WechatRecordDao wechatRecordDao;
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<WechatRecord> searchByPage(Map<String,Object> searchParams) throws Exception {
		return wechatRecordDao.searchByPage(searchParams);
	}

	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<WechatRecord> search(Map<String,Object> searchParams) throws Exception  {
		return wechatRecordDao.search(searchParams);
	}

	/**
	 * 获取所有记录
	 * @return
	 */
	public List<WechatRecord> findAll() throws Exception {
		return  wechatRecordDao.findAll();
	}
	
	/**
	 * 通过获取一条记录
	 * @param id 主键
	 * @return
	 */
	public WechatRecord findById(String id) throws Exception {
		WechatRecord wechatRecord = wechatRecordDao.findById(id);
		if(wechatRecord == null){
			wechatRecord = new WechatRecord();
		}
		return wechatRecord;
	}

	/**
	 * 保存一条记录
	 * @param wechatRecord
	 */
	public void save(WechatRecord wechatRecord) throws Exception {
		wechatRecordDao.save(wechatRecord);
	}

	/**
	 * 通过  删除一条记录
	 * @param id  主键
	 */
	public void delete(String id) throws Exception {
		wechatRecordDao.delete(id);

	}
	
	/**
	 * 修改记录
	 * @param wechatRecord
	 */
	public void update(WechatRecord wechatRecord) throws Exception {
		wechatRecordDao.update(wechatRecord);
	}
}

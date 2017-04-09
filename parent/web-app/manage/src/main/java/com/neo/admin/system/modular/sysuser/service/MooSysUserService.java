package com.neo.admin.system.modular.sysuser.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.admin.common.tools.Md5PwdEncoder;
import com.neo.admin.common.tools.Sequence;
import com.neo.admin.common.value.BootstrapTable;
import com.neo.admin.system.modular.sysrole.dao.MooSysRoleMapper;
import com.neo.admin.system.modular.sysuser.dao.MooSysUserMapper;
import com.neo.admin.system.modular.sysuser.domain.MooSysUser;

@Service
public class MooSysUserService {

	@Autowired
	private MooSysUserMapper mooSysUserDao;
	@Autowired
	private MooSysRoleMapper  mooSysRoleDao;
	
	/**
	 * 按条件分页查询记录
	 * 
	 * @param searchParams
	 *            条件
	 * @return
	 */
	public BootstrapTable<MooSysUser> searchByPage(Map<String, Object> searchParams) throws Exception {

		List<MooSysUser> users = mooSysUserDao.searchByPage(searchParams);
		Integer total = mooSysUserDao.total(searchParams);

		return new BootstrapTable<MooSysUser>(total, users);
	}

	/**
	 * 按条件查询记录
	 * 
	 * @param searchParams
	 *            条件
	 * @return
	 */
	public List<MooSysUser> search(Map<String, Object> searchParams) throws Exception {
		return (List<MooSysUser>) mooSysUserDao.search(searchParams);
	}

	/**
	 * 获取所有记录
	 * 
	 * @return
	 */
	public List<MooSysUser> findAll() throws Exception {
		return (List<MooSysUser>) mooSysUserDao.findAll();
	}

	/**
	 * 通过获取一条记录
	 * 
	 * @param 主键
	 * @return
	 */
	public MooSysUser findById(String uId) throws Exception {
		MooSysUser mooSysUser = mooSysUserDao.findById(uId);
		if (mooSysUser == null) {
			mooSysUser = new MooSysUser();
		}
		return mooSysUser;
	}

	/**
	 * 根据账号查询
	 * @param uAccount
	 * @return
	 * @throws Exception
	 */
	public MooSysUser findByAccount(String uAccount) throws Exception {
		MooSysUser mooSysUser = mooSysUserDao.findByAccount(uAccount);
		if(mooSysUser != null){
			mooSysUser.setRoles(mooSysRoleDao.findById(mooSysUser.getRoleId()));
		}
		return mooSysUser;
	}

	/**
	 * 保存一条记录
	 * 
	 * @param mooSysUser
	 */
	public void save(MooSysUser mooSysUser) throws Exception {
		mooSysUser.setuId(Sequence.getSequence().nextId()+"");
		mooSysUser.setRegDate(new Timestamp(System.currentTimeMillis()));
		mooSysUser.setuState("1");
		mooSysUser.setuPassword(Md5PwdEncoder.encodePassword(mooSysUser.getuPassword()));
		mooSysUserDao.save(mooSysUser);
	}

	/**
	 * 通过 删除一条记录
	 * 
	 * @param 主键
	 */
	public void delete(String uId) throws Exception {
		mooSysUserDao.virtualDel(uId);
	}

	/**
	 * 修改记录
	 * 
	 * @param mooSysUser
	 */
	public void update(MooSysUser mooSysUser) throws Exception {
		mooSysUserDao.update(mooSysUser);
	}
}

package top.clearlight.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import top.clearlight.dao.RoleDao;
import top.clearlight.dto.PageDataBody;
import top.clearlight.entity.Role;
import top.clearlight.entity.RolePermissionRel;
import top.clearlight.exception.ApiAssert;
import top.clearlight.service.AdminUserRoleRelService;
import top.clearlight.service.RolePermissionRelService;
import top.clearlight.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private RolePermissionRelService rolePermissionRelService;
	
	@Autowired
	private AdminUserRoleRelService adminUserRoleRelService;

	@Override
	public Role getById(Integer id) {
		return roleDao.selectById(id);
	}

	@Override
	public PageDataBody<Role> page(Integer pageNumber, Integer pageSize) {
		List<Role> list = roleDao.selectAll((pageNumber - 1) * pageSize, pageSize);
		int countAll = this.countAll();
		return new PageDataBody<>(list, pageNumber, pageSize, countAll);
	}

	@Override
	public List<Role> getByAdminUserId(Integer adminUserId, Integer pageNumber, Integer pageSize) {
		return roleDao.selectAllByAdminUserId(adminUserId, pageNumber, pageSize);
	}

	@Transactional
	@Override
	public void save(String roleName,Integer[] permissionIds) {
		Role role = getByName(roleName);
		ApiAssert.isNull(role, "角色名已存在");
		if(roleName != null && !StringUtils.isEmpty(roleName)) {
			role = new Role();
			role.setRoleName(roleName);
			role.setCreateDate(new Date());
			// 保存角色
			roleDao.insert(role);
		}
		Role role2 = roleDao.selectById(role.getRoleId());
		List<RolePermissionRel> list = new ArrayList<>();
		if(permissionIds != null && permissionIds.length > 0) {
			Arrays.asList(permissionIds).forEach(permissionId -> {
				RolePermissionRel rolePermissionRel = new RolePermissionRel();
				rolePermissionRel.setRoleId(role2.getRoleId());
				rolePermissionRel.setPermissionId(permissionId);
				rolePermissionRel.setCreateDate(new Date());
				list.add(rolePermissionRel);
			});
		}
		
		if(list != null && list.size() > 0) {
			// 建立关联关系
			rolePermissionRelService.saveBatch(list);
		}
		
	}

	@Transactional
	@Override
	public void update(Integer roleId,String roleName,Integer[] permissionIds) {
		Role role = roleDao.selectById(roleId);
		ApiAssert.notNull(role, "角色不存在");
		if(roleName != null && !StringUtils.isEmpty(roleName) && !role.getRoleName().equals(roleName)) {
			role.setRoleName(roleName);
			role.setUpdateDate(new Date());
			// 更新角色
			roleDao.update(role);
		}
		// 删除role与permission 的关联关系
		rolePermissionRelService.removeByRoleId(roleId);
		
		List<RolePermissionRel> list = new ArrayList<>();
		if(permissionIds != null && permissionIds.length > 0) {
			Arrays.asList(permissionIds).forEach(permissionId -> {
				RolePermissionRel rolePermissionRel = new RolePermissionRel();
				rolePermissionRel.setRoleId(roleId);
				rolePermissionRel.setPermissionId(permissionId);
				rolePermissionRel.setCreateDate(new Date());
				rolePermissionRel.setUpdateDate(new Date());
				list.add(rolePermissionRel);
			});
		}
		// 重新建立关联关系
		if(list != null && list.size() > 0) {
			rolePermissionRelService.saveBatch(list);
		}
	}

	@Transactional
	@Override
	public void removeById(Integer id) {
		// 先删除用户与角色的关联关系
		adminUserRoleRelService.removeByRoleId(id);
		// 再删除权限与角色的关联关系
		rolePermissionRelService.removeByRoleId(id);
		// 最后删除角色
		roleDao.deleteById(id);
	}

	@Override
	public int countAll() {
		return roleDao.countAll();
	}

	@Override
	public Role getByName(String name) {
		return roleDao.selectByName(name);
	}
	
}

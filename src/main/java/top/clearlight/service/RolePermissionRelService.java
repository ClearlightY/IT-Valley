package top.clearlight.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import top.clearlight.entity.RolePermissionRel;

public interface RolePermissionRelService {

	List<RolePermissionRel> getAllByRoleId(Integer roleId);
	
	List<RolePermissionRel> getAllByPermissionId(Integer permissionId);
	
	void saveBatch(Collection<? extends Serializable> rolePermissionRels);
	
	void removeByRoleId(Integer roleId);
	
	void removeByPermissionId(Integer permissionId);
	
	void removeBatch(Collection<? extends Serializable> permissionIds);
}

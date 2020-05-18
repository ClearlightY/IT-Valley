package top.clearlight.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.clearlight.entity.RolePermissionRel;

public interface RolePermissionRelDao {

	List<RolePermissionRel> selectAllByRoleId(@Param("roleId") Integer roleId);
	
	List<RolePermissionRel> selectAllByPermissionId(@Param("permissionId") Integer permissionId);
	
	int insertBatch(@Param("rolePermissionRels") Collection<? extends Serializable> rolePermissionRels);
	
	int deleteByRoleId(@Param("roleId") Integer roleId);
	
	int deleteByPermissionId(@Param("permissionId") Integer permissionId);
	
	int deleteBatch(@Param("permissions") Collection<? extends Serializable> permissions);
}

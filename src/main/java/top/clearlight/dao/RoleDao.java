package top.clearlight.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.clearlight.entity.Role;

public interface RoleDao {
	
	// 根据ID查询角色
	Role selectById(@Param("id") Integer id);
	
	// 根据名称查询角色
	Role selectByName(@Param("name") String name);
	
	// 查询所有角色
	List<Role> selectAll(@Param("start") Integer satrt,@Param("limit") Integer limit);
	
	// 根据后台用户ID查询所关联的所有角色
	List<Role> selectAllByAdminUserId(@Param("adminUserId") Integer adminUserId,@Param("start") Integer satrt,@Param("limit") Integer limit);
	
	// 新增角色
	int insert(Role role);
	
	// 更新角色
	int update(Role role);
	
	// 删除角色
	int deleteById(@Param("id") Integer id);
	
	// 统计所有角色
	int countAll();
}

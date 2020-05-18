package top.clearlight.dao;

import java.io.Serializable;
import java.util.Collection;
import org.apache.ibatis.annotations.Param;

public interface CustomerDao {

	int insertBatch(@Param("customers") Collection<? extends Serializable> customers);
}

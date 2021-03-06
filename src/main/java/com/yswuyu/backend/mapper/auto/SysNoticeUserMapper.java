package com.yswuyu.backend.mapper.auto;

import com.yswuyu.backend.model.auto.SysNoticeUser;
import com.yswuyu.backend.model.auto.SysNoticeUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 公告_用户外键 SysNoticeUserMapper
 * @author Hankin_自动生成
 * @email 115889198@qq.com
 * @date 2019-09-08 02:12:54
 */
public interface SysNoticeUserMapper {
      	      	   	      	   	      	   	      
    long countByExample(SysNoticeUserExample example);

    int deleteByExample(SysNoticeUserExample example);
		
    int deleteByPrimaryKey(String id);
		
    int insert(SysNoticeUser record);

    int insertSelective(SysNoticeUser record);

    List<SysNoticeUser> selectByExample(SysNoticeUserExample example);
		
    SysNoticeUser selectByPrimaryKey(String id);
		
    int updateByExampleSelective(@Param("record") SysNoticeUser record, @Param("example") SysNoticeUserExample example);

    int updateByExample(@Param("record") SysNoticeUser record, @Param("example") SysNoticeUserExample example); 
		
    int updateByPrimaryKeySelective(SysNoticeUser record);

    int updateByPrimaryKey(SysNoticeUser record);
  	  	
}
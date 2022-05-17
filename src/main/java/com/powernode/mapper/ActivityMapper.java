package com.powernode.mapper;

import com.powernode.pojo.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.powernode.pojo.Activity
 */
public interface ActivityMapper {

    /**
     * 查询所有市场活动
     * @return
     */
    List<Activity> selectAll();

    /**
     * 添加市场活动
     */
    int insert(Activity activity);

    /**
     * 根据id删除
     * @param idList
     * @return
     */
    int deleteById(@Param("ids") List<String> idList);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Activity selectById(String id);

    /**
     * 修改市场活动信息
     * @param activity
     * @return
     */
    int update(Activity activity);

    /**
     * 多条件查询
     * @param activity
     * @return
     */
    List<Activity> selectConditions(Activity activity);

    /**
     * 批量插入
     */
    int insertBatch(List<Activity> activityList);

}





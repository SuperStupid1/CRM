package com.powernode.service.Impl;

import com.powernode.exception.CRMException;
import com.powernode.mapper.ActivityMapper;
import com.powernode.pojo.Activity;
import com.powernode.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    /**
     * 查询所有市场活动
     * @return
     */
    @Override
    public List<Activity> findAll() {
        return activityMapper.selectAll();
    }

    /**
     * 添加市场活动
     * @param activity
     * @return
     */
    @Override
    public void addActivity(Activity activity) {
        int rows = activityMapper.insert(activity);
        if (rows < 1){
            throw new CRMException("添加失败");
        }
    }

    @Override
    public void delActivity(String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        int rows = activityMapper.deleteById(idList);
        if (rows != idList.size()){
            throw new CRMException("删除失败");
        }
    }

    @Override
    public Activity findActivity(String id) {
        return activityMapper.selectById(id);
    }

    @Override
    public void update(Activity activity) {
        int rows = activityMapper.update(activity);
        if (rows < 1){
            throw new CRMException("修改失败");
        }
    }

    /**
     * 多条件查询
     * @param activity
     * @return
     */
    @Override
    public List<Activity> conditionsFind(Activity activity) {
        return activityMapper.selectConditions(activity);
    }
}

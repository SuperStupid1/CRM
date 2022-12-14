package com.powernode.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.powernode.exception.CRMException;
import com.powernode.mapper.ActivityMapper;
import com.powernode.mapper.ActivityRemarkMapper;
import com.powernode.pojo.Activity;
import com.powernode.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;

    /**
     * 查询所有市场活动
     * @return
     */
    @Override
    public PageInfo<Activity> findAll(Integer pageNum,Integer pageSize) {
        // 分页查询
        Page<Activity> page = PageHelper.startPage(pageNum, pageSize);
        List<Activity> activityList = activityMapper.selectAll();
        return page.toPageInfo();
    }

    /**
     * 查询所有市场活动(不分页)
     * @return
     */
    @Override
    public List<Activity> findAll(){
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

    /**
     * 根据市场活动id删除市场活动，并先删除其所有备注信息
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)// 添加事务管理注解（如果在删除备注信息的过程中发生异常，那么需要进行回滚）
    public void delActivity(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList){
            activityRemarkMapper.deleteByActivityId(id);
            activityMapper.deleteById(id);
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
    public PageInfo<Activity> conditionsFind(Activity activity,Integer pageNum,Integer pageSize) {
        // 分页查询
        Page<Activity> page = PageHelper.startPage(pageNum, pageSize);
        List<Activity> activityList = activityMapper.selectConditions(activity);
        return page.toPageInfo();
    }

    @Override
    public void addBatch(List<Activity> activityList) {
        int rows = activityMapper.insertBatch(activityList);
        if (rows < activityList.size()){
            throw new CRMException("导入失败");
        }
    }
}

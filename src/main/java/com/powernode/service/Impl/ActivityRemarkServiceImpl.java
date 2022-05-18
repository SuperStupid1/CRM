package com.powernode.service.Impl;

import com.powernode.exception.CRMException;
import com.powernode.mapper.ActivityRemarkMapper;
import com.powernode.pojo.ActivityRemark;
import com.powernode.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityRemarkServiceImpl implements ActivityRemarkService {

    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;

    @Override
    public void addActivityRemark(ActivityRemark activityRemark) {
        int row = activityRemarkMapper.insert(activityRemark);
        if (row == 0){
            throw new CRMException("添加备注失败");
        }
    }
}

package com.powernode.util;

import com.powernode.commns.Constant;
import com.powernode.pojo.Activity;
import com.powernode.pojo.User;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 杜波
 */
public class SheetUtil {

    /**
     * 将activity的集合转成表格对象
     * @param list
     * @return
     * @throws IllegalAccessException
     */
    public static HSSFWorkbook list2Sheet(List<Activity> list) throws IllegalAccessException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        // 设置表头
        HSSFRow rowHeader = sheet.createRow(0);
        for (int j = 4; j < Activity.class.getDeclaredFields().length-1 ; j++) {
            HSSFCell cell = rowHeader.createCell(j-4);
            cell.setCellValue(Activity.class.getDeclaredFields()[j].getName());
        }
        // 通过反射获取值
        for (int i = 1; i <= list.size(); i++) {
            Class<? extends Activity> aClass = list.get(i-1).getClass();
            HSSFRow row = sheet.createRow(i);
            Field[] fields = aClass.getDeclaredFields();

                for (int j = 4; j < fields.length-1 ; j++) {
                    HSSFCell cell = row.createCell(j-4);
                    fields[j].setAccessible(true);
                    Object obj = fields[j].get(list.get(i-1));
                    if (obj != null){
                        cell.setCellValue(fields[j].get(list.get(i-1)).toString());
                    }else {
                        cell.setCellValue("");
                    }
            }
        }
        return workbook;
    }

    public static List<Activity> sheet2List(InputStream inputStream, HttpSession session) throws IOException {
        List<Activity> activityList = new ArrayList<>();
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        // 获取上传的表格对象
        HSSFSheet sheet = workbook.getSheetAt(0);
        // 将表格对象每一行解析称为activity对象并存入activityList
        // 第一行为表头需要跳过
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            HSSFRow row = sheet.getRow(i);
            Activity activity = new Activity();
            // 设置id，owner ，createTime ,creatBy的值
            activity.setId(UUID.randomUUID().toString().replace("-",""));
            activity.setOwner(((User)session.getAttribute(Constant.LOGIN_USER)).getId());
            activity.setCreateTime(DateUtil.date2String(new Date()));
            activity.setCreateBy(((User)session.getAttribute(Constant.LOGIN_USER)).getId());
            // 读取表格对象中的信息进行赋值
            activity.setName(row.getCell(0).getStringCellValue());
            activity.setStartDate(row.getCell(1).getStringCellValue());
            activity.setEndDate(row.getCell(2).getStringCellValue());
            activity.setCost(row.getCell(3).getStringCellValue());
            activity.setDescription(row.getCell(4).getStringCellValue());
            // 将activity添加到集合中
            activityList.add(activity);
        }
        return activityList;
    }
}

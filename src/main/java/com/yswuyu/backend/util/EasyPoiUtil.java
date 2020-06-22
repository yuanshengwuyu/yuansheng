package com.yswuyu.backend.util;

import cn.afterturn.easypoi.csv.CsvImportUtil;
import cn.afterturn.easypoi.csv.entity.CsvImportParams;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author Hankin
 * @date 2020/1/2 10:34 上午
 * @Version 1.0
 * @Description:
 */
@Slf4j
public class EasyPoiUtil {

    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    //@描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    public static boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            return false;
        }
        return true;
    }

    public static <T> List<T> getImportContent(MultipartFile file, Class<T> cls, int headRow, int titleRow) {
        String fileName = file.getOriginalFilename();

        List<T> resultList = Lists.newArrayList();
        try {
            //验证文件名
            if (!validateExcel(fileName)) {
                //csv文件
                CsvImportParams importParams = new CsvImportParams();
                importParams.setHeadRows(headRow);
                importParams.setTitleRows(titleRow);
                importParams.setKeyIndex(0);
                resultList = CsvImportUtil.importCsv(file.getInputStream(), cls, importParams);
            } else {
                //Excel文件
                ImportParams importParams = new ImportParams();
                importParams.setHeadRows(headRow);
                importParams.setTitleRows(titleRow);
                importParams.setKeyIndex(0);
                ExcelImportResult<T> result = ExcelImportUtil.importExcelMore(file.getInputStream(), cls, importParams);
                resultList = result.getList();
            }
            return resultList;
        } catch (Exception e) {
            log.error("解析上传文件报错：", e);
        }

        return null;
    }

}

package com.yswuyu.backend.model.business.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Hankin
 * @date 2019/12/31 2:05 下午
 * @Version 1.0
 * @Description:
 */
@ExcelTarget("AddressExcelEntity")
@Data
public class AddressExcelEntity implements Serializable {


    @Excel(name = "收货人姓名")
    private String receiverName;

    @Excel(name = "收货人手机号")
    private String phone;

    @Excel(name = "收货人省份")
    private String province;

    @Excel(name = "收货人城市")
    private String city;

    @Excel(name = "收货人地区")
    private String county;

    @Excel(name = "详细收货地址")
    private String detailAddress;

    @Excel(name = "备注")
    private String remark;
}

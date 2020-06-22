package com.yswuyu.backend.model.business.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户收货地址
 * @author
 * @date
 */
@Data
@NoArgsConstructor
public class AddressDTO implements Serializable{

    /**
     * 地址id
     */
    public Long id;
    /**
     * 用户id
     */
    public Long userId;
    /**
     * 收货人姓名
     */
    public String receiverName;
    /**
     * 手机号
     */
    public String phone;
    /**
     * 省/直辖市/自治区
     */
    public String province;
    /**
     * 省(直辖市/自治区)编号
     */
    public Integer provinceId;
    /**
     * 市
     */
    public String city;
    /**
     * 市编号
     */
    public Integer cityId;
    /**
     * 县/区
     */
    public String county;
    /**
     * 县(区)编号
     */
    public Integer countyId;
    /**
     * 详细地址
     */
    public String detailAddress;

    /**
     * 是否是默认地址(0:非默认  1:默认地址)
     */
    public Integer defaultAddress;

    /**
     * 邮编
     */
    public String postalCode;

    /**
     * 导入地址簿的批次号
     */
    private String batchNum;

    /**
     * 地址信息是否完整：0-默认完整，1-不完整
     */
    private Integer isCompleted;

    /**
     * 地址簿导入的订单备注
     */
    private String remark;
}

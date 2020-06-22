package com.yswuyu.backend.model.business;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户地址表
 * </p>
 *
 * @author Hankin
 * @since 2020-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 省/直辖市/自治区
     */
    private String province;

    /**
     * 省(直辖市/自治区)id
     */
    private Integer provinceId;

    /**
     * 市
     */
    private String city;

    /**
     * 市id
     */
    private Integer cityId;

    /**
     * 县/区
     */
    private String county;

    /**
     * 县(区)id
     */
    private Integer countyId;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 邮编
     */
    private String postalCode;

    /**
     * 是否是默认地址(0:非默认  1:默认地址)
     */
    private Integer defaultAddress;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 导入地址批次号
     */
    private String batchNum;

    /**
     * 地址是否完整：0-是，1-否
     */
    private Integer isCompleted;

    /**
     * 是否删除：默认0-否；1-是
     */
    private Integer delFlag;


}

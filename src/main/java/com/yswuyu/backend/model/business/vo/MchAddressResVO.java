package com.yswuyu.backend.model.business.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Hankin
 * @date 2019/12/31 4:26 下午
 * @Version 1.0
 * @Description:
 */
@ApiModel("商家地址簿导入出参")
@Data
public class MchAddressResVO implements Serializable {

    @ApiModelProperty(value = "导入总数量")
    private Integer totalCount;

    @ApiModelProperty(value = "成功导入数量")
    private Integer sucCount;

    @ApiModelProperty(value = "导入异常数量")
    private Integer errCount;

    @ApiModelProperty(value = "批次号")
    private String batchNum;

    @ApiModelProperty(value = "整体错误信息")
    private String errMsg;
}

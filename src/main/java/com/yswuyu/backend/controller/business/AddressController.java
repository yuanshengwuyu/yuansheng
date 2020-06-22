package com.yswuyu.backend.controller.business;


import com.yswuyu.backend.common.ResResult;
import com.yswuyu.backend.model.business.vo.AddressExcelEntity;
import com.yswuyu.backend.model.business.vo.MchAddressResVO;
import com.yswuyu.backend.service.AddressService;
import com.yswuyu.backend.util.CodeGen;
import com.yswuyu.backend.util.EasyPoiUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 用户地址表 前端控制器
 * </p>
 *
 * @author Hankin
 * @since 2020-03-05
 */
@Slf4j
@RestController
@RequestMapping("/user/address")
@Api(value = "MchAddressController", tags = {"商家地址簿管理"})
public class AddressController {

    @Autowired
    private CodeGen codeGen;

    @Autowired
    private AddressService addressService;

    /**
     * 商家地址簿导入
     *
     * @return
     */
    @ApiOperation(value = "地址导入")
    @PostMapping(value = "import")
    public ResResult<MchAddressResVO> importAddress(@RequestParam(value = "file") MultipartFile file) {

        Long userId = 270L;
        try {
            //批次号
            String batchCode = codeGen.genCode(userId);

            List<AddressExcelEntity> resultList = EasyPoiUtil.getImportContent(file, AddressExcelEntity.class, 1, 0);
            if (CollectionUtils.isEmpty(resultList)){
                return ResResult.fail();
            }

            MchAddressResVO mchAddressResVO = addressService.batchImportAddress(resultList, batchCode, userId);
            return ResResult.success(mchAddressResVO);
        } catch (Exception e) {
            log.error("地址导入解析失败：", e);
        }

        return ResResult.fail();
    }
}

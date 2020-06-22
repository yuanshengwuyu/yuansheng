package com.yswuyu.backend.util;

import com.yswuyu.backend.model.business.TAddress;
import com.yswuyu.backend.model.business.dto.AddressDTO;
import com.yswuyu.backend.model.business.vo.AddressExcelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author Hankin
 * @date 2019/12/11 4:12 下午
 * @Version 1.0
 * @Description:
 */
@Mapper
public interface ConvertorUtil {

    ConvertorUtil INSTANCE = Mappers.getMapper( ConvertorUtil.class );

    /**
     * 地址簿导入对象转传输对象
     * @param addressExcelEntity
     * @return
     */
    TAddress addressExcelEntityToDTO(AddressExcelEntity addressExcelEntity);


    /**
     * 数据库对象转传输对象
     * @param addressPo
     * @return
     */
    AddressDTO addressPoToDTO(TAddress addressPo);
}

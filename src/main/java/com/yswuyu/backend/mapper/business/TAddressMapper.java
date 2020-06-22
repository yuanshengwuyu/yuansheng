package com.yswuyu.backend.mapper.business;

import com.yswuyu.backend.model.business.TAddress;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户地址表 Mapper 接口
 * </p>
 *
 * @author Hankin
 * @since 2020-03-05
 */
@Repository
public interface TAddressMapper {

    /**
     * 新增地址
     * @param address
     * @return
     */
    long insertSelective(TAddress address);

    /**
     * 批量新增地址
     * @param address
     * @return
     */
    int batchInsertSelective(TAddress address);

    /**
     * 新增地址（放重复插入）
     * @param address
     * @return
     */
    long insertWithoutDuplicate(TAddress address);


    /**
     * 批量新增地址（放重复插入）
     * @param address
     * @return
     */
    int batchInsertWithoutDuplicate(TAddress address);
}

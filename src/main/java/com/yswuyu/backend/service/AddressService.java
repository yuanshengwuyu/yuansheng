package com.yswuyu.backend.service;

import com.alibaba.fastjson.JSON;
import com.yswuyu.backend.common.exception.BaseException;
import com.yswuyu.backend.common.exception.SystemErrorType;
import com.yswuyu.backend.mapper.business.TAddressMapper;
import com.yswuyu.backend.model.business.TAddress;
import com.yswuyu.backend.model.business.dto.AddressDTO;
import com.yswuyu.backend.model.business.vo.AddressExcelEntity;
import com.yswuyu.backend.model.business.vo.MchAddressResVO;
import com.yswuyu.backend.util.ConvertorUtil;
import com.yswuyu.backend.util.ValidateUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 用户地址表 服务实现类
 * </p>
 *
 * @author Hankin
 * @since 2020-03-05
 */
@Slf4j
@Service
public class AddressService {

    @Autowired
    private TAddressMapper tAddressMapper;

    /**
     * 批量导入地址簿
     *
     * @param resultList
     * @param batchCode
     * @return
     */
    public MchAddressResVO batchImportAddress(List<AddressExcelEntity> resultList, String batchCode, Long userId) {
        int errCount = 0;
        List<TAddress> addressList = Lists.newArrayList();
        for (AddressExcelEntity entity : resultList) {
            TAddress address = ConvertorUtil.INSTANCE.addressExcelEntityToDTO(entity);
            String validMsg = validAddress(address);
            if (StringUtils.isEmpty(validMsg)) {
                address.setPhone(entity.getPhone().trim());
                checkMobilePhoneNum(address.getPhone());
                address.setUserId(userId);
                address.setBatchNum(batchCode);
                addressList.add(address);
            } else {
                errCount += 1;
            }
        }
        if (CollectionUtils.isEmpty(addressList)) {
            throw new BaseException(SystemErrorType.IMPORT_ADDRESS_NONE);
        }
        log.info("批次号{}执行地址簿批量插入操作，执行条数：" + addressList.size(), batchCode);
        List<AddressDTO> errList = batchInsertAddress(addressList);
        log.info("批次号{}执行地址簿批量插入错误结果：{}", batchCode, JSON.toJSONString(errList));
        if (!CollectionUtils.isEmpty(errList)) {
            errCount += errList.size();
        }
        MchAddressResVO mchAddressResVO = new MchAddressResVO();
        mchAddressResVO.setTotalCount(resultList.size());
        mchAddressResVO.setBatchNum(batchCode);
        mchAddressResVO.setSucCount(resultList.size() - errCount);
        mchAddressResVO.setErrCount(errCount);
        return mchAddressResVO;
    }

    public List<AddressDTO> batchInsertAddress(List<TAddress> addressList) {
        if (CollectionUtils.isEmpty(addressList)) {
            return Collections.emptyList();
        }


        List<AddressDTO> errList = Lists.newArrayList();
        List<AddressDTO> sucList = Lists.newArrayList();
        long beginTime = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger(0);
        addressList.forEach(addressPo -> {
            try {
                //导入地址簿设置默认值0
                addressPo.setProvinceId(0);
                addressPo.setCityId(0);
                addressPo.setCountyId(0);
                addressPo.setDefaultAddress(0);
                String validMsg = validAddressDetail(addressPo);
                if (StringUtils.isNotEmpty(validMsg)) {
                    addressPo.setIsCompleted(1);
                } else {
                    addressPo.setIsCompleted(0);
                }
                addressPo.setCreateTime(new Date());
                tAddressMapper.insertWithoutDuplicate(addressPo);
                count.addAndGet(1);
                if (addressPo.getIsCompleted() == 0){
                    //地址完整
                    sucList.add(ConvertorUtil.INSTANCE.addressPoToDTO(addressPo));
                } else {
                    errList.add(ConvertorUtil.INSTANCE.addressPoToDTO(addressPo));
                }
            } catch (Exception e) {
                log.error("保存商家地址簿异常", e);
                errList.add(ConvertorUtil.INSTANCE.addressPoToDTO(addressPo));
            }
        });
        long endTime = System.currentTimeMillis();
        log.info("当前方法执行时间：" + (endTime - beginTime) + " ms， 执行成功条数：" + count.intValue());
        return errList;
    }


    /**
     * 校验地址簿
     *
     * @param addressPo
     */
    private String validAddressDetail(TAddress addressPo) {
        String validMsg = null;
        if (StringUtils.isEmpty(addressPo.getProvince())) {
            addressPo.setProvince("缺失");
            validMsg = "收货人省/直辖市/自治区信息不能为空";
        }
        if (StringUtils.isEmpty(addressPo.getCity())) {
            addressPo.setCity("缺失");
            validMsg = "收货人市信息不能为空";
        }
        if (StringUtils.isEmpty(addressPo.getCounty())) {
            addressPo.setCounty("缺失");
            validMsg = "收货人区信息不能为空";
        }
        if (StringUtils.isEmpty(addressPo.getDetailAddress())) {
            addressPo.setDetailAddress("缺失");
            validMsg = "收货人详细地址不能为空";
        }
        if (StringUtils.isEmpty(addressPo.getReceiverName())) {
            addressPo.setReceiverName("缺失");
            validMsg = "收货人姓名不能为空";
        }
        if (StringUtils.isEmpty(addressPo.getPhone())) {
            addressPo.setPhone("缺失");
            validMsg = "收货人手机号不能为空";
        }
        if (!ValidateUtil.checkMobilePhoneNum(addressPo.getPhone())) {
            validMsg = "收货人手机号格式不合法";
        }
        return validMsg;
    }


    /**
     * 校验地址簿
     *
     * @param address
     */
    private String validAddress(TAddress address) {
        String validMsg = null;
        if (Objects.isNull(address)) {
            validMsg = "信息不能为空";
        }
        if (StringUtils.isEmpty(address.getReceiverName()) || StringUtils.isEmpty(address.getPhone())) {
            validMsg = "收货人姓名和手机号不能为空";
        }

        return validMsg;
    }

    public static boolean checkMobilePhoneNum(String phoneNum) {
        String phone = dealPhoneNumber(phoneNum);
        String regex = "^(1[3-9]\\d{9}$)";
        if (phoneNum.length() == 11) {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            if (m.matches()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 处理电话特殊字符问题
     *
     * @param phone
     * @return
     */
    public static String dealPhoneNumber(String phone) {
        if (StringUtils.isNotBlank(phone)) {
            phone = removeNonAscii(phone);
            phone = removeSomeControlChar(phone);
            phone = removeFullControlChar(phone).trim();
            return digitFileter(phone);
        }
        return null;
    }


    /**
     * 数字过滤
     *
     * @param str
     * @return
     */
    public static String digitFileter(String str) {
        if (StringUtils.isNotBlank(str)) {
            char[] num = str.trim().toCharArray();
            char[] arr = new char[num.length];
            int j = 0;
            for (int i = 0; i < num.length; i++) {
                if (Character.isDigit(num[i])) {
                    arr[j] = num[i];
                    j++;
                }
            }
            return String.valueOf(arr).trim();
        }
        return null;
    }

    /**
     * 去除非ascii码字符
     *
     * @param str
     * @return
     */
    public static String removeNonAscii(String str) {
        return str.replaceAll("[^\\x00-\\x7F]", "");
    }

    /**
     * 去除不可打印字符
     *
     * @param str
     * @return
     */
    public static String removeNonPrintable(String str) {
        return str.replaceAll("[\\p{C}]", "");
    }

    /**
     * 去除一些控制字符 Control Char
     *
     * @param str
     * @return
     */
    public static String removeSomeControlChar(String str) {
        return str.replaceAll("[\\p{Cntrl}\\p{Cc}\\p{Cf}\\p{Co}\\p{Cn}]", "");
    }

    /**
     * 去除一些换行制表符
     *
     * @param str
     * @return
     */
    public static String removeFullControlChar(String str) {
        return removeNonPrintable(str).replaceAll("[\\r\\n\\t]", "");
    }
}

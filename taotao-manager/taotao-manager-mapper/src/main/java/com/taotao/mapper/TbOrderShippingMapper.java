package com.taotao.mapper;

import com.taotao.pojo.TbOrderShipping;
import com.taotao.pojo.TbOrderShippingCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbOrderShippingMapper {
    int countByExample(TbOrderShippingCriteria example);

    int deleteByExample(TbOrderShippingCriteria example);

    int deleteByPrimaryKey(String orderId);

    int insert(TbOrderShipping record);

    int insertSelective(TbOrderShipping record);

    List<TbOrderShipping> selectByExample(TbOrderShippingCriteria example);

    TbOrderShipping selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") TbOrderShipping record, @Param("example") TbOrderShippingCriteria example);

    int updateByExample(@Param("record") TbOrderShipping record, @Param("example") TbOrderShippingCriteria example);

    int updateByPrimaryKeySelective(TbOrderShipping record);

    int updateByPrimaryKey(TbOrderShipping record);
}
package com.taotao.mapper;

import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbContentMapper {
    int countByExample(TbContentCriteria example);

    int deleteByExample(TbContentCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(TbContent record);

    int insertSelective(TbContent record);

    List<TbContent> selectByExampleWithBLOBs(TbContentCriteria example);

    List<TbContent> selectByExample(TbContentCriteria example);

    TbContent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbContent record, @Param("example") TbContentCriteria example);

    int updateByExampleWithBLOBs(@Param("record") TbContent record, @Param("example") TbContentCriteria example);

    int updateByExample(@Param("record") TbContent record, @Param("example") TbContentCriteria example);

    int updateByPrimaryKeySelective(TbContent record);

    int updateByPrimaryKeyWithBLOBs(TbContent record);

    int updateByPrimaryKey(TbContent record);
}
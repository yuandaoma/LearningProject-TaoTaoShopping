package com.taotao.mapper;

import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public interface TbItemCatMapper {
	int countByExample(TbItemCatCriteria example);

	int deleteByExample(TbItemCatCriteria example);

	int deleteByPrimaryKey(Long id);

	int insert(TbItemCat record);

	int insertSelective(TbItemCat record);

	List<TbItemCat> selectByExample(TbItemCatCriteria example);

	TbItemCat selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") TbItemCat record, @Param("example") TbItemCatCriteria example);

	int updateByExample(@Param("record") TbItemCat record, @Param("example") TbItemCatCriteria example);

	int updateByPrimaryKeySelective(TbItemCat record);

	int updateByPrimaryKey(TbItemCat record);

	/**
	 * 依据商品类目id查询商品类目名
	 * 
	 * @param itemCatId
	 * @return
	 */
	@Select("select name from tb_item_cat where id = #{itemCatId}")
	String selectItemCatNameById(@Param("itemCatId") Long itemCatId);
}
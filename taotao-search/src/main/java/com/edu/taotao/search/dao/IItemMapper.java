package com.edu.taotao.search.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.taotao.common.pojo.SearchItemVO;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月6日
 * @description
 */
@Repository
public interface IItemMapper {

	List<SearchItemVO> listSolrAnalyItems();

}

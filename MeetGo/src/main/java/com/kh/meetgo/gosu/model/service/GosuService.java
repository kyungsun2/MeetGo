package com.kh.meetgo.gosu.model.service;

import java.util.ArrayList;

import com.kh.meetgo.common.model.vo.PageInfo;
import com.kh.meetgo.gosu.model.dto.GosuOpt;
import com.kh.meetgo.member.model.vo.Gosu;

public interface GosuService {
	
	// 고수찾기 회원수 조회
	int selectOptionalGosuCount(String region, String regionSub, int categoryBigNo, int categorySmallNo);
	
	// 고수찾기
	ArrayList<GosuOpt> selectOptionalGosu(String region, String regionSub, int categoryBigNo, int categorySmallNo, PageInfo pi);

	// 고수찾기 상세정보
	ArrayList<GosuOpt> gosuDetail(int gosuNo);
	
	// 고수등록
	int insertGosu(Gosu gosu);
	
	// 고수 카테고리 등록
	int insertGosuCate(int service, int gosuNo);
	
	// 고수로 상태변경
	int changeStatus(int userNo);
	
}

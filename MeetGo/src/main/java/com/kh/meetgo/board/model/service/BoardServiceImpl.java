package com.kh.meetgo.board.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.kh.meetgo.board.model.dao.BoardDao;
import com.kh.meetgo.board.model.vo.Board;
import com.kh.meetgo.board.model.vo.Reply;
import com.kh.meetgo.common.model.vo.PageInfo;
import com.kh.meetgo.gosu.model.dto.PofolOpt;
import com.kh.meetgo.gosu.model.vo.Pofol;

// @Component
@Service
@EnableTransactionManagement // 트랜잭션 관리를 하겠다.
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int selectGosuReqListCount() {
		return boardDao.selectGosuReqListCount(sqlSession);
	}
	
	// 고수찾아요 게시판 리스트 
	@Override
	public ArrayList<Board> selectGosuReqList(PageInfo pi) {
		return boardDao.selectGosuReqList(sqlSession, pi);
	}
	
	// 고수찾아요 작성하기
	@Override
	@Transactional 
	public int insertGosuReqBoard(Board m) {
		return boardDao.insertGosuReqBoard(sqlSession, m);
	}
	
	@Override
	@Transactional
	public int increaseGosuReqCount(int boardNo) {
		return boardDao.increaseGosuReqCount(sqlSession, boardNo);
	}
	
	
	@Override
	public int selectTipListCount() {
		return boardDao.selectTipListCount(sqlSession);
	}
	
	// 팁노하우 게시판 리스트 
	@Override
	public ArrayList<Board> selectTipList(PageInfo pi) {
		return boardDao.selectTipList(sqlSession, pi);
	}
	
	// 팁노하우 작성하기
	@Override
	@Transactional 
	public int insertTipBoard(Board m) {
		return boardDao.insertTipBoard(sqlSession, m);
	}
	
	@Override
	@Transactional
	public int increaseTipCount(int boardNo) {
		return boardDao.increaseTipCount(sqlSession, boardNo);
	}
	
	


	


	@Override
	public ArrayList<Reply> selectReplyList(int boardNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertReply(Reply r) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int countPofolList() {
		return boardDao.countPofolList(sqlSession);
	}
	@Override
	public ArrayList<PofolOpt> selectPofolList(PageInfo pi, String standard, int categoryBigNo) {
		return boardDao.selectPofolList(sqlSession, pi, standard, categoryBigNo);
	}
	@Override
	public ArrayList<String> getLoginUserCtgName(int userNo) {
		return boardDao.getLoginUserCtgName(sqlSession, userNo);
	}

	@Override
	public Board selectGosuReqBoard(int boardNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Board selectTipBoard(int boardNo) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public int insertPofol(Pofol pofol) {
		return boardDao.insertPofol(sqlSession, pofol);
	}
	
	@Override
	public int insertPofolImg(String pofolImgUrl, int pofolNo) {
		return boardDao.insertPofolImg(sqlSession, pofolImgUrl, pofolNo);
	}

}








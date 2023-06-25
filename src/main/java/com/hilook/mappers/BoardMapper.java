package com.hilook.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.hilook.beans.vo.BoardVO;
import com.hilook.beans.vo.Criteria;

@Mapper
public interface BoardMapper {
	// 게시글 등록
	public void write(@Param("board") BoardVO board, @Param("file") MultipartFile file) throws Exception;
	
	// 게시글 목록
	public List<BoardVO> getList();
	public List<BoardVO> getList(Criteria cri);
	
	// 게시글 상세보기
	public BoardVO getPage(Long bno); 
	
	// 게시글 수정
	public void modify(@Param("board") BoardVO board, @Param("file") MultipartFile file) throws Exception;
	
	// 게시글 삭제
	public int delete(Long bno);
	
	// 게시판 페이지 적용
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	// 게시물 총 개수
	public int getTotal(Criteria cri);
	
	// 조회수
	public int cnt(Long bno);
	
}

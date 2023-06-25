package com.hilook.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hilook.beans.vo.BoardVO;
import com.hilook.beans.vo.Criteria;

@Service
public interface BoardService {

	public void write(BoardVO board, MultipartFile file) throws Exception;
	public List<BoardVO> getList(Criteria cri);
	public List<BoardVO> getList();
	public BoardVO getPage(Long bno);
	public void modify(BoardVO board, MultipartFile file) throws Exception;
	public int delete(Long bno);
	
	// 게시글 목록(페이징)
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	// 게시물 개수
	public int getTotal(Criteria cri);
	
	// 조회수증가
	public int cnt(Long bno);
	
}
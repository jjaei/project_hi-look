package com.hilook.services;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hilook.beans.vo.BoardVO;
import com.hilook.beans.vo.Criteria;
import com.hilook.mappers.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;
	
	@Override
	public void write(BoardVO board, MultipartFile file) throws Exception {
		String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";
		
		UUID uuid = UUID.randomUUID();
		String filename = uuid + "_" + file.getOriginalFilename();
		File saveFile = new File(projectPath, filename);
		
		board.setFilename(filename);
		board.setFilepath("/files/" + filename);
		
		file.transferTo(saveFile);
		mapper.write(board, file);
	}	
	
	@Override
	public List<BoardVO> getList(){
		return mapper.getList();
	}
	
	@Override
	public List<BoardVO> getList(Criteria cri){
		cri.setParam();
		return mapper.getListWithPaging(cri);
	}
	
	@Override
	public BoardVO getPage(Long bno) {
		return mapper.getPage(bno);
	}
	
	@Override
	public void modify(BoardVO board, MultipartFile file) throws Exception {
		
		String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/filesup";
		
		if (file != null && !file.isEmpty()) {
	        UUID uuid = UUID.randomUUID();
	        String filename = uuid + "_" + file.getOriginalFilename();
	        File saveFile = new File(projectPath, filename);

	        board.setFilename(filename);
	        board.setFilepath("/filesup/" + filename);

	        file.transferTo(saveFile);
	    }
		
		mapper.modify(board, file);
	
	}
	
	@Override
	public int delete(Long bno) {
		return mapper.delete(bno);
	}
	
	@Override
	public List<BoardVO> getListWithPaging(Criteria cri){
		return mapper.getListWithPaging(cri);
	}
	
	@Override
	public int getTotal(Criteria cri) {
		return mapper.getTotal(cri);
	}
	
	@Override
	public int cnt(Long bno) {
		return mapper.cnt(bno);
	}	
}
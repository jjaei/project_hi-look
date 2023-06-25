package com.hilook.services;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import com.hilook.beans.vo.Criteria;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class BoardServiceTest {

	@Autowired
	private BoardService service;
	private MultipartFile file;
	
//	@Test
//	public void testWrite() {
//		BoardVO board = new BoardVO();
//		
//		board.setTitle("service test");
//		board.setContent("service test");
//		board.setWriter("service test");
//		
//		service.write(board, file);
//	}
	
//	@Test
	public void testGetList() {
		service.getList().forEach(board -> log.info("" + board));
	}
	
//	@Test
	public void testGetPage() {
		long bno=1;
		log.info("------------------------------" + service.getPage(bno));
	}
	
//	@Test
//	public void testModify() {
//		BoardVO board = new BoardVO();
//		board.setBno(2L);
//		board.setTitle("서비스 수정");
//		board.setWriter("작성자 수정");
//		board.setContent("내용 또 수정");
//		
//		int result = service.modify(board);
//		log.info("result : " + result);
//	}
	
//	@Test
	public void testDelete() {
		int result = service.delete(13L);
		log.info("result : " + result);
	}
	
	@Test
	public void testListPaging() {
		Criteria cri = new Criteria();
		List list = service.getListWithPaging(cri);
		list.forEach(board -> log.info("--------" + board));
	}
}

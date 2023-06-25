package com.hilook.mappers;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hilook.beans.vo.Criteria;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class BoardMapperTest {

	@Autowired
	private BoardMapper mapper;
	
//	@Test
//	public void testWrite() {
//		BoardVO board = new BoardVO();
//		
//		board.setTitle("mapper test");
//		board.setWriter("mapper test");
//		board.setContent("mapper test");
//		
//		log.info("------------------mapper test");	
//		mapper.write(board);
//	}
	
//	@Test
	public void testGetPage() {
		Long bno = 1L;
		
		log.info("" + mapper.getPage(bno));
	}	
	
//	@Test
	public void testGetList() {
		List list = mapper.getList();
		for(Object obj : list) {
			log.info("" + obj);
		}
	}
	
//	@Test
//	public void testModify() {
//		BoardVO board = new BoardVO();
//		
//		board.setBno(2L);
//		board.setTitle("마지막 수정");
//		board.setContent("마지막 수정 내용");
//		
//		int result = mapper.modify(board,file);
//		log.info("---------------------------------- result : " + result);
//	}
	
//	@Test
	public void testDelete() {
		int result = mapper.delete(14L);
		log.info("result : " + result);
	}

//	@Test
	public void testPaging() {
		Criteria cri = new Criteria();
		cri.setPageNum(1);
		List list = mapper.getListWithPaging(cri);
		list.forEach(board -> log.info("------------------------------" + board));
	}
	
	@Test
	public void testUpdateCnt() {
		Long bno = 1l;
		mapper.cnt(bno);
	}
}
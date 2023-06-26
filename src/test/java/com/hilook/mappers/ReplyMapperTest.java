package com.hilook.mappers;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hilook.beans.vo.Criteria;
import com.hilook.beans.vo.ReplyVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class ReplyMapperTest {
	@Autowired
	private ReplyMapper mapper;
	
//	@Test
	public void replyInsert() {
		String id = "admin";
		Long bno = 2L;
		String reply = "댓글 테스트";
		
		ReplyVO replyVO = new ReplyVO();
		replyVO.setBno(bno);
		replyVO.setReplier(id);
		replyVO.setReply(reply);
		
		mapper.enrollReply(replyVO);
	}
	
//	@Test
	public void testRead() {
		Long rno = 2L;
		ReplyVO reply = mapper.read(rno);
		log.info(reply.toString());
	}
	
//	@Test
	public void testDelete() {
		Long targetRno = 1L;
		mapper.delete(targetRno);
	}
	
//	@Test
	public void testUpdate() {
		Long targetRno=2L;
		ReplyVO reply = mapper.read(targetRno);
		reply.setReply("댓글 수정 완료");
		
		int updateCount = mapper.update(reply);
	    log.info("update count : " + updateCount);
	}
	
//	 자기 게시판에서 5개의 board 글을 선택합니다.
	private Long[] arBno = {7L, 12L, 36L, 38L, 40L};
	
	@Test
	public void testInsert() {
		// 5개의 게시글에 2개씩 댓글을 달아봅시다.
		IntStream.rangeClosed(1, 10).forEach(i -> {
			ReplyVO reply = new ReplyVO();
			
			reply.setBno(arBno[i%5]);
			reply.setReply("댓글 자동 생성" + i);
			reply.setReplier("Robot.A" + i);
			mapper.enrollReply(reply);
		});
	}
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		
		List<ReplyVO> replies = mapper.getListWithPaging(cri, arBno[2]);
		log.info("-------------------reply list-------------------");
		replies.forEach(reply -> log.info(reply.toString()));
	}
}

package com.hilook.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hilook.beans.vo.Criteria;
import com.hilook.beans.vo.ReplyVO;
import com.hilook.services.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController  
@RequiredArgsConstructor
@RequestMapping("/replies/*")
@Slf4j
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
//	댓글 등록 : 브라우저에서 JSON 타입으로 데이터를 전송하면 서버에서는 댓글 처리 결과에 따라 문자열로 결과를 리턴
	@PostMapping(value="/new", consumes="application/json", produces="text/plain; charset=utf-8")
	public ResponseEntity<String> createReply(@RequestBody ReplyVO reply) throws UnsupportedEncodingException {
		int insertCount = 0;
		log.info("ReplyVO : insert --------------------------------");
		log.info(reply.toString());
		insertCount = replyService.enrollReply(reply);
		
		if(insertCount == 1) {
			return new ResponseEntity<>(new String("댓글이 등록되었습니다.".getBytes(), "UTF-8"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	게시글 댓글 전체 조회
	@GetMapping("pages/{bno}/{page}")
	public List<ReplyVO> getList(@PathVariable("bno") Long bno, @PathVariable("page") int page) {
		log.info("getList------------------------------>");
		Criteria cri = new Criteria(page,10);
		log.info(cri.toString());
		return replyService.getList(cri, bno);
	}
	
//	게시글 댓글 조회
	
	@GetMapping("{rno}")
	public ReplyVO get(@PathVariable("rno") Long rno){
		log.info("get----------------------->" + rno);
		return replyService.get(rno);
	}
	
//	댓글 수정
	@RequestMapping(method= {RequestMethod.PATCH}, value= {"/{rno}", "/{rno}/{replier}"}, consumes="application/json", produces="text/plain; charset=utf-8")
	public ResponseEntity<String> modify(
			@RequestBody ReplyVO reply,
			@PathVariable(value="replier", required=false) String replier,
			@PathVariable("rno") Long rno) 
	throws UnsupportedEncodingException {
		
		reply.setRno(rno);
		
		int replyCount = 0;
		
		if(reply.getReplier() == null) {
			reply.setReplier(Optional.ofNullable(replier).orElse("anonymous"));
		}
		
		replyCount = replyService.modify(reply);
		
		if(replyCount == 1) {
			return new ResponseEntity<> (new String("댓글 수정이 완료되었습니다.".getBytes(), "UTF-8"), HttpStatus.OK);
		} else {
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	댓글 삭제
	@DeleteMapping(value="{rno}", produces="text/plain; charset=utf-8")
	public String remove(@PathVariable("rno") Long rno) {
		return replyService.remove(rno) == 1 ? "댓글 삭제가 완료되었습니다." : "댓글 삭제에 실패했습니다."	;
			
	}
}
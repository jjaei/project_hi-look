package com.hilook.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hilook.beans.vo.Criteria;
import com.hilook.beans.vo.ReplyVO;

@Service
public interface ReplyService {
	
	// 댓글 등록
	public int enrollReply(ReplyVO reply);
	public ReplyVO get(Long rno);
	public int modify(ReplyVO reply);
	public int remove(Long rno);
	public List<ReplyVO> getList(Criteria cri, Long bno);
	
}

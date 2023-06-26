package com.hilook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hilook.beans.vo.Criteria;
import com.hilook.beans.vo.ReplyVO;
import com.hilook.mappers.ReplyMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyMapper replyMapper;
	
	@Override
	public int enrollReply(ReplyVO reply) {
		return replyMapper.enrollReply(reply);
	}
	
	@Override
	public ReplyVO get(Long rno) {
		return replyMapper.read(rno);
	}
	
	@Override
	public int modify(ReplyVO reply) {
		return replyMapper.update(reply);
	}
	
	@Override
	public int remove(Long rno) {
		return replyMapper.delete(rno);
	}
	
	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno){
		return replyMapper.getListWithPaging(cri, bno);
	}
}

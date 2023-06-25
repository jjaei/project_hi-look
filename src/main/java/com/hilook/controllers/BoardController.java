package com.hilook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.hilook.beans.vo.BoardVO;
import com.hilook.beans.vo.Criteria;
import com.hilook.beans.vo.PageDTO;
import com.hilook.services.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board/*")
@Slf4j
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	/*
	@GetMapping("/list")
	public void boardList(Model model) {
		log.info("게시판 목록 진입");
		model.addAttribute("list", service.getList());
	}
	*/
	
//	게시글 페이징 처리
	@GetMapping("/list")
	public void boardLisPaging(Criteria cri, Model model) {
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
		
	}
	
	@GetMapping("/write")
	public void boardWrite() {
		log.info("게시글 등록 진입");
	}
	
//	게시글 등록	
	@PostMapping("/write")
	public RedirectView boardWritePost(BoardVO board, RedirectAttributes rttr, MultipartFile file) throws Exception {
		log.info("BoardVO : " + board);
		service.write(board, file);
		
		rttr.addFlashAttribute("result", "write success!");  // 게시글 등록 성공을 위한 일회 알람.
		
		return new RedirectView("list");
	}
	
//	게시글 조회
	@GetMapping("/get")
	public String boardGetPage(@RequestParam("bno") Long bno, Model model) {
	    service.cnt(bno);
	    BoardVO board = service.getPage(bno);
	    model.addAttribute("pageInfo", board);
	    
	    return "board/get";
	}
	
//	게시글 수정 페이지로 이동
	@GetMapping("/modify")
	public void boardModify(Long bno, Model model) {
		model.addAttribute("pageInfo", service.getPage(bno));
	}
	
//	게시글 수정
	@PostMapping("/modify")
	public RedirectView boardModifyPost(BoardVO board, RedirectAttributes rttr, MultipartFile file) throws Exception {
	
		service.modify(board, file);
		
		rttr.addFlashAttribute("result", "modify success!");
	
		return new RedirectView("list");
	}
	
//	게시글 삭제
	@PostMapping("/delete")
	public RedirectView boardDeletePost(Long bno, RedirectAttributes rttr) {
		service.delete(bno);
		rttr.addFlashAttribute("result", "delete success!");
		
		return new RedirectView("list");
	}
}
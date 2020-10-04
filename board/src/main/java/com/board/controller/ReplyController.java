package com.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardVO;
import com.board.domain.ReplyVO;
import com.board.service.ReplyService;

@Controller
@RequestMapping("/reply/*")
public class ReplyController {

	@Inject
	private ReplyService service;

	// 댓글 조회
	@RequestMapping(value = "/replyModify", method = RequestMethod.GET)
	public void getView(@RequestParam("bno") int bno, Model model) throws Exception {

		List<ReplyVO> reply = null;
		reply = service.list(bno);
		model.addAttribute("reply", reply);

	}

	// 댓글 수정
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String postModify(ReplyVO vo) throws Exception {
		service.modify(vo);

		return "redirect:/board/view?bno=" + vo.getBno();
	}

}

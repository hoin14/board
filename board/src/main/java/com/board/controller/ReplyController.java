package com.board.controller;

import java.util.List;

import javax.inject.Inject;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.ReplyVO;
import com.board.service.ReplyService;

@Controller
@RequestMapping("/reply/*")
public class ReplyController {

	@Inject
	private ReplyService service;

	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	// 댓글 작성
	@RequestMapping(value = "/replyWrite", method = RequestMethod.POST)
	public String postreplyWrite(ReplyVO vo, @RequestParam("bno") int bno) throws Exception {
		
		logger.info("post replywrite!");
		
		vo.setBno(bno);
		service.write(vo);

		return "redirect:/board/view?bno=" + vo.getBno();
	}

	// 댓글 수정
	@RequestMapping(value = "/replyModify", method = RequestMethod.GET)
	public void getrView(@RequestParam("rno") int rno, @RequestParam("bno") int bno, Model model) throws Exception {
		
		logger.info("get replymodify!");
		List<ReplyVO> reply = null;
		ReplyVO vo = new ReplyVO();

		vo.setRno(rno);
		vo.setBno(bno);
		logger.info(rno +"-" + bno);
		reply = service.listReply(vo);
		model.addAttribute("reply", reply);
	}

	// 댓글 수정
	@RequestMapping(value = "/replyModify", method = RequestMethod.POST)
	public String postreplyModify(ReplyVO vo) throws Exception {
		
		logger.info("post replymodify!");
		logger.info("1-"+vo.getRno() +"-" + vo.getBno());

		service.modify(vo);

		return "redirect:/board/view?bno=" + vo.getBno();
	}

	// 댓글 삭제
	@RequestMapping(value = "/replyDelete", method = RequestMethod.GET)
	public String getreplyDelete(@RequestParam("rno") int rno, @RequestParam("bno") int bno) throws Exception {

		logger.info("get replydelete!");
		
		ReplyVO vo = new ReplyVO();
		int result = 0;

		vo.setRno(rno);
		vo.setBno(bno);

		try {

			service.delete(vo);
		} catch (Exception e) {

			result = 1;
			e.printStackTrace();
			throw e;
		} finally {
			if (result == 1) {
				JOptionPane.showMessageDialog(null, "삭제하지 못했습니다.");
			}
		}

		return "redirect:/board/view?bno=" + vo.getBno();
	}

}

package com.board.controller;

import java.util.List;

import javax.inject.Inject;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardVO;
import com.board.domain.Page;
import com.board.domain.ReplyVO;
import com.board.service.BoardService;
import com.board.service.ReplyService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Inject
	private BoardService service;

	@Inject
	private ReplyService replyService;

	// 게시물목록
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void getList(Model model) throws Exception {
		List<BoardVO> list = null;
		list = service.list();
		model.addAttribute("list", list);
	}

	// 게시물작성
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void getwrite() throws Exception {

	}

	// 게시물 작성
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String posttWirte(BoardVO vo) throws Exception {
		service.write(vo);
		return "redirect:/board/list";
	}

	// 게시물 조회
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void getView(@RequestParam("bno") int bno, Model model) throws Exception {
		BoardVO vo = service.view(bno);
		model.addAttribute("view", vo);

		// 댓글 조회
		List<ReplyVO> reply = null;
		reply = replyService.list(bno);
		model.addAttribute("reply", reply);

	}

	// 게시물 수정
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void getModify(@RequestParam("bno") int bno, Model model) throws Exception {
		BoardVO vo = service.view(bno);

		model.addAttribute("view", vo);
	}

	// 게시물 수정
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String postModify(BoardVO vo) throws Exception {
		service.modify(vo);

		return "redirect:/board/view?bno=" + vo.getBno();
	}

	// 게시물 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String getDelete(@RequestParam("bno") int bno) throws Exception {

		service.delete(bno);

		return "redirect:/board/list";
	}

	// 게시물 목록 + 페이징 추가
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void getListPage(Model model, @RequestParam("num") int num) throws Exception {

		Page page = new Page();

		page.setNum(num);
		page.setCount(service.count());

		List<BoardVO> list = null;
		list = service.listPage(page.getDisplayPost(), page.getPostNum());

		model.addAttribute("list"	, list);
		model.addAttribute("page", page);
		model.addAttribute("select", num);

	}

	// 게시물 목록 + 페이징 추가 + 검색
	@RequestMapping(value = "/listPageSearch", method = RequestMethod.GET)
	public void getListPageSearch(Model model, @RequestParam("num") int num,
			@RequestParam(value = "searchType", required = false, defaultValue = "title") String searchType,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) throws Exception {

		Page page = new Page();

		page.setNum(num);
		page.setCount(service.searchCount(searchType, keyword));

		// 검색 타입과 검색어
		page.setSearchType(searchType);
		page.setKeyword(keyword);

		List<BoardVO> list = null;
		// list = service.listPage(page.getDisplayPost(), page.getPostNum());
		list = service.listPageSearch(page.getDisplayPost(), page.getPostNum(), searchType, keyword);

		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("select", num);
		
	}

	// 댓글 작성
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public String postreplyWrite(ReplyVO vo) throws Exception {
		replyService.write(vo);

		return "redirect:/board/view?bno=" + vo.getBno();
	}

	// 댓글 수정
	@RequestMapping(value = "/replyModify", method = RequestMethod.GET)
	public void getrView(@RequestParam("rno") int rno, @RequestParam("bno") int bno, Model model) throws Exception {

		List<ReplyVO> reply = null;
		ReplyVO vo = new ReplyVO();

		vo.setRno(rno);
		vo.setBno(bno);

		reply = replyService.listReply(vo);
		model.addAttribute("reply", reply);
	}

	// 댓글 수정
	@RequestMapping(value = "/replyModify", method = RequestMethod.POST)
	public String postreplyModify(ReplyVO vo) throws Exception {
		replyService.modify(vo);

		return "redirect:/board/view?bno=" + vo.getBno();
	}

	// 댓글 삭제
	@RequestMapping(value = "/replyDelete", method = RequestMethod.GET)
	public String getreplyDelete(@RequestParam("rno") int rno, @RequestParam("bno") int bno) throws Exception {

		ReplyVO vo = new ReplyVO();
		int result = 0;

		vo.setRno(rno);
		vo.setBno(bno);

		try {

			replyService.delete(vo);
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
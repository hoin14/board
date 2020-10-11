package com.board.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.MemberVO;
import com.board.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Inject
	private MemberService service;

	// 회원가입 GET
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void getRegister() throws Exception {
		logger.info("get register");
	}

	// 회원가입 POST
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String postRegister(MemberVO vo) throws Exception {
		logger.info("post register");
		service.register(vo);

		return "redirect:/";
	}

	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		logger.info("post login");

		HttpSession session = req.getSession();
		MemberVO login = service.login(vo);

		if (login == null) {
			session.setAttribute("member", null);
			session.setAttribute("msg", false);
		} else {
			session.setAttribute("member", login);
		}

		return "redirect:/";
	}

	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		logger.info("get logout");

		session.invalidate();

		return "redirect:/";
	}

	// 회원정보 수정 GET
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void getRegisterUpdate() throws Exception {
		logger.info("get RegisterUpdate");
	}

	// 회원정보 수정 POST
	@RequestMapping(value = "/memberUpdate", method = RequestMethod.POST)
	public String postRegisterUpdate(MemberVO vo, HttpSession session) throws Exception {
		logger.info("post RegisterUpdate");

		service.memberUpdate(vo);
		session.invalidate();

		return "redirect:/";
	}

	// 회원정보 삭제 GET
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public void getMemberDelete() throws Exception {
		logger.info("get MemberDelete");
	}

	// 회원정보 삭제 POST
	@RequestMapping(value = "/memberDelete", method = RequestMethod.POST)
	public String postMemberDelete(MemberVO vo, HttpSession session) throws Exception {
		logger.info("post MemberDelete");

		MemberVO member = (MemberVO) session.getAttribute("member");
		String sessionPass = member.getUserPass();
		String voPass = vo.getUserPass();

		logger.info(sessionPass + "&&" + voPass);
		if (!sessionPass.equals(voPass)) {
			return "redirect:/member/delete";
		}

		service.memberDelete(vo);
		session.invalidate();

		return "redirect:/";
	}

	// 패스워드 체크(Ajax)
	@ResponseBody
	@RequestMapping(value = "/passChk", method = RequestMethod.POST)
	public int passChk(MemberVO vo) throws Exception {
		logger.info("post passChk");

		int result = service.passChk(vo);
		logger.info("id:" + vo.getUserId() + "pass:" + vo.getUserPass() + "result:" + result);
		return result;

	}

}

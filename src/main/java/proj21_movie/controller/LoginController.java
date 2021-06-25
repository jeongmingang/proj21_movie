package proj21_movie.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import proj21_movie.dto.AuthInfo;
import proj21_movie.dto.LoginCommand;
import proj21_movie.dto.Member;
import proj21_movie.dto.RegisterRequest;
import proj21_movie.exception.WrongIdPasswordException;
import proj21_movie.service.AuthService;
import proj21_movie.service.LoginService;
import proj21_movie.service.MemberService;
import proj21_movie.service.impl.MemberServiceImpl;
import proj21_movie.validator.LoginCommandValidator;

@Controller
public class LoginController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberServiceImpl service;

	@Autowired
	private AuthService authService;

	@Autowired
	private LoginService logService;

	// 로그인 화면 연결(성공)
	@RequestMapping("/login")
	public String login() {
		return "login/login";
	}

	// 임시 로그인성공 화면 연결(성공)
	@RequestMapping("/loginSuccess")
	public String loginSuccess() {
		return "login/loginSuccess";
	}

	// 임시 로그인실패(성공)
	@RequestMapping("/loginfail")
	public String loginfail() {
		return "login/loginfail";
	}

	// 로그인(시도중)
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginPOST(HttpServletRequest request, Member member, RedirectAttributes rttr) throws Exception {

        System.out.println("login 메서드 진입");
        System.out.println("전달된 데이터 : " + member);

		HttpSession session = request.getSession();
		Member memb = logService.LoginCommand(member);

		if (memb == null) { // 일치하지 않는 아이디, 비밀번호 입력 경우

			int result = 0;
			rttr.addFlashAttribute("result", result);
			return "redirect:/loginfail";

		}

		session.setAttribute("member", memb); // 일치하는 아이디, 비밀번호 경우 (로그인 성공)

		return "redirect:/loginSuccess";
	}
}
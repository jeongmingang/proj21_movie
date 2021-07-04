package proj21_movie.controller;

import java.io.IOException;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import proj21_movie.dto.Member;
import proj21_movie.service.MemberService;

@Controller
public class FindIDPWController {

	@Autowired
	private MemberService service;
	
	@Autowired
	private JavaMailSender mailSender;

	// 찾기 메인
	@RequestMapping("/find_main")
	public String findmain() {
		return "login/find_main";
	}

	// 아이디 찾기 접속(성공)
	@RequestMapping("/find_ID")
	public String findid() {
		return "login/find_ID";
	}

	// 패스워드 찾기 접속(성공)
	@RequestMapping("/find_PW")
	public String findpw() {
		return "login/find_PW";
	}
	
	// 패스워드 찾기 코드(성공)
	@RequestMapping("/find_PW_code")
	public String findpw_code() {
		return "login/find_PW_code";
	}
	
	// 패스워드 찾기 비번바꾸기(성공)
	@RequestMapping("/find_PW_change")
	public String findpw_change() {
		return "login/find_PW_change";
	}
	
		

	// 아이디 찾기 실행
	@RequestMapping(value = "find_id", method = RequestMethod.POST)
	public String findIdAction(Member member, Model model) {
		Member memb = service.findId(member);

		if (memb == null) {
			model.addAttribute("check", 1);
		} else {
			model.addAttribute("check", 0);
			model.addAttribute("memEmail", memb.getMemEmail());
		}
		return "login/find_ID";
	}

	// 비밀번호 찾기 실행(비로그인) 작성중
	// 인증번호 생성
	@RequestMapping(value = "/find_PW_code.do")
	public ModelAndView find_PW_code(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String memEmail = (String) request.getParameter("memEmail");
		String memName = (String) request.getParameter("memName");

		Member member = service.selectMemberBymemEmail(memEmail);

		if (member != null) {
			Random r = new Random();
			int num = r.nextInt(999999); // 랜덤난수설정

			if (member.getMemName().equals(memName)) {
				session.setAttribute("memEmail", member.getMemEmail());

				String from = "passtest950@gmail.com"; // 보내는사람
				String to = memEmail; // 받는사람
				String subject = "[MOVIE BOX] 비밀번호 변경용 인증번호 메일입니다.";
				String text = System.getProperty("line.separator") + "안녕하세요 회원님" + System.getProperty("line.separator")
						+ "MOVIE BOX 비밀번호 변경용 인증번호는 " + num + " 입니다." + System.getProperty("line.separator"); //

				try {
					
					MimeMessage message = mailSender.createMimeMessage();
					MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");

					messageHelper.setFrom(from);
					messageHelper.setTo(to);
					messageHelper.setSubject(subject);
					messageHelper.setText(text);

					mailSender.send(message);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				ModelAndView mv = new ModelAndView();
				mv.setViewName("login/find_PW_code");
				mv.addObject("num", num);
				return mv;

			} else {
				ModelAndView mv = new ModelAndView();
				mv.setViewName("login/find_PW");
				return mv;

			}
		} else {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("login/find_PW");
			return mv;
		}
	}
	
	// 인증번호 확인
	@RequestMapping(value = "pw_set.me", method = RequestMethod.POST)
	public String pw_set(@RequestParam(value="email_injeung") String email_injeung,
				@RequestParam(value = "num") String num) throws IOException{
			
			if(email_injeung.equals(num)) {
				return "login/find_PW_change";
			}
			else {
				return "login/find_PW";
			}
	}
	
	
	// 비밀번호 업데이트
	@RequestMapping(value = "find_PW_change.do", method = RequestMethod.POST)
	public String find_PW_change(Member member, HttpSession session) throws IOException{
		int result = service.modifyMember(member);

		if(result == 1) {
			return "login/login";
		}
		else {
			System.out.println("pw_update"+ result);
			return "login/find_PW_change";
		}
}
}

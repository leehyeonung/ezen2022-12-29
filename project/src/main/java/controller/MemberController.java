package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.MemberVO;
import service.MemberService;
import service.MemberServiceImpl;


@WebServlet("/mem/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log=LoggerFactory.getLogger(MemberController.class);
    private RequestDispatcher rdp;
    private String destPage;
    private int isOk;
    private MemberService msv;
   
    public MemberController() {
    	msv=new MemberServiceImpl();
    }


	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=UTF-8");
		
		String uri=req.getRequestURI(); //mem/list
		String path=uri.substring(uri.lastIndexOf("/")+1);
		
		log.info(">>>URI"+uri);
		log.info(">>>PATH"+path);
		
		switch (path) {
		case "login":
			destPage="/member/login.jsp";
			break;
			
		case "login_auth": //실제 로그인이 일어나는 케이스
			try {
				MemberVO mvo = msv.login(
						new MemberVO(
								req.getParameter("email"),
								req.getParameter("pwd")
								));
				log.info("login 객체 input");
				if(mvo!=null) {
					HttpSession ses=req.getSession();
					ses.setAttribute("ses", mvo);
					ses.setMaxInactiveInterval(10*60);
				}else {
					req.setAttribute("msg_login", 0);
				}
				destPage="/";
				
			} catch (Exception e) {
				
			}
			break;
			
		case "join":
			destPage="/member/join.jsp";
			break;
			
		case "register": //회원가입 DB 저장
			isOk=msv.register(new MemberVO(
					req.getParameter("email"),
					req.getParameter("pwd"),
					req.getParameter("nick_name")
					));
			log.info(">>> join>"+(isOk>0? "ok":"fail"));
			destPage="login";
			break;
			
		case "logout":
			//연결된 세션이 있다면 해당 세션을 가져오기
			try {
				HttpSession ses=req.getSession();
				ses.invalidate(); //세션 끊기
				//로그인정보 email을 주고 로그인 시간 update
				isOk=msv.lastLogin(req.getParameter("email"));
				log.info(">>>lastjoin>"+(isOk>0? "ok":"fail"));
				destPage="/";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "list":
			
			log.info("list check 1");
			List<BoardVO> list=msv.getList();
			System.out.println(list.size());
			log.info("list check 4");
			System.out.println(list.size());
			req.setAttribute("list", list);
			destPage="/member/list.jsp";
			
			break;
			
		case "detail":
			log.info("list check 1");
			
			MemberVO mvo = msv.getDetail(req.getParameter("email"));
		
			log.info("detail check 4");
			req.setAttribute("mvo", mvo);
			destPage="/member/detail.jsp";
		
			break;
		
		case "modify":
			String email=req.getParameter("email");
			MemberVO mvo2=msv.getDetail(email);
			req.setAttribute("mvo", mvo2);
			destPage="/member/modify.jsp";
			break;
			
		case "update":
			String email2=req.getParameter("email");
			String pwd=req.getParameter("pwd");
			String nick_name=req.getParameter("nick_name");
			MemberVO mvo3=new MemberVO(email2,pwd,nick_name);
			log.info("detail check 1");
			isOk = msv.update(mvo3);
			log.info("update:"+(isOk>0?"성공":"실패"));
			destPage="list";
			
		case "remove":
			String email3=req.getParameter("email");
			
			isOk = msv.remove(email3);
			log.info("delete:"+(isOk>0?"성공":"실패"));
			destPage="list";
			
		}
		//목적지 주소 값 세팅
		rdp=req.getRequestDispatcher(destPage);
		//정보싣고 보내기.
		rdp.forward(req, res);
		
	}


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		service(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		service(req, res);
	}

}

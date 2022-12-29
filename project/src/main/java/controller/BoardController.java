package controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.MemberVO;
import domain.PagingVO;
import handler.FileHandler;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnails;
import service.BoardService;
import service.BoardServiceImpl;
import service.MemberService;

@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log=LoggerFactory.getLogger(MemberController.class);
    private RequestDispatcher rdp;
    private String destPage;
    private int isOk;
    private BoardService bsv;
    private String savePath; //파일경로를 저장할 변수
    private final String UTF8="utf-8"; //인코딩 설정시 필요함
       
    
    public BoardController() {
    	bsv=new BoardServiceImpl();
    }

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=UTF-8");
		
		String uri=req.getRequestURI(); //mem/list
		String path=uri.substring(uri.lastIndexOf("/")+1);
		
		log.info(">>>URI"+uri);
		log.info(">>>PATH"+path);
		
		switch(path) {
		//bno,title,writer,reg_date,read_count
		
		case "register":
			destPage="/board/register.jsp";
			System.out.println(">>path"+path);
			log.info("path>>"+path);
			break;
			
		case "insert":
			try {
				//파일을 저장할 물리적이 경로설정(업로드 할때 설정)
				savePath=getServletContext().getRealPath("/_fileUpload");
				File fileDir=new File(savePath);
				log.info("저장위치"+savePath);
				
				DiskFileItemFactory fileItemFactory=new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir); //저장할 위치를 file 객체로 지정 
				fileItemFactory.setSizeThreshold(2*1024*1024); //저장을 위한 임시 메모리 저장 용량 설정 : byte단위
				
				BoardVO bvo = new BoardVO();
				// multipart/form-data 형식으로 넘어온 request 객체를 다루기 쉽게 변환해주는 역할
				ServletFileUpload fileUpload=new ServletFileUpload(fileItemFactory);
				
				List<FileItem> itemList=fileUpload.parseRequest(req);
				for (FileItem item:itemList) {
					switch(item.getFieldName()) {
					
					case "title":
						bvo.setTitle(item.getString(UTF8)); //인코딩 형식을 담아서 변환
						break;
					case "writer":
						bvo.setWriter(item.getString(UTF8));
						break;
					case "content":
						bvo.setContent(item.getString(UTF8));
						break;
					case "image_file":
						//이미지가 있는지 없는지 체크
						if(item.getSize()>0) { //데이터의 크기를 바이트 단위로 리턴
							String fileName = item.getName() //경로를 포함한 파일이름 ~~~~/dog.jsp
									.substring(item.getName().lastIndexOf("/")+1); //파일 이름만 분리
							//시스템의 현재 시간 _dog.jsp
							fileName=System.currentTimeMillis()+"_"+fileName;
							File uploadfilePath=new File(fileDir+File.separator+fileName);
							log.info("파일 경로+이름 : "+uploadfilePath);
							
							//저장
							try {
								item.write(uploadfilePath); //자바 객체를 디스크에 쓰기
								bvo.setImage_file(fileName);
								
								//썸내일 작업 : 리스트 페이지에서 트래픽 과다사용
								Thumbnails.of(uploadfilePath)
								.size(75, 75)
								.toFile(new File(fileDir+File.separator+"th_"+fileName));
								
							} catch (Exception e) {
								log.info(">>>file writer on disk fail");
								e.printStackTrace();
							}
						}
						break;
						
					}
				}
				isOk=bsv.register(bvo);
				log.info(">>>insert>"+(isOk>0? "ok":"fail"));
								
//				String title=req.getParameter("title");
//				String writer=req.getParameter("writer");
//				String content=req.getParameter("content");
//				log.info("insert check 1");
//				isOk = bsv.register(new BoardVO(title, writer, content));
//				log.info(isOk>0?"성공":"실패");
//				
				destPage="pageList";
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		case "list":
	         try {
	         log.info("list check 1");
	         List<BoardVO> list=bsv.getList();
	         System.out.println(list.size());
	         log.info("list check 4");
	         req.setAttribute("list", list);
	         destPage="/board/list.jsp";
	         }catch(Exception e){
	            e.printStackTrace();
	         }
	         break;
			
		case "list2":
			try {
				HttpSession ses=req.getSession();
				MemberVO mvo=(MemberVO)ses.getAttribute("ses");
				String email=mvo.getEmail();
				List<BoardVO> list=bsv.getMyList(email);
				req.setAttribute("list", list);
				destPage="/board/list.jsp";
				
			}catch(Exception e){
				e.printStackTrace();
			}
			break;
			
		case "pageList":
			try {
				PagingVO pgvo=new PagingVO();
				int totCount=bsv.getPageCnt(); //전체 카운트 호출
				List<BoardVO> list=bsv.getListPage(pgvo); //limit 이용한 한페이지 리스트 호출
				PagingHandler ph=new PagingHandler(pgvo, totCount);
				req.setAttribute("list", list); // 한페이지에 나와야 하는 리스트 보내기
				req.setAttribute("pgh", ph); //페이지 정보 보내기
				destPage="/board/list.jsp";
				
			} catch (Exception e) {
				log.info("paging error");
				e.printStackTrace();
			}
			break;
			
		case"page":
			System.out.println("1");
			try {
				int pageNo=Integer.parseInt(req.getParameter("pageNo"));
				int qty=Integer.parseInt(req.getParameter("qty"));
				PagingVO pgvo=new PagingVO(pageNo,qty);
				System.out.println("2");
				
				int totCount=bsv.getPageCnt(); //전체 카운트 호출
				System.out.println("123123"+totCount);
				List<BoardVO> list=bsv.getListPage(pgvo); //limit 이용한 한페이지 리스트 호출
				PagingHandler ph=new PagingHandler(pgvo, totCount);
				req.setAttribute("list", list); // 한페이지에 나와야 하는 리스트 보내기
				req.setAttribute("pgh", ph); //페이지 정보 보내기
				destPage="/board/list.jsp";
			} catch (Exception e) {
				log.info("subPage error");
				e.printStackTrace();
			}
			break;
			
		case "detail":
			try {
			log.info("list check 1");
			int bno= Integer.parseInt(req.getParameter("bno"));	

			isOk =bsv.countUp(bno);
			BoardVO bvo2 = bsv.getDetail(bno);
			req.setAttribute("bvo", bvo2);
			log.info("detail check 4");
			destPage="/board/detail.jsp";
			}catch (Exception e) {
				log.info("subPage error");
				e.printStackTrace();
			}
			break;
			
		case "modify":
	
				int bno2=Integer.parseInt(req.getParameter("bno"));
				log.info("detail check 1");
				BoardVO bvo3=bsv.getDetail(bno2);
				log.info("detail check 2");
				req.setAttribute("bvo", bvo3);
				destPage="/board/modify.jsp";
			
			break;
			
		case "update":
			
			try {
				savePath=getServletContext().getRealPath("/_fileUpload");
				File fileDir=new File(savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);
				fileItemFactory.setSizeThreshold(2*1024*1024);
				
				BoardVO bvo = new BoardVO();
				
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				log.info(">>update 준비>>");
				
				List<FileItem> itemList = fileUpload.parseRequest(req);
				
				String old_file=null;
				
				for(FileItem item : itemList) {
					
					switch(item.getFieldName()) {
					case "bno":
						bvo.setBno(Integer.parseInt(item.getString(UTF8)));
						break;
					case "title":
						bvo.setTitle(item.getString(UTF8));
						break;
					case "content":
						bvo.setContent(item.getString(UTF8));
						break;
					case "image_file":
						//old_file
						old_file=item.getString(UTF8);
						break;
					case "new_file":
						if(item.getSize()>0) { //새로운 파일이 등록 됨.
							if(old_file != null) {
								//파일 핸들러 작업 (기존 파일을 삭제)
								FileHandler fileHandler = new FileHandler();
								isOk = fileHandler.deleteFile(old_file,savePath);
							}
							//경로가 포함된 전체경로와 파일이름 생성
							String fileName =item.getName().substring(item.getName().lastIndexOf(File.separator)+1);
							log.info("new File Name : "+fileName);
							//실제 저장될 파일이름
							fileName=System.currentTimeMillis()+"_"+fileName;
							File uploadFilePath = new File(fileDir+File.separator+fileName);
							try {
								item.write(uploadFilePath); //자바 객체를 디스크에 쓰기
								bvo.setImage_file(fileName);
								log.info(">>upload img_file"+(bvo.getImage_file()));
								//썸네일 작업
								Thumbnails.of(uploadFilePath)
								.size(75, 75)
								.toFile(new File(fileDir+File.separator+"th_"+fileName));
								
							}catch(Exception e) {
								log.info(">> File Write on disk Fail");
								e.printStackTrace();
							}
						}else { //새로운 파일이 없다면 기존파일을 bvo 객체에 담기
							bvo.setImage_file(old_file);
						}
						break;
					}
				}
				isOk=bsv.update(bvo);
				log.info(">> update>"+((isOk>0)?"성공":"실패"));
			
//			int bno3=Integer.parseInt(req.getParameter("bno"));
//			String title2=req.getParameter("title");
//			String content2=req.getParameter("content");
//			BoardVO bvo4=new BoardVO(bno3,title2,content2);
//			log.info("detail check 1");
//			isOk = bsv.update(bvo4);
//			log.info("update:"+(isOk>0?"성공":"실패"));
			destPage="pageList";
			
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		case "remove":
			try {
				
				int bno=Integer.parseInt(req.getParameter("bno"));
				//image_file Name 불러오기
				String imageFileName=bsv.getFileName(bno); //삭제할 파일 이름 호출
				FileHandler fileHandler=new FileHandler();
				savePath=getServletContext().getRealPath("/_fileUpload"); 
				
				isOk=fileHandler.deleteFile(imageFileName,savePath);
				
				log.info("fileDelete >> "+(isOk>0? "ok":"fail"));
				isOk=bsv.remove(bno);
				
				
				
				destPage="pageList";
			} catch (Exception e) {
				// TODO: handle exception
			}
			
//			int bno4=Integer.parseInt(req.getParameter("bno"));
//			log.info("detail check 1");
//			isOk = bsv.remove(bno4);
//			log.info("delete:"+(isOk>0?"성공":"실패"));
//			destPage="pageList";	
			
		case"myList":
			
			
		}
		rdp=req.getRequestDispatcher(destPage);
		//정보싣고 보내기.
		rdp.forward(req, res);
	}


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		service(req, res);	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		service(req, res);	}

}

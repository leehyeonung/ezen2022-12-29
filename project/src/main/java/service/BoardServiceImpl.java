package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {
	private static final Logger log= LoggerFactory.getLogger(BoardServiceImpl.class);
	private BoardDAO bdao; //repository->interface로 생성
	
	public BoardServiceImpl() {
		bdao=new BoardDAOImpl(); //repository -> class로 생성
	}

	@Override
	public List<BoardVO> getList() {
		// TODO Auto-generated method stub
		log.info("insert check 2");
		return bdao.selectList();
	}

	@Override
	public BoardVO getDetail(int bno) {
		// TODO Auto-generated method stub
		log.info("insert check 2");
		return bdao.selectOne(bno);
	}

	@Override
	public int update(BoardVO bvo) {
		// TODO Auto-generated method stub
		return bdao.selectOne(bvo);
	}

	@Override
	public int remove(int bno) {
		// TODO Auto-generated method stub
		CommentServiceImpl csv=new CommentServiceImpl();
		
		int isOk=csv.removeAll(bno);
		return bdao.remove(bno);
	}

	@Override
	public int countUp(int bno) {
		// TODO Auto-generated method stub
		return bdao.countUp(bno);
	}

	@Override
	public int register(BoardVO boardVO) {
		// TODO Auto-generated method stub
		return bdao.insert(boardVO);
	}

	@Override
	public int getPageCnt() {
		// TODO Auto-generated method stub
		return bdao.selectCount();
	}

	@Override
	public List<BoardVO> getListPage(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.selectList(pgvo);
	}

	@Override
	public List<BoardVO> getMyList(String email) {
		// TODO Auto-generated method stub
		return bdao.selectList2(email);
	}

	@Override
	public String getFileName(int bno) {
		// TODO Auto-generated method stub
		return bdao.selectImg(bno);
	}









	

}

package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {
	private static final Logger log=LoggerFactory.getLogger(BoardDAOImpl.class);
	//DB와 연결
	private SqlSession sql;
	private final String NS="BoardMapper."; //namespqce설정
	
	public BoardDAOImpl() {
		new DatabaseBuilder(); //orm -> db연결 클래스 객체 생성
		sql=DatabaseBuilder.getFactory().openSession();
	}
	
	@Override
	public List<BoardVO> selectList() {
		// TODO Auto-generated method stub
		log.info("insert check 3");
		return sql.selectList(NS+"list");
	}
	
	@Override
	public BoardVO selectOne(int bno) {
		log.info("insert check 3");
		return sql.selectOne(NS+"detail",bno);
		
	}

	@Override
	public int selectOne(BoardVO bvo) {
		// TODO Auto-generated method stub
		log.info("insert check 3");
		
		int isOk=sql.update(NS+"update",bvo);
		if(isOk>0) {
			sql.commit(); //실제 값이 1개가 완료가 되었따면 실제 commit
		}
		return isOk;
	}

	@Override
	public int remove(int bno) {
		// TODO Auto-generated method stub
		int isOk=sql.delete(NS+"remove",bno);
		if(isOk>0) {
			sql.commit(); //실제 값이 1개가 완료가 되었따면 실제 commit
		}
		return isOk;

	}

	@Override
	public int countUp(int bno) {
		// TODO Auto-generated method stub
		int isOk=sql.update(NS+"countUp",bno);
		if(isOk>0) {
			sql.commit(); //실제 값이 1개가 완료가 되었따면 실제 commit
		}
		return isOk;

	}

	@Override
	public int insert(BoardVO boardVO) {
		// TODO Auto-generated method stub
		log.info("insert check 3");
		int isOk=sql.insert(NS+"add",boardVO);
		if(isOk>0) {
			sql.commit(); //실제 값이 1개가 완료가 되었따면 실제 commit
		}
		return isOk;
	}

	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return sql.selectOne(NS+"cnt");
	}

	@Override
	public List<BoardVO> selectList(PagingVO pgvo) {
		
		return sql.selectList(NS+"pagingList",pgvo);
	}

	@Override
	public List<BoardVO> selectList2(String email) {
		// TODO Auto-generated method stub
		log.info("insert check 3");
		return sql.selectList(NS+"list2",email);
	}

	@Override
	public String selectImg(int bno) {
		// TODO Auto-generated method stub
		return sql.selectOne(NS+"imgFile",bno);
	}










	
}
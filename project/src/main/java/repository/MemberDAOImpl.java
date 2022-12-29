package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.MemberVO;
import orm.DatabaseBuilder;
import service.MemberServiceImpl;

public class MemberDAOImpl implements MemberDAO {

	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	private SqlSession sql;
	private final String NS="MemberMapper.";
	
	public MemberDAOImpl() {
		new DatabaseBuilder();
		sql=DatabaseBuilder.getFactory().openSession();
	}
		
			
	@Override
	public MemberVO selectOne(MemberVO mvo) {
		log.info("login check 3");
		return sql.selectOne(NS+"login",mvo);
	}




	@Override
	public int insert(MemberVO mvo) {
		log.info("login check 3");
		int isOk=sql.insert(NS+"reg",mvo);
		if(isOk>0) sql.commit();
		return isOk;
	}


	@Override
	public int update(String email) {
		log.info("login check 3");
		int isOk = sql.update(NS+"last",email);
		if(isOk>0) sql.commit();
		return isOk;

	}


	@Override
	public List<BoardVO> selectList() {
		log.info("login check 3");
		return sql.selectList(NS+"list");
	}


	@Override
	public MemberVO selectDetail(String email) {
		// TODO Auto-generated method stub
		return  sql.selectOne(NS+"detail",email);
	}


	@Override
	public int selectUpdate(MemberVO mvo) {
		// TODO Auto-generated method stub
		return sql.update(NS+"update",mvo);
	}


	@Override
	public int selectRemove(String email) {
		// TODO Auto-generated method stub
		return sql.delete(NS+"remove",email);
	}

}

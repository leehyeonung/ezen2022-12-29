package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.MemberVO;
import repository.MemberDAO;
import repository.MemberDAOImpl;

public class MemberServiceImpl implements MemberService {
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	private MemberDAO mdao;
	
	public MemberServiceImpl() {
		mdao=new MemberDAOImpl();
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		log.info("login check 2");	
		return mdao.selectOne(mvo);
	}

	@Override
	public int register(MemberVO mvo) {
		log.info("login check 2");	
		return mdao.insert(mvo);
	}

	@Override
	public int lastLogin(String email) {
		log.info("lastLogin check 2");
		return mdao.update(email);
	}

	@Override
	public List<BoardVO> getList() {
		// TODO Auto-generated method stub
		log.info("lastLogin check 2");
		return mdao.selectList();
	}

	@Override
	public MemberVO getDetail(String email) {
		log.info("lastLogin check 2");
		return mdao.selectDetail(email);
	}

	@Override
	public int update(MemberVO mvo) {
		// TODO Auto-generated method stub
		return mdao.selectUpdate(mvo);
	}

	@Override
	public int remove(String email) {
		// TODO Auto-generated method stub
		return mdao.selectRemove(email);
	}


	
	
}

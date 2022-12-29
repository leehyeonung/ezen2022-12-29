package repository;

import java.util.List;

import domain.BoardVO;
import domain.MemberVO;

public interface MemberDAO {

	MemberVO selectOne(MemberVO mvo);

	int insert(MemberVO mvo);

	int update(String email);

	List<BoardVO> selectList();

	MemberVO selectDetail(String email);

	int selectUpdate(MemberVO mvo);

	int selectRemove(String email);


}

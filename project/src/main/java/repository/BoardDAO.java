package repository;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardDAO {

	List<BoardVO> selectList();

	BoardVO selectOne(int bno);

	int selectOne(BoardVO bvo);

	int remove(int bno);

	int countUp(int bno);

	int insert(BoardVO boardVO);

	int selectCount();

	List<BoardVO> selectList(PagingVO pgvo);

	List<BoardVO> selectList2(String email);

	String selectImg(int bno);



	
}

package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import orm.DatabaseBuilder;
import service.BoardServiceImpl;

public class CommentDAOImpl implements CommentDAO {
	
	private static final Logger log= LoggerFactory.getLogger(BoardServiceImpl.class);
	private SqlSession sql;
	private final String NS = "CommentMapper.";
	private int isOk;
   
   public CommentDAOImpl() {
      new DatabaseBuilder();
      sql = DatabaseBuilder.getFactory().openSession();
   }

@Override
public int insert(CommentVO cvo) {
	log.info(">>>comment>post>check3");
	isOk=sql.insert(NS+"add",cvo);
	if(isOk>0)sql.commit();
	return isOk;
}

@Override
public List<CommentVO> selectList(int bno) {
	return sql.selectList(NS+"list",bno);
}

@Override
public int delete(int cno) {
	log.info(">>>comment>remove>check3");
	isOk=sql.delete(NS+"del",cno);
	if(isOk>0)sql.commit();
	return isOk;
}

@Override
public int update(CommentVO cvo) {
	log.info(">>>comment>update>check3");
	isOk=sql.update(NS+"mod",cvo);
	if(isOk>0)sql.commit();
	return isOk;
	
}

@Override
public int remove(int bno) {
	// TODO Auto-generated method stub
	log.info(">>>comment>remove>check3");
	isOk=sql.delete(NS+"delImg",bno);
	if(isOk>0)sql.commit();
	return isOk;
}


   
   
}
package gdu.diary.service;

import java.sql.Connection;
import java.sql.SQLException;

import gdu.diary.dao.ADao;
import gdu.diary.dao.BDao;
import gdu.diary.util.DBUtil;

public class ABService {
	
	private DBUtil dbUtil;
	private ADao aDao;
	private BDao bDao;
	
	public void insert() {
		
		this.dbUtil = new DBUtil();
		this.aDao = new ADao();
		this.bDao = new BDao();
		
		Connection conn = null;
		
		try {
			
			conn = this.dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			aDao.insert(conn);
			bDao.insert(conn);
			
			conn.commit();
			
		} catch (SQLException e) {
			
			try {
				
				conn.rollback();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			
			}
			e.printStackTrace();
			
		} finally {
			dbUtil.close(conn, null, null);
			
		}
		
	}

}

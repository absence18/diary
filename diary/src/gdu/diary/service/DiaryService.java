package gdu.diary.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gdu.diary.dao.TodoDao;
import gdu.diary.util.DBUtil;
import gdu.diary.vo.Todo;

public class DiaryService {
	
	private DBUtil dbUtil;
	private TodoDao todoDao;
	
	public Map<String, Object> getDiary(int memberNo, String targetYear, String targetMonth) {
		
		// 타겟 년, 월, 일(날짜)
		// 타겟의 1일(날짜)
		// 타겟의 마지막일의 숫자 -> endDay
		
		// startBlank + endDay + (7-(startBlank + endDay)%7)
		// 전체 셀의 갯수(마지막일의 숫자보다는 크고 7로 나누어 떨어져야 한다)
		// 앞의 빈셀의 갯수 -> startBlank
		// 이번달 숫자가 나와야 할 셀의 갯수(1~마지막날짜)
		// 뒤의 빈셀의 갯수 -> endBlank -> (startBlank + endDay)%7 
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Calendar target = Calendar.getInstance();
		
	
		// 이 코드가 밑의 주석 처리 부분을 자동으로 하게 해줌..
		if(targetYear != null) {
			target.set(Calendar.YEAR, Integer.parseInt(targetYear));
		}
		if(targetMonth != null) {
			// 두번째 인수값이 -1이면 target의 년을 -1하고 월은 11값(12월)이 들어간다.
			// 두번째 인수값이 12이면 target의 년을 +1하고 월은 0값(1월)이 들어간다.
			target.set(Calendar.MONTH, Integer.parseInt(targetMonth));
		}
	
		
	/*
	 * target.set(Calendar.MONTH, Integer.parseInt(targetMonth)); 직접구현 하면 밑의 내용
	 
		int numTargetMonth = 0;
		int numTargetYear = 0;
		
		if(targetMonth != null && targetYear != null) {
			
			numTargetYear = Integer.parseInt(targetYear);
			numTargetMonth = Integer.parseInt(targetMonth);
			
			if(numTargetMonth == 0) {
				
				numTargetYear = numTargetYear - 1;
				numTargetMonth = 12;
				
			} else if(numTargetMonth == 13) {
				
				numTargetYear = numTargetYear + 1;
				numTargetMonth = 1;
				
			}
			
			target.set(Calendar.YEAR, numTargetYear);
			target.set(Calendar.MONTH, numTargetMonth-1);
			
		}
		
	*/
		
		target.set(Calendar.DATE, 1);
		// target월의 1숫자앞에 와야할 빈셀의 갯수
		int startBlank = target.get(Calendar.DAY_OF_WEEK) - 1;
		// target월의 마지막 날짜
		int endDay = target.getActualMaximum(Calendar.DATE);
		int endBlank = 0;
		if ((startBlank + endDay) % 7 != 0) {
			endBlank = 7 - (startBlank + endDay)%7;
		}
		
		// int totalCell = startBlank + endDay + endBlank; 
		
		map.put("targetYear", target.get(Calendar.YEAR));
		map.put("targetMonth", target.get(Calendar.MONTH));
		map.put("startBlank", startBlank);
		map.put("endDay", endDay);
		map.put("endBlank", endBlank);
		
		
		// 2. targetYear, targetMonth(0이면 1월, 1이면 2월)에 해당하는 todo목록 가져와서 추가
		
		this.dbUtil = new DBUtil();
		this.todoDao = new TodoDao();
		
		List<Todo> todoList = null;
		List<Map<String, Object>> ddayList = null;
		
		Connection conn = null;		
		
		try {
			
			conn = this.dbUtil.getConnection();
			todoList = this.todoDao.selectTodoListByDate(conn, memberNo, target.get(Calendar.YEAR), target.get(Calendar.MONTH)+1);
			ddayList = this.todoDao.selectTodoDdayList(conn, memberNo);
			conn.commit();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		map.put("todoList", todoList);
		map.put("ddayList", ddayList);
		
		return map;
	}
	
}

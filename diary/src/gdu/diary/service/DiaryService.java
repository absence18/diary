package gdu.diary.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DiaryService {
	
	public Map<String, Object> getDiary(String targetYear, String targetMonth) {
		
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
		map.put("targetMonth", target.get(Calendar.MONTH)+1);
		map.put("startBlank", startBlank);
		map.put("endDay", endDay);
		map.put("endBlank", endBlank);
		
		return map;
	}
}

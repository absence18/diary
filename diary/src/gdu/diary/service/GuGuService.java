package gdu.diary.service;

public class GuGuService {
	
	public String getGuGu() {
		
		StringBuffer sb = new StringBuffer();
		
		for(int i=2; i<10; i+=1) {
			for(int j=1; j<10; j+=1) {
				sb.append(i + "*" + j + "=" + i*j + "\t");
				
			}
			sb.append("\n");
		}
		return null;
	}

}

package gdu.diary.vo;

public class Todo {
	
	private int todoNo;
	private int todoMember;
	private int memberNo;
	private String todoDate;
	private String todoTitle;
	private String todoContent;
	private String todoFontColor;
	private String todoAddDate;
	
	public int getTodoNo() {
		return todoNo;
	}
	public void setTodoNo(int todoNo) {
		this.todoNo = todoNo;
	}
	public int getTodoMember() {
		return todoMember;
	}
	public void setTodoMember(int todoMember) {
		this.todoMember = todoMember;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getTodoDate() {
		return todoDate;
	}
	public void setTodoDate(String todoDate) {
		this.todoDate = todoDate;
	}
	public String getTodoTitle() {
		return todoTitle;
	}
	public void setTodoTitle(String todoTitle) {
		this.todoTitle = todoTitle;
	}
	public String getTodoContent() {
		return todoContent;
	}
	public void setTodoContent(String todoContent) {
		this.todoContent = todoContent;
	}
	public String getTodoFontColor() {
		return todoFontColor;
	}
	public void setTodoFontColor(String todoFontColor) {
		this.todoFontColor = todoFontColor;
	}
	public String getTodoAddDate() {
		return todoAddDate;
	}
	public void setTodoAddDate(String todoAddDate) {
		this.todoAddDate = todoAddDate;
	}
	
	@Override
	public String toString() {
		return "Todo [todoNo=" + todoNo + ", todoMember=" + todoMember + ", memberNo=" + memberNo + ", todoDate="
				+ todoDate + ", todoTitle=" + todoTitle + ", todoContent=" + todoContent + ", todoFontColor="
				+ todoFontColor + ", todoAddDate=" + todoAddDate + "]";
	}
	
}

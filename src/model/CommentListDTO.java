package model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class CommentListDTO {
	
	private ArrayList<Integer> commentNoList = new ArrayList<Integer>(); //댓글 번호
    private ArrayList<Integer> boardNoList = new ArrayList<Integer>();   //게시물 번호
    private ArrayList<String> idList = new ArrayList<String>();  // 작성자 아이디
    private ArrayList<Date> commentDateList = new ArrayList<Date>();        // 저장일자
    private ArrayList<Time> commentTimeList = new ArrayList<Time>();        // 저장시각
    private ArrayList<String> contentList = new ArrayList<String>();        // 댓글내용
    private boolean lastPage = false;      // 게시글 목록의 마지막 페이지인지 여부
    
	public Integer[] getCommentNoList() {
		return commentNoList.toArray(new Integer[commentNoList.size()]);
	}
	public void setCommentNoList(int index,Integer no) {
		this.commentNoList.add(index, no);
	}
	public Integer[] getBoardNoList() {
		return boardNoList.toArray(new Integer[boardNoList.size()]);
	}
	public void setBoardNoList(int index,Integer boardNo) {
		this.boardNoList.add(index, boardNo);
	}
	public String[] getIdList() {
		return idList.toArray(new String[idList.size()]);
	}
	public void setIdList(int index, String id) {
		this.idList.add(index, id);
	}
	public Date[] getCommentDateList() {
		return commentDateList.toArray(new Date[commentDateList.size()]);
	}
	public void setCommentDateList(int index, Date date) {
		this.commentDateList.add(index, date);
	}
	public Time[] getCommentTimeList() {
		return commentTimeList.toArray(new Time[commentTimeList.size()]);
	}
	public void setCommentTimeList(int index, Time time) {
		this.commentTimeList.add(index, time);
	}
	public String[] getContentList() {
		return contentList.toArray(new String[contentList.size()]);
	}
	public void setContent(int index, String content) {
		this.contentList.add(index, content);
	}
	public boolean isLastPage() {
		return lastPage;
	}
	public void setLastPage(boolean lastPage) {
         this.lastPage = lastPage;
    }
	 public int getListSize() {   // 게시글의 수를 리턴하는 메서드
         return commentNoList.size();
    }
}
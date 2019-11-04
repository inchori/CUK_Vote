package model;
import java.sql.*;

/**
 * @author 이동현
 * bulletin board system(전자 게시판)을 나타내는 DTO입니다.
 */
public class BBSDTO {
   private int seqno; //게시물 번호
   private String title; //제목 
   private String content; //내용
   private String writer; //작성자
   private Date date; //작성일자
   private Time time; // 작성시간
   private int step;
   public int getSeqno() {
      return seqno;
   }
   public void setSeqno(int seqno) {
      this.seqno = seqno;
   }
   public String getTitle() {
      return title;
   }
   public void setTitle(String title) {
      this.title = title;
   }
   public String getContent() {
      return content;
   }
   public void setContent(String content) {
      this.content = content;
   }
   public String getWriter() {
      return writer;
   }
   public void setWriter(String writer) {
      this.writer = writer;
   }
   public Date getDate() {
      return date;
   }
   public void setDate(Date date) {
      this.date = date;
   }
   public int getStep() {
      return step;
   }
   public Time getTime() {
		return time;
   }
	public void setTime(Time time) {
		this.time = time;
}
   public void setStep(int step) {
      this.step = step;
   }

}
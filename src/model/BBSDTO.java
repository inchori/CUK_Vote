package model;
import java.sql.*;

/**
 * @author �̵���
 * bulletin board system(���� �Խ���)�� ��Ÿ���� DTO�Դϴ�.
 */
public class BBSDTO {
   private int seqno; //�Խù� ��ȣ
   private String title; //���� 
   private String content; //����
   private String writer; //�ۼ���
   private Date date; //�ۼ�����
   private Time time; // �ۼ��ð�
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
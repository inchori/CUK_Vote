package model;

import java.sql.Date;
import java.sql.Time;

public class SentMemoDTO {
	private int realseqno;
	private int seqno;
	private String sent_id;
	private String recv_id; //받는이 아이디
	private String title; //쪽지 제목
	private String content; //쪽지 내용
	private Date sentDate;
	private Time sentTime;
	private String read; //게시판에서 누름
	private Date readDate; //본 날짜
	private Time readTime; //본 시간
	public int getRealseqno() {
		return realseqno;
	}
	public void setRealseqno(int realseqno) {
		this.realseqno = realseqno;
	}
	public int getSeqno() {
		return seqno;
	}
	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}
	public String getSent_id() {
		return sent_id;
	}
	public void setSent_id(String sent_id) {
		this.sent_id = sent_id;
	}
	public String getRecv_id() {
		return recv_id;
	}
	public void setRecv_id(String recv_id) {
		this.recv_id = recv_id;
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
	public Date getSentDate() {
		return sentDate;
	}
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	public Time getSentTime() {
		return sentTime;
	}
	public void setSentTime(Time sentTime) {
		this.sentTime = sentTime;
	}
	public String getRead() {
		return read;
	}
	public void setRead(String read) {
		this.read = read;
	}
	public Date getReadDate() {
		return readDate;
	}
	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}
	public Time getReadTime() {
		return readTime;
	}
	public void setReadTime(Time readTime) {
		this.readTime = readTime;
	}
}

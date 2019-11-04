package model;

import java.sql.Date;
import java.sql.Time;

/**
 * @author 이동현
 * 메모를 나타내는 DTO입니다.
 * 
 * 사용자가 로그인해서 쪽지 란에 들어가면 쪽지 팝업을 띄워서 거기서 보낸 쪽지(보낸이 아이디가 자기자신이면 다 로드 auto increment 속성이고 order by seqno로 들고온다.), 받은쪽지에서는 (쪽지가 자신이 받은 쪽지라면 다들고온다.)
 * 수신자가 읽으면 db에 읽었다고 표시해준다 읽은 날짜와, 시간 그리고 recv_read처리, 쪽지에 해당하는 데이터를  삭제한다면  alter table로 기존의 col값들을 재정렬 및 셋팅 삭제는 seqno으로 삭제 고유값이기 때문에
 */
public class RecvMemoDTO {
	private int realseqno;
	private int seqno; //식별번호로 중복되지 않은 primary key (쪽지 들고 올때 order by seqno로 들고 올것 auto increment 안읽은 쪽지는 꺼멋게 읽은 쪽지는 연하게)
	private String sent_id; //보낸이 아이디
	private String recv_id; //받는이 아이디
	private String title; //쪽지 제목
	private String content; //쪽지 내용
	private Date sentDate; //보낸 날짜
	private Time sentTime; //보낸 시간
	private Date readDate; //보낸 날짜
	private Time readTime; //보낸 시간
	private String read; //게시판에서 누름 봄
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

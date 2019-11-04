package model;
import java.sql.*;

/**
 * @author 이동현
 * 어떤 투표이고 시작일과 종료일을 가지고 있는 DTO입니다.
 */
public class VoteDTO implements Comparable<VoteDTO>  	{
	private String voteKind; //투표중류 primary key
	private Date startDate; //투표시작날짜 
	private Time startTime; //투표시작시간
	private Date endDate; //투표끝나는날짜
	private Time endTime; //투표끝나는시간
	private int seqno;
	public int getSeqno() {
		return seqno;
	}
	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}
	public VoteDTO() {
	}
	public String getVoteKind() {
		return voteKind;
	}
	public void setVoteKind(String voteKind) {
		this.voteKind = voteKind;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	@Override
	public int compareTo(VoteDTO o) {
		return getEndDate().compareTo(o.getEndDate());
	}
}

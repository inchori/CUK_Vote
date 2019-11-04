package model;

import java.sql.*;

public class EndVoteDTO {
	private String voteKind;
	private Date endDate;
	private Time endTime;
	private int getVote;
	private int seqno;
	public int getSeqno() {
		return seqno;
	}
	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}
	public String getVoteKind() {
		return voteKind;
	}
	public void setVoteKind(String voteKind) {
		this.voteKind = voteKind;
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
	public int getGetVote() {
		return getVote;
	}
	public void setGetVote(int getVote) {
		this.getVote = getVote;
	}
}

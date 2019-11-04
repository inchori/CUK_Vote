package model;

import java.sql.*;

public class BBSVoteInfoDTO {
	private int seqno;
	private String voteKind;
	private Date endDate;
	private Time endTime;
	private String candidate1;
	private int getVote1;
	private String candidate2;
	private int getVote2;
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
	public String getCandidate1() {
		return candidate1;
	}
	public void setCandidate1(String candidate1) {
		this.candidate1 = candidate1;
	}
	public int getGetVote1() {
		return getVote1;
	}
	public void setGetVote1(int getVote1) {
		this.getVote1 = getVote1;
	}
	public String getCandidate2() {
		return candidate2;
	}
	public void setCandidate2(String candidate2) {
		this.candidate2 = candidate2;
	}
	public int getGetVote2() {
		return getVote2;
	}
	public void setGetVote2(int getVote2) {
		this.getVote2 = getVote2;
	}
}

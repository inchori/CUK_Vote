package model;
import java.sql.*;

/**
 * @author �̵���
 * � ��ǥ�̰� �����ϰ� �������� ������ �ִ� DTO�Դϴ�.
 */
public class VoteDTO implements Comparable<VoteDTO>  	{
	private String voteKind; //��ǥ�߷� primary key
	private Date startDate; //��ǥ���۳�¥ 
	private Time startTime; //��ǥ���۽ð�
	private Date endDate; //��ǥ�����³�¥
	private Time endTime; //��ǥ�����½ð�
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

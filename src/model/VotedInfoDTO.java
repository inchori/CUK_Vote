package model;

public class VotedInfoDTO {
	private String voteKind;	// ��ǥ����
	private int studentID;		// �й�
	private int candidateNo;	//�ĺ��ڹ�ȣ
	public String getVoteKind() {
		return voteKind;
	}
	public void setVoteKind(String voteKind) {
		this.voteKind = voteKind;
	}
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	public int getCandidateNo() {
		return candidateNo;
	}
	public void setCandidateNo(int candidateNo) {
		this.candidateNo = candidateNo;
	}
}

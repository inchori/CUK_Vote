package model;

/**
 * @author 이동현
 * 후보자를 나타내는 DTO입니다.
 */
public class CandidateDTO implements Comparable<CandidateDTO>{
	private int studentID; //primaryKey 학번
	private int no; // 후보자 번호 
	private String commitment; //공약
	private String carreer; //경력
	private int getVote; //득표
	private String voteKind; //투표 종류 
	private String imgPath; //사진 경로
	public CandidateDTO() {
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID=studentID;
	}
	public String getCommitment() {
		return commitment;
	}
	public void setCommitment(String commitment) {
		this.commitment = commitment;
	}
	public String getCarreer() {
		return carreer;
	}
	public void setCarreer(String carreer) {
		this.carreer = carreer;
	}
	public int getGetVote() {
		return getVote;
	}
	public void setGetVote(int getVote) {
		this.getVote = getVote;
	}
	public String getVoteKind() {
		return voteKind;
	}
	public void setVoteKind(String voteKind) {
		this.voteKind = voteKind;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Override
	public int compareTo(CandidateDTO o) {
		return Integer.compare(getNo(), o.getNo());
	}
}

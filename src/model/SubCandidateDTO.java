package model;

public class SubCandidateDTO {
	private int studentID; //primaryKey 학번
	private int no; // 후보자 번호 
	private String carreer; //경력
	private String voteKind; //투표 종류 
	private String imgPath; //사진 경로
	public SubCandidateDTO() {
	}
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID=studentID;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getCarreer() {
		return carreer;
	}
	public void setCarreer(String carreer) {
		this.carreer = carreer;
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
}

package model;

public class SubCandidateDTO {
	private int studentID; //primaryKey �й�
	private int no; // �ĺ��� ��ȣ 
	private String carreer; //���
	private String voteKind; //��ǥ ���� 
	private String imgPath; //���� ���
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

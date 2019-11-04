package model;

/**
 * @author �̵���
 * �ĺ��ڸ� ��Ÿ���� DTO�Դϴ�.
 */
public class CandidateDTO implements Comparable<CandidateDTO>{
	private int studentID; //primaryKey �й�
	private int no; // �ĺ��� ��ȣ 
	private String commitment; //����
	private String carreer; //���
	private int getVote; //��ǥ
	private String voteKind; //��ǥ ���� 
	private String imgPath; //���� ���
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

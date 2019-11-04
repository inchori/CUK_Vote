package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentInfoDAO {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	public StudentInfoDAO() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	}
	private void getConnection() throws Exception {
		this.conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/webvotedb", "root", "student");
		if(conn==null)
			throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
	}
	private void close(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
		if(rs!=null)rs.close();
		if(stmt!=null)stmt.close();
		if(conn!=null)conn.close();
	}
	public int insert(StudentInfoDTO studentinfo){
		try {
			getConnection();
			this.stmt=conn.createStatement();
			String query="insert into studentinfo (studentID, password, birthday, ID, name, major, gender, grade, mailAddress, phoneNumber) "
					+ "values ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');";
			String command=String.format(query, studentinfo.getStudentID(),studentinfo.getPassword(),studentinfo.getBirthday(),studentinfo.getID(),
					studentinfo.getName(),studentinfo.getMajor(),studentinfo.getGender(),studentinfo.getGrade(),studentinfo.getMailAddress(),studentinfo.getPhoneNumber());
			String query2="select * from studentinfo where studentID='%s';";
			String command2=String.format(query2, String.valueOf(studentinfo.getStudentID()));
			rs=stmt.executeQuery(command2);
			if(rs.next()) return -2; //�̹� �����й��� ����
			return stmt.executeUpdate(command);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				close(conn,stmt,rs);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return -1;//�����ͺ��̽� ����
	}
	public int insertCheck(String ID) {
		try {
			if(ID.equals("")) return -2; //���̵� �Է��ϼ���
			getConnection();
			this.stmt=conn.createStatement();
			String query="select * from studentinfo where ID='%s';";
			String command=String.format(query, ID);
			rs=stmt.executeQuery(command);
			if(rs.next()) return 0; //�̹� ����
			else return 1; //�� ������ ����
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				close(conn, stmt, rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return -1; //�����ͺ��̽� ����
	}
	public int change(StudentInfoDTO studentinfo) throws Exception{
		getConnection();
		this.stmt=conn.createStatement();
		String query="update studentinfo set password:='%s', birthday:='%s', major:='%s', gender:='%s', grade:='%s', mailAddress:='%s', phoneNumber:='%s' where ID='%s';";
		String command=String.format(query,studentinfo.getPassword(),studentinfo.getBirthday(),studentinfo.getMajor(),studentinfo.getGender(),studentinfo.getGrade(),studentinfo.getMailAddress(),studentinfo.getPhoneNumber(),studentinfo.getID());
		int rowNum=stmt.executeUpdate(command);
		if(rowNum < 1) {
			throw new Exception("�����͸� DB�� �Է��� �� �����ϴ�.");
		}
		close(this.conn,this.stmt,this.rs);
		return rowNum;
	}
	public void delete(int key) throws Exception{
		getConnection();
		this.stmt=conn.createStatement();
		String query="delete from studentinfo where studentID ='%d'";
		String command=String.format(query,key);
		int rowNum=stmt.executeUpdate(command);
		if(rowNum<1)
			throw new Exception("�����͸� ������ �� �����ϴ�.");
		close(this.conn,this.stmt,this.rs);
	}
	public StudentInfoDTO load(String key) throws Exception{
		getConnection();
		this.stmt=conn.createStatement();
		StudentInfoDTO result=new StudentInfoDTO();
		String query="select * from studentinfo where ID = '" + key + "';";
		this.rs=stmt.executeQuery(query);
		if(!this.rs.next())
			throw new Exception("��ǰ�ڵ�(" + key + ")�� �ش��ϴ� �����Ͱ� �����ϴ�.");
		result.setStudentID(rs.getInt("studentID"));
		result.setPassword(rs.getString("password"));
		result.setBirthday(rs.getString("birthday"));
		result.setID(rs.getString("ID"));
		result.setName(rs.getString("name"));
		result.setMajor(rs.getString("major"));
		result.setGender(rs.getString("gender"));
		result.setGrade(rs.getString("grade"));
		result.setMailAddress(rs.getString("mailAddress"));
		result.setPhoneNumber(rs.getString("phoneNumber"));
		close(this.conn,this.stmt,this.rs);
		return result;
	}
	public StudentInfoDTO load(int key) throws Exception{
		getConnection();
		this.stmt=conn.createStatement();
		StudentInfoDTO result=new StudentInfoDTO();
		String query="select * from studentinfo where studentID = '" + key + "';";
		this.rs=stmt.executeQuery(query);
		if(!this.rs.next())
			throw new Exception("��ǰ�ڵ�(" + key + ")�� �ش��ϴ� �����Ͱ� �����ϴ�.");
		result.setStudentID(rs.getInt("studentID"));
		result.setPassword(rs.getString("password"));
		result.setBirthday(rs.getString("birthday"));
		result.setID(rs.getString("ID"));
		result.setName(rs.getString("name"));
		result.setMajor(rs.getString("major"));
		result.setGender(rs.getString("gender"));
		result.setGrade(rs.getString("grade"));
		result.setMailAddress(rs.getString("mailAddress"));
		result.setPhoneNumber(rs.getString("phoneNumber"));
		close(this.conn,this.stmt,this.rs);
		return result;
	}
	public int login(String ID, String password) {
		try {
			if(ID.equals(""))
				return -3;
			if(password.equals(""))
				return -4;
			getConnection();
			this.stmt=conn.createStatement();
			String query="select password from studentinfo where ID='%s';";
			String command=String.format(query, ID);
			rs=stmt.executeQuery(command);
			if(rs.next()){
				if(rs.getString(1).equals(password)) {
					return 1;//�α��� ����
				}
				else
					return 0;//��й�ȣ ����ġ
			}
			return -1; //���̵� ����
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				close(conn, stmt, rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return -2; //�����ͺ��̽� ����
	}
}

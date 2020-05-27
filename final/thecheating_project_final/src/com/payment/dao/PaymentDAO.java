package com.payment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.payment.dto.AccCheckDTO;
import com.payment.dto.AccUserDTO;
import com.payment.dto.DutchMoneyDTO;

public class PaymentDAO {

	DataSource ds;
	
	/** DB 연결을 위한 메서드**/
	public PaymentDAO(){
		try {
			Context ctx =new InitialContext();
			ds=(DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
			System.out.println("ds : "+ds);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}	// public PaymentDAO() END
	
	/*
	 *  1. 회원가입시 이름을 통해 전체 맴버 계좌테이블과 회원개인용 테이블 생성을 위한 메서드들
	 *  
	 *  	1) public ArrayList<AccCheckDTO> checklist(String holder)
	 *  		stats값이 1일 경우 : 2)번 3)번으로 진행한다.
	 *  		1이 아닐 경우 : error 메시지를 띄워 휴면/정지중인 계좌는 등록할수 없다고 한다.
	 *  			=> 컨트롤러에서 메시지내용을 객체로 전달하여 화면에 뿌려주기 
	 *  
	 *  	2)  public void joinMemAcc(String user_name, String user_account, int user_bankname, int user_balance) 실행
	 *  		이때 input값들은 1)에서 리턴해주는 list에서 getter를 이용해 넣어준다.
	 *  
	 *  	3)  public void accountUserInsert (String user_name, String user_account, int user_bankname, int user_balance)
	 *  		2) 와 같이 1)에서 리턴해주는 list값을 받아서 input.
	 */	
	
	
	/** 회원가입시 전체 회원 계좌 테이블 생성을 위한 사전준비 계좌의 값 찾아오기**/
	// input : 예금주명 output : <AccCheckDTO>arraylist (은행번호, 계좌번호, 잔액, 상태)
	public ArrayList<AccCheckDTO> checklist(String holder){
		ArrayList<AccCheckDTO> list = new ArrayList<AccCheckDTO>();
		try {
			String sql="SELECT bank_name, account_num, balance, stats FROM acc_check where holder = "+holder;
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				AccCheckDTO data = new AccCheckDTO();
				data.setBank_name(rs.getInt("bank_name"));
				data.setAccount_num(rs.getString("account_num"));
				data.setBalance(rs.getInt("balance"));
				data.setStats(rs.getInt("stats"));	// stats이 1번일 경우만 등록
				list.add(data);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("checklist ERR : "+e.getMessage());
		}
		return list;
	}	// public ArrayList<AccCheckDTO> checklist(String holder) END
	
	/** 전체 회원 계좌 등록 매서드 **/
	// input : 회원이름, 계좌번호, 잔액, 은행번호
	public void joinMemAcc(String name, String account_num, int balance, int bank_name){
		String sql="INSERT INTO acc_total (seq_total, name, account_num, balance, stats, reg_date, bank_name) "
				+" VALUES (seq_total.Nextval, ?, ?, ?, 1, now(), ?)";
		try {
			Connection con =ds.getConnection();
			PreparedStatement pstmt =con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, account_num);
			pstmt.setInt(3,  balance);
			pstmt.setInt(4, bank_name);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("joinMemAcc ERR : "+e.getMessage());
		}
	}	// public void joinMemAcc(String name, String account_num, int balance, int bank_name) END

	
	/* 회원가입시 사용자 계좌 테이블에 insert */
	// input : 회원명, 회원계좌, 회원은행번호, 회원의 잔액
	public void accountUserInsert (String user_name, String user_account, int user_bankname, int user_balance){
		String sql="INSERT INTO acc_user (seq_user, user_name, user_account, user_bankname, user_balance, user_stats) "
				+" VALUES (seq_user.Nextval, ?, ?, ?, ?, 1, 0)";
		try {
			Connection con =ds.getConnection();
			PreparedStatement pstmt =con.prepareStatement(sql);
			pstmt.setString(1, user_name);
			pstmt.setString(2, user_account);
			pstmt.setInt(3,  user_bankname);
			pstmt.setInt(4, user_balance);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("accountUserInsert ERR : "+e.getMessage());
		}
	}	// public void accountUserInsert (String user_name, String user_account, int user_bankname, int user_balance)
	
	/* 등록된 회원 계좌 휴면 혹은 삭제 */
	// input : 회원명, 회원계좌번호, 회원 은행번호
	public void userAccountStop(String name, String account_num, int bank_name){
		String query = "UPDATE ACC_USER SET USER_STATS = 2 WHERE NAME = ? AND ACCOUNT_NUM = ? AND BANK_NAME = ?";
		try{
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, account_num);
			pstmt.setInt(3, bank_name);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch (SQLException e){
			System.out.println("userAccountStop ERR : "+e.getMessage());
		}
	} // public void userAccountStop(String name, String account_num, int bank_name) END
	
	/* 등록된 회원의 계좌를 갖는 전체 테이블 휴면 혹은 삭제 처리*/
	// input : 회원명, 회원계좌번호, 회원 은행번호
	public void memAccountStop(String user_name, String user_account, int user_bankname){
		String query = "UPDATE acc_total SET stats = 2 WHERE USER_NAME = ? AND USER_ACCOUNT = ? AND USER_BANKNAME = ?";
		try{
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, user_name);
			pstmt.setString(2, user_account);
			pstmt.setInt(3, user_bankname);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch (SQLException e){
			System.out.println("userAccountStop ERR : "+e.getMessage());
		}
	}
	
	/* 
	 *  2. 결제
	 *  	1) 채팅방에서 리더의 계좌를 대표 계좌로 만들어 놓기
	 *  		결제용 계좌 찾기 메서드를 통해 리더의 계좌 정보 찾아놓은뒤
	 *  
	 *  	2) 단톡방 결제용 테이블에 넣어준다. 이때 결제금액까지 같이 넣어 둔다.
	 *  
	 */
	
	/* 결제용 계좌 찾기 */
	// input : 채팅방 방장이름 output: AccUserDTO list(방장계좌번호, 방장 계좌은행명)
	public ArrayList<AccUserDTO> paymentAccountSelect(String chat_leader){
		ArrayList<AccUserDTO> list = new ArrayList<AccUserDTO>();
		try {
			String sql="SELECT USER_ACCOUNT, USER_BANKNAME FROM ACC_USER WHERE USER_NAME = "+chat_leader;
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				AccUserDTO data = new AccUserDTO();
				data.setUser_account(rs.getString("user_account"));
				data.setUser_bankname(rs.getInt("user_bankname"));
				list.add(data);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("paymentAccountSelect ERR : "+e.getMessage());
		}
		return list;
	}	// public ArrayList<AccUserDTO> paymentAccountSelect(String chat_leader)
	
	/* 계좌가 결제가 가능한지 알아보는 메서드*/
	// input : 회원명, 회원계좌번호, 회원은행번호, 결제금액 output : boolean(기본값: FALSE)
	public boolean paymentAvailablity(String user_name, String user_account, int user_bankname, int payMoney){
		boolean availability = false;
		String sql = "SELECT USER_STATS, USER_BALANCE FROM ACC_USER WHERE USER_NAME = ? AND USER_ACCOUNT = ? AND USER_BANKNAME = ?";
		try{
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_name);
			pstmt.setString(2, user_account);
			pstmt.setInt(3, user_bankname);
			ResultSet rs = pstmt.executeQuery();
			int stats = rs.getInt("USER_STATS");
			int balance = rs.getInt("USER_BALANCE");
			if(stats==1){
				if(balance >= payMoney){
					availability = true;
				}
				else{
					availability = false;
				}
			}else{
				availability = false;
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch(SQLException e){
			System.out.println("paymentAvailablity ERR : " + e.getMessage());		
		}
		return availability;
	} // public boolean paymentAvailablity(String user_name, String user_account, int user_bankname, int payMoney) END
	
	/*	단톡방 테이블 생성 */
	// input : 채팅방 번호, 결제 대표자(채팅방 리더), 결제 종류(무슨 결제인지)
	public void dutchMoneyCreate (int chat_idx, String chat_leader, String pay_type){
		String sql="INSERT INTO dutch_money (seq_dutch, chat_idx, chat_leader, pay_type, pay_date) "
				+" VALUES (seq_dutch.NEXTVAL, ?, ?, ?, now())";
		try {
			Connection con =ds.getConnection();
			PreparedStatement pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, chat_idx);
			pstmt.setString(2, chat_leader);
			pstmt.setString(3, pay_type);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("dutchMoneySelect ERR : "+e.getMessage());
		}		
	} // public void dutchMoneyCreate (int seq_dutch, int chat_idx, String chat_leader, String pay_type, String pay_date) END
	
	/* 단톡방 sequence값 찾는 메서드 */
	public int dutchMoneySeqFind(int chat_idx, String pay_type) {
		String sql = "SELECT seq_dutch FROM dutchMoney WHERE chat_idx=? AND pay_type=?";
		int seq = 0;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, chat_idx);
			pstmt.setString(2, pay_type);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				seq = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("dutchMoneySeqFind ERR : " + e.getMessage());
		}
		return seq;
	} // public int dutchMoneySeqFind(int chat_idx, String pay_type) END
	
	/* 단톡방 결제 시작시 테이블 update */
	public void dutchMoneyStart(int chat_idx, String pay_type, int target) {
		String sql = "UPDATE dutch_money SET target=?, pay_date=now() WHERE seq_dutch=? AND chat_idx=? AND pay_type=?";
		try {
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, target);
			pstmt.setInt(2, dutchMoneySeqFind(chat_idx, pay_type));
			pstmt.setInt(3, chat_idx);
			pstmt.setString(4, pay_type);
			pstmt.executeUpdate();
			System.out.println("dutchMoneyStart 실행됨");
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("dutchMoneyStart ERR : "+e.getMessage());
		}
	} // public void dutchMoneyStart(int chat_idx, String pay_type, int target) END
	
	/* 단톡방 결제 완료시 테이블 update */
	public void dutchMoneyFinish(int chat_idx, String pay_type) {
		String sql = "UPDATE dutch_money SET deposit=0, target=0, current_raise=0, pay_date=now() WHERE seq_dutch=? AND chat_idx=? AND pay_type=?";
		try {
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dutchMoneySeqFind(chat_idx, pay_type));
			pstmt.setInt(2, chat_idx);
			pstmt.setString(3, pay_type);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("dutchMoneyFinish ERR : "+e.getMessage());
		}
	} // public void dutchMoneyFinish(int chat_idx, String pay_type) END
	
	/* 더치페이에서 입금한 사람들 찾는 메서드 */
	public String dutchMoneyFindSender (int chat_idx, String pay_type) {
		String sender = "";
		String sql = "SELECT sender_idx FROM dutch_money WHERE seq_dutch=?";
		try {
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dutchMoneySeqFind(chat_idx, pay_type));
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				sender = rs.getString(1);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("dutchMoneyFindSender ERR : "+e.getMessage());
		}
		return sender;
	}
	
	/* 더치 페이 입금 전 현재까지 모은 금액 확인 메서드 */
	public int dutchMoneyFindRaise(int chat_idx, String pay_type) {
		int current_raise = 0;
		String sql = "SELECT current_raise FROM dutch_money WHERE seq_dutch=?";
		try {
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dutchMoneySeqFind(chat_idx, pay_type));
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				current_raise=rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("dutchMoneyFindRaise ERR : "+e.getMessage());
		}
		return current_raise;
	} // public int dutchMoneyFindRaise(int chat_idx, String pay_type) END
	
	/* 더치페이 입금시 실행되어야할 메서드 */
	public void dutchMoneyInput(int chat_idx, String pay_type, String sender_idx, int deposit) {
		String sql = "UPDATE dutch_money SET sender_idx=?, deposit=?, current_raise=?, depo_date=now() WHERE seq_dutch=? ";
		try {
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dutchMoneyFindSender(chat_idx, pay_type)+","+sender_idx);
			pstmt.setInt(2, deposit);
			pstmt.setInt(3, dutchMoneyFindRaise(chat_idx, pay_type)+deposit);
			pstmt.setInt(4, dutchMoneySeqFind(chat_idx, pay_type));
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("dutchMoneyInput ERR : " + e.getMessage());
		}
	} // public void dutchMoneyInput(int chat_idx, String pay_type, String sender_idx, int deposit, int current_raise)  END

	/* 더치페이시 입금한 사람 및 기록 확인 메서드 */
	public ArrayList<DutchMoneyDTO> dutchMoneyHist (int chat_idx, String pay_type){
		ArrayList<DutchMoneyDTO> list = new ArrayList<DutchMoneyDTO>();
		DutchMoneyDTO data = new DutchMoneyDTO();
		String sql = "SELECT sender_idx, deposit, depo_date FROM dutch_money WHERE chat_idx=? AND pay_type=? ORDER BY depo_date";
		try {
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, chat_idx);
			pstmt.setString(2, pay_type);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				data.setSender_idx(rs.getString("sender_idx"));
				data.setDeposit(rs.getInt("deposit"));
				data.setDepo_date(rs.getString("depo_date"));
				list.add(data);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("dutchMoneyList ERR : "+e.getMessage());
		}
		return list;
	}
	
	/* 더치페이 채팅방 종료시 상태창 변경 */
	public void dutchMoneyStatChange(int chat_idx) {
		String sql ="UPDATE dutch_money SET stats=? WHERE chat_idx=?";
		try {
			Connection con =ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 2);
			pstmt.setInt(2, chat_idx);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("dutchMoneyStatChange ERR : "+e.getMessage());
		}
	} // public void dutchMoneyStatChange(int chat_idx, String pay_type)  END
	
	/* sender를 이용해서 실제 이름 확인 메서드 */
	public String findUserName(int seq_user) {
		String username = "";
		String sql = "SELECT user_name FROM acc_user WHERE seq_user = ?";
		try {
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq_user);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				username=rs.getString(1);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("findUserName ERR : " + e.getMessage());
		}
		return username;
	}
	
	/* 더치 페이 입금 확인 조회 메서드 */
	public ArrayList<String> paymentConfirm(int chat_idx, String pay_type) {
		ArrayList<String> realNameList = new ArrayList<String>();
		String sender_idx = dutchMoneyFindSender(chat_idx, pay_type);
		String[] senders = sender_idx.split(",");
		int seq_user = 0;
		for(int i=1;i<senders.length+1; i++) {
			seq_user = Integer.parseInt(senders[i]);
			realNameList.add(findUserName(seq_user));
		}
		return realNameList;
	}
	
	/* 사용자 계좌 잔액 조회 메서드 */
	// input: 사용자이름 , 사용자 계좌, 사용자 은행번호 output: 
	public int userBalanceSelect(String user_name, String account_num, int bank_num) {
		int balance = 0;
		try {
			String sql="SELECT USER_BALANCE FROM ACC_USER WHERE USER_NAME = ? AND ACCOUNT_NUM = ? AND BANK_NUM = ?";
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_name);
			pstmt.setString(2, account_num);
			pstmt.setInt(3, bank_num);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				balance = rs.getInt("balance");
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("userBalanceSelect ERR : "+e.getMessage());
		}
		return balance;
	} // public int userBalanceSelect(String user_name, String account_num, int bank_num) END
	
	/* 계좌 입금 메서드 */
	public void depositMyaccountI(String user_name, String account_num, int bank_num, int deposit) {
		String sql ="UPDATE acc_user set user_usedate=now(), balance = ? WHERE account_num=? AND user_name=? AND user_bankname=?";
		try {
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, this.userBalanceSelect(user_name, account_num, bank_num)+deposit);
			pstmt.setString(2, account_num);
			pstmt.setString(3, user_name);
			pstmt.setInt(4, bank_num);
			pstmt.executeUpdate();
			System.out.println("depositMyaccountI 실행");
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("depositMyaccountI ERR : "+e.getMessage());
		}
	}
	
	/* 입금자 계좌 출금 처리*/
	public void depositMyaccountO(String user_name, String account_num, int bank_num, int withdraw){	// 수정 할것
		String sql="update acc_user set user_usedate = now(), balance = ? where account_num =? and user_name = ? AND user_bankname =?";
		try {
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, this.userBalanceSelect(user_name, account_num, bank_num)-withdraw);
			pstmt.setString(2, account_num);
			pstmt.setString(3, user_name);
			pstmt.setInt(4, bank_num);
			pstmt.executeUpdate();
			System.out.println("depositMyaccountO 실행");
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("depositMyaccountO ERR : "+e.getMessage());
		}
	}	// public void depositMyaccountI(String user_name, String account_num, int bank_num, int withdraw); END
	
	/* 출금 기록에 저장 */
	public void histOutputInsert (String user_account, String output_account, String receiver, int withdraw){
		String sql="INSERT INTO hist_output (seq_output, user_account, output_account, withdraw, o_resdate, receiver) "
				+" VALUES (seq_output.Nextval, ?, ?, ?, now(), ? )";
		try {
			Connection con =ds.getConnection();
			PreparedStatement pstmt =con.prepareStatement(sql);
			pstmt.setString(1, user_account);
			pstmt.setString(2, output_account);
			pstmt.setInt(3, withdraw);
			pstmt.setString(4,  receiver);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("histOutputInsert ERR : "+e.getMessage());
		}		
	} // public void histOutputInsert (String output_account, String receiver, int withdraw, String memo) END
	
	/* 사용 계좌를 통해 시퀀스값과 찾기 */
	public int findUserSeq(String user_account) {
		String sql = "SELECT seq_user from acc_user WHERE user_account=?";
		int seq_user = 0;
		try {
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_account);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				seq_user = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch(SQLException e) {
			System.out.println("findUserSeq ERR : "+e.getMessage());
		}
		return seq_user;
	} // public int findUserSeq(String user_account) END
	
	/* 입금 기록에 저장 */
	public void histInputInsert (String user_account, String user_name, String input_account, int user_banknum, String sender, int deposit, String memo){
		String sql="INSERT INTO hist_input (seq_input, input_account, deposit, i_resdate, sender, balance, memo) "
				+" VALUES (seq_input.Nextval, ?, ?, now(), ?, ? )";
		try {
			Connection con =ds.getConnection();
			PreparedStatement pstmt =con.prepareStatement(sql);
			pstmt.setString(1, input_account);
			pstmt.setInt(2, deposit);
			pstmt.setString(3,  sender);
			pstmt.setInt(4, this.userBalanceSelect(user_name, user_account, user_banknum)); 
			// 입금이 된 이후의 잔액을 보여주는 것이므로 현재의 매서드는 입출금 메서드 이후 진행되어야함.
			pstmt.setString(4, memo); 
			// memo 부분은 입력 폼에서 Null이 들어와도 되지만 이 메서드를 처리하기 전에
			// 논리문을 통하여 input으로 주거나 null일 경우 "-" 값을 input으로 넣어주면 된다.
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("histInputInsert ERR : "+e.getMessage());
		}		
	} // public void histOutputInsert (String output_account, String receiver, int withdraw, String memo) END
	
	/* 전체 계좌 테이블의 고유값 찾기 (잔액 수정을 위한 전단계) */
	public int findTotalSeq (String user_name, String user_account, int user_banknum) {
		String sql = "SELECT seq_total FROM acc_total WHERE name=? AND account_num=? AND bank_name=?";
		int seq_total=0;
		try {
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_name);
			pstmt.setString(2, user_account);
			pstmt.setInt(3, user_banknum);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				seq_total = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("findTotalSeq ERR : " + e.getMessage());
		}
		return seq_total;
	} // public int findTotalSeq (String user_name, String user_account, int user_banknum) END
	
	/* 전체 계좌 테이블의 잔액 변경 */
	public void updateAccTotal(String user_name, String user_account, int user_banknum) {
		
		String sql = "UPDATE acc_total set balance=? WHERE seq_total=?";
		try {
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userBalanceSelect(user_name, user_account, user_banknum));
			pstmt.setInt(2, findTotalSeq(user_name, user_account, user_banknum));
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("updateAccTotal ERR : "+e.getMessage());
		}
	} // public void updateAccTotal(String user_name, String user_account, int user_banknum) END

}

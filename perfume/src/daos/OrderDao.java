package daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.CatPerfume;
import models.CatUser;
import models.Item;
import models.Order;
import models.Perfume;
import models.User;
import util.DBConnectionUtil;

public class OrderDao extends AbstractDAO {

	public int order(Order order) {
		int result = 0;
		int idValue = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO `order`(user_id,phone,email,adress,note) VALUES(?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql, st.RETURN_GENERATED_KEYS);
			pst.setInt(1, order.getCustomer().getId());
			pst.setString(2, order.getPhone());
			pst.setString(3, order.getEmail());
			pst.setString(4, order.getAdress());
			pst.setString(5, order.getNote());
			result = pst.executeUpdate();

			rs = pst.getGeneratedKeys();
			if (rs.next()) {
				idValue = rs.getInt(1);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idValue;
	}
	
	public boolean checkUserExist(int userID) {
		boolean ck = true;
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT `order`.`user_id` from `order`";

		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				int userIDFormTable = rs.getInt("user_id");
				if(userIDFormTable == userID) {
					ck = false;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ck;
	}
	
	
	public int editOrder(int orderID) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "UPDATE `order` SET `phone` = 'x' WHERE `order`.`id` = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, orderID);			
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public List<Order> findAll() {
		con = DBConnectionUtil.getConnection();
		List<Order> orderList = new ArrayList<Order>();
		String sql = "SELECT `order`.*,user.*" + " FROM `order`" + " INNER JOIN user ON `order`.`user_id` = user.id"
				+ " ORDER BY `order`.`id` DESC";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Order order = new Order(rs.getInt("id"),
						new User(rs.getString("username"), rs.getString("fullname"), rs.getString("password"),
								rs.getString("money"), new CatUser(rs.getInt("cat_user"))),
						rs.getBoolean("status"), rs.getString("phone"), rs.getString("email"), rs.getString("adress"),
						rs.getString("note"));
				orderList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}
	
	public List<Order> findByID(int userID) {
		con = DBConnectionUtil.getConnection();
		List<Order> orderList = new ArrayList<Order>();
		String sql = "SELECT `order`.*,user.* FROM `order` INNER JOIN user ON `order`.`user_id` = user.id WHERE `order`.`user_id` = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, userID);
			rs = pst.executeQuery();
			while (rs.next()) {
				Order order = new Order(rs.getInt("id"),
						new User(rs.getString("username"), rs.getString("fullname"), rs.getString("password"),
								rs.getString("money"), new CatUser(rs.getInt("cat_user"))),
						rs.getBoolean("status"), rs.getString("phone"), rs.getString("email"), rs.getString("adress"),
						rs.getString("note"));
				orderList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}
	
	
	
	public int getIdDonHang(int userID) {
		int userGetID = 0;
		con = DBConnectionUtil.getConnection();
		List<Order> orderList = new ArrayList<Order>();
		String sql = "SELECT `order`.`id` from `order` WHERE `order`.user_id = ? ORDER BY `order`.`id` DESC LIMIT 1";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, userID);
			rs = pst.executeQuery();
			while (rs.next()) {
				userGetID = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userGetID;
	}
	

	public int addStatus(Order order) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "UPDATE `order` SET status = ? WHERE id = ?";

		try {
			pst = con.prepareStatement(sql);
			pst.setBoolean(1, order.getStatus());
			pst.setInt(2, order.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int delOrder(int orderIDdel) {
		int resultItem = delItem(orderIDdel);
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM `order` WHERE id = ?";
		
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, orderIDdel);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}
	
	public int delItem(int orderIDdel) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM `item` WHERE item.order_id = ?";
		
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, orderIDdel);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}
	

	public int countID() {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT count(*) AS count FROM `order`";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public int countOrderHuy() {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT count(*) AS count FROM `order` where `order`.`phone` = 'x'";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public int countOrderDaDuyet() {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT count(*) AS count FROM `order` where `order`.`status` = 1";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}

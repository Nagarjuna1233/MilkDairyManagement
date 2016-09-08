package com.milkdairy.fileservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.milkdairy.managedobjects.Collection;
import com.milkdairy.managedobjects.Former;
import com.milkdairy.managedobjects.PadValue;
import com.milkdairy.services.ManagedObjectsEnum;

public class MilkDairyPersistenceManager {
	private static final Logger LOG = Logger.getLogger(MilkDairyPersistenceManager.class);
	private String driverName;
	private String dbName;
	private String userName;
	private String passWord;
	private Connection connection;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public boolean save(Object data) {
		String className = data
				.getClass()
				.getName()
				.substring(data.getClass().getName().lastIndexOf('.') + 1,
						data.getClass().getName().length());
		LOG.info("Managed object Name "+className);
		if (String.valueOf(ManagedObjectsEnum.COLLECTION).equalsIgnoreCase(
				className)) {
			Collection value = (Collection) data;
			createCollectionTable();
			createCollectionsRow(value);
			try {
				showCollectionRow("SELECT * FROM Collection");
			} catch (SQLException e) {
				LOG.error(e.getMessage());
			}
		} else if (String.valueOf(ManagedObjectsEnum.FORMER).equalsIgnoreCase(
				className)) {
			Former value = (Former) data;
			createFormerTable();
			createFormerRow(value);
			try {
				showCollectionRow("SELECT * FROM Former");
			} catch (SQLException e) {
				LOG.error(e.getMessage());
			}
		} else if (String.valueOf(ManagedObjectsEnum.PADVALUE)
				.equalsIgnoreCase(className)) {
			PadValue value = (PadValue) data;
			createPadValueTable();
			createPadValueRow(value);
			try {
				showCollectionRow("SELECT * FROM PadValue");
			} catch (SQLException e) {
				LOG.error(e.getMessage());
			}
		}
		return true;
	}

	public void dataSourceInit() {
		try {
			// Class.forName("org.hsqldb.jdbcDriver");
			Class.forName(driverName);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection("jdbc:hsqldb:" + dbName
					+ "," + userName + "," + passWord);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // password

	}

	protected boolean createPadValueTable() {
		PreparedStatement ps;
		try {
			ps = this.getConnection().prepareStatement(
					"CREATE TABLE PadValue (value REAL,creationTime VARCHAR(256))");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	protected boolean createCollectionTable() {
		PreparedStatement ps;
		try {
   ps = this.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Collection (formerID VARCHAR(256),formerName VARCHAR(256),milkPad VARCHAR(256),milkQnty VARCHAR(256),milkPrice VARCHAR(256),timeStemp VARCHAR(256))");
//	ps = this.getConnection().prepareStatement(
//			"DROP TABLE Collection IF EXISTS");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	protected boolean createFormerTable() {
		PreparedStatement ps;
		try {
			ps = this
					.getConnection()
					.prepareStatement(
							"CREATE TABLE IF NOT EXISTS Former (ID VARCHAR(256) ,Name VARCHAR(256),phonenum VARCHAR(256),imageurl VARCHAR(256),email VARCHAR(256),address VARCHAR(256),startdate VARCHAR(256),enddate VARCHAR(256),PRIMARY KEY (ID))");
			ps.executeUpdate();
//			ps = this.getConnection().prepareStatement(
//					"DROP TABLE phonenum IF EXISTS");
//					ps.execute();
		} catch (SQLException e) {
			
			LOG.error(e.getMessage());
			return false;
		}
		return true;
	}

	protected void createFormerRow(Former former) {
		PreparedStatement ps;
		try {
			ps = this.getConnection().prepareStatement(
					"INSERT INTO Former VALUES('" + former.getId() + "','"
							+ former.getName() + "','" + former.getPhoneNum()
							+ "','" + former.getImageUrl() + "','"
							+ former.getEmail() + "','" + former.getAddress()
							+ "','" + former.getStartdate() + "','"
							+ former.getEnddate() + "')");
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.getMessage());

		}

	}

	protected void createPadValueRow(PadValue padValue) {
		PreparedStatement ps;
		try {
			ps = this.getConnection().prepareStatement(
					"INSERT INTO PadValue VALUES(" + padValue.getValue()
							+ "','" + padValue.getCreationTime() + ")");
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.getMessage());

		}

	}

//	public Former getFormerNameBY(String id, String name) {
//		Statement st = null;
//		ResultSet rs = null;
//
//		try {
//			st = connection.createStatement();
//			String query = "SELECT * FROM Former WHERE id='" + id + "'";
//			if (!StringUtils.isEmpty(name)) {
//				query.concat(" AND name='" + name + "'");
//			}
//			rs = st.executeQuery(query);
//
//			while (rs.next()) {
//				Former former = new Former();
//				former.setId(rs.getString("id"));
//				former.setAddress(rs.getString("address"));
//				former.setEmail(rs.getString("email"));
//				former.setEnddate(rs.getString("enddate"));
//				former.setImageUrl(rs.getString("imageurl"));
//				former.setName(rs.getString("name"));
//				former.setPhoneNum(rs.getString("phonenum"));
//				former.setStartdate(rs.getString("startdate"));
//				return former;
//			}
//
//			st.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			LOG.error(e.getMessage());
//		}
//		return null;
//	}

	public List<String> getFormerByProperty(String collumnName) {

		Statement st = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		try {
			st = connection.createStatement();
			rs = st.executeQuery("SELECT " + collumnName + " FROM Former");

			while (rs.next()) {
				list.add(rs.getString(collumnName));
			}

			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.error(e.getMessage());
		}
		return list;
	}

	public Former getFormerBy(String id, String name) {

		if ("".equals(id) && "".equals(name)) {
			return null;
		}
		StringBuffer buf = new StringBuffer("SELECT * FROM Former WHERE");
		if (!"".equals(id)) {
			buf.append(" id='" + id + "'");
		}
		if (!"".equals(name) && !"".equals(id)) {
			buf.append(" AND name='" + name + "'");
		}
		if (!"".equals(name) && "".equals(id)) {
			buf.append(" name='" + name + "'");
		}
        LOG.info("Query "+buf.toString());
		Statement st = null;
		ResultSet rs = null;
		 Former former=null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(buf.toString());
			while (rs.next()) {
				former=new Former();
				former.setId(rs.getString("id"));
				former.setName(rs.getString("name"));
				former.setPhoneNum(rs.getString("phonenum"));
				former.setImageUrl(rs.getString("imageurl"));
				former.setEmail(rs.getString("email"));
				former.setAddress(rs.getString("address"));
				former.setStartdate(rs.getString("startdate"));
				former.setEnddate(rs.getString("enddate"));
			
			}

			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.error(e.getMessage());
		}
		return former;
	}

	protected void createCollectionsRow(Collection collection) {
		PreparedStatement ps;
		try {
			ps = this.getConnection().prepareStatement(
					"INSERT INTO Collection VALUES('"
							+ collection.getFormerID() + "','"
							+ collection.getFormerName() + "','"
							+ collection.getMilkPad() + "','"
							+ collection.getMilkQuantity() + "','"
							+ collection.getMilkPrice() + "','"
							+ collection.getTimeStemp() + "')");
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.getMessage());

		}

	}

	public List<Collection> getCollectionsBy(String formerID, String name,
			String amPM, String date) {

		List<Collection> list=new ArrayList<Collection>();
		if ("".equals(formerID) && "".equals(name) && "".equals(amPM)
				&& "".equals(date)) {
			LOG.error("getCollectionsBy() arguments con't be empty");
			return list;
		}
		StringBuffer query = new StringBuffer();
		if (!"".equals(formerID)) {
			query.append("SELECT * FROM Collection WHERE formerID='" + formerID
					+ "'");
			formerID = "";
		} else if (!"".equals(formerID)) {
			query.append("SELECT * FROM Collection WHERE formerName='" + name
					+ "'");
			name = "";
		} else if (!"".equals(amPM)) {
			query.append("SELECT * FROM Collection WHERE timeStemp LIKE '"
					+ "%" + amPM + "'");
			amPM = "";
		} else if (!"".equals(date)) {
			query.append("SELECT * FROM Collection WHERE timeStemp LIKE '" + date + "%'");
			date = "";
		}
		// query.append("SELECT * FROM Collection WHERE formerID='"+formerID+"'");
		if (!"".equals(formerID)) {
			query.append(" AND formerID='" + formerID + "'");
		}
		if (!"".equals(name)) {
			query.append(" AND formerName='" + name + "'");
		}
		if (!"".equals(amPM)) {
			query.append(" AND timeStemp LIKE '%" + amPM + "'");
		}
		if (!"".equals(date)) {
			query.append(" AND timeStemp LIKE '" + date + "%'");
		}
		LOG.info("Query : " + query.toString());
		Statement st;
		ResultSet rs;
		Collection temp = null;
		try {
			st = this.getConnection().createStatement();
			rs = st.executeQuery(query.toString());
			while (rs.next()) {
				temp = new Collection();
				temp.setFormerID(rs.getString("formerID"));
				temp.setFormerName(rs.getString("formerName"));
				temp.setPadValue(rs.getString("milkPad"));
				temp.setMilkQuantity(rs.getString("milkQnty"));
				temp.setTimeStemp(rs.getString("timeStemp"));
				temp.setMilkPrice(rs.getString("milkPrice"));
				list.add(temp);
			}

			st.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage());

		}
		return list;
	}

	public List<Collection> getAllCollections() {

		List<Collection> list = new ArrayList<Collection>();
		String query = "SELECT * FROM Collection";
		LOG.info("Query : " + query.toString());
		Statement st;
		ResultSet rs;
		try {
			st = this.getConnection().createStatement();
			rs = st.executeQuery(query.toString());
			Collection temp = null;
			while (rs.next()) {
				temp = new Collection();
				temp.setFormerID(rs.getString("formerID"));
				temp.setFormerName(rs.getString("formerName"));
				temp.setPadValue(rs.getString("milkPad"));
				temp.setMilkQuantity(rs.getString("milkQnty"));
				temp.setTimeStemp(rs.getString("timeStemp"));
				temp.setMilkPrice(rs.getString("milkPrice"));
				list.add(temp);
			}

			st.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage());

		}
		return list;
	}

	public synchronized void showCollectionRow(String expression)
			throws SQLException {

		Statement st = null;
		ResultSet rs = null;
		st = connection.createStatement(); // statement objects can be reused
											// with
		rs = st.executeQuery(expression);
		while (rs.next()) {
			LOG.info("One data:" + rs.getString(1));
		}

		st.close(); // NOTE!! if you close a statement the associated ResultSet
					// is
	}
}

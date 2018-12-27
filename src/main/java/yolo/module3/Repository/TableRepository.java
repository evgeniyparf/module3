package yolo.module3.Repository;

import yolo.module3.DB.DB;
import yolo.module3.Entity.Cell;
import yolo.module3.Entity.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableRepository implements CrudRepository<Table> {

    private static final String SQL_SELECT_QUERY = "SELECT table.id, table.name FROM table WHERE table.id=?";
    private static final String SQL_SELECT_ALL_QUERY = "SELECT table.id, table.name FROM table";
    private static final String SQL_INSERT_ONE_QUERY = "INSERT INTO table(table.name) VALUES (?)";
    private static final String SQL_DELETE_ONE_QUERY = "DELETE FROM table WHERE table.id = ?";

    public Table getOne(long id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Table table = new Table();

        try {
            con = DB.getConnection();
            if(con == null) return null;
            ps = con.prepareStatement(SQL_SELECT_QUERY);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            while(rs.next()) {
                table.setId(rs.getInt(1));
                table.setName(rs.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DB.closeAll(rs, ps, con);
        }
        return table;
    }

    public List<Table> getAll() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Table> tables = new ArrayList<Table>();

        try {
            con = DB.getConnection();
            if(con == null) return null;
            ps = con.prepareStatement(SQL_SELECT_ALL_QUERY);
            rs = ps.executeQuery();
            while (rs.next()) {
                Table table = new Table();
                table.setId(rs.getInt(1));
                table.setName(rs.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DB.closeAll(rs, ps, con);
        }
        return tables;
    }

    public void addOne(Table object) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DB.getConnection();
            if (con == null) return;
            con.setAutoCommit(false);
            ps = con.prepareStatement(SQL_INSERT_ONE_QUERY);
            ps.setString(1, object.getName());
            ps.execute();
            con.commit();
        } catch (SQLException ex) {
            try {
                if(con != null) con.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
        } finally {
            DB.closePSandCon(ps, con);
        }
    }

    public void addMany(List<Table> objects) {
        for(Table table : objects) {
            addOne(table);
        }
    }

    public void deleteOne(long id) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DB.getConnection();
            if(con == null) return;
            ps = con.prepareStatement(SQL_DELETE_ONE_QUERY);
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DB.closePSandCon(ps, con);
        }
    }

    public void deleteMany(List<Table> objects) {

    }
}

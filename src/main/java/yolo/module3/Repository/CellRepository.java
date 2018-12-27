package yolo.module3.Repository;

import yolo.module3.DB.DB;
import yolo.module3.Entity.Cell;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CellRepository implements CrudRepository<Cell> {

    private static final String SQL_SELECT_QUERY = "SELECT cell.id, cell.row, cell.col, cell.value, cell.table_id FROM cell WHERE cell.id=?";
    private static final String SQL_SELECT_ALL_QUERY = "SELECT cell.id, cell.row, cell.col, cell.value, cell.table_id FROM cell";
    private static final String SQL_INSERT_ONE_QUERY = "INSERT INTO cell(cell.row, cell.col, cell.value, cell.table_id) VALUES (?,?,?,?)";
    private static final String SQL_DELETE_ONE_QUERY = "DELETE FROM cell WHERE cell.id = ?";


    public Cell getOne(long id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cell cell = new Cell();

        try {
            con = DB.getConnection();
            if(con == null) return null;
            ps = con.prepareStatement(SQL_SELECT_QUERY);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            while(rs.next()) {
                cell.setId(rs.getInt(1));
                cell.setRow(rs.getInt(2));
                cell.setCol(rs.getInt(3));
                cell.setValue(rs.getString(4));
                cell.setTable_id(rs.getInt(5));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DB.closeAll(rs, ps, con);
        }
        return cell;
    }

    public List<Cell> getAllFromTable(int table_id) {
        List<Cell> cells = getAll();
        List<Cell> filtered = new ArrayList<Cell>();
        for(Cell cell : cells) {
            if(cell.getTable_id() == table_id) filtered.add(cell);
        }
        return filtered;
    }

    public List<Cell> getAll() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Cell> cells = new ArrayList<Cell>();

        try {
            con = DB.getConnection();
            if(con == null) return null;
            ps = con.prepareStatement(SQL_SELECT_ALL_QUERY);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cell cell = new Cell();
                cell.setId(rs.getInt(1));
                cell.setRow(rs.getInt(2));
                cell.setCol(rs.getInt(3));
                cell.setValue(rs.getString(4));
                cell.setTable_id(rs.getInt(5));
                cells.add(cell);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DB.closeAll(rs, ps, con);
        }
        return cells;
    }

    public void addOne(Cell object) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DB.getConnection();
            if (con == null) return;

            con.setAutoCommit(false);
            ps = con.prepareStatement(SQL_INSERT_ONE_QUERY);
            ps.setInt(1, object.getRow());
            ps.setInt(2, object.getCol());
            ps.setString(3, object.getValue());
            ps.setInt(4, object.getTable_id());
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

    public void addMany(List<Cell> objects) {
        for(Cell c : objects) {
            addOne(c);
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

    public void deleteMany(List<Cell> cells) {

    }

}

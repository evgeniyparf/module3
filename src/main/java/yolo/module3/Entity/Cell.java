package yolo.module3.Entity;

public class Cell {

    private int id;
    private int row;
    private int col;
    private int table_id;

    private String value;

    public Cell() {

    }

    public Cell(int id, int row, int col, String value, int table_id) {
        this.id = id;
        this.value = value;
        this.row = row;
        this.col = col;
        this.table_id = table_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "id=" + id +
                ", row=" + row +
                ", col=" + col +
                ", table_id=" + table_id +
                ", value='" + value + '\'' +
                '}';
    }
}

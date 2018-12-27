package yolo.module3.Entity;

import yolo.module3.Service.TableService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Table {

    private int id;
    private String name;

    private List<Cell> cells;

    public Table() {

    }

    public Table(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void buildTable() {
        cells = TableService.getAllCells(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Table(List<Cell> cells) {
        this.cells = cells;
    }

    public void addCell(Cell cell) {
        cells.add(cell);
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }
}

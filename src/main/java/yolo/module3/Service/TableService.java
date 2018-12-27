package yolo.module3.Service;

import yolo.module3.Entity.Cell;
import yolo.module3.Repository.CellRepository;
import yolo.module3.Repository.CrudRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TableService {

    private static CellRepository repository = new CellRepository();

    public static List<Cell> getAllCells(int id) {
        return sortTable(repository.getAllFromTable(id));
    }

    private static List<Cell> sortTable(List<Cell> cells) {
        Collections.sort(cells, new Comparator<Cell>() {
            public int compare(Cell o1, Cell o2) {
                int res = 0;
                if (o1.getRow() < o2.getRow())
                    res = -1;
                if (o1.getRow() > o2.getRow())
                    res = 1;
                if (o1.getRow() == o2.getRow()) {
                    if (o1.getCol() < o2.getCol())
                        res = -1;
                    if (o1.getCol() > o2.getCol())
                        res = 1;
                    if (o1.getCol() == o2.getCol())
                        res = 0;
                }
                return res;
            }
        });
        return cells;
    }

    public static void deleteCol(List<Cell> cells, int col) {
        for(Cell c : cells) {
            if(c.getCol() == col) {
                cells.remove(c);
                repository.deleteOne(c.getId());
            }
        }
    }

    public static void deleteRow(List<Cell> cells, int row) {
        for(Cell c : cells) {
            if(c.getRow() == row) {
                cells.remove(c);
                repository.deleteOne(c.getId());
            }
        }
    }

    public static void deleteCell(List<Cell> cells, Cell cell) {
        for(Cell c : cells) {
            if(c.getId() == cell.getId()) {
                cells.remove(c);
                repository.deleteOne(c.getId());
            }
        }
    }
}

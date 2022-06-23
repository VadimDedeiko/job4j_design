package ru.job4j.ood.lsp;

/**
 * нарушение LSP: усиление предусловий в подклассе
 */

public class SuperTable {

    private int rows;

    public SuperTable(int rows) {
        this.rows = rows;
    }

    public int getRows() {
        return rows;
    }

    public boolean isHuge(int rows) {
        return rows > 10;
    }
}

class Table extends SuperTable {

    public Table(int rows) {
        super(rows);
    }

    @Override
    public boolean isHuge(int rows) {
        return rows > 8;
    }
}
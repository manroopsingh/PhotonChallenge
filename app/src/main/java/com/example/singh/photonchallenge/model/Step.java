package com.example.singh.photonchallenge.model;

public class Step {
    private int row;
    private int cost;

    @Override
    public Step clone() {
        Step step = new Step();
        step.setCost(this.getCost());
        step.setRow(this.getRow());
        return step;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Step)) return false;

        Step step = (Step) o;

        if (row != step.row) return false;
        return cost == step.cost;

    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + cost;
        return result;
    }
}

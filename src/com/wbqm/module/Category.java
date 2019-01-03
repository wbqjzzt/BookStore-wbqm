package com.wbqm.module;

import java.util.List;

public class Category {
    private int id;
    private int parentId;
    private int turn;
    private String enName;
    private String name;
    private String description;
    private List<Category> subCategory;

    public List<Category> getSubCategory() { return subCategory; }

    public void setSubCategory(List<Category> subCategory) { this.subCategory = subCategory; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) { this.turn = turn; }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

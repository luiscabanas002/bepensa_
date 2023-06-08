package com.gen.checklist.model;

import ir.mirrajabi.searchdialog.core.Searchable;

public class SearchViewModel implements Searchable {
    private String mTitle;

    public SearchViewModel(String title) {
        mTitle = title;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    public SearchViewModel setTitle(String title) {
        mTitle = title;
        return this;
    }
}

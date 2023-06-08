package com.gen.checklist.views.viewcontroller;

import com.gen.checklist.model.ViewModelItem;

import java.util.List;

public interface ListaItemViewController extends ViewControllerBase<ViewModelItem> {
    void emptyRecyclerView(boolean visible);
    void cargarListItem(List<ViewModelItem> list);
}

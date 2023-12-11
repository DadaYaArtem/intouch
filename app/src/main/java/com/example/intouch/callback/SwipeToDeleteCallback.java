package com.example.intouch.callback;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intouch.adapter.ToDoAdapter;

public class SwipeToDeleteCallback extends ItemTouchHelper.Callback {
    private ToDoAdapter adapter;


    public SwipeToDeleteCallback(ToDoAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int swipeFlags = ItemTouchHelper.RIGHT;
        return makeMovementFlags(0, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        // Do nothing, since you are not handling moves
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        // Swiped to the right, delete the item
        int position = viewHolder.getAdapterPosition();
        adapter.deleteTask(position);
    }
}

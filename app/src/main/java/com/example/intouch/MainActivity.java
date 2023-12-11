package com.example.intouch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.intouch.adapter.ToDoAdapter;
import com.example.intouch.callback.SwipeToDeleteCallback;
import com.example.intouch.model.ToDoEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    private RecyclerView tasksRecycleView;
    private ToDoAdapter tasksAdapter;
    private List<ToDoEntity> tasksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        tasksList = new ArrayList<>();

        tasksRecycleView = findViewById(R.id.tasksRecycleView);
        tasksRecycleView.setLayoutManager(new LinearLayoutManager(this ));
        tasksAdapter = new ToDoAdapter(this);
        tasksRecycleView.setAdapter(tasksAdapter);

        tasksList.add(new ToDoEntity(ThreadLocalRandom.current().nextInt(0 ,10000), false,  UUID.randomUUID().toString()));
        tasksList.add(new ToDoEntity(ThreadLocalRandom.current().nextInt(0 ,10000), false,  UUID.randomUUID().toString()));
        tasksList.add(new ToDoEntity(ThreadLocalRandom.current().nextInt(0 ,10000), false,  UUID.randomUUID().toString()));
        tasksList.add(new ToDoEntity(ThreadLocalRandom.current().nextInt(0 ,10000), false,  UUID.randomUUID().toString()));
        tasksList.add(new ToDoEntity(ThreadLocalRandom.current().nextInt(0 ,10000), false,  UUID.randomUUID().toString()));
        tasksList.add(new ToDoEntity(ThreadLocalRandom.current().nextInt(0 ,10000), false,  UUID.randomUUID().toString()));
        tasksList.add(new ToDoEntity(ThreadLocalRandom.current().nextInt(0 ,10000), false,  UUID.randomUUID().toString()));

        tasksAdapter.setTasks(tasksList);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> showAddTaskDialog());

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecycleView);

    }

    private void showAddTaskDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add New Task");

        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_task, null);
        final EditText taskEditText = dialogView.findViewById(R.id.editTextTask);

        builder.setView(dialogView);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String taskText = taskEditText.getText().toString().trim();

                if (!TextUtils.isEmpty(taskText)) {
                    // Create a new task and add it to the adapter
                    ToDoEntity newTask = new ToDoEntity(
                            ThreadLocalRandom.current().nextInt(0, 10000),
                            false,
                            taskText
                    );
                    tasksAdapter.addTask(newTask);
                }
            }
        });

        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
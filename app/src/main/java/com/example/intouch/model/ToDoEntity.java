package com.example.intouch.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoEntity {
    private long id;
    @SerializedName("done")
    private boolean isDone;
    private String description;
}

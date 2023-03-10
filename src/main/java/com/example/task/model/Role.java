package com.example.task.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public enum Role {
    ADMIN,

    MANAGER,
    USER,
}

package com.viagens.waymilhas.Controller;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class BaseEntityListener {

    @PrePersist
    public void prePersist(BaseEntity entity) {
        entity.onCreate();
    }

    @PreUpdate
    public void preUpdate(BaseEntity entity) {
        entity.updateTimestamp();
    }
}

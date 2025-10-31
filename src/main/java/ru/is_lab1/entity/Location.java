package ru.is_lab1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.eclipse.persistence.annotations.CascadeOnDelete;

import java.util.List;

@Entity
@Table(name="ms_location")
@Data
public class Location extends AbstractEntity{
    @Column(name="x")
    private long x;

    @Column(name="y")
    private float y;

    @Column(name="z")
    private int z;
}

package com.mt.entity;

import javax.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "Categories") // Đảm bảo khớp chính xác với tên bảng trong DB
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private String describe;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}

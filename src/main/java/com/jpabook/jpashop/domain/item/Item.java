package com.jpabook.jpashop.domain.item;

import com.jpabook.jpashop.domain.Category;
import com.jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    /**
     * stock 증가
     * @param quentity
     */
    public void addStock(int quentity) {
        this.stockQuantity += quentity;
    }

    /**
     * stock 감소
     * @param quentity
     */
    public void removeStock(int quentity) {
        int restStock = this.stockQuantity - quentity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more Stock");
        }
        this.stockQuantity = restStock;

    }


}

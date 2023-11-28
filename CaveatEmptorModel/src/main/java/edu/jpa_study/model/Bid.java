package edu.jpa_study.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "bids")
public class Bid {
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    @Column(name = "id", nullable = false)
    protected Long id;
    protected BigDecimal amount;
    protected Date createdOn;
    @ManyToOne(fetch = FetchType.LAZY)
    protected Item item;

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Item getItem() {
        return item;
    }

    void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id=" + id +
                ", amount=" + amount +
                ", createdOn=" + createdOn +
                ", item=" + item +
                '}';
    }
}

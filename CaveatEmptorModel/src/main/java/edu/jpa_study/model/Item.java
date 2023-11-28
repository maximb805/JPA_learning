package edu.jpa_study.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.generator.EventType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "items")
public class Item implements Serializable {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    protected Long id;

    @NotNull
    @Size(min = 2, max = 255, message = "Name is required. Min 2 and max 255 characters")
    protected String name;

    @Column(name =  "imperial_weight")
    @org.hibernate.annotations.ColumnTransformer(
            read = "imperial_weight / 2.20462",
            write = "? * 2.20462"
    )
    protected double metricWeight;

    protected String description;

    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.CreationTimestamp
    @Column(name = "created_on", updatable = false)
    protected Date createdOn;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "auction_type")
    protected AuctionType auctionType;

    protected boolean verified;

    @org.hibernate.annotations.ColumnDefault("1.00")
    @org.hibernate.annotations.Generated(
            event = {EventType.INSERT}
    )
    @Column(name = "initial_price")
    protected BigDecimal initialPrice;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "auction_start")
    protected Date auctionStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Future
    @Column(name = "auction_end")
    protected Date auctionEnd;

    @OneToMany(mappedBy = "item")
    protected Set<Bid> bids = new HashSet<>();

    @org.hibernate.annotations.Formula(
            "(SELECT COUNT(b.id) FROM bids b WHERE b.item_id = id)")
    protected BigDecimal bidsCount;

    @Temporal(TemporalType.TIMESTAMP)
    @ColumnDefault("NOW()")
    @Column(name = "last_modified", insertable = false, updatable = false)
    @org.hibernate.annotations.Generated(
            event = {EventType.INSERT, EventType.UPDATE}
    )
    protected Date lastModified;


    public Item() {
    }

    public Item(String name, double metricWeight, String description,
                Date createdOn, AuctionType auctionType, boolean verified,
                BigDecimal initialPrice, Date auctionStart,
                Date auctionEnd) {
        this.name = name;
        this.metricWeight = metricWeight;
        this.description = description;
        this.createdOn = createdOn;
        this.auctionType = auctionType;
        this.verified = verified;
        this.initialPrice = initialPrice;
        this.auctionStart = auctionStart;
        this.auctionEnd = auctionEnd;
        this.lastModified = Calendar.getInstance().getTime();
    }

    public void addBid(Bid bid) {
        if (bid == null) {
            throw new NullPointerException("Can't add null bid");
        }
        if (bid.getItem() != null) {
            throw new IllegalStateException("Bid is already assigned to an Item");
        }
        bids.add(bid);
        bid.setItem(this);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMetricWeight() {
        return metricWeight;
    }

    public void setMetricWeight(double metricWeight) {
        this.metricWeight = metricWeight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public AuctionType getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(AuctionType auctionType) {
        this.auctionType = auctionType;
    }

    public BigDecimal getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Date getAuctionStart() {
        return auctionStart;
    }

    public void setAuctionStart(Date auctionStart) {
        this.auctionStart = auctionStart;
    }

    public Date getAuctionEnd() {
        return auctionEnd;
    }

    public void setAuctionEnd(Date auctionEnd) {
        this.auctionEnd = auctionEnd;
    }

    public Set<Bid> getBids() {
        return Collections.unmodifiableSet(bids);
    }

    public BigDecimal getBidsCount() {
        return bidsCount;
    }

    public Date getLastModified() {
        return lastModified;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", metricWeight=" + metricWeight +
                ", description='" + description + '\'' +
                ", createdOn=" + createdOn +
                ", auctionType=" + auctionType +
                ", verified=" + verified +
                ", initialPrice=" + initialPrice +
                ", auctionStart=" + auctionStart +
                ", auctionEnd=" + auctionEnd +
                ", bidsCount=" + bidsCount +
                ", lastModified=" + lastModified +
                '}';
    }
}

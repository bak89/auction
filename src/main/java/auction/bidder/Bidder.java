package auction.bidder;

import auction.catalog.Auction;

public abstract class Bidder {

    private String name;
    protected Auction auction;

    public Bidder(String name, Auction auction) {
        this.auction = auction;
        this.name = name;
        this.auction.register(this);
    }

    public String getName() {
        return name;
    }

    public abstract void auctionChanged();
}

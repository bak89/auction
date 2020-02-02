package auction.bidder;

import auction.catalog.Auction;
import auction.domain.Bid;

public class RobotBidder extends Bidder {
    private double increment;
    private double limit;


    public RobotBidder(String s, Auction auction, double increment, double limit) {
        super(s, auction);
        this.increment = increment;
        this.limit = limit;
    }

    @Override
    public void auctionChanged() {


    }
}

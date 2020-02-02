package auction.bidder;

import auction.catalog.Auction;
import auction.domain.Bid;

public class HumanBidder extends Bidder {

    private Auction auction ;
    public HumanBidder(String s, Auction auction) {
        super(s, auction);
    }

    public void auctionChanged() {
        Bid currentBid = auction.getCurrentBid();

    }

}

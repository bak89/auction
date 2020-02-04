package auction.bidder;

import auction.catalog.Auction;
import auction.catalog.InvalidBidException;
import auction.domain.Bid;
import javafx.application.Platform;

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
        Bid currentBid = this.auction.getCurrentBid();
        if (currentBid == null) {
            return; // Or make a bid.
        }
        if (currentBid.getBidder() == this) {
            return;
        }
        if (currentBid.getAmount() >= this.limit) {
            return;
        }

        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                try {
                    auction.bid(this, Math.min(currentBid.getAmount() + this.increment, this.limit));
                } catch (InvalidBidException e) {
                    e.printStackTrace();
                }
            });
        }).start();
    }
}

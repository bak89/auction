package auction.gui;

import auction.bidder.Bidder;
import auction.bidder.RobotBidder;
import auction.catalog.*;
import auction.domain.AuctionStatus;
import auction.domain.Bid;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.time.LocalTime;

public class AuctionController {//implements PropertyChangeListener {

    private Auction auction = null;

    @FXML
    public Label status;
    public Label endTime;
    public Label remTime;
    public Label currentBid;
    public TextField money;

    @FXML
    private ListView<AuctionItem> auctionItems;

    @FXML
    private Button startAuctionButton;

    @FXML
    private Button placeBidButton;
    private HumanBidder bidder;


    public void initialize() {
        AuctionCatalog catalog = AuctionCatalogDAO.loadCatalog();
        catalog.getItems().forEach(item -> auctionItems.getItems().add(item));
        auctionItems.getSelectionModel().select(0);
        startAuctionButton.setOnAction(click -> startAuction());
        placeBidButton.setOnAction(click -> placeBid());
    }

    @FXML
    public void startAuction() {
        assert this.auction == null;
        LocalTime endTime = LocalTime.now().plusMinutes(1);
        AuctionItem selectedAuctionItem = auctionItems.getSelectionModel().getSelectedItem();
        this.auction = new Auction(endTime, selectedAuctionItem);
        this.bidder = new HumanBidder("Number",this.auction);
        this.bidder.auctionChanged();
        new RobotBidder("Chevvoi",this.auction,1,selectedAuctionItem.getMinimumPrice()*5);
        //this.render();
        //this.auction.addPropertyChangeListener(this);
        this.auction.start();
    }

    @FXML
    public void placeBid() {
        try {
            this.auction.bid(this.bidder, Double.parseDouble(this.money.getText()));
        } catch (InvalidBidException e) {
            e.printStackTrace();
        }
    }

   /* private void render() {
        if (this.auction != null) {
            this.endTime.setText(this.auction.getEndTime().toString());
            this.status.setText(String.valueOf(this.auction.getStatus()));
            this.remTime.setText(String.valueOf(this.auction.getRemainingTime()));
            boolean isRunning = this.auction.getStatus() == AuctionStatus.RUNNING;
            this.startAuctionButton.setDisable(isRunning);
            this.placeBidButton.setDisable(!isRunning);
            Bid currentBid = this.auction.getCurrentBid();
            if (currentBid != null) {
                this.currentBid.setText(currentBid.toString());
            } else {
                this.currentBid.setText("No one");
            }
        } else {
            this.startAuctionButton.setDisable(false);
            this.placeBidButton.setDisable(true);
        }
    }*/

    class HumanBidder extends Bidder {
        public HumanBidder(String name, Auction auction) {
            super(name, auction);
        }

        @Override
        public void auctionChanged() {
            if (this.auction != null) {
                endTime.setText(this.auction.getEndTime().toString());
                status.setText(String.valueOf(this.auction.getStatus()));
                remTime.setText(String.valueOf(this.auction.getRemainingTime()));
                boolean isRunning = this.auction.getStatus() == AuctionStatus.RUNNING;
                startAuctionButton.setDisable(isRunning);
                placeBidButton.setDisable(!isRunning);
                Bid bid = this.auction.getCurrentBid();
                if (bid != null) {
                    currentBid.setText(bid.toString());
                } else {
                    currentBid.setText("No one");
                }
            } else {
                startAuctionButton.setDisable(false);
                placeBidButton.setDisable(true);
            }
        }
    }

  /*  @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(this::render);
    }
*/
}

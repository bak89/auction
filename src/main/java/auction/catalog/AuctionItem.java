package auction.catalog;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class AuctionItem {

    private int nr;
    private String description;
    private double minimumPrice;

    public AuctionItem() {
    }

    public AuctionItem(int nr, String description, double minimumPrice) {
        this.nr = nr;
        this.description = description;
        this.minimumPrice = minimumPrice;
    }

    @XmlAttribute//questo è un xml attribute->guarda catalog.xml
    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    @XmlElement//questo è un xml element->guarda catalog.xml
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement
    public double getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(double minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public String toString() {
        return nr + ": " + description + " (minimum price " + String.format("%1.2f", minimumPrice) + ")";
    }
}

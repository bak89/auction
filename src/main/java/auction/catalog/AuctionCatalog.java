package auction.catalog;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

//questa è la main class per il jaxb
@XmlRootElement
public class AuctionCatalog {

    private List<AuctionItem> items = new ArrayList<>();

    @XmlElement(name = "item")
    public List<AuctionItem> getItems() {
        return items;
    }

    public void setItems(List<AuctionItem> items) {
        this.items = items;
    }
}

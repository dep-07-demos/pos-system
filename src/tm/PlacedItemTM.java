package tm;

public class PlacedItemTM {
    private String itemId;
    private double unitPrice;
    private int placedQty;

    public PlacedItemTM() {
    }

    public PlacedItemTM(String itemId, double unitPrice, int placedQty) {
        this.itemId = itemId;
        this.unitPrice = unitPrice;
        this.placedQty = placedQty;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getPlacedQty() {
        return placedQty;
    }

    public void setPlacedQty(int placedQty) {
        this.placedQty = placedQty;
    }
}

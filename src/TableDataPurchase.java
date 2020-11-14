
import javafx.beans.property.SimpleStringProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dhanush
 */
public class TableDataPurchase {
    private SimpleStringProperty order,supplier,desc,unitValue,units,totalValue,totalTax;

    public TableDataPurchase(String order, String supplier, String desc, double unitValue, int units, double totalValue, double totalTax) {
        this.order = new SimpleStringProperty(order);
        this.supplier = new SimpleStringProperty(supplier);
        this.desc = new SimpleStringProperty(desc);
        this.unitValue = new SimpleStringProperty(String.valueOf(unitValue));
        this.units = new SimpleStringProperty(String.valueOf(units));
        this.totalValue = new SimpleStringProperty(String.valueOf(totalValue));
        this.totalTax = new SimpleStringProperty(String.valueOf(totalTax));
    }

    public String getOrder() {
        return order.get();
    }

    public void setOrder(SimpleStringProperty order) {
        this.order = order;
    }

    public String getSupplier() {
        return supplier.get();
    }

    public void setSupplier(SimpleStringProperty supplier) {
        this.supplier = supplier;
    }

    public String getDesc() {
        return desc.get();
    }

    public void setDesc(SimpleStringProperty desc) {
        this.desc = desc;
    }

    public String getUnitValue() {
        return unitValue.get();
    }

    public void setUnitValue(SimpleStringProperty unitValue) {
        this.unitValue = unitValue;
    }

    public String getUnits() {
        return units.get();
    }

    public void setUnits(SimpleStringProperty units) {
        this.units = units;
    }

    public String getTotalValue() {
        return totalValue.get();
    }

    public void setTotalValue(SimpleStringProperty totalValue) {
        this.totalValue = totalValue;
    }

    public String getTotalTax() {
        return totalTax.get();
    }

    public void setTotalTax(SimpleStringProperty totalTax) {
        this.totalTax = totalTax;
    }
    
}

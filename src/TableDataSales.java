
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
public class TableDataSales {
    private SimpleStringProperty invoice,to,desc,unitValue,units,totalValue,totalTax;

    public TableDataSales(String invoice, String to, String desc, double unitValue, int units, double totalValue, double totalTax) {
        this.invoice = new SimpleStringProperty(invoice);
        this.to = new SimpleStringProperty(to);
        this.desc = new SimpleStringProperty(desc);
        this.unitValue = new SimpleStringProperty(String.valueOf(unitValue));
        this.units = new SimpleStringProperty(String.valueOf(units));
        this.totalValue = new SimpleStringProperty(String.valueOf(totalValue));
        this.totalTax = new SimpleStringProperty(String.valueOf(totalTax));
    }

    public String getInvoice() {
        return invoice.get();
    }

    public void setInvoice(SimpleStringProperty invoice) {
        this.invoice = invoice;
    }

    public String getTo() {
        return to.get();
    }

    public void setTo(SimpleStringProperty to) {
        this.to = to;
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

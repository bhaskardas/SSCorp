/*
 * This is custom bean used to display the list of products of
 * Super Sales Corporation.
 */

package com.superconduits.web.formbean;

import java.util.List;

/**
 *
 * @author bhaskar
 * @created on 22 Sep 2010
 * @version 1.0
 * @changeLog
 */
public class ProductForm{

    private String productCatId;
    private String productCatName;

    private String productId;
    private String productName;
    private String material;
    private String finish;
    private String size;
    private String way;
    private String surfaceDiameter;
    private String length;
    private String thickness;
    private List<String> photoName;
    private String gauge;

    /**
     * @return the productCatId
     */
    public String getProductCatId() {
        return productCatId;
    }

    /**
     * @param productCatId the productCatId to set
     */
    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }

    /**
     * @return the productCatName
     */
    public String getProductCatName() {
        return productCatName;
    }

    /**
     * @param productCatName the productCatName to set
     */
    public void setProductCatName(String productCatName) {
        this.productCatName = productCatName;
    }

    /**
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the material
     */
    public String getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * @return the finish
     */
    public String getFinish() {
        return finish;
    }

    /**
     * @param finish the finish to set
     */
    public void setFinish(String finish) {
        this.finish = finish;
    }

    /**
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * @return the way
     */
    public String getWay() {
        return way;
    }

    /**
     * @param way the way to set
     */
    public void setWay(String way) {
        this.way = way;
    }

    /**
     * @return the surfaceDiameter
     */
    public String getSurfaceDiameter() {
        return surfaceDiameter;
    }

    /**
     * @param surfaceDiameter the surfaceDiameter to set
     */
    public void setSurfaceDiameter(String surfaceDiameter) {
        this.surfaceDiameter = surfaceDiameter;
    }

    /**
     * @return the length
     */
    public String getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(String length) {
        this.length = length;
    }

    /**
     * @return the thickness
     */
    public String getThickness() {
        return thickness;
    }

    /**
     * @param thickness the thickness to set
     */
    public void setThickness(String thickness) {
        this.thickness = thickness;
    }

    /**
     * @return the gauge
     */
    public String getGauge() {
        return gauge;
    }

    /**
     * @param gauge the gauge to set
     */
    public void setGauge(String gauge) {
        this.gauge = gauge;
    }

    /**
     * @return the photoName
     */
    public List<String> getPhotoName() {
        return photoName;
    }

    /**
     * @param photoName the photoName to set
     */
    public void setPhotoName(List<String> photoName) {
        this.photoName = photoName;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.superconduits.core.to;

import com.superconduits.core.to.project.ProjectTO;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author bhaskar
 * @since 20 Nov 2010
 * @version 1.0
 * @changeLog
 */
@Entity
@Table(name="sscorp_address_m")
public class AddressTO implements Serializable{

    @Id
    @Column(name="address_id", nullable=false, length=11)
    @GeneratedValue
    private Integer addressId;

    @Column(name="address", nullable=false, length=200)
    private String address;

    @Column(name="city", nullable=true, length=50)
    private String city;

    @Column(name="province", nullable=true, length=50)
    private String province;

    @Column(name="country", nullable=true, length=50)
    private String country;

    @Column(name="zipcode", nullable=true, length=20)
    private String zipcode;

    @ManyToOne(cascade={CascadeType.REMOVE, CascadeType.REFRESH})
    @JoinColumn(name="company_id", referencedColumnName="company_id")
    private CompanyProfileTO companyProfileTO;

    @OneToMany(mappedBy="addressTO", fetch=FetchType.EAGER)
    private Collection<ProjectTO> projectTOs;

    /**
     * @return the addressId
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * @param addressId the addressId to set
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * @return the companyProfileTO
     */
    public CompanyProfileTO getCompanyProfileTO() {
        return companyProfileTO;
    }

    /**
     * @param companyProfileTO the companyProfileTO to set
     */
    public void setCompanyProfileTO(CompanyProfileTO companyProfileTO) {
        this.companyProfileTO = companyProfileTO;
    }

    /**
     * @return the projectTOs
     */
    public Collection<ProjectTO> getProjectTOs() {
        return projectTOs;
    }

    /**
     * @param projectTOs the projectTOs to set
     */
    public void setProjectTOs(Collection<ProjectTO> projectTOs) {
        this.projectTOs = projectTOs;
    }
}

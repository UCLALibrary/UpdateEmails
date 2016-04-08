package edu.ucla.library.libservices.voyager.patrons.beans;

import java.util.Date;

public class PatronAddressBean
{
  private int address_Id;
  private int patron_Id;
  private int address_Type;
  private String address_Status;
  private String protect_Address;
  private String address_Line1;
  private String address_Line2;
  private String address_Line3;
  private String address_Line4;
  private String address_Line5;
  private String city;
  private String state_Province;
  private String zip_Postal;
  private String country;
  private Date effect_Date;
  private Date expire_Date;

  public PatronAddressBean()
  {
  }

  public void setPatron_Id( int patron_Id )
  {
    this.patron_Id = patron_Id;
  }

  public int getPatron_Id()
  {
    return patron_Id;
  }

  public void setAddress_Type( int address_Type )
  {
    this.address_Type = address_Type;
  }

  public int getAddress_Type()
  {
    return address_Type;
  }

  public void setAddress_Status( String address_Status )
  {
    this.address_Status = address_Status;
  }

  public String getAddress_Status()
  {
    return address_Status;
  }

  public void setProtect_Address( String protect_Address )
  {
    this.protect_Address = protect_Address;
  }

  public String getProtect_Address()
  {
    return protect_Address;
  }

  public void setAddress_Line1( String address_Line1 )
  {
    this.address_Line1 = address_Line1;
  }

  public String getAddress_Line1()
  {
    return address_Line1;
  }

  public void setAddress_Line2( String address_Line2 )
  {
    this.address_Line2 = address_Line2;
  }

  public String getAddress_Line2()
  {
    return address_Line2;
  }

  public void setAddress_Line3( String address_Line3 )
  {
    this.address_Line3 = address_Line3;
  }

  public String getAddress_Line3()
  {
    return address_Line3;
  }

  public void setAddress_Line4( String address_Line4 )
  {
    this.address_Line4 = address_Line4;
  }

  public String getAddress_Line4()
  {
    return address_Line4;
  }

  public void setAddress_Line5( String address_Line5 )
  {
    this.address_Line5 = address_Line5;
  }

  public String getAddress_Line5()
  {
    return address_Line5;
  }

  public void setCity( String city )
  {
    this.city = city;
  }

  public String getCity()
  {
    return city;
  }

  public void setState_Province( String state_Province )
  {
    this.state_Province = state_Province;
  }

  public String getState_Province()
  {
    return state_Province;
  }

  public void setZip_Postal( String zip_Postal )
  {
    this.zip_Postal = zip_Postal;
  }

  public String getZip_Postal()
  {
    return zip_Postal;
  }

  public void setCountry( String country )
  {
    this.country = country;
  }

  public String getCountry()
  {
    return country;
  }

  public void setEffect_Date( Date effect_Date )
  {
    this.effect_Date = effect_Date;
  }

  public Date getEffect_Date()
  {
    return effect_Date;
  }

  public void setExpire_Date( Date expire_Date )
  {
    this.expire_Date = expire_Date;
  }

  public Date getExpire_Date()
  {
    return expire_Date;
  }
  
  public String toString()
  {
    return getPatron_Id() + "\t " + getAddress_Line1() + "\t" + getExpire_Date() + "\t" + getAddress_Id();
  }

  public void setAddress_Id( int address_Id )
  {
    this.address_Id = address_Id;
  }

  public int getAddress_Id()
  {
    return address_Id;
  }
}

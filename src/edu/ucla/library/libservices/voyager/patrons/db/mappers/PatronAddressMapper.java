package edu.ucla.library.libservices.voyager.patrons.db.mappers;

import edu.ucla.library.libservices.voyager.patrons.beans.PatronAddressBean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PatronAddressMapper
  implements RowMapper
{
  public PatronAddressMapper()
  {
  }
  public Object mapRow( ResultSet rs, int i )
    throws SQLException
  {
    PatronAddressBean bean;
    
    bean = new PatronAddressBean();
    
    bean.setAddress_Id(rs.getInt("ADDRESS_ID"));
    bean.setAddress_Line1(rs.getString("ADDRESS_LINE1"));    
    bean.setAddress_Line2(rs.getString("ADDRESS_LINE2"));
    bean.setAddress_Line3(rs.getString("ADDRESS_LINE3"));
    bean.setAddress_Line4(rs.getString("ADDRESS_LINE4"));
    bean.setAddress_Line5(rs.getString("ADDRESS_LINE5"));
    bean.setAddress_Status(rs.getString("ADDRESS_STATUS"));
    bean.setAddress_Type(rs.getInt("ADDRESS_TYPE"));
    bean.setCity(rs.getString("CITY"));
    bean.setCountry(rs.getString("COUNTRY"));
    bean.setEffect_Date(rs.getDate("EFFECT_DATE"));
    bean.setExpire_Date(rs.getDate("EXPIRE_DATE"));
    bean.setPatron_Id(rs.getInt("PATRON_ID"));
    bean.setProtect_Address(rs.getString("PROTECT_ADDRESS"));
    bean.setState_Province(rs.getString("STATE_PROVINCE"));
    bean.setZip_Postal(rs.getString("ZIP_POSTAL"));

    return bean;
  }
}

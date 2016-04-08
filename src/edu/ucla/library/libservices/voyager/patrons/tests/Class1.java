package edu.ucla.library.libservices.voyager.patrons.tests;

import edu.ucla.library.libservices.voyager.patrons.beans.PatronAddressBean;
import edu.ucla.library.libservices.voyager.patrons.db.mappers.PatronAddressMapper;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Class1
{
  private static final String QUERY = 
    "SELECT pa.* FROM ucladb.patron_address pa inner join ucladb.patron p ON pa.patron_id = p.patron_id WHERE pa.address_type = 3 AND p.expire_date >= SYSDATE";
  private static List<PatronAddressBean> patrons;
  private static BufferedWriter writer;
  
  public Class1()
  {
  }

  public static void main( String[] args )
    throws IOException
  {
    DriverManagerDataSource ds;

    ds = new DriverManagerDataSource();
    ds.setDriverClassName( "oracle.jdbc.OracleDriver" );
    ds.setUrl( "jdbc:oracle:thin:@//eliot.library.ucla.edu:1521/VGER.VGER" );
    //ds.setUrl( "jdbc:oracle:thin:@//butler.library.ucla.edu:1521/VGER.butler.library.ucla.edu" );
    ds.setUsername( "vger_support" );
    ds.setPassword( "vger_support_pwd" );

    patrons = 
        new JdbcTemplate( ds ).query( QUERY, new PatronAddressMapper() );
    writer = new BufferedWriter( new FileWriter( "C:\\Temp\\patrons\\emails.csv" ) );
    
    writer.write("\"patron id\",\"email\",\"email expires\",\"address id\"");
    writer.newLine();
    
    for ( PatronAddressBean thePatron: patrons )
    {
      //System.out.println(thePatron.toString());
      writer.write(thePatron.toString());
      writer.newLine();
    }
    
    writer.flush();
    writer.close();
  }
}

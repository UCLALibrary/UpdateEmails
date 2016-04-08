package edu.ucla.library.libservices.voyager.patrons.db.connections;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import java.util.Properties;

public class DataSourceFactory
{
  public DataSourceFactory()
  {
  }
  
  public static DriverManagerDataSource getConnection(Properties props)
  {
    DriverManagerDataSource ds;
    
    ds = new DriverManagerDataSource();
    
    ds.setDriverClassName( props.getProperty("db.driver") );
    ds.setUrl( props.getProperty("db.url") );
    ds.setUsername( props.getProperty("db.user") );
    ds.setPassword( props.getProperty("db.password") );
    
    return ds;
  }
}

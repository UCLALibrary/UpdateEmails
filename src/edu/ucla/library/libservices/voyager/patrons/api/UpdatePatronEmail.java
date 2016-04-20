package edu.ucla.library.libservices.voyager.patrons.api;

import edu.ucla.library.libservices.voyager.api.core.ApiParameter;
import edu.ucla.library.libservices.voyager.api.core.ApiRequest;
import edu.ucla.library.libservices.voyager.api.core.ApiResponse;
import edu.ucla.library.libservices.voyager.api.core.ApiServer;
import edu.ucla.library.libservices.voyager.api.core.VoyagerException;
import edu.ucla.library.libservices.voyager.patrons.beans.PatronAddressBean;
import edu.ucla.library.libservices.voyager.patrons.db.connections.DataSourceFactory;
import edu.ucla.library.libservices.voyager.patrons.db.mappers.PatronAddressMapper;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.JdbcTemplate;

public class UpdatePatronEmail
{
  private static List<PatronAddressBean> patrons;
  private static String props_File; // = "C:\\Temp\\patrons\\voyager.properties";
  private static String error_File; // = "C:\\Temp\\patrons\\email_errors.txt";
  private static List<String> errors;
  private static Properties props;
  private static String machine = null;
  private static String appCode;
  private static int port = 0;
  private static String version = null;
  private static ApiRequest request;
  private static ApiResponse response;
  private static ApiServer server;
  private static String dbKey = "";

  public static void main( String[] args )
    throws IOException
  {
    props_File = args[0];
    error_File = args[1];
    
    System.out.println( "Starting email updates @ " + new Date().toString() );
    initializeVariables();
    getEmails();

    try
    {
      doVoyagerInit();
    }
    catch ( VoyagerException ve )
    {
      ve.printStackTrace();
      System.exit( -1 );
    }
    errors = new ArrayList<String>();

    for ( PatronAddressBean thePatron: patrons )
    {
      int returnCode;
      returnCode = updateEmailExpire( thePatron );

      if ( returnCode != 0 )
      {
        errors.add( Integer.toString( returnCode ) + "\t" + 
                    thePatron.toString() );
      }
    }
    if ( errors.size() > 0 )
    {
      BufferedWriter errorWriter = 
        new BufferedWriter( new FileWriter( error_File ) );
      for ( String theError: errors )
      {
        errorWriter.write( theError );
        errorWriter.newLine();
      }
      errorWriter.close();
    }
    System.out.println( "Ending email updates @ " + new Date().toString() );
  }

  private static void getEmails()
  {
    patrons = 
        new JdbcTemplate( DataSourceFactory.getConnection( props ) ).query( props.getProperty( "email.query" ), 
                                                                            new PatronAddressMapper() );
  }

  private static void initializeVariables()
    throws IOException
  {
    props = new Properties();
    props.load( new FileInputStream( props_File ) );

    appCode = "CIRC";
    machine = props.getProperty( "voyager.server" );
    version = props.getProperty( "voyager.version" );
    port = Integer.parseInt( props.getProperty( "voyager.circsvr" ) );
    server = new ApiServer( machine, port );
    dbKey = props.getProperty( "voyager.dbkey" );
    
  }

  private static void doVoyagerInit()
    throws VoyagerException
  {
    request = new ApiRequest( appCode, "INIT" );
    request.addParameter( new ApiParameter( "AP", appCode ) );
    request.addParameter( new ApiParameter( "VN", version ) );

    server.send( request.toString() );
    response = new ApiResponse( server.receive() );
    if ( response.getReturnCode() != 0 )
    {
      throw new VoyagerException( response );
    }
  }

  private static int updateEmailExpire( PatronAddressBean thePatron )
  {
    request = new ApiRequest( appCode, "UPDPADDRSS" );
    request.addParameter( "AI", thePatron.getAddress_Id() );
    request.addParameter( "AT", thePatron.getAddress_Type() );
    request.addParameter( "ST", thePatron.getAddress_Status() );
    request.addParameter( "AP", thePatron.getProtect_Address() );
    request.addParameter( "A1", thePatron.getAddress_Line1() );
    request.addParameter( "A2", thePatron.getAddress_Line2() );
    request.addParameter( "A3", thePatron.getAddress_Line3() );
    request.addParameter( "A4", thePatron.getAddress_Line4() );
    request.addParameter( "A5", thePatron.getAddress_Line5() );
    request.addParameter( "CY", thePatron.getCity() );
    request.addParameter( "SP", thePatron.getState_Province() );
    request.addParameter( "ZP", thePatron.getZip_Postal() );
    request.addParameter( "CT", thePatron.getCountry() );
    request.addParameter( "FD", thePatron.getEffect_Date().toString() );
    request.addParameter( "ED", "" );
    request.addParameter( "PH", "" );
    request.addParameter( "UBID", "1@" + dbKey );

    server.send( request.toString() );
    response = new ApiResponse( server.receive() );
    return response.getReturnCode();
  }
}

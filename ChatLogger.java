import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatLogger {

    public static void writeChatToFile(String message){ //Log all chat to a text file and timestamp the messages
    	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy [HH:mm:ss]");
		Date dateFull = new Date();
		
    	DateFormat dateFormatShort = new SimpleDateFormat("MM-dd-yyyy");
		Date dateShort = new Date();

		
		BufferedWriter writer = null;
		
		Console.updateChat(message);
		
		try
		{
		    writer = new BufferedWriter(new FileWriter("Logs/General/["+(dateFormatShort.format(dateShort))+"] Log.txt", true));
		    writer.write("\n");
		    writer.write(dateFormat.format(dateFull)+message);

		}
		catch (IOException e)
		{
		}
		finally
		{
		    try
		    {
		        if (writer != null)
		        writer.close( );
		    }
		    catch (IOException e)
		    {
		    }
		}
			
    }
	
    public static void writePMToFile(String message){
    	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy [HH:mm:ss]");
		Date dateFull = new Date();
		
    	DateFormat dateFormatShort = new SimpleDateFormat("MM-dd-yyyy");
		Date dateShort = new Date();

		
		BufferedWriter writer = null;
				
		try
		{
		    writer = new BufferedWriter(new FileWriter("Logs/PMs/["+(dateFormatShort.format(dateShort))+"] Log.txt", true));
		    writer.write("\n");
		    writer.write(dateFormat.format(dateFull)+message);

		}
		catch (IOException e)
		{
		}
		finally
		{
		    try
		    {
		        if (writer != null)
		        writer.close( );
		    }
		    catch (IOException e)
		    {
		    }
		}
			
    }
    
    
    public static void writeBotActionToFile(String message){
    	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy [HH:mm:ss]");
		Date dateFull = new Date();
		
    	DateFormat dateFormatShort = new SimpleDateFormat("MM-dd-yyyy");
		Date dateShort = new Date();

		
		BufferedWriter writer = null;
				
		try
		{
		    writer = new BufferedWriter(new FileWriter("Logs/BotActions/["+(dateFormatShort.format(dateShort))+"] Log.txt", true));
		    writer.write("\n");
		    writer.write(dateFormat.format(dateFull)+message);

		}
		catch (IOException e)
		{
		}
		finally
		{
		    try
		    {
		        if (writer != null)
		        writer.close( );
		    }
		    catch (IOException e)
		    {
		    }
		}
			
    }

    
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

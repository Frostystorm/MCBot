import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LottoHandler {

	public static void newLotto(String winner, String pot, String winnerTickets){
    	
    	int totalTickets = (Integer.parseInt(pot))/(850);
    	int percentChanceToWin = ((Integer.parseInt(winnerTickets))/(totalTickets))*100;

    	writeLottoFile(winner+" won $"+pot+" with "+winnerTickets+" tickets and a "+percentChanceToWin+"% to win.");
    	
	}
	
	
	
    public static void writeLottoFile(String message){
    	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy [HH:mm:ss]");
		Date dateFull = new Date();
		
    	DateFormat dateFormatShort = new SimpleDateFormat("MM-dd-yyyy");
		Date dateShort = new Date();

		
		BufferedWriter writer = null;
				
		try
		{
		    writer = new BufferedWriter(new FileWriter("Logs/Lotto/["+(dateFormatShort.format(dateShort))+"] Log.txt", true));
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

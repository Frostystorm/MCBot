import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PartyHandler {

	
    public static void kick(String kicker, String kickMember, String reason) throws IOException{ //Removes member from party and getinvite whitelist. Logs action in a text file
    	if(SpamFilter.check("kick", kicker, kickMember, reason)){
    		
    		MCBotMain.sendMessage("/party kick "+kickMember);
    		
    		WhitelistHandler.removeMember(kickMember);
    		
			BufferedWriter writer = null;
			
        	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy [HH:mm:ss]");
			Date dateFull = new Date();
			    			
			
			try
			{
			    writer = new BufferedWriter(new FileWriter("Logs/Party/PartyLog.txt", true));
			    writer.write("\n");
			    writer.write("["+dateFormat.format(dateFull)+"] Player "+kicker+ " kicked "+kickMember+" from the party: "+reason);

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
    }

    
    public static void invite(String inviter, String inviteMember){ //Invites member to party and logs action in a text file
    	if(SpamFilter.check("invite", inviter, inviteMember, "")){
    		MCBotMain.sendMessage("/party invite "+inviteMember);
    		
			BufferedWriter writer = null;
			
        	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy [HH:mm:ss]");
			Date dateFull = new Date();
			
			
			try
			{
			    writer = new BufferedWriter(new FileWriter("Logs/Party/PartyLog.txt", true));
			    writer.write("\n");
			    writer.write("["+dateFormat.format(dateFull)+"] "+inviter+ " invited "+inviteMember+" to the party.");

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
    }

    
    public static void getinvite(String user){ //Invites user to party
    	if(SpamFilter.check("getinvite",user, "","")){    
			MCBotMain.sendMessage("/party invite "+user);
		}
    }

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

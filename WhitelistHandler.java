import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WhitelistHandler {

    public static ArrayList<String> whitelist = new ArrayList<String>();
    
    
    //Determines the type of !whitelist command issued and either adds or removes member from whitelist
    public static void whitelistCommandRecieved(String subCommand, String user, String targetUser){
			if(subCommand.equals("remove")){
				removeMember(targetUser);
			}
			else if(subCommand.equals("add")){
				if(!whitelist.contains(targetUser)){
					addMember(targetUser);
				}

			}
		}
    
    //If command is !whitelist info the user who called the command receives a pm with the current whitelist
    public static void whitelistinfoCommandRecieved(String subCommand, String user){
			if(subCommand.equals("info")){
				if(SpamFilter.check("whitelistinfo", user,"","")){    
					MCBotMain.sendMessage("/pm "+user+" "+whitelist);
				}
			}
    }
    
	
    public static void writeWhitelist(){ //Writes whitelist arraylist into text file
		
		BufferedWriter whitelistwriter = null;

		try
		{
			
			File whitelistfile = new File("Whitelist.txt");
			
			whitelistfile.delete();

			whitelistwriter = new BufferedWriter(new FileWriter("Whitelist.txt", true));

			for(int i=0;i<whitelist.size();i++){
				whitelistwriter.write(whitelist.get(i));
				whitelistwriter.write("\n");
			}
							
		}
		catch (IOException e)
		{
		}
		finally
		{
		    try
		    {
		        if (whitelistwriter != null)
		        	whitelistwriter.close( );
		        
		    }
		    catch (IOException e)
		    {
		    }
		} 	
    }
    
	
    public static void readWhitelist(){ //Reads text file and puts it in whitelist arraylist.
    	
	      String thisLine = null;
	      
	      try {
	      
	    	 BufferedReader br = new BufferedReader(new FileReader(new File("Whitelist.txt")));

	         while ((thisLine = br.readLine()) != null) {
	            whitelist.add(thisLine);
	         } 
	         
	      } catch(Exception e) {
	         e.printStackTrace();
	      }	

    	
    }
    
    public static boolean checkWhitelist(String member){ //Check if whitelist contains a specific member (used for !getinvite)
    	if(whitelist.contains(member)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    //Remove member from whitelist array and rewrite text file
    public static void removeMember(String member){ 
    	whitelist.remove(member);
		ChatLogger.writeBotActionToFile("Bot removed "+member+" from the whitelist array.");

    	writeWhitelist();	
    }
    
    //Add member to whitelist array and rewrite text file
    public static void addMember(String member){
    	whitelist.add(member);
		ChatLogger.writeBotActionToFile("Bot added "+member+" to the whitelist array.");

    	writeWhitelist();
    }
        
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
}

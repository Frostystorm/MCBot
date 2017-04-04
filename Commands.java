import java.io.IOException;
import java.util.regex.Matcher;

public class Commands {

//	
// (Active) PM Message Methods
//	
	
    public static void say(String message){ //Says or runs command when !say is issued
    	    	
    	if(SpamFilter.check("say", message, "", "")){
    		MCBotMain.sendMessage(message);
      	}
    }

    
    public static void ping(){ //Responds with Pong! when !ping is issued
    	if(SpamFilter.check("ping", "", "", "")){
    		MCBotMain.sendMessage("/r Pong!");
    	}
    }
    
    
    
    public static void stopBot(){ //Disconnects the bot when !stop is issued
		ChatLogger.writeBotActionToFile("Bot was disconnected. Reason: !stop recieved.");
    	MCBotMain.client.getSession().disconnect("Disconnected!");
    }
    
    
    public static void calc(String firstValue, String calcType, String secondValue){ //Calculate simple functions    	
    	
    	if(SpamFilter.check("calc", firstValue, calcType, secondValue)){
    		
    		double numberOne = Double.parseDouble(firstValue);
    		double numberTwo = Double.parseDouble(secondValue);

    		if(calcType.equals("+")){
    			MCBotMain.sendMessage("/r = "+(numberOne+numberTwo));
    		}
    		else if(calcType.equals("-")){
    			MCBotMain.sendMessage("/r = "+(numberOne-numberTwo));
    		}
    		else if(calcType.equals("*")){
    			MCBotMain.sendMessage("/r = "+(numberOne*numberTwo));
    		}
    		else if(calcType.equals("/")){
    			MCBotMain.sendMessage("/r = "+(numberOne/numberTwo));
    		}
    	}
    }
    
    public static void shout(String message){ //Shouts message to party
    	    	
    	if(SpamFilter.check("shout", message, "", "")){
    		MCBotMain.sendMessage("/p [Shout] "+message);
    	}
    }
    
    

    
    
    public static void kick(String kicker, String kickMember, String reason){ //Kick party member
		try {
			PartyHandler.kick(kicker, kickMember, reason);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    public static void invite(String inviter, String inviteMember){ //Invite member to party
		PartyHandler.invite(inviter, inviteMember);
    }
    
    public static void getinvite(String user){ //Request party invite from bot
		PartyHandler.getinvite(user);	
    }
    
    public static void whitelistCommand(String subCommand, String user, String targetUser){ //Add or Remove member from getinvite whitelist
		WhitelistHandler.whitelistCommandRecieved(subCommand, user, targetUser);
    }
    public static void whitelistinfoCommand(String subCommand, String user){
		WhitelistHandler.whitelistinfoCommandRecieved(subCommand, user);
    }
      
    
    
    
    
    
//    
// (Passive) Generic Message Methods 
//    
    

    public static void globalMessage(String rank, String userTags, String nickname, String message){ //Called when a user talks in global

    }
    
    public static void pmRecieved(String user, String message){ //Called when a user talks in global
    	ChatLogger.writePMToFile("PM From "+user+": "+message);
    }
    
        
    public static void loglotto(String winner, String pot, String winnerTickets){
    	LottoHandler.newLotto(winner, pot, winnerTickets);
    }
    

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

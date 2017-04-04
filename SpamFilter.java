import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


class Command{ //Creates command class so commands can be added to a map
    
	String type;
	String param1;
	String param2;
	String param3;

    public Command(String type, String param1, String param2, String param3){
    	this.type = type;
    	this.param1 = param1;
    	this.param2 = param2;
    	this.param3 = param3;

    }

}

public class SpamFilter {

    private static double now = 0;
    private static double lastCMDTime = 0;

    private static Map<Integer,Command> map = new HashMap<Integer,Command>();
    
    
    private static Command c;
    
    //Checks if the time difference between last command and current time is greater than 3 seconds
    //If the time is less than 3 seconds, add the command to the map of queued commands
    public static boolean check(String function, String parameter1, String parameter2, String parameter3){
        now = (double) System.currentTimeMillis();
        if (now-lastCMDTime > 3000){ 
      	  lastCMDTime = now;
      	  return true;
        }
        else{
      	  if(!function.equals("")){
      		  if(function.equals("say")){
      			  c = new Command("say", parameter1, "", "");
      			  map.put(map.size()+1,c);
      		  }
      		  else if(function.equals("shout")){
      			  c = new Command("shout", parameter1, "", "");
      			  map.put(map.size()+1,c);
      		  }
      		  else if(function.equals("kick")){
      			  c = new Command("kick", parameter1, parameter2, parameter3);
      			  map.put(map.size()+1,c);
      		  }
      		  else if(function.equals("invite")){
      			c = new Command("invite", parameter1, parameter2, "");
    			  map.put(map.size()+1,c);
      		  }
      		  else if(function.equals("whitelistinfo")){
      			  c = new Command("whitelistinfo", parameter1, "", "");
      			  map.put(map.size()+1,c);

      		  }
      		  else if(function.equals("getinvite")){
      			  c = new Command("getinvite", parameter1, "", "");
      			  map.put(map.size()+1,c);  
      		  }
      	  }
      	  return false;

        }

      }
      

    public static void runCommandQueue(){ //Called when the bot receives a packet from the server and time difference is > 3s, runs most recent command in map 
    
    Command c;
    	
    	if(map.size() > 0){
    	
    		c = map.get(1);
    	    	
    		if(c.type.equals("say")){
    			Commands.say(c.param1);
    		}
    		else if(c.type.equals("shout")){
    			Commands.shout(c.param1);
    		}
    		else if(c.type.equals("kick")){
    			Commands.kick(c.param1, c.param2, c.param3);
    		}
    		else if(c.type.equals("invite")){
    			Commands.invite(c.param1, c.param2);
    		}
    		else if(c.type.equals("whitelistinfo")){
    			Commands.whitelistinfoCommand("info", c.param1);
    		}
    		else if(c.type.equals("getinvite")){
    			Commands.getinvite(c.param1);
    		}
     
    		map.remove(1);
    	}     
    }
            
    		

    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

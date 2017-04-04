import java.util.ArrayList;

public class Ranks {

    //Party Command Arrays
	public static ArrayList<String> partyOwners = new ArrayList<String>();
    
    
    //PM Command Rank Arrays
    public static ArrayList<String> admins = new ArrayList<String>();
    public static ArrayList<String> commanders = new ArrayList<String>();
    public static ArrayList<String> sergeants = new ArrayList<String>();
    public static ArrayList<String> captains = new ArrayList<String>();

    
    public static void initialize(){
    	
    	//Party Owners
	    partyOwners.add("Frostystorm1");
    	
    	//Admins
	    admins.add("Frostystorm1");
	    admins.add("FrostyMaingo");
	    
	    //Commanders
	    
	    //Sergeants
	    
	    //Captains
	   
	   
	    //Add the admins to all the rank arrays
	    for(int x = 0; x < admins.size(); x = x + 1) {
	    commanders.add(admins.get(x));
	    sergeants.add(admins.get(x));
	    captains.add(admins.get(x));
	    }
	    
	    //Add commanders to the sergeant and captain rank arrays
	    for(int x = 0; x < commanders.size()-3; x = x + 1) {
	    sergeants.add(commanders.get(x));
	    captains.add(commanders.get(x));
	    }
	    
	    //Add sergeants to the captain rank array
	    for(int x = 0; x < sergeants.size()-4; x = x + 1) {
	    captains.add(sergeants.get(x));
	    }
	    
	    	    
    }
    
    
    public static boolean checkAdmin(String username){
    	if(admins.contains(username)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public static boolean checkCommander(String username){
    	if(commanders.contains(username)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public static boolean checkSergeant(String username){
    	if(sergeants.contains(username)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public static boolean checkCaptain(String username){
    	if(captains.contains(username)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public static boolean checkPartyOwner(String username){
    	if(partyOwners.contains(username)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
}

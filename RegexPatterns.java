import java.util.regex.Pattern;

public class RegexPatterns {

    //Party Regex
    public static Pattern p_kick = Pattern.compile("^From ([*_A-Za-z0-9]+): !kick ([a-zA-Z0-9]+) (.+)$");
    public static Pattern p_invite = Pattern.compile("^From ([*_A-Za-z0-9]+): !invite ([*_A-Za-z0-9]+)$");
    public static Pattern p_whitelist = Pattern.compile("^From ([*_A-Za-z0-9]+): !whitelist (add|remove|info) ?([*_A-Za-z0-9]+)?$");
    public static Pattern p_getinvite = Pattern.compile("^From ([*_A-Za-z0-9]+): !getinvite$");
	    
    
    //PM Command Regex
    public static Pattern p_say = Pattern.compile("^From ([*_A-Za-z0-9]+): !say (.+)$");
    public static Pattern p_ping = Pattern.compile("^From ([*_A-Za-z0-9]+): !ping$");
    public static Pattern p_stop = Pattern.compile("^From ([*_A-Za-z0-9]+): !stop$");
    public static Pattern p_calc = Pattern.compile("^From ([*_A-Za-z0-9]+): !calc ([0-9.-]+)([+\\-\\*/])([0-9.-]+)$");
    public static Pattern p_shout = Pattern.compile("^From ([*_A-Za-z0-9]+): !shout (.+)$");
    public static Pattern p_togglelogblock = Pattern.compile("^From ([*_A-Za-z0-9]+): !logblocks$");

    
    //Generic Regex 
    public static Pattern p_pmRecieved = Pattern.compile("^From ([*_A-Za-z0-9]+): (.+)$");
    //Group 1: Gives user rank, Group 2: Gives CTAG, DTAG, Group 3: Gives Username/Nickname, Group 4: Gives message
    public static Pattern p_global = Pattern.compile("^\\[G] \\[([_A-Za-z0-9$]+)](\\[.+])? (.+): (.+)$"); 
    public static Pattern p_loglotto = Pattern.compile("^\\[LOTTERY\\] Congratulations go to ([*_A-Za-z0-9]+) for winning \\$([0-9]+) with ([0-9])+ tickets\\.$");

    

}

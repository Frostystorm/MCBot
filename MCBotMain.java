//FrostyMaingo Code
//
//
// Version 2.0
//
//
// Code By: Frostystorm1
// Contributors: 314 (Some aspects of regex and commands were modified from his bot)
//

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Proxy;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.spacehq.mc.auth.data.GameProfile;
import org.spacehq.mc.auth.exception.request.RequestException;
import org.spacehq.mc.protocol.MinecraftConstants;
import org.spacehq.mc.protocol.MinecraftProtocol;
import org.spacehq.mc.protocol.ServerLoginHandler;
import org.spacehq.mc.protocol.data.SubProtocol;
import org.spacehq.mc.protocol.data.game.PlayerListEntry;
import org.spacehq.mc.protocol.data.game.PlayerListEntryAction;
import org.spacehq.mc.protocol.data.game.entity.player.GameMode;
import org.spacehq.mc.protocol.data.game.setting.Difficulty;
import org.spacehq.mc.protocol.data.game.world.WorldType;
import org.spacehq.mc.protocol.data.game.world.block.value.BlockValueType;
import org.spacehq.mc.protocol.data.message.ChatColor;
import org.spacehq.mc.protocol.data.message.ChatFormat;
import org.spacehq.mc.protocol.data.message.Message;
import org.spacehq.mc.protocol.data.message.MessageStyle;
import org.spacehq.mc.protocol.data.message.TextMessage;
import org.spacehq.mc.protocol.data.message.TranslationMessage;
import org.spacehq.mc.protocol.data.status.PlayerInfo;
import org.spacehq.mc.protocol.data.status.ServerStatusInfo;
import org.spacehq.mc.protocol.data.status.VersionInfo;
import org.spacehq.mc.protocol.data.status.handler.ServerInfoBuilder;
import org.spacehq.mc.protocol.data.status.handler.ServerInfoHandler;
import org.spacehq.mc.protocol.data.status.handler.ServerPingTimeHandler;
import org.spacehq.mc.protocol.packet.ingame.client.ClientChatPacket;
import org.spacehq.mc.protocol.packet.ingame.server.ServerChatPacket;
import org.spacehq.mc.protocol.packet.ingame.server.ServerJoinGamePacket;
import org.spacehq.mc.protocol.packet.ingame.server.ServerPlayerListEntryPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityTeleportPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.player.ServerPlayerChangeHeldItemPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnPlayerPacket;
import org.spacehq.mc.protocol.packet.ingame.server.world.ServerBlockChangePacket;
import org.spacehq.mc.protocol.packet.ingame.server.world.ServerBlockValuePacket;
import org.spacehq.packetlib.Client;
import org.spacehq.packetlib.Server;
import org.spacehq.packetlib.Session;
import org.spacehq.packetlib.event.server.ServerAdapter;
import org.spacehq.packetlib.event.server.SessionAddedEvent;
import org.spacehq.packetlib.event.server.SessionRemovedEvent;
import org.spacehq.packetlib.event.session.DisconnectedEvent;
import org.spacehq.packetlib.event.session.PacketReceivedEvent;
import org.spacehq.packetlib.event.session.SessionAdapter;
import org.spacehq.packetlib.tcp.TcpSessionFactory;

public class MCBotMain {
	    private static final boolean SPAWN_SERVER = false;
	    private static final boolean VERIFY_USERS = true;
	    private static final String HOST = "mc.ecocitycraft.com";
	    private static final int PORT = 25565;
	    private static final Proxy PROXY = Proxy.NO_PROXY;
	    private static final Proxy AUTH_PROXY = Proxy.NO_PROXY;
	    private static String USERNAME = "";
	    private static String PASSWORD = "";
	    
	    public static Client client;     
                
	    
	    public static void recieveCredentials(String username, String password){ //Recieve login info from console GUI
	    	USERNAME = username;
	    	PASSWORD = password;
	    	
	        status();
	        login();
	    }
	    
        public static void sendMessage(String message){ //Send a message/command to the server from the bot
			ChatLogger.writeBotActionToFile("Bot issued: "+message);
        	client.getSession().send(new ClientChatPacket(message));
        }

		public static void main(String[] args) {

	    	
	        if(SPAWN_SERVER) {
	            Server server = new Server(HOST, PORT, MinecraftProtocol.class, new TcpSessionFactory(PROXY));
	            server.setGlobalFlag(MinecraftConstants.AUTH_PROXY_KEY, AUTH_PROXY);
	            server.setGlobalFlag(MinecraftConstants.VERIFY_USERS_KEY, VERIFY_USERS);
	            server.setGlobalFlag(MinecraftConstants.SERVER_INFO_BUILDER_KEY, new ServerInfoBuilder() {
	                @Override
	                public ServerStatusInfo buildInfo(Session session) {
	                    return new ServerStatusInfo(new VersionInfo(MinecraftConstants.GAME_VERSION, MinecraftConstants.PROTOCOL_VERSION), new PlayerInfo(100, 0, new GameProfile[0]), new TextMessage("Hello world!"), null);
	                }
	            });

	            server.setGlobalFlag(MinecraftConstants.SERVER_LOGIN_HANDLER_KEY, new ServerLoginHandler() {
	                @Override
	                public void loggedIn(Session session) {
	                    session.send(new ServerJoinGamePacket(0, false, GameMode.SURVIVAL, 0, Difficulty.PEACEFUL, 10, WorldType.DEFAULT, false));
	                }
	                
	                  

	            });
	            


	            server.setGlobalFlag(MinecraftConstants.SERVER_COMPRESSION_THRESHOLD, 100);
	            server.addListener(new ServerAdapter() {
	                @Override
	                public void sessionAdded(SessionAddedEvent event) {
	                    event.getSession().addListener(new SessionAdapter() {
	                        @Override
	                        public void packetReceived(PacketReceivedEvent event) { 
	                        	if(event.getPacket() instanceof ClientChatPacket) {
	                                ClientChatPacket packet = event.getPacket();
	                                GameProfile profile = event.getSession().getFlag(MinecraftConstants.PROFILE_KEY);
	                                System.out.println(profile.getName() + ": " + packet.getMessage());
	                                Message msg = new TextMessage("Hello, ").setStyle(new MessageStyle().setColor(ChatColor.GREEN));
	                                Message name = new TextMessage(profile.getName()).setStyle(new MessageStyle().setColor(ChatColor.AQUA).addFormat(ChatFormat.UNDERLINED));
	                                Message end = new TextMessage("!");
	                                msg.addExtra(name);
	                                msg.addExtra(end);
	                                event.getSession().send(new ServerChatPacket(msg));
	                            }
	                        	
	                        	
	                        }

	                    });
	                }

	                @Override
	                public void sessionRemoved(SessionRemovedEvent event) {
	                    MinecraftProtocol protocol = (MinecraftProtocol) event.getSession().getPacketProtocol();
	                    if(protocol.getSubProtocol() == SubProtocol.GAME) {
	                        System.out.println("Closing server.");
	                        event.getServer().close();
	                    }
	                }
	            });

	            server.bind();
	        }

	        
	    }
		

		
	    public static void status() {
	        MinecraftProtocol protocol = new MinecraftProtocol(SubProtocol.STATUS);
	        Client client = new Client(HOST, PORT, protocol, new TcpSessionFactory(PROXY));
	        client.getSession().setFlag(MinecraftConstants.AUTH_PROXY_KEY, AUTH_PROXY);
	        client.getSession().setFlag(MinecraftConstants.SERVER_INFO_HANDLER_KEY, new ServerInfoHandler() {
	            
	        	
	        	@Override
	            public void handle(Session session, ServerStatusInfo info) {
	                System.out.println("Version: " + info.getVersionInfo().getVersionName() + ", " + info.getVersionInfo().getProtocolVersion());
	                System.out.println("Player Count: " + info.getPlayerInfo().getOnlinePlayers() + " / " + info.getPlayerInfo().getMaxPlayers());
	                System.out.println("Players: " + Arrays.toString(info.getPlayerInfo().getPlayers()));
	                System.out.println("Description: " + info.getDescription().getFullText());
	                System.out.println("Icon: " + info.getIcon());
	            }
	        });

	        client.getSession().setFlag(MinecraftConstants.SERVER_PING_TIME_HANDLER_KEY, new ServerPingTimeHandler() {
	            @Override
	            public void handle(Session session, long pingTime) {
	                System.out.println("Server ping took " + pingTime + "ms");
	            }
	        });

	        client.getSession().connect();
	        while(client.getSession().isConnected()) {
	            try {
	                Thread.sleep(5);
	            } catch(InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    
		public static void newChat(String message){ //Called whenever a chat packet is received
	          
			Matcher m_say = RegexPatterns.p_say.matcher(message);
			Matcher m_ping = RegexPatterns.p_ping.matcher(message);
			Matcher m_stop = RegexPatterns.p_stop.matcher(message);
			Matcher m_calc = RegexPatterns.p_calc.matcher(message);
			Matcher m_shout = RegexPatterns.p_shout.matcher(message);
			 

			Matcher m_kick = RegexPatterns.p_kick.matcher(message);
			Matcher m_invite = RegexPatterns.p_invite.matcher(message);
			Matcher m_whitelist = RegexPatterns.p_whitelist.matcher(message);
			Matcher m_getinvite = RegexPatterns.p_getinvite.matcher(message);

			 
			Matcher m_global = RegexPatterns.p_global.matcher(message);
			Matcher m_pmRecieved = RegexPatterns.p_pmRecieved.matcher(message);
			Matcher m_loglotto = RegexPatterns.p_loglotto.matcher(message);
//			Matcher m_togglelogblock = RegexPatterns.p_togglelogblock.matcher(message);
			 
			 
			if(m_say.find() && Ranks.checkAdmin(m_say.group(1))) {
				ChatLogger.writeBotActionToFile("User "+m_say.group(1)+" ran: !say "+m_say.group(2));
				Commands.say(m_say.group(2));
			}
			else if(m_ping.find() && Ranks.checkAdmin(m_ping.group(1))) {
				ChatLogger.writeBotActionToFile("User "+m_ping.group(1)+" ran: !ping");
				Commands.ping();
			}
			else if(m_stop.find() && Ranks.checkAdmin(m_stop.group(1))) {
				ChatLogger.writeBotActionToFile("User "+m_stop.group(1)+" ran: !stop");
				Commands.stopBot();
			}
			else if(m_calc.find() && Ranks.checkCaptain(m_calc.group(1))){
				ChatLogger.writeBotActionToFile("User "+m_calc.group(1)+" ran: !calc "+m_calc.group(2)+m_calc.group(3)+m_calc.group(4));
				Commands.calc(m_calc.group(2),m_calc.group(3),m_calc.group(4));
			}
			else if(m_shout.find() && Ranks.checkSergeant(m_shout.group(1))){
				ChatLogger.writeBotActionToFile("User "+m_shout.group(1)+" ran: !shout "+m_calc.group(2));
				Commands.shout(m_shout.group(2));
			}
			else if(m_kick.find() && Ranks.checkPartyOwner(m_kick.group(1))){
				ChatLogger.writeBotActionToFile("User "+m_kick.group(1)+" ran: !kick "+m_kick.group(2)+" "+m_kick.group(3));
				Commands.kick(m_kick.group(1), m_kick.group(2), m_kick.group(3));
			}			
			else if(m_invite.find() && Ranks.checkPartyOwner(m_invite.group(1))){
				ChatLogger.writeBotActionToFile("User "+m_invite.group(1)+" ran: !invite "+m_invite.group(2));
				Commands.invite(m_invite.group(1), m_invite.group(2));
			}
			else if(m_whitelist.find() && Ranks.checkPartyOwner(m_whitelist.group(1))){
				if(m_whitelist.groupCount()==3){
					ChatLogger.writeBotActionToFile("User "+m_whitelist.group(1)+" ran: !whitelist "+m_whitelist.group(2)+" "+m_whitelist.group(3));
					Commands.whitelistCommand(m_whitelist.group(2), m_whitelist.group(1), m_whitelist.group(3));
				}
				else{
					ChatLogger.writeBotActionToFile("User "+m_whitelist.group(1)+" ran: !whitelist "+m_whitelist.group(2));
					Commands.whitelistinfoCommand(m_whitelist.group(2), m_whitelist.group(1));
				}
			}
			else if(m_getinvite.find() && WhitelistHandler.checkWhitelist(m_getinvite.group(1))){
				ChatLogger.writeBotActionToFile("User "+m_getinvite.group(1)+" ran: !getinvite");
				Commands.getinvite(m_getinvite.group(1));
			}
			
			
			else if(m_global.find()){
				Commands.globalMessage(m_global.group(1),m_global.group(2),m_global.group(3),m_global.group(4));
			}
			else if(m_pmRecieved.find()){
				Commands.pmRecieved(m_pmRecieved.group(1), m_pmRecieved.group(2));
			}
			else if(m_loglotto.find()){
				Commands.loglotto(m_loglotto.group(1),m_loglotto.group(2),m_loglotto.group(3));
			}
 	
       }

	    
	    
	    public static void login() {
	    			    
	        MinecraftProtocol protocol = null;
	        if(VERIFY_USERS) {
	            try {
	                protocol = new MinecraftProtocol(USERNAME, PASSWORD, false);
	                System.out.println("Successfully authenticated user.");
	                Console.loginSuccess();
	                
	            } catch(RequestException e) {
	            	Console.loginError();
	                e.printStackTrace();
	                return;
	            }
	        } else {
	            protocol = new MinecraftProtocol(USERNAME);
	        }

	        client = new Client(HOST, PORT, protocol, new TcpSessionFactory(PROXY));
	        client.getSession().setFlag(MinecraftConstants.AUTH_PROXY_KEY, AUTH_PROXY);
	        client.getSession().addListener(new SessionAdapter() {
	            @Override
	            public void packetReceived(PacketReceivedEvent event) {
	            	  
	            	PacketHandler.packetRecieved(event);
	            	
	                if (SpamFilter.check("", "", "", "")){ 
	                	SpamFilter.runCommandQueue();
	                }
    
	            }        
	       
	            
	            @Override
	            public void disconnected(DisconnectedEvent event) {
	                System.out.println("Disconnected: " + Message.fromString(event.getReason()).getFullText());
	                if(event.getCause() != null) {
	                    event.getCause().printStackTrace();
	                }
	            }
	        });

	        client.getSession().connect();       
	        
	    }
	    
	}
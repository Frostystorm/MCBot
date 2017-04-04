import java.util.Arrays;

import org.spacehq.mc.protocol.data.game.PlayerListEntry;
import org.spacehq.mc.protocol.data.game.PlayerListEntryAction;
import org.spacehq.mc.protocol.data.message.Message;
import org.spacehq.mc.protocol.data.message.TranslationMessage;
import org.spacehq.mc.protocol.packet.ingame.server.ServerChatPacket;
import org.spacehq.mc.protocol.packet.ingame.server.ServerJoinGamePacket;
import org.spacehq.mc.protocol.packet.ingame.server.ServerPlayerListEntryPacket;
import org.spacehq.mc.protocol.packet.ingame.server.world.ServerBlockChangePacket;
import org.spacehq.mc.protocol.packet.ingame.server.world.ServerBlockValuePacket;
import org.spacehq.packetlib.event.session.PacketReceivedEvent;



public class PacketHandler {

	private static boolean startLoggingPlayerJoin = false;
    
	public static void packetRecieved(PacketReceivedEvent event){
        if(event.getPacket() instanceof ServerJoinGamePacket) {
            //sendMessage("/p", false);	                	
        } 
//        else if(event.getPacket() instanceof ServerPlayerListEntryPacket) {
//        	
//            if(((ServerPlayerListEntryPacket) event.getPacket()).getAction() == PlayerListEntryAction.ADD_PLAYER){
//            	PlayerListEntry[] entry = ((ServerPlayerListEntryPacket) event.getPacket()).getEntries();
//                    
//            	
//            			if(!entry[0].getProfile().getName().startsWith(" BTLP Slot ") && startLoggingPlayerJoin == true){
//            				System.out.println(entry[0].getProfile().getName()+" joined the server.");
//            			}
//            			
//            			if(entry[0].getProfile().getName().equals("FrostyMaingo")){
//            				startLoggingPlayerJoin = true;
//            			}
//
//            }
//
//        }
        else if(event.getPacket() instanceof ServerChatPacket) {
            Message message = event.<ServerChatPacket>getPacket().getMessage();

			ChatLogger.writeChatToFile(message.getFullText());
            MCBotMain.newChat(message.getFullText());

            if(message instanceof TranslationMessage) {
                System.out.println("Received Translation Components: " + Arrays.toString(((TranslationMessage) message).getTranslationParams()));
            }

           // event.getSession().disconnect("Finished");
        
        }

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

package org.jibble.pircbot.api;

import java.util.Date;
import org.jibble.pircbot.DccChat;
import org.jibble.pircbot.DccFileTransfer;
import org.jibble.pircbot.IrcServerConnection;
import org.jibble.pircbot.User;

/**
 * Internal event handler; 
 * Some events have to be handled with default actions to keep some PircBot goodies work.
 * 
 * @author Ondrej Zizka, ozizka at redhat.com
 */
public class InternalIrcEventHandler implements IIrcEventHandler {
    
    private IrcServerConnection pircBot;


    public InternalIrcEventHandler( IrcServerConnection pircBot ) {
        this.pircBot = pircBot;
    }
    
    
    @Override public void onConnect() {}

    @Override public void onDisconnect() {}

    @Override public void onMode(String channel, String sourceNick, String sourceLogin, String sourceHostname, String mode) {}

    @Override public void onUserMode(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String mode) {}

    @Override public void onOp(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {}

    @Override public void onDeop(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {}


    @Override public void onVoice(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {}

    @Override public void onDeVoice(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {}


    @Override public void onSetChannelKey(String channel, String sourceNick, String sourceLogin, String sourceHostname, String key) {}


    @Override public void onRemoveChannelKey(String channel, String sourceNick, String sourceLogin, String sourceHostname, String key) {}


    @Override public void onSetChannelLimit(String channel, String sourceNick, String sourceLogin, String sourceHostname, int limit) {}


    @Override public void onRemoveChannelLimit(String channel, String sourceNick, String sourceLogin, String sourceHostname) {}


    @Override public void onSetChannelBan(String channel, String sourceNick, String sourceLogin, String sourceHostname, String hostmask) {}


    @Override public void onRemoveChannelBan(String channel, String sourceNick, String sourceLogin, String sourceHostname, String hostmask) {}


    @Override public void onSetTopicProtection(String channel, String sourceNick, String sourceLogin, String sourceHostname) {}


    @Override public void onRemoveTopicProtection(String channel, String sourceNick, String sourceLogin, String sourceHostname) {}


    @Override public void onSetNoExternalMessages(String channel, String sourceNick, String sourceLogin, String sourceHostname) {}


    @Override public void onRemoveNoExternalMessages(String channel, String sourceNick, String sourceLogin, String sourceHostname) {}


    @Override public void onSetInviteOnly(String channel, String sourceNick, String sourceLogin, String sourceHostname) {}


    @Override public void onRemoveInviteOnly(String channel, String sourceNick, String sourceLogin, String sourceHostname) {}


    @Override public void onSetModerated(String channel, String sourceNick, String sourceLogin, String sourceHostname) {}

    @Override public void onRemoveModerated(String channel, String sourceNick, String sourceLogin, String sourceHostname) {}


    @Override public void onSetPrivate(String channel, String sourceNick, String sourceLogin, String sourceHostname) {}


    @Override public void onRemovePrivate(String channel, String sourceNick, String sourceLogin, String sourceHostname) {}


    @Override public void onSetSecret(String channel, String sourceNick, String sourceLogin, String sourceHostname) {}


    @Override public void onRemoveSecret(String channel, String sourceNick, String sourceLogin, String sourceHostname) {}


    
    @Override public void onInvite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel)  {}


    
    @Override public void onDccSendRequest(String sourceNick, String sourceLogin, String sourceHostname, String filename, long address, int port, int size) {}


    
    @Override public void onDccChatRequest(String sourceNick, String sourceLogin, String sourceHostname, long address, int port) {}


    
    @Override public void onIncomingFileTransfer(DccFileTransfer transfer) {}


    
    @Override public void onFileTransferFinished(DccFileTransfer transfer, Exception e) {}


    
    @Override public void onIncomingChatRequest(DccChat chat) {}


    /**
     * This method is called whenever we receive a VERSION request.
     * This abstract implementation responds with the PircBot's _version string,
     * so if you override this method, be sure to either mimic its functionality
     * or to call super.onVersion(...);
     *
     * @param sourceNick The nick of the user that sent the VERSION request.
     * @param sourceLogin The login of the user that sent the VERSION request.
     * @param sourceHostname The hostname of the user that sent the VERSION request.
     * @param target The target of the VERSION request, be it our nick or a channel name.
     */
    @Override public void onVersion(String sourceNick, String sourceLogin, String sourceHostname, String target) {
        pircBot.sendRawLine("NOTICE " + sourceNick + " :\u0001VERSION " + pircBot.getVersion() + "\u0001");
    }


    /**
     * This method is called whenever we receive a PING request from another
     * user.
     *  <p>
     * This abstract implementation responds correctly, so if you override this
     * method, be sure to either mimic its functionality or to call
     * super.onPing(...);
     *
     * @param sourceNick The nick of the user that sent the PING request.
     * @param sourceLogin The login of the user that sent the PING request.
     * @param sourceHostname The hostname of the user that sent the PING request.
     * @param target The target of the PING request, be it our nick or a channel name.
     * @param pingValue The value that was supplied as an argument to the PING command.
     */
    @Override public void onPing(String sourceNick, String sourceLogin, String sourceHostname, String target, String pingValue) {
        pircBot.sendRawLine("NOTICE " + sourceNick + " :\u0001PING " + pingValue + "\u0001");
    }


    /**
     * The actions to perform when a PING request comes from the server.
     *  <p>
     * This sends back a correct response, so if you override this method,
     * be sure to either mimic its functionality or to call
     * super.onServerPing(response);
     *
     * @param response The response that should be given back in your PONG.
     */
    @Override public void onServerPing(String response) {
        pircBot.sendRawLine("PONG " + response);
    }


    /**
     * This method is called whenever we receive a TIME request.
     *  <p>
     * This abstract implementation responds correctly, so if you override this
     * method, be sure to either mimic its functionality or to call
     * super.onTime(...);
     *
     * @param sourceNick The nick of the user that sent the TIME request.
     * @param sourceLogin The login of the user that sent the TIME request.
     * @param sourceHostname The hostname of the user that sent the TIME request.
     * @param target The target of the TIME request, be it our nick or a channel name.
     */
    @Override public void onTime(String sourceNick, String sourceLogin, String sourceHostname, String target) {
        pircBot.sendRawLine("NOTICE " + sourceNick + " :\u0001TIME " + new Date().toString() + "\u0001");
    }


    /**
     * This method is called whenever we receive a FINGER request.
     *  <p>
     * This abstract implementation responds correctly, so if you override this
     * method, be sure to either mimic its functionality or to call
     * super.onFinger(...);
     *
     * @param sourceNick The nick of the user that sent the FINGER request.
     * @param sourceLogin The login of the user that sent the FINGER request.
     * @param sourceHostname The hostname of the user that sent the FINGER request.
     * @param target The target of the FINGER request, be it our nick or a channel name.
     */
    @Override public void onFinger(String sourceNick, String sourceLogin, String sourceHostname, String target) {
        pircBot.sendRawLine("NOTICE " + sourceNick + " :\u0001FINGER " + pircBot.getFinger() + "\u0001");
    }

    
    @Override public void onUnknown(String line) {}

    public void onServerResponse(int code, String response) {}
    
    public void onUserList(String channel, User[] users) {}
    
    public void onMessage(String channel, String sender, String login, String hostname, String message) {}
    
    public void onPrivateMessage(String sender, String login, String hostname, String message) {}
    
    public void onAction(String sender, String login, String hostname, String target, String action) {}
    
    public void onNotice(String sourceNick, String sourceLogin, String sourceHostname, String target, String notice) {}
    
    public void onJoin(String channel, String sender, String login, String hostname) {}
    
    public void onPart(String channel, String sender, String login, String hostname) {}

    public void onNickChange(String oldNick, String login, String hostname, String newNick) {}

    
    public void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason) {}
    
    public void onQuit(String sourceNick, String sourceLogin, String sourceHostname, String reason) {}
    
    public void onTopic(String channel, String topic) {}

    public void onTopic(String channel, String topic, String setBy, long date, boolean changed) {}

    public void onChannelInfo(String channel, int userCount, String topic) {}

}// interface

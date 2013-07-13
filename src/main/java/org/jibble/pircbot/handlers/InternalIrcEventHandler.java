package org.jibble.pircbot.handlers;

import java.util.Date;
import org.jibble.pircbot.IrcServerConnection;
import org.jibble.pircbot.api.IrcEventHandlerBase;

/**
 * Default implementation of IRC event handler, which reacts to basic IRC protocol events like PING.
 * 
 * @author Ondrej Zizka, ozizka at redhat.com
 */
public class InternalIrcEventHandler extends IrcEventHandlerBase {
    
    private IrcServerConnection pircBot;


    public InternalIrcEventHandler( IrcServerConnection pircBot ) {
        this.pircBot = pircBot;
    }
    

    
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

}// class

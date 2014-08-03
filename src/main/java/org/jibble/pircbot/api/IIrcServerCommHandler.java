package org.jibble.pircbot.api;

/**
 * The minimum methods required to be implemented for every IRC client.
 * 
 * @since 1.8.0
 * 
 * @author wwsean08
 *
 */
public interface IIrcServerCommHandler
{
    /**
     * This method is called whenever we receive a FINGER request.
     * <p>
     * This abstract implementation responds correctly, so if you override this
     * method, be sure to either mimic its functionality or to call
     * super.onFinger(...);
     *
     * @param sourceNick
     *            The nick of the user that sent the FINGER request.
     * @param sourceLogin
     *            The login of the user that sent the FINGER request.
     * @param sourceHostname
     *            The hostname of the user that sent the FINGER request.
     * @param target
     *            The target of the FINGER request, be it our nick or a channel
     *            name.
     */
    void onFinger(String sourceNick, String sourceLogin, String sourceHostname, String target);
    
    /**
     * This method is called whenever we receive a PING request from another
     * user.
     * <p>
     * This abstract implementation responds correctly, so if you override this
     * method, be sure to either mimic its functionality or to call
     * super.onPing(...);
     *
     * @param sourceNick
     *            The nick of the user that sent the PING request.
     * @param sourceLogin
     *            The login of the user that sent the PING request.
     * @param sourceHostname
     *            The hostname of the user that sent the PING request.
     * @param target
     *            The target of the PING request, be it our nick or a channel
     *            name.
     * @param pingValue
     *            The value that was supplied as an argument to the PING
     *            command.
     */
    void onPing(String sourceNick, String sourceLogin, String sourceHostname, String target, String pingValue);
    
    /**
     * The actions to perform when a PING request comes from the server.
     * <p>
     * This sends back a correct response, so if you override this method, be
     * sure to either mimic its functionality or to call
     * super.onServerPing(response);
     *
     * @param response
     *            The response that should be given back in your PONG.
     */
    void onServerPing(String response);
    
    /**
     * This method is called whenever we receive a TIME request.
     * <p>
     * This abstract implementation responds correctly, so if you override this
     * method, be sure to either mimic its functionality or to call
     * super.onTime(...);
     *
     * @param sourceNick
     *            The nick of the user that sent the TIME request.
     * @param sourceLogin
     *            The login of the user that sent the TIME request.
     * @param sourceHostname
     *            The hostname of the user that sent the TIME request.
     * @param target
     *            The target of the TIME request, be it our nick or a channel
     *            name.
     */
    void onTime(String sourceNick, String sourceLogin, String sourceHostname, String target);
    
    /**
     * This method is called whenever we receive a line from the server that the
     * PircBot has not been programmed to recognize.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @param line
     *            The raw line that was received from the server.
     */
    void onUnknown(String line);
    
    /**
     * This method is called when we receive a numeric response from the IRC
     * server.
     * <p>
     * Numerics in the range from 001 to 099 are used for client-server
     * connections only and should never travel between servers. Replies
     * generated in response to commands are found in the range from 200 to 399.
     * Error replies are found in the range from 400 to 599.
     * <p>
     * For example, we can use this method to discover the topic of a channel
     * when we join it. If we join the channel #test which has a topic of
     * &quot;I am King of Test&quot; then the response will be &quot;
     * <code>PircBot #test :I Am King of Test</code>&quot; with a code of 332 to
     * signify that this is a topic. (This is just an example - note that
     * overriding the <code>onTopic</code> method is an easier way of finding
     * the topic for a channel). Check the IRC RFC for the full list of other
     * command response codes.
     * <p>
     * PircBot implements the interface ReplyConstants, which contains
     * contstants that you may find useful here.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @param code
     *            The three-digit numerical code for the response.
     * @param response
     *            The full response from the IRC server.
     *
     * @see ReplyConstants
     */
    void onServerResponse(int code, String response);
    
    /**
     * This method is called whenever we receive a VERSION request. This
     * abstract implementation responds with the PircBot's _version string, so
     * if you override this method, be sure to either mimic its functionality or
     * to call super.onVersion(...);
     *
     * @param sourceNick
     *            The nick of the user that sent the VERSION request.
     * @param sourceLogin
     *            The login of the user that sent the VERSION request.
     * @param sourceHostname
     *            The hostname of the user that sent the VERSION request.
     * @param target
     *            The target of the VERSION request, be it our nick or a channel
     *            name.
     */
    void onVersion(String sourceNick, String sourceLogin, String sourceHostname, String target);
}

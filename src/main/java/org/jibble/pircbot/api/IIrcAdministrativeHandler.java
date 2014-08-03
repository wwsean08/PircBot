package org.jibble.pircbot.api;

import org.jibble.pircbot.beans.User;

/**
 * An interface for handling administrative tasks like op/deop, kick, join, part
 * etc.
 * 
 * @since 1.8.0
 * 
 * @author wwsean08
 *
 */
public interface IIrcAdministrativeHandler
{
    /**
     * After calling the listChannels() method in PircBot, the server will start
     * to send us information about each channel on the server. You may override
     * this method in order to receive the information about each channel as
     * soon as it is received.
     * <p>
     * Note that certain channels, such as those marked as hidden, may not
     * appear in channel listings.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @param channel
     *            The name of the channel.
     * @param userCount
     *            The number of users visible in this channel.
     * @param topic
     *            The topic for this channel.
     *
     * @see #listChannels() listChannels
     */
    void onChannelInfo(String channel, int userCount, String topic);
    
    /**
     * This method is called once the PircBot has successfully connected to the
     * IRC server.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.6
     */
    void onConnect();
    
    /**
     * Called when a user (possibly us) gets operator status taken away.
     * <p>
     * This is a type of mode change and is also passed to the onMode method in
     * the PircBot class.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.5
     *
     * @param channel
     *            The channel in which the mode change took place.
     * @param sourceNick
     *            The nick of the user that performed the mode change.
     * @param sourceLogin
     *            The login of the user that performed the mode change.
     * @param sourceHostname
     *            The hostname of the user that performed the mode change.
     * @param recipient
     *            The nick of the user that got 'deopped'.
     */
    void onDeop(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient);
    
    /**
     * Called when a user (possibly us) gets voice status removed.
     * <p>
     * This is a type of mode change and is also passed to the onMode method in
     * the PircBot class.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.5
     *
     * @param channel
     *            The channel in which the mode change took place.
     * @param sourceNick
     *            The nick of the user that performed the mode change.
     * @param sourceLogin
     *            The login of the user that performed the mode change.
     * @param sourceHostname
     *            The hostname of the user that performed the mode change.
     * @param recipient
     *            The nick of the user that got 'devoiced'.
     */
    void onDeVoice(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient);
    
    /**
     * This method carries out the actions to be performed when the PircBot gets
     * disconnected. This may happen if the PircBot quits from the server, or if
     * the connection is unexpectedly lost.
     * <p>
     * Disconnection from the IRC server is detected immediately if either we or
     * the server close the connection normally. If the connection to the server
     * is lost, but neither we nor the server have explicitly closed the
     * connection, then it may take a few minutes to detect (this is commonly
     * referred to as a "ping timeout").
     * <p>
     * If you wish to get your IRC bot to automatically rejoin a server after
     * the connection has been lost, then this is probably the ideal method to
     * override to implement such functionality.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     */
    void onDisconnect();
    
    /**
     * Called when we are invited to a channel by a user.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.5
     *
     * @param targetNick
     *            The nick of the user being invited - should be us!
     * @param sourceNick
     *            The nick of the user that sent the invitation.
     * @param sourceLogin
     *            The login of the user that sent the invitation.
     * @param sourceHostname
     *            The hostname of the user that sent the invitation.
     * @param channel
     *            The channel that we're being invited to.
     */
    public void onInvite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel);
    
    /**
     * This method is called whenever someone (possibly us) joins a channel
     * which we are on.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @param channel
     *            The channel which somebody joined.
     * @param sender
     *            The nick of the user who joined the channel.
     * @param login
     *            The login of the user who joined the channel.
     * @param hostname
     *            The hostname of the user who joined the channel.
     */
    void onJoin(String channel, String sender, String login, String hostname);
    
    /**
     * This method is called whenever someone (possibly us) is kicked from any
     * of the channels that we are in.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @param channel
     *            The channel from which the recipient was kicked.
     * @param kickerNick
     *            The nick of the user who performed the kick.
     * @param kickerLogin
     *            The login of the user who performed the kick.
     * @param kickerHostname
     *            The hostname of the user who performed the kick.
     * @param recipientNick
     *            The unfortunate recipient of the kick.
     * @param reason
     *            The reason given by the user who performed the kick.
     */
    void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick,
            String reason);
    
    /**
     * Called when the mode of a channel is set.
     * <p>
     * You may find it more convenient to decode the meaning of the mode string
     * by overriding the onOp, onDeOp, onVoice, onDeVoice, onChannelKey,
     * onDeChannelKey, onChannelLimit, onDeChannelLimit, onChannelBan or
     * onDeChannelBan methods as appropriate.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @param channel
     *            The channel that the mode operation applies to.
     * @param sourceNick
     *            The nick of the user that set the mode.
     * @param sourceLogin
     *            The login of the user that set the mode.
     * @param sourceHostname
     *            The hostname of the user that set the mode.
     * @param mode
     *            The mode that has been set.
     *
     */
    void onMode(String channel, String sourceNick, String sourceLogin, String sourceHostname, String mode);
    
    /**
     * This method is called whenever someone (possibly us) changes nick on any
     * of the channels that we are on.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @param oldNick
     *            The old nick.
     * @param login
     *            The login of the user.
     * @param hostname
     *            The hostname of the user.
     * @param newNick
     *            The new nick.
     */
    void onNickChange(String oldNick, String login, String hostname, String newNick);
    
    /**
     * Called when a user (possibly us) gets granted operator status for a
     * channel.
     * <p>
     * This is a type of mode change and is also passed to the onMode method in
     * the PircBot class.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.5
     *
     * @param channel
     *            The channel in which the mode change took place.
     * @param sourceNick
     *            The nick of the user that performed the mode change.
     * @param sourceLogin
     *            The login of the user that performed the mode change.
     * @param sourceHostname
     *            The hostname of the user that performed the mode change.
     * @param recipient
     *            The nick of the user that got 'opped'.
     */
    void onOp(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient);
    
    /**
     * This method is called whenever someone (possibly us) parts a channel
     * which we are on.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @param channel
     *            The channel which somebody parted from.
     * @param sender
     *            The nick of the user who parted from the channel.
     * @param login
     *            The login of the user who parted from the channel.
     * @param hostname
     *            The hostname of the user who parted from the channel.
     */
    void onPart(String channel, String sender, String login, String hostname);
    
    /**
     * This method is called whenever someone (possibly us) quits from the
     * server. We will only observe this if the user was in one of the channels
     * to which we are connected.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @param sourceNick
     *            The nick of the user that quit from the server.
     * @param sourceLogin
     *            The login of the user that quit from the server.
     * @param sourceHostname
     *            The hostname of the user that quit from the server.
     * @param reason
     *            The reason given for quitting the server.
     */
    void onQuit(String sourceNick, String sourceLogin, String sourceHostname, String reason);
    
    /**
     * Called when a hostmask ban is removed from a channel.
     * <p>
     * This is a type of mode change and is also passed to the onMode method in
     * the PircBot class.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.5
     *
     * @param channel
     *            The channel in which the mode change took place.
     * @param sourceNick
     *            The nick of the user that performed the mode change.
     * @param sourceLogin
     *            The login of the user that performed the mode change.
     * @param sourceHostname
     *            The hostname of the user that performed the mode change.
     * @param hostmask
     */
    public void onRemoveChannelBan(String channel, String sourceNick, String sourceLogin, String sourceHostname,
            String hostmask);
    
    /**
     * Called when a channel key is removed.
     * <p>
     * This is a type of mode change and is also passed to the onMode method in
     * the PircBot class.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.5
     *
     * @param channel
     *            The channel in which the mode change took place.
     * @param sourceNick
     *            The nick of the user that performed the mode change.
     * @param sourceLogin
     *            The login of the user that performed the mode change.
     * @param sourceHostname
     *            The hostname of the user that performed the mode change.
     * @param key
     *            The key that was in use before the channel key was removed.
     */
    public void onRemoveChannelKey(String channel, String sourceNick, String sourceLogin, String sourceHostname,
            String key);
    
    /**
     * Called when the user limit is removed for a channel.
     * <p>
     * This is a type of mode change and is also passed to the onMode method in
     * the PircBot class.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.5
     *
     * @param channel
     *            The channel in which the mode change took place.
     * @param sourceNick
     *            The nick of the user that performed the mode change.
     * @param sourceLogin
     *            The login of the user that performed the mode change.
     * @param sourceHostname
     *            The hostname of the user that performed the mode change.
     */
    public void onRemoveChannelLimit(String channel, String sourceNick, String sourceLogin, String sourceHostname);
    
    /**
     * Called when a channel has 'invite only' removed.
     * <p>
     * This is a type of mode change and is also passed to the onMode method in
     * the PircBot class.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.5
     *
     * @param channel
     *            The channel in which the mode change took place.
     * @param sourceNick
     *            The nick of the user that performed the mode change.
     * @param sourceLogin
     *            The login of the user that performed the mode change.
     * @param sourceHostname
     *            The hostname of the user that performed the mode change.
     */
    public void onRemoveInviteOnly(String channel, String sourceNick, String sourceLogin, String sourceHostname);
    
    /**
     * Called when a channel has moderated mode removed.
     * <p>
     * This is a type of mode change and is also passed to the onMode method in
     * the PircBot class.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.5
     *
     * @param channel
     *            The channel in which the mode change took place.
     * @param sourceNick
     *            The nick of the user that performed the mode change.
     * @param sourceLogin
     *            The login of the user that performed the mode change.
     * @param sourceHostname
     *            The hostname of the user that performed the mode change.
     */
    public void onRemoveModerated(String channel, String sourceNick, String sourceLogin, String sourceHostname);
    
    /**
     * Called when a channel is set to allow messages from any user, even if
     * they are not actually in the channel.
     * <p>
     * This is a type of mode change and is also passed to the onMode method in
     * the PircBot class.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.5
     *
     * @param channel
     *            The channel in which the mode change took place.
     * @param sourceNick
     *            The nick of the user that performed the mode change.
     * @param sourceLogin
     *            The login of the user that performed the mode change.
     * @param sourceHostname
     *            The hostname of the user that performed the mode change.
     */
    public void onRemoveNoExternalMessages(String channel, String sourceNick, String sourceLogin, String sourceHostname);
    
    /**
     * Called when a channel is marked as not being in private mode.
     * <p>
     * This is a type of mode change and is also passed to the onMode method in
     * the PircBot class.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.5
     *
     * @param channel
     *            The channel in which the mode change took place.
     * @param sourceNick
     *            The nick of the user that performed the mode change.
     * @param sourceLogin
     *            The login of the user that performed the mode change.
     * @param sourceHostname
     *            The hostname of the user that performed the mode change.
     */
    public void onRemovePrivate(String channel, String sourceNick, String sourceLogin, String sourceHostname);
    
    /**
     * Called when a channel has 'secret' mode removed.
     * <p>
     * This is a type of mode change and is also passed to the onMode method in
     * the PircBot class.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.5
     *
     * @param channel
     *            The channel in which the mode change took place.
     * @param sourceNick
     *            The nick of the user that performed the mode change.
     * @param sourceLogin
     *            The login of the user that performed the mode change.
     * @param sourceHostname
     *            The hostname of the user that performed the mode change.
     */
    public void onRemoveSecret(String channel, String sourceNick, String sourceLogin, String sourceHostname);
    
    /**
     * Called when topic protection is removed for a channel.
     * <p>
     * This is a type of mode change and is also passed to the onMode method in
     * the PircBot class.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.5
     *
     * @param channel
     *            The channel in which the mode change took place.
     * @param sourceNick
     *            The nick of the user that performed the mode change.
     * @param sourceLogin
     *            The login of the user that performed the mode change.
     * @param sourceHostname
     *            The hostname of the user that performed the mode change.
     */
    public void onRemoveTopicProtection(String channel, String sourceNick, String sourceLogin, String sourceHostname);
    
    /**
     * Called when a user (possibly us) gets banned from a channel. Being banned
     * from a channel prevents any user with a matching hostmask from joining
     * the channel. For this reason, most bans are usually directly followed by
     * the user being kicked :-)
     * <p>
     * This is a type of mode change and is also passed to the onMode method in
     * the PircBot class.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.5
     *
     * @param channel
     *            The channel in which the mode change took place.
     * @param sourceNick
     *            The nick of the user that performed the mode change.
     * @param sourceLogin
     *            The login of the user that performed the mode change.
     * @param sourceHostname
     *            The hostname of the user that performed the mode change.
     * @param hostmask
     *            The hostmask of the user that has been banned.
     */
    public void onSetChannelBan(String channel, String sourceNick, String sourceLogin, String sourceHostname,
            String hostmask);
    
    /**
     * Called when a channel key is set. When the channel key has been set,
     * other users may only join that channel if they know the key. Channel keys
     * are sometimes referred to as passwords.
     * <p>
     * This is a type of mode change and is also passed to the onMode method in
     * the PircBot class.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.5
     *
     * @param channel
     *            The channel in which the mode change took place.
     * @param sourceNick
     *            The nick of the user that performed the mode change.
     * @param sourceLogin
     *            The login of the user that performed the mode change.
     * @param sourceHostname
     *            The hostname of the user that performed the mode change.
     * @param key
     *            The new key for the channel.
     */
    public void onSetChannelKey(String channel, String sourceNick, String sourceLogin, String sourceHostname, String key);
    
    /**
     * Called when a user limit is set for a channel. The number of users in the
     * channel cannot exceed this limit.
     * <p>
     * This is a type of mode change and is also passed to the onMode method in
     * the PircBot class.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.5
     *
     * @param channel
     *            The channel in which the mode change took place.
     * @param sourceNick
     *            The nick of the user that performed the mode change.
     * @param sourceLogin
     *            The login of the user that performed the mode change.
     * @param sourceHostname
     *            The hostname of the user that performed the mode change.
     * @param limit
     *            The maximum number of users that may be in this channel at the
     *            same time.
     */
    public void onSetChannelLimit(String channel, String sourceNick, String sourceLogin, String sourceHostname,
            int limit);
    
    /**
     * Called when a channel is set to 'invite only' mode. A user may only join
     * the channel if they are invited by someone who is already in the channel.
     * <p>
     * This is a type of mode change and is also passed to the onMode method in
     * the PircBot class.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.5
     *
     * @param channel
     *            The channel in which the mode change took place.
     * @param sourceNick
     *            The nick of the user that performed the mode change.
     * @param sourceLogin
     *            The login of the user that performed the mode change.
     * @param sourceHostname
     *            The hostname of the user that performed the mode change.
     */
    public void onSetInviteOnly(String channel, String sourceNick, String sourceLogin, String sourceHostname);
    
    /**
     * Called when a channel is set to 'moderated' mode. If a channel is
     * moderated, then only users who have been 'voiced' or 'opped' may speak or
     * change their nicks.
     * <p>
     * This is a type of mode change and is also passed to the onMode method in
     * the PircBot class.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.5
     *
     * @param channel
     *            The channel in which the mode change took place.
     * @param sourceNick
     *            The nick of the user that performed the mode change.
     * @param sourceLogin
     *            The login of the user that performed the mode change.
     * @param sourceHostname
     *            The hostname of the user that performed the mode change.
     */
    public void onSetModerated(String channel, String sourceNick, String sourceLogin, String sourceHostname);
    
    /**
     * Called when a channel is set to only allow messages from users that are
     * in the channel.
     * <p>
     * This is a type of mode change and is also passed to the onMode method in
     * the PircBot class.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.5
     *
     * @param channel
     *            The channel in which the mode change took place.
     * @param sourceNick
     *            The nick of the user that performed the mode change.
     * @param sourceLogin
     *            The login of the user that performed the mode change.
     * @param sourceHostname
     *            The hostname of the user that performed the mode change.
     */
    public void onSetNoExternalMessages(String channel, String sourceNick, String sourceLogin, String sourceHostname);
    
    /**
     * Called when a channel is marked as being in private mode.
     * <p>
     * This is a type of mode change and is also passed to the onMode method in
     * the PircBot class.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.5
     *
     * @param channel
     *            The channel in which the mode change took place.
     * @param sourceNick
     *            The nick of the user that performed the mode change.
     * @param sourceLogin
     *            The login of the user that performed the mode change.
     * @param sourceHostname
     *            The hostname of the user that performed the mode change.
     */
    public void onSetPrivate(String channel, String sourceNick, String sourceLogin, String sourceHostname);
    
    /**
     * Called when a channel is set to be in 'secret' mode. Such channels
     * typically do not appear on a server's channel listing.
     * <p>
     * This is a type of mode change and is also passed to the onMode method in
     * the PircBot class.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.5
     *
     * @param channel
     *            The channel in which the mode change took place.
     * @param sourceNick
     *            The nick of the user that performed the mode change.
     * @param sourceLogin
     *            The login of the user that performed the mode change.
     * @param sourceHostname
     *            The hostname of the user that performed the mode change.
     */
    public void onSetSecret(String channel, String sourceNick, String sourceLogin, String sourceHostname);
    
    /**
     * Called when topic protection is enabled for a channel. Topic protection
     * means that only operators in a channel may change the topic.
     * <p>
     * This is a type of mode change and is also passed to the onMode method in
     * the PircBot class.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.5
     *
     * @param channel
     *            The channel in which the mode change took place.
     * @param sourceNick
     *            The nick of the user that performed the mode change.
     * @param sourceLogin
     *            The login of the user that performed the mode change.
     * @param sourceHostname
     *            The hostname of the user that performed the mode change.
     */
    public void onSetTopicProtection(String channel, String sourceNick, String sourceLogin, String sourceHostname);
    
    /**
     * This method is called whenever a user sets the topic, or when PircBot
     * joins a new channel and discovers its topic.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @param channel
     *            The channel that the topic belongs to.
     * @param topic
     *            The topic for the channel.
     *
     * @deprecated As of 1.2.0, replaced by
     *             {@link #onTopic(String,String,String,long,boolean)}
     */
    void onTopic(String channel, String topic);
    
    /**
     * This method is called whenever a user sets the topic, or when PircBot
     * joins a new channel and discovers its topic.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @param channel
     *            The channel that the topic belongs to.
     * @param topic
     *            The topic for the channel.
     * @param setBy
     *            The nick of the user that set the topic.
     * @param date
     *            When the topic was set (milliseconds since the epoch).
     * @param changed
     *            True if the topic has just been changed, false if the topic
     *            was already there.
     *
     */
    void onTopic(String channel, String topic, String setBy, long date, boolean changed);
    
    /**
     * This method is called whenever we receive a line from the server that the
     * PircBot has not been programmed to recognise.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @param line
     *            The raw line that was received from the server.
     */
    void onUnknown(String line);
    
    /**
     * This method is called when we receive a user list from the server after
     * joining a channel.
     * <p>
     * Shortly after joining a channel, the IRC server sends a list of all users
     * in that channel. The PircBot collects this information and calls this
     * method as soon as it has the full list.
     * <p>
     * To obtain the nick of each user in the channel, call the getNick() method
     * on each User object in the array.
     * <p>
     * At a later time, you may call the getUsers method to obtain an up to date
     * list of the users in the channel.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 1.0.0
     *
     * @param channel
     *            The name of the channel.
     * @param users
     *            An array of User objects belonging to this channel.
     *
     * @see User
     */
    void onUserList(String channel, User[] users);
    
    /**
     * Called when the mode of a user is set.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 1.2.0
     *
     * @param targetNick
     *            The nick that the mode operation applies to.
     * @param sourceNick
     *            The nick of the user that set the mode.
     * @param sourceLogin
     *            The login of the user that set the mode.
     * @param sourceHostname
     *            The hostname of the user that set the mode.
     * @param mode
     *            The mode that has been set.
     *
     */
    void onUserMode(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String mode);
    
    /**
     * Called when a user (possibly us) gets voice status granted in a channel.
     * <p>
     * This is a type of mode change and is also passed to the onMode method in
     * the PircBot class.
     * <p>
     * The implementation of this method in the PircBot abstract class performs
     * no actions and may be overridden as required.
     *
     * @since PircBot 0.9.5
     *
     * @param channel
     *            The channel in which the mode change took place.
     * @param sourceNick
     *            The nick of the user that performed the mode change.
     * @param sourceLogin
     *            The login of the user that performed the mode change.
     * @param sourceHostname
     *            The hostname of the user that performed the mode change.
     * @param recipient
     *            The nick of the user that got 'voiced'.
     */
    void onVoice(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient);
}

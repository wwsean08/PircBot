/*
Copyright Paul James Mutton, 2001-2009, http://www.jibble.org/
This file is part of PircBot.

This software is dual-licensed, allowing you to choose between the GNU
General Public License (GPL) and the www.jibble.org Commercial License.
Since the GPL may be too restrictive for use in a proprietary application,
a commercial license is also provided. Full license information can be
found at http://www.jibble.org/licenses/
 */
package org.jibble.pircbot.threads;

import java.io.*;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

import org.jibble.pircbot.IrcServerConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Thread which is responsible for sending messages to the IRC server.
 * Messages are obtained from the outgoing message queue and sent immediately if
 * possible. If there is a flood of messages, then to avoid getting kicked from
 * a channel, we put a small delay between each one.
 *
 * @author Paul James Mutton, <a
 *         href="http://www.jibble.org/">http://www.jibble.org/</a>
 */
public class OutputThread extends Thread
{
    private static final Logger log = LoggerFactory.getLogger(OutputThread.class);
    
    /**
     * Constructs an OutputThread for the underlying PircBot. All messages sent
     * to the IRC server are sent by this OutputThread to avoid hammering the
     * server. Messages are sent immediately if possible. If there are multiple
     * messages queued, then there is a delay imposed.
     *
     * @param bot
     *            The underlying PircBot instance.
     * @param outQueue
     *            The Queue from which we will obtain our messages.
     */
    public OutputThread(IrcServerConnection bot, Queue<String> outQueue)
    {
        _bot = bot;
        _outQueue = (LinkedBlockingDeque<String>) outQueue;
        this.setName("output-thread");
    }
    
    /**
     * A static method to write a line to a BufferedOutputStream and then pass
     * the line to the log method of the supplied PircBot instance.
     *
     * @param bot
     *            The underlying PircBot instance.
     * @param out
     *            The BufferedOutputStream to write to.
     * @param line
     *            The line to be written. "\r\n" is appended to the end.
     * @param encoding
     *            The charset to use when encoing this string into a byte array.
     */
    public static void sendRawLine(IrcServerConnection bot, BufferedWriter bwriter, String line)
    {
        if (line.length() > bot.getMaxLineLength() - 2)
        {
            line = line.substring(0, bot.getMaxLineLength() - 2);
        }
        synchronized (bwriter)
        {
            try
            {
                bwriter.write(line + "\r\n");
                bwriter.flush();
                if (log.isDebugEnabled())
                    log.debug(">>>" + line);
            }
            catch (Exception e)
            {
                // Silent response - just lose the line.
            }
        }
    }
    
    /**
     * This method starts the Thread consuming from the outgoing message Queue
     * and sending lines to the server.
     */
    public void run()
    {
        try
        {
            boolean running = true;
            while (running)
            {
                // Small delay to prevent spamming of the channel
                Thread.sleep(_bot.getMessageDelay());
                
                String line = _outQueue.take();
                if (line != null)
                {
                    _bot.sendRawLine(line);
                }
                else
                {
                    running = false;
                }
            }
        }
        catch (InterruptedException e)
        {
            // Just let the method return naturally...
            log.error("Interrupt exception in " + this.getName(), e);
        }
    }
    
    private IrcServerConnection _bot = null;
    private LinkedBlockingDeque<String> _outQueue = null;
    
}

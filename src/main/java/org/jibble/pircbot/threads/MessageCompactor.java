package org.jibble.pircbot.threads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Removed duplicate objects from the queue based on the .equals method. There
 * is no guarantee that all objects will be removed however due to the fact that
 * multiple threads are accessing and modifying the queue
 * 
 * @author wwsean08
 *
 */
public class MessageCompactor extends Thread
{
    Logger log = LoggerFactory.getLogger(getClass());
    LinkedBlockingDeque<String> queue;
    
    public MessageCompactor(LinkedBlockingDeque<String> _outQueue)
    {
        this.queue = _outQueue;
        this.setName("message-compactor");
    }
    
    public void run()
    {
        // no need to do anything on a queue of size 0 or 1
        if (queue.isEmpty() || queue.size() == 1)
        {
            return;
        }
        log.trace("begin compacting output queue which currently has " + queue.size() + " elements");
        List<String> messageList = new ArrayList<>();
        Iterator<String> iterator = queue.iterator();
        int messageRemovedCount = 0;
        while (iterator.hasNext())
        {
            boolean removedItem = false;
            String message = iterator.next();
            // we already read this message, lets remove it
            if (messageList.contains(message))
            {
                // the remove last occurrence will prevent is from removing
                // Occurrences that are closer to the head
                queue.removeLastOccurrence(message);
                removedItem = true;
                messageRemovedCount++;
            }
            if (!removedItem)
            {
                messageList.add(message);
            }
        }
        log.debug(messageRemovedCount + " messages removed from output queue");
    }
}

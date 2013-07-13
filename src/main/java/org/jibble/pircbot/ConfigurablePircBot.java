/*
Author: David Lazar
*/
package org.jibble.pircbot;

import org.jibble.pircbot.beans.ConnectionSettings;
import java.io.*;
import org.apache.commons.configuration.*;

public abstract class ConfigurablePircBot extends IrcServerConnection {
    private Configuration configuration;

    public Configuration getConfiguration() {
        return configuration;
    }

    public void initBot(String fileName) throws Exception {
        initBot(new File(fileName));
    }

    public void initBot(File file) throws Exception {
        PropertiesConfiguration conf = new PropertiesConfiguration();
        conf.setDelimiterParsingDisabled(true);
        conf.load(file);
        initBot(conf);
    }

    public void initBot(Configuration conf) throws Exception {
        this.configuration = conf;

        if (conf.containsKey("Verbose")) {
            setVerbose(conf.getBoolean("Verbose"));
        }

        if (conf.containsKey("Nick")) {
            setName(conf.getString("Nick"));
        }

        if (conf.containsKey("UserName")) {
            setUserName(conf.getString("UserName"));
        }

        if (conf.containsKey("RealName")) {
            setRealName(conf.getString("RealName"));
        }

        if (conf.containsKey("Version")) {
            setVersion(conf.getString("Version"));
        }

        if (conf.containsKey("Finger")) {
            setFinger(conf.getString("Finger"));
        }

        if (conf.containsKey("Server")) {
            ConnectionSettings cs = new ConnectionSettings(conf.getString("Server"));

            if (conf.containsKey("Port")) {
                cs.port = conf.getInt("Port");
            }

            if (conf.containsKey("SSL")) {
                cs.useSSL = conf.getBoolean("SSL");
            }

            if (conf.containsKey("VerifySSL")) {
                cs.verifySSL = conf.getBoolean("VerifySSL");
            }

            if (conf.containsKey("Password")) {
                cs.password = conf.getString("Password");
            }

            connect(cs);

            if (conf.containsKey("Channels")) {
                joinChannel(conf.getString("Channels"));
            }
        }
    }// initBot()
    
}// class

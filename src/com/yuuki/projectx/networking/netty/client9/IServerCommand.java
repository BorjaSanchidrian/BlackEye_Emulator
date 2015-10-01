package com.yuuki.projectx.networking.netty.client9;

import java.io.DataOutputStream;

/**
 * @author Yuuki
 * @date 22/06/2015
 * @package simulator.netty
 * @project S7KBetaServer
 */
public interface IServerCommand {
    void write(DataOutputStream out);
}

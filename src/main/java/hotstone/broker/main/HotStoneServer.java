/*
 * Copyright (C) 2022. Henrik Bærbak Christensen, Aarhus University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package hotstone.broker.main;

import frds.broker.Invoker;
import frds.broker.ipc.http.UriTunnelServerRequestHandler;
import hotstone.broker.common.BrokerConstants;
import hotstone.broker.doubles.StubGameForBroker;
import hotstone.broker.server.HotStoneGameInvoker;
import hotstone.framework.Game;

/** The main program running the HotStone Server,
 * using the FRDS.Broker library's ServerRequestHandler
 * in the HTTP variant running on port 5555.
 */
public class HotStoneServer {
  public static void main(String[] args) throws Exception {
    new HotStoneServer();
  }

  public HotStoneServer() {
    int port = BrokerConstants.HOTSTONE_PORT;
    // Define the server side root servant
    Game servant = new StubGameForBroker();

    // Create server side implementation of Broker roles
    Invoker invoker = new HotStoneGameInvoker(servant);
    UriTunnelServerRequestHandler srh =
            new UriTunnelServerRequestHandler(invoker, port,
                    BrokerConstants.HOTSTONE_TUNNEL_PATH);
    srh.start();

    // Welcome
    System.out.println("=== HotStone Spark based Server Request Handler (port:"
            + port + ") ===");
    System.out.println(" Use ctrl-c to terminate!");
  }

}

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
package hotstone.broker;

import hotstone.broker.client.CardClientProxy;
import hotstone.broker.doubles.NullObserver;
import hotstone.broker.doubles.StubCardForBroker;
import hotstone.broker.server.CardInvoker;
import hotstone.framework.*;

import hotstone.observer.GameObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;

import hotstone.broker.client.GameClientProxy;
import hotstone.broker.doubles.LocalMethodClientRequestHandler;
import hotstone.broker.doubles.StubGameForBroker;
import hotstone.broker.server.HotStoneGameInvoker;

/** Skeleton test case for a 'depth-first' test-driven
 * development process to develop the Broker implementation
 * of client proxies and invokers, for all 'primitive' methods
 * in Game, that is, methods that do NOT take objects as
 * parameters, only primitive types and Strings.
 */
public class TestCardBroker {
    private Card card;
    private StubCardForBroker servant;

    @BeforeEach
    public void setup() {
        // === We start at the server side of the Broker pattern:
        // define the servant, next the invoker

        // Given a Servant game, here a test stub with canned output
       /* servant = new StubCardForBroker();
        //servant.addObserver(nullObserver);
        // Which is injected into the dedicated Invoker which you must
        // develop
        Invoker invoker = new CardInvoker(servant);

        // === Next define the client side of the pattern:
        // the client request handler, the requestor, and the client proxy

        // Instead of a network-based client- and server request handler
        // we make a fake object CRH that talks directly with the injected
        // invoker
        ClientRequestHandler crh =
                new LocalMethodClientRequestHandler(invoker);

        // Which is injected into the standard JSON requestor of the
        // FRDS.Broker library
        Requestor requestor = new StandardJSONRequestor(crh);

        // Which is finally injected into the GameClientProxy that
        // you must develop...
        card = new CardClientProxy(requestor);
    }

    @Test
    public void healthShouldBe1() {
        // Test stub hard codes the turn number to 312
        assertThat(card.getHealth(), is(1));
    }

    @Test
    public void manaShouldBe1() {
        // Test stub hard codes the turn number to 312
        assertThat(card.getManaCost(), is(1));
    }

    @Test
    public void AttackShouldBe1() {
        // Test stub hard codes the turn number to 312
        assertThat(card.getAttack(), is(1));
    }

    @Test
    public void nameShouldBeCard1() {
        // Test stub hard codes the turn number to 312
        assertThat(card.getName(), is("card0"));
    }

    @Test
    public void ownerShouldBeFindus() {
        // Test stub hard codes the turn number to 312
        assertThat(card.getOwner(), is(Player.FINDUS));
    }

    @Test
    public void cardShouldBeInactive() {
        // Test stub hard codes the turn number to 312
        assertThat(card.isActive(), is(false));
    }*/
    }
}

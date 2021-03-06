/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.cxf.jaxrs;

import org.apache.camel.CamelContext;
import org.apache.camel.component.cxf.common.AbstractSslEndpointConfigurer;
import org.apache.camel.support.jsse.SSLContextParameters;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.AbstractJAXRSFactoryBean;
import org.apache.cxf.jaxrs.client.Client;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.http.HTTPConduit;

public final class SslCxfRsEndpointConfigurer extends AbstractSslEndpointConfigurer implements CxfRsEndpointConfigurer {

    private SslCxfRsEndpointConfigurer(SSLContextParameters sslContextParameters,
                                       CamelContext camelContext) {
        super(sslContextParameters, camelContext);
    }

    public static CxfRsEndpointConfigurer create(SSLContextParameters sslContextParameters, CamelContext camelContext) {
        if (sslContextParameters == null) {
            return new ChainedCxfRsEndpointConfigurer.NullCxfRsEndpointConfigurer();
        } else {
            return new SslCxfRsEndpointConfigurer(sslContextParameters, camelContext);
        }
    }

    @Override
    public void configure(AbstractJAXRSFactoryBean factoryBean) {
    }

    @Override
    public void configureClient(Client client) {
        HTTPConduit httpConduit = (HTTPConduit) WebClient.getConfig(client).getConduit();
        setupHttpConduit(httpConduit);
    }

    @Override
    public void configureServer(Server server) {
    }
}

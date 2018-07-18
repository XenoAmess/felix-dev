/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.felix.framework;

import java.lang.reflect.Method;

import junit.framework.TestCase;

import org.osgi.framework.Version;

public class FrameworkVersionTest extends TestCase
{
    public void testFrameworkVersion() throws Exception
    {
        testFrameworkVersion("1.0.0", "1");
        testFrameworkVersion("2.3.0", "2.3");
        testFrameworkVersion("1.0.0", "1.0.0");
        testFrameworkVersion("5.0.0.SNAPSHOT", "5-SNAPSHOT");
        testFrameworkVersion("1.0.0.SNAPSHOT", "1.0-SNAPSHOT");
        testFrameworkVersion("1.2.3.SNAPSHOT", "1.2.3-SNAPSHOT");
        testFrameworkVersion("1.2.3.foo-123", "1.2.3.foo-123");
        testFrameworkVersion("1.2.3.foo-123-hello", "1.2.3.foo-123-hello");
    }

    private void testFrameworkVersion(String out, String in) throws Exception
    {
        Method method = Felix.class.getDeclaredMethod("cleanMavenVersion", new Class [] {StringBuilder.class});
        method.setAccessible(true);

        StringBuilder sb = new StringBuilder(in);
        assertEquals(new Version(out), new Version((String) method.invoke(null, sb)));
    }
}

/*
 * Copyright 2013-2014 eBay Software Foundation and selendroid committers.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.selendroid;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.remote.CommandInfo;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.HttpVerb;
import org.openqa.selenium.remote.http.HttpMethod;

public class SelendroidCommandExecutor extends HttpCommandExecutor {

  private final static Map<String, CommandInfo> SELENDROID_COMMANDS =
      new HashMap<String, CommandInfo>() {
        {
          // TODO remove network connection once 2.42 is released for Selenium
          put("getNetworkConnection", new CommandInfo("/session/:sessionId/network_connection",
              HttpMethod.GET));
          put("setNetworkConnection", new CommandInfo("/session/:sessionId/network_connection",
              HttpMethod.POST));
          put("selendroid-getBrightness", new CommandInfo(
              "-selendroid/:sessionId/screen/brightness", HttpMethod.GET));
          put("selendroid-setBrightness", new CommandInfo(
              "-selendroid/:sessionId/screen/brightness", HttpMethod.POST));
          put("selendroid-getCommandConfiguration", new CommandInfo(
              "-selendroid/:sessionId/configure/command/:command", HttpMethod.GET));
          put("selendroid-setCommandConfiguration", new CommandInfo(
              "-selendroid/:sessionId/configure/command/:command", HttpMethod.POST));
          put("selendroid-adb-sendKeyEvent", new CommandInfo(
              "-selendroid/:sessionId/adb/sendKeyEvent", HttpMethod.POST));
          put("selendroid-adb-sendText", new CommandInfo("-selendroid/:sessionId/adb/sendText",
              HttpVerb.POST));
          put("selendroid-adb-tap",
              new CommandInfo("-selendroid/:sessionId/adb/tap", HttpVerb.POST));
          put("selendroid-adb-executeShellCommand",
            new CommandInfo("-selendroid/:sessionId/adb/executeShellCommand", HttpVerb.POST));
          put("backgroundApp",
            new CommandInfo("/session/:sessionId/-selendroid/background", HttpVerb.POST));
          put("resumeApp",
            new CommandInfo("/session/:sessionId/-selendroid/resume", HttpVerb.POST));
          put("addCallLog",
            new CommandInfo("/session/:sessionId/-selendroid/addcalllog",HttpVerb.POST));
          put("readCallLog",
            new CommandInfo("/session/:sessionId/-selendroid/readcalllog",HttpVerb.POST));
        }
      };

  public SelendroidCommandExecutor(URL url) throws MalformedURLException {
    super(SELENDROID_COMMANDS, url);
  }

  public SelendroidCommandExecutor() throws MalformedURLException {
    super(SELENDROID_COMMANDS, (URL) null);
  }
}

/*
 * Copyright 2014 eBay Software Foundation and selendroid committers.
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
package io.selendroid.server.handler;

import io.selendroid.exceptions.PermissionDeniedException;
import io.selendroid.server.RequestHandler;
import io.selendroid.server.Response;
import io.selendroid.server.SelendroidResponse;
import io.selendroid.server.http.HttpRequest;
import io.selendroid.server.model.DefaultSelendroidDriver;
import io.selendroid.server.utils.CallLogEntry;
import io.selendroid.util.SelendroidLogger;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

public class AddCallLog extends RequestHandler {

  public AddCallLog(String mappedUri) {
    super(mappedUri);
  }

  @Override
  public Response handle(HttpRequest request) throws JSONException {
    SelendroidLogger.info("add call log");
    JSONObject parameters = getPayload(request).getJSONObject("parameters");
    String callLogJson = parameters.getString("calllogjson");
    CallLogEntry log = new Gson().fromJson(callLogJson, CallLogEntry.class);
    try {
        ((DefaultSelendroidDriver) getSelendroidDriver(request)).addCallLog(log);
        String response = "Added the number "+log.getNumber()+" with duration of "+ log.getDuration()+" to call log.";
        SelendroidLogger.info("Succesfully added record to call log.");
        return new SelendroidResponse(getSessionId(request), response);
    }
    catch(PermissionDeniedException e) {
        SelendroidLogger.info("WRITE_CALL_LOG permission must be in a.u.t. to write to call log.");
        return new SelendroidResponse(getSessionId(request), 10, e);
    }
  }
  
}

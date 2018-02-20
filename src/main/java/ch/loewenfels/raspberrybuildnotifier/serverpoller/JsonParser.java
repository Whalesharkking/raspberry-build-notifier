package ch.loewenfels.raspberrybuildnotifier.serverpoller;

import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;

public class JsonParser {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final Logger LOGGER = Logger.getLogger(JsonParser.class);
    private String buildName;
    private String timestamp;
    private String status;

    public BuildInformationDto get() {
        try {
            final String url = "https://unified-skein-195809.appspot.com/rest/build/get";
            //String url = "http://192.168.43.23:800/server.json";
            final HttpClient client = HttpClientBuilder.create().build();
            final HttpGet request = new HttpGet(url);
            request.addHeader("User-Agent", USER_AGENT);
            final HttpResponse response = client.execute(request);
            LOGGER.info("Response Code : " + response.getStatusLine().getStatusCode());
            final String jsonData = EntityUtils.toString(response.getEntity());
            final JSONObject obj = new JSONObject(jsonData);
            LOGGER.info("name: " + obj.getString("name"));
            final JSONObject build = obj.getJSONObject("build");
            setBuildInformation(build);
            LOGGER.info("timestamp: " + timestamp);
            LOGGER.info("status: " + status);
            return new BuildInformationDto(buildName, status, LocalDateTime.now());
        } catch (final JSONException | ParseException | IOException e) {
            LOGGER.error(e);
        }
        return new BuildInformationDto(buildName, "FAILURE", LocalDateTime.now());
    }

    private void setBuildInformation(final JSONObject build) throws JSONException {
        buildName = build.getString("buildName");
        timestamp = build.getString("status");
        status = build.getString("timestamp");
    }
}

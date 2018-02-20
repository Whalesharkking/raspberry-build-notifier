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

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;

public class JsonParser {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final Logger LOGGER = Logger.getLogger(JsonParser.class);

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
            //            final JSONObject obj = new JSONObject(jsonData);
            //            LOGGER.info("name: " + obj.getString("name"));
            //            final JSONObject build = obj.getJSONObject("build");
            //            LOGGER.info("timestamp: " + build.getString("timestamp"));
            //            LOGGER.info("status: " + build.getString("status"));
            return new BuildInformationDto("a", jsonData, LocalDateTime.now());
        } catch (final ParseException | IOException e) {
            LOGGER.error(e);
        }
        return new BuildInformationDto("Test", "FAILURE", LocalDateTime.now());
    }
}

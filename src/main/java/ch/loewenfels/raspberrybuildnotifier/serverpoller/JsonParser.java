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

import com.google.gson.Gson;

public class JsonParser {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final Logger LOGGER = Logger.getLogger(JsonParser.class);

    public BuildInformationDto get() {
        try {
            final Gson gson = new Gson();
            final String url = "https://unified-skein-195809.appspot.com/rest/build/get/a";
            final HttpClient client = HttpClientBuilder.create().build();
            final HttpGet request = new HttpGet(url);
            request.addHeader("User-Agent", USER_AGENT);
            final HttpResponse response = client.execute(request);
            LOGGER.info("Response Code : " + response.getStatusLine().getStatusCode());
            final String string = EntityUtils.toString(response.getEntity());
            final BuildInformationDto buildInformationDto = gson.fromJson(string, BuildInformationDto.class);
            //            LOGGER.info("name: " + obj.getString("name"));
            //            final JSONObject build = obj.getJSONObject("build");
            //            LOGGER.info("timestamp: " + build.getString("timestamp"));
            //            LOGGER.info("status: " + build.getString("status"));
            return new BuildInformationDto(buildInformationDto.jobName, buildInformationDto.jobStatus, buildInformationDto.resultDateTime);
        } catch (final ParseException | IOException e) {
            LOGGER.error(e);
        }
        return new BuildInformationDto("ERROR", "ERROR", LocalDateTime.now().toString());
    }
}

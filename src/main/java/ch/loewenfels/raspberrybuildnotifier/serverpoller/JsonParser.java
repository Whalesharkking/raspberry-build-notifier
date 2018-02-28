package ch.loewenfels.raspberrybuildnotifier.serverpoller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;
import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto.JobStatus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonParser {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final Logger LOGGER = Logger.getLogger(JsonParser.class);

    public BuildInformationDto get() {
        try {
            final String url = "https://unified-skein-195809.appspot.com/rest/build/get/SampleJobName";
            final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyy HH:mm:ss");
            // final String url = "http://localhost:8080/rest/build/get/a";
            final HttpClient client = HttpClientBuilder.create().build();
            final HttpGet request = new HttpGet(url);
            request.addHeader("User-Agent", USER_AGENT);
            final HttpResponse response = client.execute(request);
            LOGGER.info("Response Code : " + response.getStatusLine().getStatusCode());
            final String string = EntityUtils.toString(response.getEntity());
            final Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, UnixEpochDateTypeAdaptersd.getUnixEpochDateTypeAdapter()).create();
            final BuildInformationDto buildInformationDto = gson.fromJson(string, BuildInformationDto.class);
            LOGGER.info("JobName: " + buildInformationDto.getJobName() + "\tJobStatus: " + buildInformationDto.getJobStatus() + "\tJobTime: " + format.format(buildInformationDto.getJobTime()));
            return buildInformationDto;
        } catch (final ParseException | IOException e) {
            LOGGER.error(e);
        }
        return new BuildInformationDto("ERROR", JobStatus.ERROR, new Date());
    }
}

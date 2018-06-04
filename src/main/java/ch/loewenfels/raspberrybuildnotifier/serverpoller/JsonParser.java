package ch.loewenfels.raspberrybuildnotifier.serverpoller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;
import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto.JobStatus;
import ch.loewenfels.raspberrybuildnotifier.ResponsilbleJobs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonParser {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonParser.class);
    private static String PINR = "PI1";

    public List<Optional<BuildInformationDto>> get() {
        //final String serverUrl = "unified-skein-195809.appspot.com";
        final String serverUrl = "localhost:8080";
        final List<Optional<BuildInformationDto>> optinalBuildInfortmationList = new ArrayList<>();
        String aJobName = "empty";
        for (final String jobName : getJobName(serverUrl)) {
            try {
                LOGGER.error(jobName);
                aJobName = jobName;
                final String url = String.format("http://%s/rest/build/get/%s", serverUrl, jobName);
                final HttpClient client = HttpClientBuilder.create().build();
                final HttpGet request = new HttpGet(url);
                request.addHeader("User-Agent", USER_AGENT);
                final HttpResponse response = client.execute(request);
                LOGGER.debug("Response Code: {}", response.getStatusLine().getStatusCode());
                final String string = EntityUtils.toString(response.getEntity());
                final BuildInformationDto buildInformationDto = parseBuildInformation(string);
                LOGGER.debug("JobInfo: {}", buildInformationDto);
                optinalBuildInfortmationList.add(Optional.of(buildInformationDto));
            } catch (final Exception e) {
                LOGGER.error("Can not parse BuildInformation: {}", e);
                optinalBuildInfortmationList.add(Optional.of(new BuildInformationDto(aJobName, JobStatus.ERROR, LocalDateTime.now())));
            }
        }
        return optinalBuildInfortmationList;
    }

    private List<String> getJobName(final String serverUrl) {
        try {
            final String url = String.format("http://%s/rest/build/responsibleJobs/%s", serverUrl, PINR);
            final HttpClient client = HttpClientBuilder.create().build();
            final HttpGet request = new HttpGet(url);
            request.addHeader("User-Agent", USER_AGENT);
            final HttpResponse response = client.execute(request);
            LOGGER.debug("Response Code: {}", response.getStatusLine().getStatusCode());
            final String string = EntityUtils.toString(response.getEntity());
            final ResponsilbleJobs responsibleJobs = parseResponsilble(string);
            LOGGER.debug("Responsible: {}", responsibleJobs);
            return responsibleJobs.getResponsibleList();
        } catch (final Exception e) {
            LOGGER.error("Can not parse RespnsiblePI: {}", e);
            return new ArrayList<>();
        }
    }

    BuildInformationDto parseBuildInformation(final String string) throws Exception {
        final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer()).create();
        return gson.fromJson(string, BuildInformationDto.class);
    }

    ResponsilbleJobs parseResponsilble(final String string) throws Exception {
        final Gson gson = new Gson();
        return gson.fromJson(string, ResponsilbleJobs.class);
    }
}

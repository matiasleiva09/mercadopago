package com.sires.mp.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebHookDTO {
    @Nullable
    private String id;
    @JsonProperty("live_mode")
    @Nullable
    private boolean liveMode;
    private String type;
    @JsonProperty("date_created")
    @Nullable
    private String dateCreated;
    @JsonProperty("api_version")
    @Nullable
    private String apiVersion;
    private String  action;
    @JsonProperty("data")
    private DataWebHookDTO data;
    @JsonProperty("application_id")
    @Nullable
    private String applicationId;
    @JsonProperty("user_id")
    @Nullable
    private String userId;
}

package com.sires.mp.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "_data", "_errors", "_links", "_status"})
public class ResponseEntityBuilder<T> extends RepresentationModel<ResponseEntityBuilder<T>> {

    @JsonProperty("_data")
    private T data;

    @JsonProperty("_errors")
    private List<ErrorDetails> errors;

    @JsonProperty("_status")
    private HttpStatus status;

    @JsonIgnore
    private final MultiValueMap<String, String> headers = new HttpHeaders();

    public ResponseEntityBuilder(@NotNull HttpStatus status) {
        super();
        this.errors = new ArrayList<>();
        this.status = status;
    }

    public static <T> ResponseEntityBuilder<T> builder() {
        return new ResponseEntityBuilder<>(HttpStatus.I_AM_A_TEAPOT);
    }

    public ResponseEntityBuilder<T> status(HttpStatus status) {
        this.status = status;
        return this;
    }

    /**
     * @param data any type of content data.
     */
    public ResponseEntityBuilder<T> data(T data){
        this.data = data;
        return this;
    }

    /**
     * @param link {@link org.springframework.hateoas.Link} indicates a client that it can navigate to a certain resource.
     */
    public ResponseEntityBuilder<T> addLink(Link link){
        add(link);
        return this;
    }

    /**
     * @param err instance of {@link ErrorDetails}. Provide non-standard errors.
     */
    public ResponseEntityBuilder<T> addError(ErrorDetails err){
        errors.add(err);
        return this;
    }

    public List<ErrorDetails> getErrors() {
        return new ArrayList<>(errors);
    }

    /**
     * @param errors list of {@link ErrorDetails}. Provide non-standard errors.
     */
    public ResponseEntityBuilder<T> errors(List<ErrorDetails> errors) {
        this.errors = errors;
        return this;
    }

    /**
     * @param k header keyword
     * @param v header value
     */
    public ResponseEntityBuilder<T> addHeader(String k, String v){
        headers.add(k, v);
        return this;
    }

    public MultiValueMap<String, String>  getHeaders() {
        return CollectionUtils.unmodifiableMultiValueMap(headers);
    }

    /**
     * @param headers list of {@link org.springframework.util.MultiValueMap}
     */
    public ResponseEntityBuilder<T> headers(MultiValueMap<String, String>  headers) {
        this.headers.addAll(headers);
        return this;
    }

    /**
     * Returns a {@link org.springframework.http.ResponseEntity} instance with status code and body (if needed).
     *
     * @return an instance of {@link org.springframework.http.ResponseEntity}
     */
    public ResponseEntity<T> build() {
        if (Objects.isNull(this.getData()) && this.getErrors().isEmpty() && this.getLinks().isEmpty()) {
            return new ResponseEntity<>(this.headers, this.getStatus());
        }
        return new ResponseEntity<>((T) this, this.headers, this.getStatus());
    }
}
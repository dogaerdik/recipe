package com.abnamro.recipe.api.constants;

import org.springframework.http.MediaType;

public final class ApiEndpoints {
    public static final String RESPONSE_CONTENTTYPE = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8";

    public static final String API_BASE_URL = "/api";

    public static final String RECIPE_URL_CONVERTER = API_BASE_URL + "/v1/recipe";

    private ApiEndpoints() {

    }
}
